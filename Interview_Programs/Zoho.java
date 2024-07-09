package Interview_Programs;

import java.util.HashSet;
import java.util.Set;

class Zoho {
	private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void Find_Possible_Combination(int[] digits, int index, String str) {

		if (index == digits.length) {

			System.out.println(str);
			return;
		}

		int sum = 0;

		for (int j = index; j <= digits.length - 1; j++) 
		{
			sum = (sum * 10) + digits[j];

			if (sum > 0 && sum <= 26)
				Find_Possible_Combination(digits, j + 1, str + alphabet.charAt(sum - 1));

		}
	}

	public static void findCombinations(int[] digits) {
		if (digits == null || digits.length == 0) {
			System.out.println("No combination ");
			return;
		}

		String str = "";
		Find_Possible_Combination(digits, 0, str);
	}
	
	 public static int lengthOfLongestSubstring(String s) {
	        Set<Character> charSet = new HashSet<>();
	        int n = s.length();
	        int maxLength = 0;
	        int start = 0;

	        for (int end = 0; end < n; end++) {
	            char currentChar = s.charAt(end);
	            while (charSet.contains(currentChar)) {
	                charSet.remove(s.charAt(start));
	                start++;
	            }
	            charSet.add(currentChar);
	            maxLength = Math.max(maxLength, end - start + 1);
	        }

	        return maxLength;
	    }

	public static void main(String[] args) {
//		int[] digits = {1,2,2};
//
//		findCombinations(digits);
		
		String s = "abcabcbb";
	        System.out.println(lengthOfLongestSubstring(s));
	}
}