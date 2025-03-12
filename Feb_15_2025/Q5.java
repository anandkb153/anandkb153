package Feb_15_2025;

import java.util.HashSet;

public class Q5 {
    public static boolean buddyStrings(String s, String goal) {

        // If lengths are different, return false
        if (s.length() != goal.length()) return false;

        // If strings are already equal, check for duplicate characters
        if (s.equals(goal)) {
            HashSet<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) {
                if (set.contains(c)) return true;  // Found a duplicate character
                set.add(c);
            }
            return false;  // No duplicate characters found
        }

        // Find mismatched positions
        int first = -1, second = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (first == -1) {
                    first = i;
                } else if (second == -1) {
                    second = i;
                } else {
                    return false;  // More than two mismatches
                }
            }
        }

        // Check if swapping makes them equal
        return second != -1 && 
               s.charAt(first) == goal.charAt(second) &&
               s.charAt(second) == goal.charAt(first);
    }

    public static void main(String[] args) {
        System.out.println(buddyStrings("bank", "kanb")); 
      
    }
}
