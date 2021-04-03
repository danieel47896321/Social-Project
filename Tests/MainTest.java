package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void validateMainUser(User user){
        try {
            assertTrue(user != null);
            assertTrue(user.getType().equals("Student") || user.getType().equals("Teacher") || user.getType().equals("Admin"));
        }
        catch (Exception e){

        }
    }
}
