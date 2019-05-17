package com.example.theavansmovieapp.DataAcces;

import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.ListItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**     Gemaakt door Tessa      **/
//De gegevens over een specifieke lijst worden verzameld

public class ListItemResponse {

    @SerializedName("created_by")
    @Expose
    private String createdBy;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("favorite_count")
    @Expose
    private int favoriteCount;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("items")
    @Expose
    private List<ListItem> listItems;

    @SerializedName("item_count")
    @Expose
    private int itemCount;

    @SerializedName("iso_639_1")
    @Expose
    private String iso;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ListItem> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
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
