package asymmetric;

import java.math.BigInteger;

public class RSA {

    public static void main(String[] args) {

        BigInteger p = BigInteger.valueOf(19);
        BigInteger q = BigInteger.valueOf(41);

        // n = p * q
        BigInteger n = p.multiply(q);

        // phi(n)
        BigInteger phi = (p.subtract(BigInteger.ONE))
                .multiply(q.subtract(BigInteger.ONE));

        System.out.println("n = " + n);
        System.out.println("phi = " + phi);

        // e (зазвичай беруть 65537, але тут підбираємо)
        BigInteger e = BigInteger.TWO;

        while (!e.gcd(phi).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.ONE);
        }

        System.out.println("e = " + e);

        // d = e^-1 mod phi (ВАЖЛИВО!)
        BigInteger d = e.modInverse(phi);

        System.out.println("d = " + d);

        BigInteger msg = BigInteger.valueOf(12);

        // encrypt
        BigInteger encrypted = msg.modPow(e, n);
        System.out.println("Encrypted: " + encrypted);

        // decrypt
        BigInteger decrypted = encrypted.modPow(d, n);
        System.out.println("Decrypted: " + decrypted);
    }
}