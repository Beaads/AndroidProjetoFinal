package com.beatriz.projetofinalandroid.service;

import com.beatriz.projetofinalandroid.model.CheckList;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface CheckListService {

    String BASE_URL = "http://192.168.0.41:8080/";

    @GET("checklist/")
    Observable<List<CheckList>> getChecklists();

    @POST("checklist/")
    Observable<CheckList> salva(@Body CheckList checkList);
}
