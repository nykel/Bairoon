package br.com.bairoon.model.negocio;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.materialdrawer.Drawer;

import br.com.bairoon.model.bean.Usuario;
import br.com.bairoon.model.negocio.navigationdrawer.InstaladorNavigationDrawerBairoon;

/**
 * Created by Nykel Andersow on 12/08/2016.
 */
public class BairoonBaseActivity extends AppCompatActivity {

    private Activity activity;
    private Drawer drawer;

    public BairoonBaseActivity(Bundle savedInstanceState, Usuario usuario) {
        this.drawer = new InstaladorNavigationDrawerBairoon(usuario, this, savedInstanceState).configurarDrawer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
