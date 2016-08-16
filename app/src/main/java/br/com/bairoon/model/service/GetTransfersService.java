package br.com.bairoon.model.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nykel Andersow on 16/08/2016.
 */
public interface GetTransfersService {

    public static String URL_BASE = "http://processoseletivoneon.azurewebsites.net/";

    @GET("GetTransfers")
   Call<ResponseBody> recuperarTransacoes(@Query("token") String token);
}
