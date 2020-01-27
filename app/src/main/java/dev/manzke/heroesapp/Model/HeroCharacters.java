package dev.manzke.heroesapp.Model;

import com.google.gson.annotations.SerializedName;

public class HeroCharacters {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    private HeroImage thumbnail;

    public HeroImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(HeroImage thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
