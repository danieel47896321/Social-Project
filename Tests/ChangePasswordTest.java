package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ChangePasswordTest {
    @Test
    public void validateChangePasswordInput(String password,String new_password,String confirm_new_password){
        try {
            assertNotNull(password);
            assertNotNull(new_password);
            assertNotNull(confirm_new_password);
            assertTrue(password.length() > 5);
            assertTrue(new_password.length() > 5);
            assertTrue(confirm_new_password.length() > 5);
            assertFalse(password.contains("'"));
            assertFalse(new_password.contains("'"));
            assertFalse(confirm_new_password.contains("'"));
            assertFalse(password.contains(" "));
            assertFalse(new_password.contains(" "));
            assertFalse(confirm_new_password.contains(" "));
        }
        catch (Exception e){

        }
    }
}
