package Feb_15_2025;

import java.util.Scanner;

public class Q3 {
    public static void printPattern(int size) {
    	 char ch = (char) ('a' + size - 1); // Highest character (e.g., 'e' for size 5)

         for (int i = 0; i < size * 2 - 1; i++) {
             int spaces = Math.abs(size - 1 - i); // Leading and trailing spaces
             int charCount = size - spaces; // Number of characters in row

             // Print leading spaces
             for (int s = 0; s < spaces; s++) {
                 System.out.print("__");
             }

             // Print decreasing characters
             for (int j = 0; j < charCount; j++) {
                 System.out.print((char) (ch - j));
                 if (j != charCount - 1) System.out.print("-");
             }

             // Print increasing characters
             for (int j = charCount - 2; j >= 0; j--) {
                 System.out.print("-" + (char) (ch - j));
             }

             // Print trailing spaces
             for (int s = 0; s < spaces; s++) {
                 System.out.print("__");
             }

             System.out.println(); // Move to the next line
         }
     }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size: ");
        int size = scanner.nextInt();
        printPattern(size);
        scanner.close();
    }
}
