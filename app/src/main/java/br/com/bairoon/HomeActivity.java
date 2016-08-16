package br.com.bairoon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;

import java.util.Calendar;
import java.util.Locale;

import br.com.bairoon.model.bean.ContaNeon;
import br.com.bairoon.model.bean.ContatoNeon;
import br.com.bairoon.model.bean.DepositoNeon;
import br.com.bairoon.model.bean.Usuario;
import br.com.bairoon.model.bean.UsuarioNeon;
import br.com.bairoon.model.negocio.navigationdrawer.InstaladorNavigationDrawerBairoon;
import br.com.bairoon.model.service.GenerateTokenService;
import br.com.bairoon.model.service.SincronizadorNeon;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.ScalarsConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private Drawer drawer;
    private Usuario usuario;
    private Activity activity;
    private UsuarioNeon usuarioNeon;
    private SincronizadorNeon sincronizadorNeon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        activity = this;

        usuarioNeon = new UsuarioNeon("kathy", "kathy@gmail.com");

        sincronizadorNeon = new SincronizadorNeon(usuarioNeon, this);

        usuario = new Usuario("Rambo Jarde");

        drawer = new InstaladorNavigationDrawerBairoon(usuario, this, savedInstanceState).configurarDrawer();

        Button btDepositar = (Button) findViewById(R.id.btDepositar);
        btDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuarioNeon.getToken() != null) {

                    ContatoNeon contatoNeon = new ContatoNeon(1, "Amanda Amorin", "pefil.png", "11 69584588");

                    DepositoNeon depositoNeon = new DepositoNeon(contatoNeon, 559.6, Calendar.getInstance(new Locale("pt", "BR")).getTime());

                    ContaNeon contaNeon = new ContaNeon();
                    contaNeon.setDepositoNeon(depositoNeon);

                    sincronizadorNeon.depositar(100.00d);
                    Log.e("Nykel", "Errodddd: " + usuarioNeon.toString());

                }

            }
        });


        Button btRecuperarTransacoes = (Button) findViewById(R.id.btRecuperarTransacoes);
        btRecuperarTransacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (usuarioNeon.getToken() != null) {
                    sincronizadorNeon.recuperarTransacoes();



                    Log.e("Nykel", "Errodddd: " + usuarioNeon.toString());

                }

            }
        });

        FloatingActionButton btPesquisar = (FloatingActionButton) findViewById(R.id.btPesquisar);

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sincronizadorNeon.gerarToken();

                Log.e("Nykel", "Errodddd: " + usuarioNeon.toString());


/*                Intent activityPesquisar = new Intent("PESQUISAR");

                Bundle parametrosEnviar = new Bundle();
                parametrosEnviar.putParcelable(Usuario.class.getName(), usuario);

                activityPesquisar.putExtras(parametrosEnviar);

                startActivity(activityPesquisar);

                Toast.makeText(activity, usuario.getNome(), Toast.LENGTH_SHORT).show();*/
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
