package Feb_15_2025;

import java.util.Scanner;

public class Q2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter pertrol pump count");
		int n=sc.nextInt();// pump count
		int petrol_pump[][]=new int[n][2];
		
		System.out.println("Enter Petrol value & distance ");
		
		for(int i=0;i<n;i++)
		{
			petrol_pump[i][0]=sc.nextInt();//petrol =U
			petrol_pump[i][1]=sc.nextInt();//distance =D
			System.out.println("-----");
		}
		
		int output = find_starting_pertrol_pump(petrol_pump,n);
		
		System.out.println("Start = "+ output +"(Index of the "+(output+1)+"nd petrol pump");

	}

	private static int find_starting_pertrol_pump(int[][] petrol_pump, int P_Count){
		
		int start_index=0;
	    int Bal_Petrol=0;
	    int Petrol =0;
	    int Distance =0;
	    
		for(int i=0;i<P_Count;i++)
		{
		    Petrol += petrol_pump[i][0];//petrol =U
		    Distance += petrol_pump[i][1];//distance =D
		   
		     Bal_Petrol+=petrol_pump[i][0]-petrol_pump[i][1];
		   
		   if(Bal_Petrol < 0)
		   {
			   start_index++;
			   Bal_Petrol=0;
			   continue;
		   }
		   
		   
		}
		return (Petrol>=Distance)? start_index : -1;
	}

}
