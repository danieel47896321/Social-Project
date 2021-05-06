package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PersonalInfoTest {
    @Test
    public void validatePersonalInfo(String Email, String Phone, String Type) {
        try {
            assertNotNull(Email);
            assertNotNull(Phone);
            assertNotNull(Type);
            assertTrue(Email.length() > 10);
            assertTrue(Phone.length() == 10);
            assertTrue(Type.length() > 0);
            assertFalse(Email.contains("'"));
            assertFalse(Phone.contains("'"));
            assertFalse(Type.contains("'"));
            assertFalse(Email.contains(" "));
            assertFalse(Phone.contains(" "));
            assertFalse(Type.contains(" "));
        } catch (Exception e) { }
    }
}
