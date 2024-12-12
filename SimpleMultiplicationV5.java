import java.util.Random;

// By Iqmal Aidid bin Zakaria and Jalal Naim bin Mat Yaacob
public class SimpleMultiplicationV5 {
    public static void main(String[] args) {
        int n = 15; // Edit the n to change up to how many digits to run

        int[][] table = new int[n + 1][2]; // Prepping the output table

        for (int i = 1; i <= n; i++) {
            System.out.println("Number of digits (n): " + i); // Print the number of digits
            int[] multiplicand = generateRandomNumber(i);
            int[] multiplier = generateRandomNumber(i);
            System.out.println("Multiplicand:   " + arrayToString(multiplicand));
            System.out.println("Multiplier:     " + arrayToString(multiplier));
            int numOperation = getAllProductAndCarrier(multiplicand, multiplier);

            // Store the values in the table array
            table[i][0] = i; // Store the value of n
            table[i][1] = numOperation; // Store the number of operations
        }

        // Print table with labels
        System.out.println("n\tNum of Operations");
        for (int i = 1; i <= n; i++) {
            System.out.println(table[i][0] + "\t" + table[i][1]);
        }
    }

    // Simulating the Simple Multiplication
    public static int getAllProductAndCarrier(int[] multiplicand, int[] multiplier) {
        int multiplicandLength = multiplicand.length;
        int multiplierLength = multiplier.length;
        int[][] partial = new int[multiplierLength][multiplicandLength];
        int[][] carriers = new int[multiplierLength][multiplicandLength + 1]; // Since carriers shifted

        int counter = 0; // Initialize the counter

        // Step 1: Multiplying each digit in multiplicand by each digit in multiplier
        // and storing all the products and carriers in an array to be added later
        for (int i = 0; i < multiplierLength; i++) {
            counter += 3; // Counter for 1 comparison, 1 assignment and 1 adddition
            for (int j = 0; j < multiplicandLength; j++) {
                counter += 3; // Counter for 1 comparison, 1 assignment and 1 adddition
                int currentMultiplicand = multiplicand[multiplicandLength - j - 1];
                int currentMultiplier = multiplier[multiplierLength - i - 1];
                counter += 4; // Counter for 2 assignment, 2 array indexing
                partial[i][j] = getPartialProduct(currentMultiplicand, currentMultiplier);
                counter += 5; // Counter for 1 array indexing, 1 function call, 1 multiplication, 1 modulus,
                              // and 1 assignment
                carriers[i][j + 1] = getCarrier(currentMultiplicand, currentMultiplier);
                counter += 5; // Counter for 1 array indexing, 1 function call, 1 multiplication, 1 division,
                              // and 1 assignment
            }
        }

        // Step 2: Print partial products and carriers
        // Technically no need counter here since irrelevant to the algorithm
        for (int i = 0; i < multiplierLength; i++) {
            counter += 3; // Counter for 1 comparison, 1 assignment and 1 addition
            System.out.print("Partial products for (");
            for (int digit : multiplicand) {
                System.out.print(digit); // Print the multiplicand digits
            }
            System.out.print(" x " + multiplier[multiplierLength - i - 1] + "): ");
            for (int j = multiplicandLength - 1; j >= 0; j--) {
                counter += 3; // Counter for 1 comparison, 1 assignment and 1 substraction
                System.out.print(partial[i][j]);
            }
            System.out.println();
            System.out.print("Carriers for (");
            for (int digit : multiplicand) {
                System.out.print(digit); // Print the multiplicand digits
            }
            System.out.print(" x " + multiplier[multiplierLength - i - 1] + "): ");
            for (int j = multiplicandLength; j > 0; j--) {
                counter += 3; // Counter for 1 comparison, 1 assignment and 1 substraction
                System.out.print(carriers[i][j]);
            }
            System.out.println();
        }

        // Step 3: Calculate and print the final result
        int[] result = new int[multiplicandLength + multiplierLength]; // Maximum possible length
        counter += 1; // Counter for array creation/assignment
        for (int i = 0; i < multiplierLength; i++) {
            counter += 3; // Counter for 1 comparison, 1 assignment and 1 addition
            for (int j = 0; j < multiplicandLength; j++) {
                counter += 3; // Counter for 1 comparison, 1 assignment and 1 addition
                result[i + j] += partial[i][j];
                counter += 4; // Counter for 1 addition, 1 assignment and 2 array indexing operations
                result[i + j + 1] += carriers[i][j + 1];
                counter += 4; // Counter for 1 addition, 1 assignment and 2 array indexing operations
            }
        }

        // Adjust result for carryovers
        for (int i = 0; i < result.length - 1; i++) {
            counter += 3; // Counter for 1 comparison, 1 assignment and 1 addition
            result[i + 1] += result[i] / 10;
            counter += 5; // Counter for 1 division, 1 assignment, 1 addition, and 2 array indexing
                          // operations
            result[i] %= 10;
            counter += 3; // Counter for 1 modulus, 1 assignment, and 1 array indexing operation
        }

        System.out.print("Final Result: ");
        for (int i = result.length - 1; i >= 0; i--) {
            System.out.print(result[i]);
        }
        System.out.println();

        System.out.println("Number of operations: " + counter); // Print total number of operations
        System.out.println();

        return counter;
    }

    // Edited to make sure leading zeros cannot be generated
    public static int[] generateRandomNumber(int n) {
        Random rand = new Random();
        int[] number = new int[n];
        // Generate the first digit (cannot be 0)
        number[0] = rand.nextInt(9) + 1; // Random digit between 1 and 9
        // Generate the rest of the digits
        for (int i = 1; i < n; i++) {
            number[i] = rand.nextInt(10); // Random digit between 0 and 9
        }
        return number;
    }

    // Function to calculate the partial product
    public static int getPartialProduct(int multiplicand, int multiplier) {
        return multiplicand * multiplier % 10;
    }

    // Function to calculate the carrier
    public static int getCarrier(int multiplicand, int multiplier) {
        return multiplicand * multiplier / 10;
    }

    // Function to convert array to String
    public static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int digit : array) {
            sb.append(digit);
        }
        return sb.toString();
    }
}