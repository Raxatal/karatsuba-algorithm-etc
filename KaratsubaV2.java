import java.util.Random;

// By Iqmal Aidid bin Zakaria and Jalal Naim bin Mat Yaacob
class KaratsubaV2 {
    static long counter = 0; // static variable so that it can be interacted with throughout the code

    public static long mult(long x, long y) {
        counter += 1; // Counter for the initial method call
        if (x < 10 && y < 10) {
            counter += 4; // Counter for 1 comparison, 1 multiplication and 1 method return
            return x * y;
        }

        int noOneLength = numLength(x);
        int noTwoLength = numLength(y);
        int maxNumLength = Math.max(noOneLength, noTwoLength);
        counter += 3; // Counter for 2 method call and 1 max operation

        int halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);
        counter += 4; // Counter for 1 assignment, 1 division, 1 addition, 1 modulus

        long maxNumLengthTen = (long) Math.pow(10, halfMaxNumLength);
        counter += 2; // Counter for 1 assignment, 1 power operation

        long a = x / maxNumLengthTen;
        counter += 2; // Counter for 1 assignment, 1 division
        long b = x % maxNumLengthTen;
        counter += 2; // Counter for 1 assignment, 1 modulus
        long c = y / maxNumLengthTen;
        counter += 2; // Counter for 1 assignment, 1 division
        long d = y % maxNumLengthTen;
        counter += 2; // Counter for 1 assignment, 1 modulus

        long z0 = mult(a, c);
        counter += 2; // Counter for 1 assignment, 1 recursive method call
        long z1 = mult(a + b, c + d);
        counter += 4; // Counter for 1 assignment, 1 recursive method call and 2 addition
        long z2 = mult(b, d);
        counter += 2; // Counter for 1 assignment, 1 recursive method call
        long ans = (z0 * (long) Math.pow(10, halfMaxNumLength * 2)
                + ((z1 - z0 - z2) * (long) Math.pow(10, halfMaxNumLength) + z2));
        counter += 9; // Counter for 1 assignments, 3 multiplication, 2 power operation, 2
                      // subtraction, 1 addition and 1 method return

        return ans;
    }

    private static int numLength(long n) {
        int noLen = 0;
        counter += 1; // Counter for 1 assignment
        while (n > 0) {
            noLen++;
            counter += 2; // Counter for 1 comparison, 1 addition
            n /= 10;
            counter += 1; // Counter for 1 division
        }
        return noLen;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int MAX_DIGITS = 15; // Edit this (n) to change up to how many digits to run
        // Note that any n > 10 will typically result in a negative value for the actual
        // and expected outcome due to data overflow since we did not use a larger size
        // data type like Big Integer.

        for (int n = 1; n <= MAX_DIGITS; n++) {
            long x = generateRandomNumber(n, rand);
            long y = generateRandomNumber(n, rand);
            long expectedProduct = x * y;
            counter = 0; // Reset counter count for each iteration
            long actualProduct = mult(x, y);

            System.out.println("Digits: " + n);
            System.out.println("Multiplicand: " + x);
            System.out.println("Multiplier: " + y);
            System.out.println("Expected: " + expectedProduct);
            System.out.println("Actual: " + actualProduct);
            System.out.println("Number of operations: " + counter);
            System.out.println();
        }
    }

    // function to generate random number with digits = n
    private static long generateRandomNumber(int n, Random rand) {
        long lower = (long) Math.pow(10, n - 1);
        long upper = (long) Math.pow(10, n) - 1;
        return rand.nextLong(upper - lower + 1) + lower;
    }
}
