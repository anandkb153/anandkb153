package tamilnadu.chennai;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
		System.out.println("Enter  array size");
		int N =obj.nextInt();
		System.out.println("Enter" +N + " digit array elements");
		int A[]=new int[N];
		
		for(int i=0;i<N;i++) {
			
			A[i]=obj.nextInt();
			
		}
		System.out.println("Enter Gap value");
		int X=obj.nextInt();
		System.out.println("Enter your output limit");
		int Y=obj.nextInt();
		
		if(Y>N) {
			System.out.println("Enter your limit value between array size");
		}
		else {

			if(N<X) 
			{
				System.out.println("Enter gap value between array size");
				
			}
			
			else {

				for(int i=0;i<Y;i++) 
				{
					int sum=0;
					for(int j=i;j<A.length;j+=X) 
					{
						sum=sum+A[j];
						
						
					
					}
					System.out.println("Output is = "+sum);
					
					
					
				}
				
				
			}
			
			
			
		}
		

		
		
	 	
	}
}
