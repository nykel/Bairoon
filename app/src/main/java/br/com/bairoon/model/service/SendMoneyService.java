package br.com.bairoon.model.service;

import br.com.bairoon.model.bean.DepositoNeon;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Nykel Andersow on 16/08/2016.
 */
public interface SendMoneyService {
    public static String URL_BASE = "http://processoseletivoneon.azurewebsites.net/";

    @POST("SendMoney")
    Call<ResponseBody> enviarRemessa(@Query("clientId") String idCliente, @Query("token") String token, @Query("valor") double valor);

}
