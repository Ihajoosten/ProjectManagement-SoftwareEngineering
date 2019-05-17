package com.example.theavansmovieapp.Domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
Gemaakt door: Stefan Ilmer
Datum laatste update: 26-03-2019

Genre bevat getters en setters om genre informatie op te vragen.
*/
public class Genre {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

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


