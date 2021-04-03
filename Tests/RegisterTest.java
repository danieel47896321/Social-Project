package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RegisterTest {
    @Test
    public void validateRegistrationInput(String username,String email,String password){
        try {
            assertNotNull(username);
            assertNotNull(email);
            assertNotNull(password);
            assertTrue(username.length() > 5);
            assertTrue(email.length() > 5);
            assertTrue(password.length() > 10);
            assertFalse(username.contains("'"));
            assertFalse(email.contains("'"));
            assertFalse(password.contains("'"));
            assertFalse(username.contains(" "));
            assertFalse(email.contains(" "));
            assertFalse(password.contains(" "));
        }
        catch (Exception e){

        }
    }
}
