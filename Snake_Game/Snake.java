package Snake_Game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Snake {
    private final char[][] snakeBoard;
    private final Queue<Node> queue = new LinkedList<>();
    private final Queue<Node> food = new LinkedList<>();
    private final Scanner scanner = new Scanner(System.in);
    private int currentRow = 0;
    private int currentCol = 0;

    public Snake(int rows, int cols) {
        snakeBoard = new char[rows][cols];
        queue.add(new Node(0, 0));
        snakeBoard[0][0] = '.';

        // Positions of food (X) to display
        food.add(new Node(1, 0));
        food.add(new Node(2, 2));
        food.add(new Node(3, 4));
        food.add(new Node(5, 2));
        food.add(new Node(4, 5));
        food.add(new Node(1, 0));
        food.add(new Node(2, 2));
        food.add(new Node(3, 4));
        food.add(new Node(5, 2));
        food.add(new Node(4, 5));
        food.add(new Node(1, 0));
        food.add(new Node(2, 2));
        food.add(new Node(3, 4));
        food.add(new Node(5, 2));
        food.add(new Node(4, 5));

        // Display first food (o)
        displayFood(food.poll());
    }

    public void startGame() {
        while (true) {
            printBoard();
            System.out.print("Enter a direction (U/D/L/R): ");
            char direction = scanner.next().toUpperCase().charAt(0);

            switch (direction) {
                case 'U':
                    snakeMove(currentRow - 1, currentCol);
                    break;
                case 'D':
                    snakeMove(currentRow + 1, currentCol);
                    break;
                case 'L':
                    snakeMove(currentRow, currentCol - 1);
                    break;
                case 'R':
                    snakeMove(currentRow, currentCol + 1);
                    break;
                default:
                    System.out.println("Invalid direction! Use U, D, L, or R.");
            }
        }
    }

    private void snakeMove(int row, int col) {
    	  // Adjust row and column for wrapping around edges
        row = (row + snakeBoard.length) % snakeBoard.length;
        col = (col + snakeBoard[0].length) % snakeBoard[0].length;
        
        if (row < 0 || row >= snakeBoard.length || col < 0 || col >= snakeBoard[0].length) {
            System.out.println("Invalid move. Game Over!");
            System.exit(0);
        }

        if (snakeBoard[row][col] == '.') {
            System.out.println("Oops !!. Game Over!");
            System.exit(0);
        }

        queue.add(new Node(row, col));

        if (snakeBoard[row][col] != 'o') {
            Node tail = queue.poll();
            snakeBoard[tail.getRow()][tail.getColumn()] = ' ';
        } else if (food.isEmpty()) {
            System.out.println("You ate all the food. You Win!");
            System.exit(0);
        } else {
            displayFood(food.poll());
        }

        snakeBoard[row][col] = '.';
        currentRow = row;
        currentCol = col;
    }

    private void displayFood(Node node) {
        snakeBoard[node.getRow()][node.getColumn()] = 'o';
    }

    private void printBoard() {
        for (char[] row : snakeBoard) {
            for (char cell : row) {
                System.out.print(cell == '\0' ? ' ' : cell);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
