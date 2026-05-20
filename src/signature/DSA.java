package signature;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class DSA {

    static SecureRandom rnd = new SecureRandom();

    public static void main(String[] args) throws Exception {

        // =========================
        // 1. Parameters (toy example)
        // =========================
        BigInteger p = new BigInteger("53");
        BigInteger q = new BigInteger("13");
        BigInteger g = new BigInteger("16");

        // =========================
        // 2. Keys
        // =========================
        BigInteger x = new BigInteger("3"); // private key
        BigInteger y = g.modPow(x, p); // public key

        System.out.println("p = " + p);
        System.out.println("q = " + q);
        System.out.println("g = " + g);
        System.out.println("x (private) = " + x);
        System.out.println("y (public) = " + y);

        // =========================
        // 3. Message hash H(m)
        // =========================
        String message = "Hello";
        BigInteger Hm = hash(message).mod(q);

        System.out.println("\nH(m) = " + Hm);

        // =========================
        // 4. Signature generation
        // =========================
        BigInteger k = new BigInteger("2");

        BigInteger r = g.modPow(k, p).mod(q);

        BigInteger kInv = k.modInverse(q);
        BigInteger s = kInv.multiply(Hm.add(x.multiply(r))).mod(q);

        System.out.println("\nSignature (r,s): " + r + ", " + s);

        // =========================
        // 5. Verification
        // =========================
        BigInteger w = s.modInverse(q);
        BigInteger u = Hm.multiply(w).mod(q);
        BigInteger z = r.multiply(w).mod(q);

        BigInteger v = (g.modPow(u, p)
                .multiply(y.modPow(z, p)))
                .mod(p)
                .mod(q);

        System.out.println("\nVerification:");
        System.out.println("v = " + v);
        System.out.println("r = " + r);

        System.out.println("\nValid? " + v.equals(r));
    }

    // simple hash (for lab)
    static BigInteger hash(String msg) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(msg.getBytes());
        return new BigInteger(1, digest);
    }
}