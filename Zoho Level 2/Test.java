package tamilnadu.chennai;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
		System.out.println("Enter  integer number");
		int no1=obj.nextInt();
	    int	n2=no1;
		int n=no1;
		double ams=0;
		int count=0;
		int result;
		
		while(no1>0) 
		{
			
			int rem=no1%10;
			no1=no1/10;
			count++;
		}
		
		System.out.println(count);
		
		for(int i=0;i<count;i++)
		{
			int temp=n%10;
			result=1;
			for(int j=0;j<count;j++)
			{
				result=result*temp;
				
			}
			ams=ams+result;
			n=n/10;
			
		
		}
		System.out.println(ams);
		if(n2==ams)
			System.out.println("Given number is armstrong number");
		else
			System.out.println("Given number is not armstrong number");

		
		
	 	
	}
}

