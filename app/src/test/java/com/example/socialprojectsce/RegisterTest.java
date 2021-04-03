package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.*;
public class RegisterTest {
    @Test
    public void validateRegistrationInput(String username,String password){
        RegisterActivity s = new RegisterActivity();
        assertNotNull(username);
        assertNotNull(password);

        //assert
        //if( username.contains("'") || username.contains(" ")  || password.contains("'") || password.contains(" ") || username.length()<6 || password.length()<6 )
    }
}
