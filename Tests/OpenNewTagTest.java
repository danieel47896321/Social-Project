package com.example.socialprojectsce;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OpenNewTagTest {
    @Test
    public void validateOpenNewTagTest(String Tagname,String Category,int Photo){
        try {
            assertNotNull(Tagname);
            assertNotNull(Category);
            assertNotNull(Photo);
            assertTrue(Tagname.length() > 4);
            assertTrue(Category.length() > 4);
            assertTrue(Photo > 1000000);
            assertFalse(Tagname.contains("'"));
            assertFalse(Category.contains("'"));
            assertFalse(Tagname.contains(" "));
            assertFalse(Category.contains(" "));
        }
        catch (Exception e){

        }
    }
}
