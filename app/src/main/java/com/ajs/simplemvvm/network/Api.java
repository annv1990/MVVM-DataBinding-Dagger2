package com.ajs.simplemvvm.network;

import com.ajs.simplemvvm.model.Hero;
import com.ajs.simplemvvm.model.OpenSourceResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface Api {
    String HERO_BASE_URL = "https://simplifiedcoding.net/demos/";

    String API_KEY = "ABCXYZ123TEST";

    String OPEN_SOURCE_END_POINT = "5926c34212000035026871cd";

    @GET(OPEN_SOURCE_END_POINT)
    Call<OpenSourceResponse> getOpenSourceApiCall(@HeaderMap Map<String, Object> headers);

    @GET("marvel")
    Call<List<Hero>> getHeroes();
}
