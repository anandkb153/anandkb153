package Jan_22_2025;

public class TrianglePattern {
	 public static void main(String[] args) {
	        int n = 5; // Number of rows
	        int currentNumber = 1; // Start with 1

	        for (int i = 0; i < n; i++) {
	            // Print leading spaces
	            for (int space = 1; space < n - i; space++) {
	                System.out.print("   ");
	            }
	            
	            int num=currentNumber;
	            int sub=n-i;
	            // Print numbers in the row
	            for (int j = 0; j <= i; j++) {
	                System.out.printf("%-3d", num);
	               num=num-sub;
	               sub++;
	            }
	            
	            currentNumber=currentNumber+n-i;

	            // Move to the next line
	            System.out.println();
	        }
	    }
}
