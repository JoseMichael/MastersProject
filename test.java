package finalprojectdb;

import java.util.Arrays;

public class test {

	public static void main(String args[])
	{
		int a[] = {2,3,4};
		int b[];
		b = a;
		a[0] = 9;
		
		System.out.println(Arrays.toString(b));
		
		System.out.println("Test 2 to check diff array sizes");
		
		int c[][] = {{1,2,3},{2,3,4,5}};
		System.out.println(Arrays.toString(c));
		for(int i=0; i<c.length; i++)
		{
			for(int j=0; j<c[i].length; j++)
			{
				System.out.print(c[i][j]);
			}
			System.out.println("");
		}
	}
	
}
