package Interview_Programs;

import java.util.Stack;

public class Solution {

	public boolean isValid(String s) {

		Stack<Character> stack_obj = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
				stack_obj.push(s.charAt(i));
			} else {
				if (stack_obj.isEmpty())
					return false;
				else if (s.charAt(i) == '}') {
					if (stack_obj.peek() == '{')

						stack_obj.pop();

					else
						return false;
				} else if (s.charAt(i) == ')') {
					if (stack_obj.peek() == '(')

						stack_obj.pop();

					else
						return false;
				} else if (s.charAt(i) == ']') {
					if (stack_obj.peek() == '[')

						stack_obj.pop();

					else
						return false;
				}
			}

		}
		if (stack_obj.size() == 0)
			return true;
		else
			return false;

	}

	public static void main(String[] args) {
		Solution obj = new Solution();
		System.out.println(obj.isValid("({[})]"));
	}
}
