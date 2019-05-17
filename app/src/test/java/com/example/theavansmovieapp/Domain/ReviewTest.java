package com.example.theavansmovieapp.Domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest
{
    @Test
    public void setAndGetAuther()
    {
        /// Create review
        Review review = new Review();

        // Set review author
        review.setAuthor("Billy Bob");

        // Check if review auther is equal to the test auther
        assertEquals("Billy Bob", review.getAuthor());
    }

    @Test
    public void setAndGetContent()
    {
        /// Create review
        Review review = new Review();

        // Set review content
        review.setContent("Kei goeie film!");

        // Check if review content is equal to the test content
        assertEquals("Kei goeie film!", review.getContent());
    }
}