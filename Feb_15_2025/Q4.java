package Feb_15_2025;

public class Q4 {

	public static int countServers(int[][] grid) {
		int count=0;
		int m=grid.length;
		int n=grid[0].length;
		int row_count[]=new int[m];
		int col_count[]=new int[n];
		
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(grid[i][j]==1)
				{
					row_count[i]++;
					col_count[j]++;
				}
			}
		}
		
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(grid[i][j]==1 && (row_count[i] > 1 || col_count[j] > 1))
				{
					count++;
				}
			}
		}
		return count;
        
    }
	
	public static void main(String[] args) {
		int grid[][] = {{1,1,0,0},
				        {0,0,1,0},
				        {0,0,1,0},
				        {0,0,0,1}};
		
		System.out.println("Output = "+ countServers(grid));
	}

}
