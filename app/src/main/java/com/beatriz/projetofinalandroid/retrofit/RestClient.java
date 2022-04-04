package com.beatriz.projetofinalandroid.retrofit;

import com.beatriz.projetofinalandroid.service.CheckListService;
import com.beatriz.projetofinalandroid.service.UsuarioService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestClient {
    private static Retrofit RETROFIT = null;

    public static Retrofit getRetrofit() {
        if (RETROFIT == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
            RETROFIT = new Retrofit.Builder()
                    .baseUrl(CheckListService.BASE_URL)
                    .baseUrl(UsuarioService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return RETROFIT;
    }
}

