package tamilnadu.chennai;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
  Scanner obj = new Scanner(System.in);
  System.out.println("Enter array size");
  int N=obj.nextInt();  // N= Array size;
  System.out.println("Enter " +N+ "digits array elements");
  int A[]=new int[N];// A= array name;
  
  for(int i=0;i<A.length;i++) 
  {
	  A[i]=obj.nextInt();
  }
  int sum=0;
  int big=0;
  int j=1;
  
  for(int i=0;i<A.length-1;i++)
  {

     
	sum= A[i]+A[j];
      if(big<sum) {
    	  big=sum;
      }
      j++;
		
  }
  
  System.out.println("Output = "+big);
    						
   
   
   
   
	}
}
