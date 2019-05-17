package com.example.theavansmovieapp.Domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


//Gemaakt door Tessa
// Account lists bevatten getters en setters om lijsten van accounts op te vragen.

public class AccountList {
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("favorite_count")
    @Expose
    private int favoriteCount;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("item_count")
    @Expose
    private int itemCount;

    @SerializedName("iso_639_1:")
    @Expose
    private String iso;

    @SerializedName("list_type")
    @Expose
    private String listType;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
