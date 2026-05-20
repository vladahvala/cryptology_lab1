package signature;

import java.math.BigInteger;

public class NybergRueppel {

    public static void main(String[] args) {

        // =========================
        // 1. Parameters
        // =========================
        BigInteger P = new BigInteger("607");
        BigInteger Q = new BigInteger("101");
        BigInteger G = new BigInteger("601");

        System.out.println("P = " + P);
        System.out.println("Q = " + Q);
        System.out.println("G = " + G);

        // =========================
        // 2. Key generation
        // =========================
        BigInteger x = new BigInteger("3"); // private key
        BigInteger Y = G.modPow(x, P); // public key

        System.out.println("\nPrivate key x = " + x);
        System.out.println("Public key Y = " + Y);

        // =========================
        // 3. Message
        // =========================
        BigInteger m = new BigInteger("12");
        System.out.println("\nMessage m = " + m);

        // =========================
        // 4. Redundancy function f(m)
        // f(m) = m + 16m = 17m
        // =========================
        BigInteger f = m.multiply(BigInteger.valueOf(17));
        System.out.println("f(m) = " + f);

        // =========================
        // 5. Signing
        // =========================
        BigInteger k = new BigInteger("45");

        BigInteger R = G.modPow(k, P);
        BigInteger E = f.multiply(R).mod(P);

        BigInteger S = x.multiply(E).add(k).mod(Q);

        System.out.println("\nk = " + k);
        System.out.println("R = " + R);
        System.out.println("E = " + E);
        System.out.println("S = " + S);

        System.out.println("\nSignature (E, S) = (" + E + ", " + S + ")");

        // =========================
        // 6. Verification
        // =========================

        // recover f(m) correctly
        BigInteger Rinv = R.modInverse(P);
        BigInteger recovered_f = E.multiply(Rinv).mod(P);

        System.out.println("\nRecovered f(m) = " + recovered_f);

        // =========================
        // 7. Message recovery (f⁻¹)
        // =========================
        BigInteger recovered_m = recovered_f.divide(BigInteger.valueOf(17));

        System.out.println("Recovered m = " + recovered_m);

        // =========================
        // 8. Check
        // =========================
        System.out.println("\nValid signature? " + recovered_m.equals(m));
    }
}