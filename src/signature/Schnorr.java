package signature;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class Schnorr {

    static SecureRandom rnd = new SecureRandom();

    public static void main(String[] args) throws Exception {

        // =========================
        // 1. System parameters
        // =========================

        BigInteger p = new BigInteger("48731");
        BigInteger q = new BigInteger("443");
        BigInteger b = new BigInteger("11444");

        int t = 8;

        System.out.println("System parameters:");
        System.out.println("p = " + p);
        System.out.println("q = " + q);
        System.out.println("b = " + b);
        System.out.println("t = " + t);

        // =========================
        // 2. Key generation
        // =========================

        BigInteger a = new BigInteger("357"); // private key

        // v = b^(-a) mod p
        BigInteger v = b.modPow(a, p).modInverse(p);

        System.out.println("\nPrivate key a = " + a);
        System.out.println("Public key v = " + v);

        // =========================
        // 3. Message
        // =========================

        String message = "Hello Schnorr";

        System.out.println("\nMessage: " + message);

        // =========================
        // 4. Signature generation
        // =========================

        // random r
        BigInteger r = new BigInteger("274");

        // x = b^r mod p
        BigInteger x = b.modPow(r, p);

        System.out.println("\nr = " + r);
        System.out.println("x = " + x);

        // E = H(m || x)
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(message.getBytes());
        md.update(x.toString().getBytes());

        BigInteger E = new BigInteger(1, md.digest()).mod(q);

        System.out.println("E = " + E);

        // y = (a*E + r) mod q
        BigInteger y = (a.multiply(E).add(r)).mod(q);

        System.out.println("y = " + y);

        // signature = (E, y)
        System.out.println("\nSignature:");
        System.out.println("(E, y) = (" + E + ", " + y + ")");

        // =========================
        // 5. Verification
        // =========================

        // z = b^y * v^E mod p
        BigInteger z = b.modPow(y, p)
                .multiply(v.modPow(E, p))
                .mod(p);

        System.out.println("\nz = " + z);

        // H(m || z)
        MessageDigest md2 = MessageDigest.getInstance("SHA-256");

        md2.update(message.getBytes());
        md2.update(z.toString().getBytes());

        BigInteger Ev = new BigInteger(1, md2.digest()).mod(q);

        System.out.println("E' = " + Ev);

        // check
        boolean valid = E.equals(Ev);

        System.out.println("\nSignature valid? " + valid);
    }
}