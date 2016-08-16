package br.com.bairoon.model.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.io.StringReader;

import br.com.bairoon.model.bean.UsuarioNeon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Nykel Andersow on 15/08/2016.
 */
public class SincronizadorNeon {
    private Retrofit retrofit;


    public void inciarConexao() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(GenerateTokenService.URL_BASE)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();


        GenerateTokenService generateTokenService = retrofit.create(GenerateTokenService.class);

        Call<String> usuarioNeon = generateTokenService.usuarioNeon("teste", "teste@gmail.com");

        usuarioNeon.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("Nykel", "Errodddd: " + response.message());
                Log.e("Nykel", "Errodddd: " + response.body());


                if (response.isSuccessful()) {
                    Log.e("Nykel", "Errodddd: " + response.body());
                }


/*                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Log.e("Nykel", "Errodddd: " + jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Nykel", "Errosss: " + t.getMessage());
            }
        });

    }
}
