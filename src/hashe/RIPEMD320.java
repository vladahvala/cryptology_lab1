package hashe;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.MessageDigest;
import java.security.Security;

public class RIPEMD320 {
    public static void main(String[] args) {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
        try {
            // Create a MessageDigest instance for RIPEMD-320
            MessageDigest digest = MessageDigest.getInstance("RIPEMD320");

            // Input data
            String input = "Hello, World!";
            byte[] hash = digest.digest(input.getBytes());
            // Print the hash in hexadecimal format
            System.out.println("RIPEMD-320 Hash: " + bytesToHex(hash));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert byte array to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}