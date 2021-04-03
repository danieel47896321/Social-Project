package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LogoutTest {
    @Test
    public void validateLogoutUser(User user){
        try {
            assertTrue(user == null);
        }
        catch (Exception e){

        }
    }
}
