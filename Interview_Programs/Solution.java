package Interview_Programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

//{ Driver Code Starts
import java.util.*;
import java.io.*;


class Solution
{
	 public static int characterReplacement(String s, int k) {
	        int[] count = new int[26];
	        int maxCount = 0;
	        int maxLength = 0;

	        int left = 0;
	        for (int right = 0; right < s.length(); right++) {
	            count[s.charAt(right) - 'A']++;
	            maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);

	            // Current window size is right - left + 1
	            // If the number of characters to change is greater than k, shrink the window
	            while ((right - left + 1) - maxCount > k) {
	                count[s.charAt(left) - 'A']--;
	                left++;
	            }

	            // Update the maximum length
	            maxLength = Math.max(maxLength, right - left + 1);
	        }

	        return maxLength;
	    }

	    public static void main(String[] args) {
	        String s = "ADBD";
	        int k = 1;
	        System.out.println(characterReplacement(s, k)); // Output: 4
	    }
	}








