package finalprojectdb;

import java.util.Random;

public class PredicateGenerator {
	
		
	String listOfNames[] = {"Price","Year"};
	int listOfValues[][] = {{0,1000},{1990,2016}};
	String listOfOperands[] = {"<",">","="};
	
	String predicatesList[][];
	
	
	public String[][] generatePredicates(int noOfPredicates)
	{
	
		predicatesList = new String[noOfPredicates][4];
		
		Random r = new Random();
		
		for(int i=0; i<noOfPredicates; i++)
		{
			int randomInt = r.nextInt(listOfNames.length);
			
			int valueRange = listOfValues[randomInt][1]-listOfValues[randomInt][0];
			int randomVal = r.nextInt(valueRange);
			int value = listOfValues[randomInt][0] + randomVal;
			
			int randomOpVal = r.nextInt(listOfOperands.length);
			String randomOp = listOfOperands[randomOpVal];
			
//			System.out.println(listOfNames[randomInt]+" "+value+" int "+randomOp);
			
			predicatesList[i][0] = listOfNames[randomInt];
			predicatesList[i][1] = Integer.toString(value);
			predicatesList[i][2] = "int";
			predicatesList[i][3] = randomOp;
			
		}
		
//		System.out.println("Printing list ");
//		for(int k=0; k<predicatesList.length; k++)
//		{
//			for(int l=0; l<predicatesList[k].length; l++)
//				System.out.print(predicatesList[k][l]+" ");
//			System.out.println();
//		}
		
		return predicatesList;
			
	}
	
	public static void main(String args[])
	{
		PredicateGenerator p = new PredicateGenerator();
		p.generatePredicates(100);
	}
		
}
