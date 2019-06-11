package com.vng.authservice.google;

import com.vng.authservice.google.GoogleUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class GoogleUtilsTest {

    private static GoogleUtils googleUtils;

    @BeforeClass
    public static void init(){
        googleUtils = new GoogleUtils();
    }

    @Test(expected = NullPointerException.class)
    public void getToken_NullPointerException() throws IOException {
        googleUtils.getToken(null);
    }

}
