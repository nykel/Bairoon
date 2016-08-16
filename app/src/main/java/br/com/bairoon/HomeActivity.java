package br.com.bairoon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;

import br.com.bairoon.model.bean.Usuario;
import br.com.bairoon.model.negocio.navigationdrawer.InstaladorNavigationDrawerBairoon;
import br.com.bairoon.model.service.SincronizadorNeon;

public class HomeActivity extends AppCompatActivity {

    private Drawer drawer;
    private Usuario usuario;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        activity = this;

        usuario = new Usuario("Rambo Jarde");

        drawer = new InstaladorNavigationDrawerBairoon(usuario, this, savedInstanceState).configurarDrawer();

        FloatingActionButton btPesquisar = (FloatingActionButton) findViewById(R.id.btPesquisar);

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SincronizadorNeon sincronizadorNeon = new SincronizadorNeon();

                sincronizadorNeon.inciarConexao();

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
