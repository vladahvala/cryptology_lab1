package hashe;

public class NumericHash {
    public static long nhash(String input) {
        long hash = 0;
        for (char c : input.toCharArray()) {
            hash = (hash * 31 + c) % 1000000007; // Simple hash calculation
        }
        return hash;
    }

    public static void main(String[] args) {
        String input = "Hello, World!";
        long hashValue = nhash(input);
        System.out.println("Numeric Hash: " + hashValue); // Output the numeric hash
    }
}