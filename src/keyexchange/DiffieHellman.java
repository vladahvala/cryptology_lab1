package keyexchange;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {

    public static void main(String[] args) {

        SecureRandom rnd = new SecureRandom();

        // Public parameters
        BigInteger p = BigInteger.probablePrime(64, rnd);
        BigInteger g = BigInteger.valueOf(3);

        System.out.println("Public p = " + p);
        System.out.println("Public g = " + g);

        // Alice private key
        BigInteger a = new BigInteger(64, rnd);
        BigInteger A = g.modPow(a, p);

        // Bob private key
        BigInteger b = new BigInteger(64, rnd);
        BigInteger B = g.modPow(b, p);

        System.out.println("\nAlice private a = " + a);
        System.out.println("Alice public A = " + A);

        System.out.println("\nBob private b = " + b);
        System.out.println("Bob public B = " + B);

        // Shared secret (both sides)
        BigInteger aliceSecret = B.modPow(a, p);
        BigInteger bobSecret = A.modPow(b, p);

        System.out.println("\nAlice secret = " + aliceSecret);
        System.out.println("Bob secret = " + bobSecret);

        // Check
        System.out.println("\nEqual secrets? " + aliceSecret.equals(bobSecret));
    }
}