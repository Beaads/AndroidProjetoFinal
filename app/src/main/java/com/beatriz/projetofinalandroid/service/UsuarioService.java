package com.beatriz.projetofinalandroid.service;

import com.beatriz.projetofinalandroid.model.Usuario;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface UsuarioService {
    public static final String BASE_URL = "http://192.168.0.55:8080/";

    @GET("usuario/{id}")
    Observable<Usuario> getUsu(@Path("id") int id, @Body Usuario usuario);
}
