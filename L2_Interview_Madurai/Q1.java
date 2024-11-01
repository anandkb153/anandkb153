package L2_Interview;

import java.util.Arrays;
import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter arr size");
		int size=sc.nextInt();
		
		
		int arr[]=new int[size];
		System.out.println("Enter "+ size +" values");
		for(int i=0;i<size;i++)
		{
			arr[i]=sc.nextInt();
		}
		
//		System.out.println(Arrays.toString(arr));
		
		boolean is_incre=true;
		boolean is_decre=true;
		
		
		if(arr.length>1)
		{
		
			int i=0;
			while(i<arr.length-1)
			{
				if(arr[i]<arr[i+1] && is_incre) // to check all number are incre order
					is_decre=false;
				else if(arr[i]>arr[i+1] && is_decre )
				{
					
					is_incre=false;
				}
				else if( is_incre && !is_decre && arr[i]!=arr[i+1] || !is_incre && is_decre && arr[i]!=arr[i+1] )
					{
					is_incre=false;
					is_decre=false;
					break;
					}
				else
				{
					i++;// skip same value
					
					continue;
				}
				i++;// normal increment
			}
			
			
//			System.out.println(is_decre+" "+is_incre);
			
			if(is_incre && is_decre)
				System.out.println("Output = False ");
			else if(is_incre || is_decre )
				System.out.println("Output = True");
			else if(is_decre== false && is_incre==false || is_decre )
				System.out.println("Output = False ");
			
		}
		else
			System.out.println("Output = False ");
			
	}
}
