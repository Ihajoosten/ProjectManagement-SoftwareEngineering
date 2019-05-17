/*
Gemaakt door: Stefan Ilmer
Datum laatste update: 03-04-2019

Gecontroleerd door:
Gecontroleerd op:
*/
package com.example.theavansmovieapp.Domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FilmTest
{
    @Test
    public void setAndGetTitle()
    {
        // Create film
        Film film = new Film();

        // Set title
        film.setTitle("TestMovie");

        // Check if film title is equal to test title
        assertEquals("TestMovie", film.getTitle());
    }

    @Test
    public void setAndGetOverview()
    {
        // Create film
        Film film = new Film();

        // Set overview
        film.setOverview("TestOverview");

        // Check if film overview is equal to test overview
        assertEquals("TestOverview", film.getOverview());
    }

    @Test
    public void setAndGetPosterPath()
    {
        // Create film
        Film film = new Film();

        // Set post path
        film.setPosterPath("https://this/test/url");

        // Check if film poster path is equal to test poster path
        assertEquals("https://this/test/url", film.getPosterPath());
    }

    @Test
    public void setAndGetGenre()
    {
        // Create film
        Film film = new Film();

        //Create new genres
        Genre genreDrama = new Genre();
        Genre genreHorror =  new Genre();
        genreDrama.setName("Drama");
        genreHorror.setName("Horror");

        // Fill genre list
        List<Genre> genreList = new ArrayList<>();
        genreList.add(genreDrama);
        genreList.add(genreHorror);

        // Set Genre list
        film.setGenres(genreList);

        // Check if the genres set are in the film
        assertEquals("Drama", film.getGenres().get(0).getName());
    }

    @Test
    public void setAndGetReleaseDate()
    {
        // Create film
        Film film = new Film();

        // Set releaseDate
        film.setReleaseDate("01-01-2001");

        // Check if film release date is equal to test release date
        assertEquals("01-01-2001", film.getReleaseDate());
    }

    @Test
    public void setAndGetRating()
    {
        /// Create film
        Film film = new Film();

        // Set film rating
        film.setRating(3.95837f);

        // Check if film rating is equal to test rating
        assertEquals(3.95837f, film.getRating(), 0.00020f);
    }
}