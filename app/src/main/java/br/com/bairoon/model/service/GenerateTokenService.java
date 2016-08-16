package br.com.bairoon.model.service;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenerateTokenService {

    public static String URL_BASE = "http://processoseletivoneon.azurewebsites.net/";

    @GET("GenerateToken")
    Call<ResponseBody> gerarToken(@Query("nome") String nome, @Query("email") String email);
}
