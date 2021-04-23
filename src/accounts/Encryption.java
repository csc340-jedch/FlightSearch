package accounts;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static String getRandomSalt() {
        // Generate a number from 5 to 9
        int saltSize = (int)(Math.random() * 5) + 5;

        // Create byte array of salt size
        byte[] saltBytes = new byte[saltSize];

        // Populate with random bytes
        for (int i = 0; i < saltBytes.length; i++) {
            // Generate ASCII values from 33 to 126
            saltBytes[i] = (byte)(Math.random() * 93 + 33);
        }

        // Return the byte array as a string
        return new String(saltBytes, StandardCharsets.UTF_8);
    }

    public static String getEncryptedPassword(String password, String salt) throws NoSuchAlgorithmException {
        // MD5 hash the salt
        String md5Salt = md5(salt);

        // MD5 hash the password
        String md5Password = md5(password);

        // Add the salt to the end of the password and MD5 hash the entire thing one last time
        return md5(md5Password + md5Salt);
    }

    private static String md5(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing MD5
        MessageDigest md = MessageDigest.getInstance("MD5");

        // digest() method is called to calculate message digest
        //  of an input digest() return array of byte
        byte[] messageDigest = md.digest(input.getBytes());

        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);

        // Convert message digest into hex value
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
