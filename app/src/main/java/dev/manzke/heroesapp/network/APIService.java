package dev.manzke.heroesapp.network;

import dev.manzke.heroesapp.model.HeroModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("characters?apikey=142c66e93e543eda3f9b64f9dab38842&ts=1&hash=617ae6ae50c32783c2f401e0cad29da0")
    Call<HeroModel> getHeroes(@Query("limit") String limit, @Query("orderBy") String orderBy);
}
