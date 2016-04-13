package finalprojectdb;

import java.util.Random;

public class PredicateGenerator {
	
	TextFileReader tfr = new TextFileReader();
	String companyNames[] = tfr.getListOfNames();
		
	String listOfNames[] = {"Price","Quantity","Name"};
	int listOfValuesInt[][] = {{0,100000},{0,100000000},{}};
	String listOfValuesString[][] = {{},{},companyNames};
	String listOfOperands[][] = {{"<",">","="},{"<",">","="},{"="}};
	String elementType[] = {"int","int","String"};
//	String listOfNames[] = {"Price"};
//	int listOfValues[][] = {{0,1000000}};
//	String listOfOperands[] = {">"};
	
	String predicatesList[][];
	
	
	public String[][] generatePredicates(int noOfPredicates)
	{
	
		predicatesList = new String[noOfPredicates][4];
		
		Random r = new Random();
		
		for(int i=0; i<noOfPredicates; i++)
		{
			int randomInt = r.nextInt(listOfNames.length);
			
			String value = null;
			
			if(elementType[randomInt].equals("int"))
			{
				int valueRange = listOfValuesInt[randomInt][1]-listOfValuesInt[randomInt][0];
				int randomVal = r.nextInt(valueRange);
				value = Integer.toString(listOfValuesInt[randomInt][0] + randomVal);
			}
			else if(elementType[randomInt].equals("String"))
			{
				int valueRange = listOfValuesString[randomInt].length;
				int randomVal = r.nextInt(valueRange);
				String stringList[] = listOfValuesString[randomInt];
				value = stringList[randomVal];
			}
			
			int randomOpVal = r.nextInt(listOfOperands[randomInt].length);
			String randomOp = listOfOperands[randomInt][randomOpVal];
			
//			System.out.println(listOfNames[randomInt]+" "+value+" int "+randomOp);
			
			predicatesList[i][0] = listOfNames[randomInt];
			predicatesList[i][1] = value;
			predicatesList[i][2] = elementType[randomInt];
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
	/*
	public static void main(String args[])
	{
		PredicateGenerator p = new PredicateGenerator();
		p.generatePredicates(100);
	}
	*/
		
}
