package br.com.bairoon.model.negocio;


import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by Nykel Andersow on 05/08/2016.
 */

public class BairoonApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}

