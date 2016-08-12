package br.com.bairoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.mikepenz.materialdrawer.Drawer;
import br.com.bairoon.model.bean.Usuario;
import br.com.bairoon.model.negocio.navigationdrawer.InstaladorNavigationDrawerBairoon;

public class PesquisarActivity extends AppCompatActivity {

    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        Intent intent = getIntent();
        Bundle parametrosRecebidos = intent.getExtras();

        Usuario usuario = parametrosRecebidos.getParcelable(Usuario.class.getName());

        drawer = new InstaladorNavigationDrawerBairoon(usuario, this, savedInstanceState).configurarDrawer();
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
