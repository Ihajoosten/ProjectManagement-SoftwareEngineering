package com.example.theavansmovieapp.DataAcces;

import com.example.theavansmovieapp.Domain.Genre;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**     Gemaakt door Anjuli      **/
//De genre gegevens uit de API worden verzameld

public class GenresResponse {

    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}