package hashe;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
import java.security.MessageDigest;

public class RIPEMD256 {
    public static void main(String[] args) {
        // Add Bouncy Castle Provider
        Security.addProvider(new BouncyCastleProvider());
        try {
            // Create a MessageDigest instance for RIPEMD-256
            MessageDigest digest = MessageDigest.getInstance("RIPEMD256");
            // Input data to hash
            String input = "Hello, World!";
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            System.out.println("RIPEMD-256 Hash: " + hexString.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}