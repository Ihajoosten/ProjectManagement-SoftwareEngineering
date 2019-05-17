package com.example.theavansmovieapp.Domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
Gemaakt door: Stefan Ilmer
Datum laatste update: 25-03-2019
Account class bevat getters en setters om accounts en account gegevens op te vragen.
*/

public class Account {
    @SerializedName("id")
    @Expose
    private int Id;                 // Account id.

    @SerializedName("hash")
    @Expose
    private String gravatarHash;    // Email hash van gravatar, opvragen profile pic kan met https://www.gravatar.com/avatar/ + hash + .jpg.

    @SerializedName("name")
    @Expose
    private String name;            // Voor en achternaam van de gebruiker.

    @SerializedName("username")
    @Expose
    private String username;        // De username van de gebruiker.

    @SerializedName("include_adult")
    @Expose
    private boolean include_adult;  // Check of the gebruiker toegang heeft tot 18+ content.

    @SerializedName("iso_639_1")
    @Expose
    private String language_code;   // Taal code van de gebruiker met de iso_639_1 norm. EN = English.

    public Account(int ID, String name, String username, boolean include_adult) {
        this.Id = ID;
        this.gravatarHash = gravatarHash;
        this.name = name;
        this.username = username;
        this.include_adult = include_adult;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getGravatarHash() {
        return gravatarHash;
    }

    public void setGravatarHash(String gravatarHash) {
        this.gravatarHash = gravatarHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isInclude_adult() {
        return include_adult;
    }

    public void setInclude_adult(boolean include_adult) {
        this.include_adult = include_adult;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }
}
