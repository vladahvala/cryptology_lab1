package asymmetric;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamal {

    public static void main(String[] args) {

        SecureRandom rnd = new SecureRandom();

        // 1. Public parameters
        BigInteger p = BigInteger.probablePrime(64, rnd);
        BigInteger g = BigInteger.valueOf(3);

        // 2. Secret key (x)
        BigInteger x = new BigInteger("1234567890");

        System.out.println("Secret key x = " + x);

        // 3. Public key y = g^x mod p
        BigInteger y = g.modPow(x, p);

        System.out.println("p = " + p);
        System.out.println("g = " + g);
        System.out.println("y = " + y);

        // =========================
        // ENCRYPTION
        // =========================

        BigInteger m = BigInteger.valueOf(42); // повідомлення

        BigInteger k = new BigInteger(64, rnd); // random r (ephemeral key)

        BigInteger c1 = g.modPow(k, p);
        BigInteger c2 = m.multiply(y.modPow(k, p)).mod(p);

        System.out.println("\nPlaintext m = " + m);
        System.out.println("k = " + k);
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);

        // =========================
        // DECRYPTION
        // =========================

        BigInteger s = c1.modPow(x, p); // s = g^(xk)
        BigInteger sInv = s.modInverse(p); // inverse

        BigInteger decrypted = c2.multiply(sInv).mod(p);

        System.out.println("\nDecrypted = " + decrypted);
    }
}