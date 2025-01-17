package Jan_13_2025;

public class Q2 {
    // X pattern program
    public static void main(String[] args) {
        String input = "zohofinance";

        int n = input.length();
       

        // Check if the input length is odd
        if (n % 2 != 0) {
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (row  == col || row +col == n-1)
                    { // Left diagonal condition
                        System.out.print(input.charAt(col) + " ");
                    }  else 
                    {
                        System.out.print("  "); // Two spaces for alignment
                    }
                }
                System.out.println(); // Move to the next line
            }
        } else {
            System.err.println("Input length should be odd. Given length: " + n);
        }
    }
}
