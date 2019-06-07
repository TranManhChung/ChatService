package com.vng.authservice.utils;

import com.vng.authservice.Utils.SecurePassword;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.persistence.Temporal;
import java.util.Arrays;
import java.util.Collection;

//@RunWith(Parameterized.class)
public class SecurePasswordTests {
//    private short length;
//    private short expected;
//
//    public SecurePasswordTests(short length, short expected) {
//        this.length = length;
//        this.expected = expected;
//    }
//
//    //create test data
//    @Parameterized.Parameters
//    public static Collection<Object[]> data() {
//        Object[][] data = new Object[][]{{1, 1}, {-1, 0},
//                {32766, 32766}, {-32767, 0}, {null, null}};
//
//        return Arrays.asList(data);
//    }
//
//    //test method getSalt()
//    @Test
//    @Ignore
//    public void getSalt(){
//        String salt = SecurePassword.getSalt(length);
//        short actual = (short) salt.length();
//
//        Assert.assertEquals(expected, actual);
//    }

//    private String rawPass;
//    private String salt;
//
//    public SecurePasswordTests(String rawPass, String salt){
//        this.rawPass  = rawPass;
//        this.salt = salt;
//    }
//
//    // creates the test data
//    @Parameterized.Parameters
//    public static Collection<Object[]> data() {
//        Object[][] data = new Object[][] { {"123", "ABCXYZ123abcxyz"},
//                {"", ""}, {null, null} };
//        return Arrays.asList(data);
//    }

    @Test
    public void hash(){
        char[] password = "123".toCharArray();
        byte[] salt = "ABCXYZ123abcxyz".getBytes();


    }

    @Test
    public void testSecurePassw(){
        String rawPass = "123";
        String salt = "ABCXYZ123abcxyz";
        String securePass = SecurePassword.generateSecurePassword(rawPass, salt);

        Assert.assertTrue(SecurePassword.verifyUserPassword(rawPass, securePass, salt));

        rawPass = "";
        salt = "";
        securePass = SecurePassword.generateSecurePassword(rawPass, salt);

        Assert.assertFalse(SecurePassword.verifyUserPassword(rawPass, securePass, salt));

        rawPass = null;
        salt = null;
        securePass = SecurePassword.generateSecurePassword(rawPass, salt);

        Assert.assertFalse(SecurePassword.verifyUserPassword(rawPass, securePass, salt));
    }
}

//    @RunWith(Parameterized.class)
//    public class HashTests {
//        private char[] password;
//        private byte[] salt;
//
//        public HashTests(char[] password, byte[] salt){
//            this.password = password;
//            this.salt = salt;
//        }
//
//        // creates the test data
//        @Parameterized.Parameters
//        public Collection<Object[]> data() {
//            Object[][] data = new Object[][] { {"123", "ABCXYZ123abcxyz"},
//                    {"", ""}, {null, null} };
//            return Arrays.asList(data);
//        }
//
//        @Test
//        public void hash(){
//            Assert.assertTrue(true);
//        }
//    }
