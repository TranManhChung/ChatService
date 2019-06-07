package com.vng.authservice.Utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class SecurePassword {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static String getSalt(short length) {
        StringBuilder salt = new StringBuilder(length);

        for (int i = 0; i < length; i++){
            salt.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return String.valueOf(salt);
    }

    public static byte[] hash(char[] password, byte[] salt){
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);

        Arrays.fill(password, Character.MIN_VALUE);

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static String generateSecurePassword(String password, String salt) {
        if (salt == null || salt.length() == 0) {
            return null;
        }

        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

       return Base64.getEncoder().encodeToString(securePassword);
    }

    public static boolean verifyUserPassword(String providedPass, String securedPass, String salt) {
        if (securedPass == null || securedPass.length() == 0){
            return false;
        }

        String newSecuredPass = generateSecurePassword(providedPass, salt);

        return newSecuredPass.equals(securedPass);
    }
}
