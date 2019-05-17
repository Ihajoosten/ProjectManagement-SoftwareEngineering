/*
Gemaakt door: Stefan Ilmer
Datum laatste update: 03-04-2019

Gecontroleerd door:
Gecontroleerd op:
*/
package com.example.theavansmovieapp.Domain;

import org.junit.Test;

import static org.junit.Assert.*;


public class AccountTest
{
    @Test
    public void setAndGetUsername()
    {
        // Create account
        Account account = new Account(1, "name", "username", true);

        // Set username
        account.setUsername("testtest");

        // Check if username is equal to test string
        assertEquals("testtest", account.getUsername());
    }

    @Test
    public void setAndGetAdultBool()
    {
        // Create account with boolean (adult) false (also default).
        Account account = new Account(1, "name", "username", false);

        // Set adult to true
        account.setInclude_adult(true);

        // Check if the adult is set to true
        assertEquals(true, account.isInclude_adult());
    }

    @Test
    public void setAndGetName()
    {
        // Create account with name 'name'.
        Account account = new Account(1, "name", "username", false);

        // Set name to 'Bob'
        account.setName("Bob");

        // Check if the returned name is equal to 'Bob'
        assertEquals("Bob", account.getName());
    }
}