package finalprojectdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFileReader {
	
	public String[] getListOfNames()
	{
		String result[];
		
		Scanner sc=null;
		try{
			sc = new Scanner(new File("C:/Users/JoseMichael/Desktop/Final Proj Stuff/listofcomps.txt"));
		}
		catch(FileNotFoundException e){
			
		}
		
		//code to get number of lines
		Scanner sb = null;
		try{
			sb = new Scanner(new File("C:/Users/JoseMichael/Desktop/Final Proj Stuff/listofcomps.txt"));
		}
		catch(FileNotFoundException e){
			
		}
		int count=0;
		while(sb.hasNextLine())
		{
			sb.nextLine();
			count++;
		}
		
		result = new String[count];
		
		int iter=0;
		while(sc.hasNextLine())
		{
			//System.out.println(sc.nextLine());
			result[iter] = sc.nextLine();
			iter++;
			
		}
		
		sc.close();
		sb.close();
		
		return result;
	}
}
