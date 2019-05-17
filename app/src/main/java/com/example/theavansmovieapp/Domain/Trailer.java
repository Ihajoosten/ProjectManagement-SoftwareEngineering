package com.example.theavansmovieapp.Domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Gemaakt door Anjuli
// trailer bevat getters en setters voor trailer informatie.

public class Trailer {

    @SerializedName("key")
    @Expose
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
