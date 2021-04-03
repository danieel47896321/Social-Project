package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    @Test
    public void validateLoginInput(String username,String password){
        try {
            assertNotNull(username);
            assertNotNull(password);
            assertTrue(username.length() > 5);
            assertTrue(password.length() > 10);
            assertFalse(username.contains("'"));
            assertFalse(password.contains("'"));
            assertFalse(username.contains(" "));
            assertFalse(password.contains(" "));
        }
        catch (Exception e){

        }
    }
}
