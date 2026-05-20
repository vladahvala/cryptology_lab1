package hashe;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class SHA1 {

    public static void main(String[] args) {

        String input = "Hello, World!";
        String hash = generateSHA1(input);

        System.out.println("Input: " + input);
        System.out.println("SHA-1 Hash: " + hash);
    }

    public static String generateSHA1(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hex = new StringBuilder();

            for (byte b : digest) {
                String h = Integer.toHexString(b & 0xff);

                if (h.length() == 1) {
                    hex.append('0');
                }

                hex.append(h);
            }

            return hex.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 not available", e);
        }
    }
}