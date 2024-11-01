package L2_Interview;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Q2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter input");
		String input=sc.nextLine();
		
		
		Set<String> li = new HashSet<>();
		
		String bold="";
		
		System.out.println("Enter dictionary word count");
		int count=sc.nextInt();
		
		System.out.println("Enter "+count+" words");
		for(int i=0;i<count;i++)
		{
			
			li.add(sc.next());
		}
		
		System.out.println(input+" "+li);
		
		
		if(li.isEmpty())
			System.out.println(input);
		else
		{
			for(String s : li)
			{
				 bold = "<b>"+s+"</b>";
				input=input.replaceAll(s,bold);
				bold="";
				
			}
		}
		
		
		System.out.println(input);
			
	
		
	}

}
