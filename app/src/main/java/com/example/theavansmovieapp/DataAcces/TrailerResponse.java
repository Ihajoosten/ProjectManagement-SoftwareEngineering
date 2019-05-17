package com.example.theavansmovieapp.DataAcces;

import com.example.theavansmovieapp.Domain.Trailer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**     Gemaakt door Anjuli      **/
//De trailer gegevens uit de API worden verzameld

public class TrailerResponse {
    @SerializedName("results")
    @Expose
    private List<Trailer> trailers;

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }
}
