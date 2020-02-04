package dev.manzke.heroesapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeroConfig {

    @SerializedName("total")
    private int total;
    @SerializedName("results")
    private List<HeroCharacters> heroCharacters;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HeroCharacters> getHeroCharacters() {
        return heroCharacters;
    }

    public void setHeroCharacters(List<HeroCharacters> heroCharacters) {
        this.heroCharacters = heroCharacters;
    }
}
