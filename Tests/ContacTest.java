package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ContacTest {
    @Test
    public void validateChangePasswordInput(String Text){
        try {
            assertNotNull(Text);
            assertTrue(Text.length() > 10);
            assertFalse(Text.contains("'"));
            assertFalse(Text.contains(" "));
        }
        catch (Exception e){

        }
    }
}
