package dev.manzke.heroesapp;

import retrofit2.http.GET;

public interface APIService {

    String URL_BASE = "https://api.spacexdata.com/v2/";

    @GET("launches")
    Call<List<HeroModel>> getHeroes();
}
