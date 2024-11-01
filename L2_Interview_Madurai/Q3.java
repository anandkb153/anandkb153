package L2_Interview;

import java.util.HashMap;
import java.util.Map;

public class Q3 {
    public int leastInterval(char[] tasks, int n) {
        // Step 1: Count the frequency of each task
        Map<Character, Integer> taskCount = new HashMap<>();
        for (char task : tasks) {
            taskCount.put(task, taskCount.getOrDefault(task, 0) + 1);
        }
        
        // Step 2: Find the maximum frequency
        int maxFrequency = 0;
        for (int count : taskCount.values()) {
            maxFrequency = Math.max(maxFrequency, count);
        }
        
        // Step 3: Count how many tasks have the maximum frequency
        int maxCount = 0;
        for (int count : taskCount.values()) {
            if (count == maxFrequency) {
                maxCount++;
            }
        }
        
        // Step 4: Calculate the minimum intervals required
        int intervals = (maxFrequency - 1) * (n + 1) + maxCount;
        
        // Step 5: Return the maximum between the calculated intervals and the length of tasks
        return Math.max(intervals, tasks.length);
    }
    
    public static void main(String[] args) {
        Q3 scheduler = new Q3();
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        
        System.out.println("Minimum intervals required: " + scheduler.leastInterval(tasks, n));
    }
}
