package Jan_13_2025;

public class Q1 {
	 public static void main(String[] args) {
	        int n = 4; // Input size

	        for (int i = 1; i <= n; i++) {
	            int num = i;

	            // Print leading spaces and pattern in one loop
	            for (int j = 1; j <= n + i - 1; j++) {
	                if (j <= n - i) {
	                    System.out.print("-");
	                } else if (j <= n) {
	                    System.out.print(num++);
	                } else {
	                    System.out.print(--num - 1);
	                }
	            }

	            System.out.println(); // Move to the next line
	        }
	    }
	}