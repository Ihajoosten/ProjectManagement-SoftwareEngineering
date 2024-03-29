package com.example.theavansmovieapp.Domain;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Gemaakt door Anjuli
// de review klasse zijn getters en setters voor review informatie

public class Review {

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("content")
    @Expose
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
