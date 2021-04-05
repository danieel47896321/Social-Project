package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RegisterTest {
    @Test
    public void validateRegistrationInput(String username,String email,String password,String firstname,String lastname,String city,String age,String depart,String sex){
        try {
            assertNotNull(username);
            assertNotNull(email);
            assertNotNull(password);
            assertNotNull(firstname);
            assertNotNull(lastname);
            assertNotNull(city);
            assertNotNull(age);
            assertNotNull(depart);
            assertNotNull(sex);
            assertTrue(username.length() > 5);
            assertTrue(email.length() > 5);
            assertTrue(password.length() > 10);
            assertTrue(firstname.length() > 1);
            assertTrue(lastname.length() > 1);
            assertTrue(city.length() > 1);
            assertTrue(age.length() > 1);
            assertTrue(depart.length() > 1);
            assertTrue(sex.length() > 1);
            assertFalse(username.contains("'"));
            assertFalse(email.contains("'"));
            assertFalse(password.contains("'"));
            assertFalse(firstname.contains("'"));
            assertFalse(lastname.contains("'"));
            assertFalse(city.contains("'"));
            assertFalse(age.contains("'"));
            assertFalse(depart.contains("'"));
            assertFalse(sex.contains("'"));
            assertFalse(username.contains(" "));
            assertFalse(email.contains(" "));
            assertFalse(password.contains(" "));
            assertFalse(firstname.contains(" "));
            assertFalse(lastname.contains(" "));
            assertFalse(city.contains(" "));
            assertFalse(age.contains(" "));
            assertFalse(depart.contains(" "));
            assertFalse(sex.contains(" "));
        }
        catch (Exception e){

        }
    }
}
