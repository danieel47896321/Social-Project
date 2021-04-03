package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ForgotPasswordTest {
    @Test
    public void validateForgotPasswordInput(String username,String email){
        try {
            assertNotNull(username);
            assertNotNull(email);
            assertTrue(username.length() > 5);
            assertTrue(email.length() > 10);
            assertFalse(username.contains("'"));
            assertFalse(email.contains("'"));
            assertFalse(username.contains(" "));
            assertFalse(email.contains(" "));
        }
        catch (Exception e){

        }
    }
}
