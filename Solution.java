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
	//zoho may 4 asked questions
	
	
	public static void main(String[] args) {
		
		
		// Problem 1
		int Problem_1 []= {1,2,3,4,10};
		System.out.println("Output = "+find_distance(Problem_1));
		//Problem 2:
		int Problem_2[]= {10,20,20,10,10,30,50,10,20};
		System.out.println("Output = "+Find_pair_of_Sock(Problem_2));
		
		//Problem 3:
		 double input = 6.00;  // Example input
	     System.out.println("Output: " +  toMixedFraction(input));  
		
	    //Problem 4:
	     String S ="abcac";
	     int N=10;
	     
	     System.out.println("Output = "+find_A_count_In_Infinite_String(S,N));
	     
	     //Problem 5:
	     String a1 = "Set";
	     String b1 = "Sangeet";
	     System.out.println(isSubsequence(a1, b1));
	     
	
		
		
	}
	 public static String isSubsequence(String a, String b) {
	        int pointerA = 0;
	        int pointerB = 0;
	        
	        while (pointerA < a.length() && pointerB < b.length()) {
	            if (a.charAt(pointerA) == b.charAt(pointerB)) {
	                pointerA++;
	            }
	            pointerB++;
	        }
	        
	        if (pointerA == a.length()) {
	            return "YES";
	        } else {
	            return "NO";
	        }
	 }
	
	 private static long find_A_count_In_Infinite_String(String s, int n) {
		// TODO Auto-generated method stub
		 
		 long A_count=0;
		 
		 for(char ch:s.toCharArray())
		 {
			 if(ch=='a') A_count++;
		 }
		 
		 // Calculate the number of full repetitions of S in N characters
		 long fullRepetitions = n / s.length(); //
		 
		 // Calculate the number of 'a' characters in the full repetitions
		  A_count *= fullRepetitions;

	        // Calculate the remaining characters after the full repetitions
	        int remainingCharacters = n % s.length();
	        
	        // Count 'a' in the remaining part
	        for (int i = 0; i < remainingCharacters; i++) {
	            if (s.charAt(i) == 'a') {
	               A_count++;
	            }
	        }

	      
		return A_count;
	}

	public static String toMixedFraction(double number) {
	        // Get the integer part
	        int wholePart = (int) number;
	        
	        // Get the fractional part
	        double fractionalPart = number - wholePart;
	        
	        // If there's no fractional part, simply return the integer part as string
	        if (fractionalPart == 0) {
	            return String.valueOf(wholePart);
	        }

	        // Convert the fractional part to a fraction dynamically
	        int denominator = 1;
//	        System.out.println(Math.floor(fractionalPart));
	        while (fractionalPart != Math.floor(fractionalPart)) {
	            fractionalPart *= 10;
	            denominator *= 10;
//	        System.out.println(Math.floor(fractionalPart));
	        }
	        int numerator = (int) fractionalPart;

	        // Simplify the fraction
	        int gcd = gcd(numerator, denominator);
	        numerator /= gcd;
	        denominator /= gcd;
	        
	        // Form the mixed fraction representation
	        return wholePart + " " + numerator + "/" + denominator;
	    }

	    // Helper function to compute the GCD (Greatest Common Divisor) using Euclidean algorithm
	    public static int gcd(int a, int b) {
	        while (b != 0) {
	            int temp = b;
	            b = a % b;
	            a = temp;
	        }
	        return a;
	    }

	private static int Find_pair_of_Sock(int[] arr) {
		// TODO Auto-generated method stub
		
		int pair_result=0;
		Map<Integer,Integer> box = new HashMap<>();
		
		
		for(int i=0;i<arr.length;i++)
		{
			if(box.containsKey(arr[i]))
			{
				box.put(arr[i], box.get(arr[i])+1);
				if(box.get(arr[i])>=2)
				{
					box.put(arr[i], box.get(arr[i])-2);
					pair_result++;
				}
			}
			else
				box.put(arr[i],1);
		}	
		
		return pair_result;
	}

	private static int find_distance(int[] arr) {
		// TODO Auto-generated method stub
		
		int result=Integer.MAX_VALUE;
		Map<Integer,Integer> box = new HashMap<>();
		
		
		for(int i=0;i<arr.length;i++)
		{
			if(box.containsKey(arr[i]))
			{
				int min_index=i-box.get(arr[i]);
				if(min_index<result)
					result=min_index;
				
			}else
				box.put(arr[i], i);
		}
		return (result==Integer.MAX_VALUE)?-1:result;
	}
   
		
	
    
 
}














