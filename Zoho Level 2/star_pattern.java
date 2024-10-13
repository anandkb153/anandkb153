package Zoho_Level_2;

public class star_pattern {
    public static void main(String[] args) {
        int input = 3; 

        // Loop for the upper triangle
        for (int i = 1; i <= input; i++) {
            // Print spaces for right alignment
            for (int space = 0; space < input - i; space++) {
                System.out.print(" ");
            }

            // Print stars in each row
            for (int star = 0; star < i; star++) {
                System.out.print("* ");
            }

            // Move to the next line after each row
            System.out.println();
        }

        // Loop for the lower inverted triangle
        for (int i = input - 1; i >= 1; i--) {
            // Print spaces for right alignment
            for (int space = 0; space < input - i; space++) {
                System.out.print(" ");
            }

            // Print stars in each row
            for (int star = 0; star < i; star++) {
                System.out.print("* ");
            }

            // Move to the next line after each row
            System.out.println();
        }
    }
}
