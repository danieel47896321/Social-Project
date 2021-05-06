package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EditInfoTest {
    @Test
    public void validateChangeInfoInput(String Firstname, String Lastname, String Age,String City) {
        try {
            assertNotNull(Firstname);
            assertNotNull(Lastname);
            assertNotNull(Age);
            assertNotNull(City);
            assertTrue(Firstname.length() > 2);
            assertTrue(Firstname.length() > 2);
            assertTrue(Age.length() > 0);
            assertTrue(City.length() > 0);
            assertFalse(Firstname.contains("'"));
            assertFalse(Lastname.contains("'"));
            assertFalse(Age.contains("'"));
            assertFalse(City.contains("'"));
            assertFalse(Firstname.contains(" "));
            assertFalse(Lastname.contains(" "));
            assertFalse(Age.contains(" "));
            assertFalse(City.contains(" "));
        } catch (Exception e) { }
    }
}
