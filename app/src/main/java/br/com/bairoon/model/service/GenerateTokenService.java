package br.com.bairoon.model.service;


import br.com.bairoon.model.bean.UsuarioNeon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GenerateTokenService {

    public static String URL_BASE = "http://processoseletivoneon.azurewebsites.net/";

    @GET("GenerateToken")
    Call<String> usuarioNeon(@Query("nome") String nome, @Query("email") String email);
}
