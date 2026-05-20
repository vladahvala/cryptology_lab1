package hashe;

import java.nio.charset.StandardCharsets;

public class MD4 {

    // =========================
    // auxiliary functions
    // =========================
    static int F(int x, int y, int z) {
        return (x & y) | (~x & z);
    }

    static int G(int x, int y, int z) {
        return (x & y) | (x & z) | (y & z);
    }

    static int H(int x, int y, int z) {
        return x ^ y ^ z;
    }

    static int rotl(int x, int s) {
        return (x << s) | (x >>> (32 - s));
    }

    // =========================
    // MD4 core
    // =========================
    public static byte[] hash(byte[] message) {

        // Initial values (from theory)
        int H1 = 0x67452301;
        int H2 = 0xEFCDAB89;
        int H3 = 0x98BADCFE;
        int H4 = 0x10325476;

        // padding (simplified: only one block demo)
        int[] X = new int[16];

        for (int i = 0; i < message.length; i++) {
            X[i / 4] |= (message[i] & 0xff) << ((i % 4) * 8);
        }

        int A = H1;
        int B = H2;
        int C = H3;
        int D = H4;

        // =========================
        // Round 1
        // =========================
        int[] z1 = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
        };
        int[] w1 = {
                3, 7, 11, 19, 3, 7, 11, 19, 3, 7, 11, 19, 3, 7, 11, 19
        };
        int y1 = 0;

        for (int j = 0; j < 16; j++) {
            int t = A + F(B, C, D) + X[z1[j]] + y1;
            A = D;
            D = C;
            C = B;
            B = rotl(t, w1[j]);
        }

        // =========================
        // Round 2
        // =========================
        int[] z2 = {
                0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15
        };
        int y2 = 0x5A827999;

        for (int j = 0; j < 16; j++) {
            int t = A + G(B, C, D) + X[z2[j]] + y2;
            A = D;
            D = C;
            C = B;
            B = rotl(t, w1[j]);
        }

        // =========================
        // Round 3
        // =========================
        int[] z3 = {
                0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15
        };
        int y3 = 0x6ED9EBA1;

        for (int j = 0; j < 16; j++) {
            int t = A + H(B, C, D) + X[z3[j]] + y3;
            A = D;
            D = C;
            C = B;
            B = rotl(t, w1[j]);
        }

        // =========================
        // Add to initial state
        // =========================
        H1 += A;
        H2 += B;
        H3 += C;
        H4 += D;

        // =========================
        // output
        // =========================
        byte[] out = new byte[16];

        int[] H = { H1, H2, H3, H4 };

        for (int i = 0; i < 4; i++) {
            out[i * 4] = (byte) (H[i]);
            out[i * 4 + 1] = (byte) (H[i] >>> 8);
            out[i * 4 + 2] = (byte) (H[i] >>> 16);
            out[i * 4 + 3] = (byte) (H[i] >>> 24);
        }

        return out;
    }

    // =========================
    // test
    // =========================
    public static void main(String[] args) {

        String msg = "Hello MD4";

        byte[] digest = hash(msg.getBytes(StandardCharsets.UTF_8));

        System.out.print("MD4 = ");
        for (byte b : digest) {
            System.out.printf("%02x", b);
        }
    }
}