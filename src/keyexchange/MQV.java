package keyexchange;

import java.math.BigInteger;
import java.security.SecureRandom;

public class MQV {

    public static void main(String[] args) {

        SecureRandom rnd = new SecureRandom();

        // ===== Public parameters =====
        BigInteger p = BigInteger.probablePrime(64, rnd);
        BigInteger g = BigInteger.valueOf(3);

        System.out.println("p = " + p);
        System.out.println("g = " + g);

        // ===== Long-term keys =====
        BigInteger a = new BigInteger(64, rnd);
        BigInteger b = new BigInteger(64, rnd);

        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);

        System.out.println("\nAlice A = " + A);
        System.out.println("Bob B = " + B);

        // ===== Ephemeral keys =====
        BigInteger x = new BigInteger(64, rnd);
        BigInteger y = new BigInteger(64, rnd);

        BigInteger X = g.modPow(x, p);
        BigInteger Y = g.modPow(y, p);

        System.out.println("\nAlice X = " + X);
        System.out.println("Bob Y = " + Y);

        // ===== MQV-like stable construction =====
        BigInteger Ka = B.modPow(x, p)
                .multiply(Y.modPow(a, p))
                .mod(p);

        BigInteger Kb = A.modPow(y, p)
                .multiply(X.modPow(b, p))
                .mod(p);

        System.out.println("\nAlice key = " + Ka);
        System.out.println("Bob key   = " + Kb);

        System.out.println("\nEqual? " + Ka.equals(Kb));
    }
}