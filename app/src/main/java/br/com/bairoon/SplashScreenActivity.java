package br.com.bairoon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import br.com.bairoon.model.bean.Usuario;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent activityHome = new Intent("HOME");

        startActivity(activityHome);

        finish();
    }
}
