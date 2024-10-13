package Zoho_Level_2;

import java.util.Stack;

public class DecodeString {

    public static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resultStack = new Stack<>();
        String result = "";
        int index = 0;

        while (index < s.length()) {
            // If digit is found, calculate the full number (could be more than one digit)
            if (Character.isDigit(s.charAt(index))) {
                int count = 0;
                while (Character.isDigit(s.charAt(index))) {
                    count = count * 10 + (s.charAt(index) - '0');
                    index++;
                }
                countStack.push(count);  // push the repeat count onto the stack
            }
            // If opening bracket, push the current result string onto resultStack
            else if (s.charAt(index) == '[') {
                resultStack.push(result);
                result = "";  // reset result for new encoded section
                index++;
            }
            // If closing bracket, pop the stacks and form the repeated string
            else if (s.charAt(index) == ']') {
                StringBuilder temp = new StringBuilder(resultStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(result);  // append the repeated part
                }
                result = temp.toString();  // update result
                index++;
            }
            // If it's a letter, just append it to the current result
            else {
                result += s.charAt(index);
                index++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String input1 = "3[a]2[bc]";
        String input2 = "3[a2[c]]";
        String input3 = "2[abc]3[cd]ef";

        System.out.println(decodeString(input1));  // Output: aaabcbc
        System.out.println(decodeString(input2));  // Output: accaccacc
        System.out.println(decodeString(input3));  // Output: abcabccdcdcdef
    }
}
