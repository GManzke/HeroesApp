package dev.manzke.heroesapp.Network;

import dev.manzke.heroesapp.Model.HeroModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("characters?orderBy=name&limit=100&apikey=142c66e93e543eda3f9b64f9dab38842&ts=1&hash=617ae6ae50c32783c2f401e0cad29da0")
    Call<HeroModel> getHeroes();
}
