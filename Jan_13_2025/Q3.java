package Jan_13_2025;

import java.util.LinkedList;

public class Q3 {
    public static void main(String[] args) {
        // Example inputs
        String input1 = "12345+-*/";
        String input2 = "73421*+*-";

        // Evaluate the expressions
        System.out.println("Output for input1: " + evaluateExpression(input1)); // Output: 0
        System.out.println("Output for input2: " + evaluateExpression(input2)); // Output: -11
    }

    public static int evaluateExpression(String input) {
        // Using LinkedList directly as a Deque
        LinkedList<Integer> queue = new LinkedList<>();

       
        for (char ch : input.toCharArray()) {
            if (Character.isDigit(ch)) {
               
                queue.offer(ch - '0');
            } else {
               
                if (queue.size() < 2) {
                    throw new IllegalArgumentException("Invalid input string: not enough operands for operation.");
                }
                
                

                int a = queue.poll(); // Second operand (FIFO)
                int b = queue.poll(); // First operand (FIFO)

                // Perform the operation based on the operator
                switch (ch) {
                    case '+':
                        queue.offerFirst(a + b);  // Add result to the front of the queue
                        break;
                    case '-':
                        queue.offerFirst(a - b);  // Add result to the front of the queue
                        break;
                    case '*':
                        queue.offerFirst(a * b);  // Add result to the front of the queue
                        break;
                    case '/':
                        if (b == 0) {
                            throw new ArithmeticException("Division by zero is not allowed.");
                        }
                        queue.offerFirst(a / b);  // Add result to the front of the queue
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + ch);
                }
            }
        }

        // The final result should be the only element left in the queue
        if (queue.size() != 1) {
            throw new IllegalStateException("Invalid input string: incorrect number of operators.");
        }

        return queue.poll(); // Final result is removed from the front of the queue
    }
}
