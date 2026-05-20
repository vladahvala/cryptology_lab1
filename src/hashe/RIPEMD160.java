package hashe;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
import java.security.MessageDigest;

public class RIPEMD160 {

    public static void main(String[] args) {

        Security.addProvider(new BouncyCastleProvider());

        try {
            MessageDigest digest = MessageDigest.getInstance("RIPEMD160", "BC");

            String input = "Hello, World!";
            byte[] hashBytes = digest.digest(input.getBytes());

            StringBuilder hex = new StringBuilder();

            for (byte b : hashBytes) {
                String h = Integer.toHexString(0xff & b);
                if (h.length() == 1)
                    hex.append('0');
                hex.append(h);
            }

            System.out.println("RIPEMD-160 Hash: " + hex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}