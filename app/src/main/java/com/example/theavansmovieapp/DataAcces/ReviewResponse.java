package com.example.theavansmovieapp.DataAcces;


import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.Review;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**     Gemaakt door Anjuli      **/
//De review gegevens uit de API worden verzameld

public class ReviewResponse {

    @SerializedName("results")
    @Expose
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}