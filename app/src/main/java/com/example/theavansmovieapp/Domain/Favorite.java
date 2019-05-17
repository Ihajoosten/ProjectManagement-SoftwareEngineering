package com.example.theavansmovieapp.Domain;

// Gemaakt door Tessa
// Favorite is een klas om een favorite film als geheel naar die api te kunnen sturen.

public class Favorite {

    private String media_type;
    private int media_id;
    private boolean favorite;

    public Favorite(String mediaType, int mediaId, boolean favorite) {
        this.media_type = mediaType;
        this.media_id = mediaId;
        this.favorite = favorite;
    }
}
