package br.com.bairoon.model.service;

import android.app.Activity;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import br.com.bairoon.model.bean.ContaNeon;
import br.com.bairoon.model.bean.ContatoNeon;
import br.com.bairoon.model.bean.DepositoNeon;
import br.com.bairoon.model.bean.ExtratoNeon;
import br.com.bairoon.model.bean.UsuarioNeon;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.ScalarsConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SincronizadorNeon {
    private UsuarioNeon usuarioNeon;
    private Activity activity;

    public SincronizadorNeon(UsuarioNeon usuarioNeon, Activity activity) {
        this.usuarioNeon = usuarioNeon;
        this.activity = activity;
    }

    public void gerarToken() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GenerateTokenService.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        GenerateTokenService generateTokenService = retrofit.create(GenerateTokenService.class);

        Call<ResponseBody> callGeradorToken = generateTokenService.gerarToken(usuarioNeon.getNome(), usuarioNeon.getEmail());

        callGeradorToken.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody d = response.body();
                    try {
                        usuarioNeon.setToken(d.string());
                        ;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(activity, "Falha na geração do token", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void depositar(double valor) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GenerateTokenService.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SendMoneyService sendMoneyService = retrofit.create(SendMoneyService.class);

        Call<ResponseBody> callEnviarDeposito = sendMoneyService.enviarRemessa(String.valueOf(usuarioNeon.getId()), usuarioNeon.getToken(), valor);

        callEnviarDeposito.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody d = response.body();
                    try {
                        if (Boolean.valueOf(d.string()).equals(true))
                            Toast.makeText(activity, "Depósito realizado com sucesso", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(activity, "Falha na realização do depósito", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void recuperarTransacoes() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GenerateTokenService.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetTransfersService getTransfersService = retrofit.create(GetTransfersService.class);

        Call<ResponseBody> callGetTransfers = getTransfersService.recuperarTransacoes(usuarioNeon.getToken());

        callGetTransfers.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    if (usuarioNeon.getContaNeon() == null)
                        usuarioNeon.setContaNeon(new ContaNeon());

                    ResponseBody a = response.body();

                    usuarioNeon.getContaNeon().setExtrato(new ExtratoNeon());
                    usuarioNeon.getContaNeon().getExtrato().setDepositos(new ArrayList<DepositoNeon>());

                    try {


                        JSONArray jr = new JSONArray(a.string().toString());

                        for (int i = 0; i < jr.length(); i++) {
                            JSONObject jb = (JSONObject) jr.getJSONObject(i);

                            ContatoNeon contatoNeon = new ContatoNeon(Integer.valueOf(jb.getString("Id")).intValue(),"","","");

                            DepositoNeon depositoNeon = new DepositoNeon(contatoNeon,Double.valueOf(jb.getString("Valor")),new Date());
                            //jb.getString("Data")
                            depositoNeon.setContatoNeon(contatoNeon);

                            usuarioNeon.getContaNeon().getExtrato().getDepositos().add(depositoNeon);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(activity, "Falha na ao recuperar as transações", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
