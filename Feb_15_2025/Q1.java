package Feb_15_2025;

public class Q1 {
    public static int minCostToMoveChips(int[] position) {
        int evenCount = 0, oddCount = 0;

        // Count how many chips are in even and odd positions
        for (int p : position) {
            if (p % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        // The minimum cost will be moving the smaller group (either even or odd)
        return Math.min(evenCount, oddCount);
    }

    public static void main(String[] args) {
        int[] position1 = {1, 2, 3};
        System.out.println(minCostToMoveChips(position1)); // Output: 1

        int[] position2 = {2, 2, 2, 3, 3};
        System.out.println(minCostToMoveChips(position2)); // Output: 2
    }
}
