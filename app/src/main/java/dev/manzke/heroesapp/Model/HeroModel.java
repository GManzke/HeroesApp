package dev.manzke.heroesapp.Model;

import com.google.gson.annotations.SerializedName;

public class HeroModel {

    @SerializedName("copyright")
    private String copyright;
    @SerializedName("data")
    private HeroConfig heroConfig;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public HeroConfig getHeroConfig() {
        return heroConfig;
    }

    public void setHeroConfig(HeroConfig heroConfig) {
        this.heroConfig = heroConfig;
    }
}
