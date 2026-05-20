package checksum;

import java.nio.charset.StandardCharsets;

public class CRC8 {

    // CRC-8 polynomial: x^8 + x^6 + x^3 + x^2 + 1 = 0xB2
    private static final int POLYNOMIAL = 0xB2;
    private static final int INIT = 0xFF;

    public static int crc8(String input) {

        byte[] data = input.getBytes(StandardCharsets.UTF_8);

        int crc = INIT;

        for (byte b : data) {
            crc ^= (b & 0xFF);

            for (int i = 0; i < 8; i++) {
                if ((crc & 1) != 0) {
                    crc = (crc >> 1) ^ POLYNOMIAL;
                } else {
                    crc = (crc >> 1);
                }
                crc &= 0xFF;
            }
        }

        return crc ^ 0xFF;
    }

    public static void main(String[] args) {

        String input = "Hello, World!";

        int result = crc8(input);

        System.out.println("Input: " + input);
        System.out.printf("CRC-8: %02X\n", result);
    }
}