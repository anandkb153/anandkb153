package Zoho_Level_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveThreeWordsFragmentDSA {

    // Custom method to extract words manually (using List)
	public static List<String> extractWords(String sentence) {
	    List<String> words = new ArrayList<>();
	    String word = "";  // Work directly with String

	    for (int i = 0; i < sentence.length(); i++) {
	        char currentChar = sentence.charAt(i);
	        
	        if (currentChar == ' ') {
	            if (!word.isEmpty()) {
	                words.add(word);  // Add word to list if it's not empty
	                word = "";  // Reset word for the next word in the sentence
	            }
	        } else {
	            word += currentChar;  // Build the word by appending characters
	        }
	    }
	    
	    // Add the last word if it exists
	    if (!word.isEmpty()) {
	        words.add(word);
	    }

	    return words;
	}

    
    // Method to find the common 3-word fragment in all strings
    public static String findCommonFragment(List<String> s1, List<String> s2, List<String> s3) {
        Set<String> commonFragments = new HashSet<>();
        
        // Check all 3-word fragments in s1
        for (int i = 0; i < s1.size() - 2; i++) {
            String fragment = s1.get(i) + " " + s1.get(i + 1) + " " + s1.get(i + 2);
            commonFragments.add(fragment);
        }
        
        // Retain only fragments found in both s1 and s2
        Set<String> fragmentsInS2 = new HashSet<>();
        for (int i = 0; i < s2.size() - 2; i++) {
            String fragment = s2.get(i) + " " + s2.get(i + 1) + " " + s2.get(i + 2);
            if (commonFragments.contains(fragment)) {
                fragmentsInS2.add(fragment);
            }
        }
        
        // Now check with s3
        for (int i = 0; i < s3.size() - 2; i++) {
            String fragment = s3.get(i) + " " + s3.get(i + 1) + " " + s3.get(i + 2);
            if (fragmentsInS2.contains(fragment)) {
                return fragment; // Return the common 3-word fragment
            }
        }
        return null; // No common fragment found
    }
    
    // Method to remove a fragment from a sentence
    public static String removeFragment(String sentence, String fragment) {
        List<String> words = extractWords(sentence);
        List<String> fragmentWords = extractWords(fragment);
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < words.size(); i++) {
            if (i <= words.size() - 3 && 
                words.get(i).equals(fragmentWords.get(0)) &&
                words.get(i + 1).equals(fragmentWords.get(1)) &&
                words.get(i + 2).equals(fragmentWords.get(2))) {
                i += 2; // Skip the 3 words that match the fragment
            } else {
                result.add(words.get(i));
            }
        }
        return String.join(" ", result); // Join the remaining words back into a sentence
    }

    public static void main(String[] args) {
        String s1 = "Every morning I want to do exercise regularly";
        String s2 = "Every morning I want to do meditation without fail";
        String s3 = "It is important that I want to be happy always";

        List<String> words1 = extractWords(s1);
        List<String> words2 = extractWords(s2);
        List<String> words3 = extractWords(s3);

        String commonFragment = findCommonFragment(words1, words2, words3);

        if (commonFragment != null) {
            System.out.println("Removed fragment = \"" + commonFragment + "\"");
            System.out.println("S1 = \"" + removeFragment(s1, commonFragment) + "\"");
            System.out.println("S2 = \"" + removeFragment(s2, commonFragment) + "\"");
            System.out.println("S3 = \"" + removeFragment(s3, commonFragment) + "\"");
        } else {
            System.out.println("No common fragment found.");
        }
    }
}
