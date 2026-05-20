package checksum;

import java.nio.charset.StandardCharsets;

public class Adler32 {

    private static final int MOD = 65521;

    public static long adler32(String input) {

        byte[] data = input.getBytes(StandardCharsets.UTF_8);

        int A = 1;
        int B = 0;

        for (byte b : data) {
            A = (A + (b & 0xFF)) % MOD;
            B = (B + A) % MOD;
        }

        return ((long) B << 16) | A;
    }

    public static void main(String[] args) {

        String input = "Hello, World!";

        long hash = adler32(input);

        System.out.println("Input: " + input);
        System.out.println("Adler-32: " + hash);
    }
}