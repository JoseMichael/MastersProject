package finalprojectdb;

import java.util.ArrayList;

public class OptSigGen {
	
	String queryFieldsToCheck[][];
	
	int signGenerated[];
	
	int indexOfElementName = 0;
	int indexOfDataType = 2;
	int indexOfIntType = 3;
	int indexOfElementValue = 1;
	
	//init data structs to hold < = and > data
//	String eqVals[][];
//	String lessVals[][];
//	String grtrVals[][];
	
	ArrayList<ArrayList<String>> eqVals;
	ArrayList<ArrayList<String>> lessVals;
	ArrayList<ArrayList<String>> grtrVals;
	
	ArrayList<ModHashMap> eqHash;
	
	//function to chop incoming data and feed to above data structs
	public void valueDistiller(String incomingQueryFields[][])
	{
		//find unique number of elements in incomingQueryFields
//		int initSize = findUniqueElements(incomingQueryFields);
//		eqVals = new String[initSize][incomingQueryFields.length];
//		lessVals = new String[initSize][incomingQueryFields.length];
//		grtrVals = new String[initSize][incomingQueryFields.length];
		
//		int eqCount=0, lessCount=0, grtrCount=0;
		
		ArrayList<String> list = new ArrayList<String>();
		int initalCounter = 0;
		
		long startTime = System.nanoTime();
		while(initalCounter!=incomingQueryFields.length)
		{
			if(!list.contains(incomingQueryFields[initalCounter][indexOfElementName]))
			{
				//if element has not already been processed
				
//				int eqCount2=-1, lessCount2=-1, grtrCount2=-1; 
				
				ArrayList<String> eqValsHolder = new ArrayList<String>(); 
				ArrayList<String> lessValsHolder = new ArrayList<String>(); 
				ArrayList<String> grtrValsHolder = new ArrayList<String>();
				
				eqValsHolder.add(incomingQueryFields[initalCounter][indexOfElementName]);
				lessValsHolder.add(incomingQueryFields[initalCounter][indexOfElementName]);
				grtrValsHolder.add(incomingQueryFields[initalCounter][indexOfElementName]);
				
				for(int iter=initalCounter; iter<incomingQueryFields.length; iter++)
				{
				//checks if condition is "=" and element name is the same as the one being checked
					if(incomingQueryFields[iter][indexOfIntType].equals("=")&&
							(incomingQueryFields[iter][indexOfElementName]==incomingQueryFields[initalCounter][indexOfElementName]))
					{
						eqValsHolder.add(String.valueOf(iter));
					}
					else if(incomingQueryFields[iter][indexOfIntType].equals("<")&&
					(incomingQueryFields[iter][indexOfElementName]==incomingQueryFields[initalCounter][indexOfElementName]))
					{
						lessValsHolder.add(String.valueOf(iter));
					}
					else if(incomingQueryFields[iter][indexOfIntType].equals(">")&&
					(incomingQueryFields[iter][indexOfElementName]==incomingQueryFields[initalCounter][indexOfElementName]))
					{
						grtrValsHolder.add(String.valueOf(iter));
					}
				
				}
				
				eqVals.add(eqValsHolder);
				lessVals.add(lessValsHolder);
				grtrVals.add(grtrValsHolder);
				
				list.add(incomingQueryFields[initalCounter][indexOfElementName]);
			}
			
			
			
			initalCounter++;
		}
		long endTime = System.nanoTime();
		System.out.println("valueDistiller initial part took " + (endTime - startTime) + " nanoseconds");
		
		startTime = System.nanoTime();
		removeEmptyListsFromMetaData();
		endTime = System.nanoTime();
		System.out.println("removeEmptyListsFromMetaData part took " + (endTime - startTime) + " nanoseconds");
		
		startTime = System.nanoTime();
		sortMetaDataHolders();
		endTime = System.nanoTime();
		System.out.println("sortMetaDataHolders part took " + (endTime - startTime) + " nanoseconds");
		
		startTime = System.nanoTime();
		convertEqValsToEqHash();
		endTime = System.nanoTime();
		System.out.println("convertEqValsToEqHash part took " + (endTime - startTime) + " nanoseconds");
	}
	
	//this function is used to remove lists that only contain the name of the element
	public void removeEmptyListsFromMetaData()
	{
		//used for printing val for eqVals
		for(int i=0; i<eqVals.size(); i++)
		{
			ArrayList<String> element = eqVals.get(i);
			if(element.size()==1)
				eqVals.remove(i);
		}
		
		for(int i=0; i<lessVals.size(); i++)
		{
			ArrayList<String> element = lessVals.get(i);
			if(element.size()==1)
				lessVals.remove(i);
		}
		
		for(int i=0; i<grtrVals.size(); i++)
		{
			ArrayList<String> element = grtrVals.get(i);
			if(element.size()==1)
				grtrVals.remove(i);
		}
		
	}
	
	//this function is used to show the values of eqVals lessvals and grtrvals
	public void printMetaDataHolders()
	{
		//used for printing val for eqVals
		System.out.println("Printing eqvals ");
		for(int i=0; i<eqVals.size(); i++)
		{
			ArrayList<String> element = eqVals.get(i);
			for(int j=0; j<element.size(); j++)
			{
				System.out.print(element.get(j)+" ");
			}
		}
		
		System.out.println();
		
		System.out.println("Printing lessVals ");
		for(int i=0; i<lessVals.size(); i++)
		{
			ArrayList<String> element = lessVals.get(i);
			for(int j=0; j<element.size(); j++)
			{
				System.out.print(element.get(j)+" ");
			}
		}
		
		System.out.println();
		
		System.out.println("Printing grtrVals ");
		for(int i=0; i<grtrVals.size(); i++)
		{
			ArrayList<String> element = grtrVals.get(i);
			for(int j=0; j<element.size(); j++)
			{
				System.out.print(element.get(j)+" ");
			}
		}
	}
	
	//this function is used to sort the values of lessvals and grtrvals
	public void sortMetaDataHolders()
	{
//		System.out.println("Sorting lessVals ");
		long startTime = System.nanoTime();
		for(int i=0; i<lessVals.size(); i++)
		{
			ArrayList<String> element = lessVals.get(i);
			element = sortArrayList(element);
			lessVals.set(i, element);
		}
		long endTime = System.nanoTime();
		System.out.println("lessVals sorting took " + (endTime - startTime) + " nanoseconds");
		
//		System.out.println("Sorting lessVals ");
		for(int i=0; i<grtrVals.size(); i++)
		{
			ArrayList<String> element = grtrVals.get(i);
			element = sortArrayList(element);
			grtrVals.set(i, element);
		}
		
	}
	
	//this function is used to sort the individual arraylists using the values in it
	//as index to the query received
	public ArrayList<String> sortArrayList(ArrayList<String> element)
	{
		for(int i=1; i<element.size()-1;i++)
		{
			for(int j=i+1; j<element.size(); j++)
			{
				int valOfi = Integer.parseInt(queryFieldsToCheck[Integer.parseInt(element.get(i))][indexOfElementValue]);
				int valOfj = Integer.parseInt(queryFieldsToCheck[Integer.parseInt(element.get(j))][indexOfElementValue]);
				
				if(valOfi>valOfj)
				{
					//need to switch i and j
					int temp = Integer.parseInt(element.get(i));
					element.set(i, element.get(j));
					element.set(j, Integer.toString(temp));
				}
			}
		}
		
		return element;
	}
	
	//this function is used to convert the arraylist of arraylist of strings into
	//arraylist of ModHashMaps
	public void convertEqValsToEqHash()
	{
		eqHash = new ArrayList<ModHashMap>();
		
		for(int i=0; i<eqVals.size(); i++)
		{
			ArrayList<String> element = eqVals.get(i);
			
			ModHashMap m = new ModHashMap(element.get(0));
			for(int j=1; j<element.size(); j++)
			{
				String key = queryFieldsToCheck[Integer.parseInt(element.get(j))][indexOfElementValue];
				int value = Integer.parseInt(element.get(j));
				m.addToHash(key, value);
			}
			
			eqHash.add(m);
		}
	}
	
	public void printEqHash()
	{
		for(int i=0; i<eqHash.size(); i++)
		{
			ModHashMap p = eqHash.get(i);
			System.out.println("Element Name : "+p.elementName);
			p.printHashMap();
		}
	}
	
	//function to find number of unique elements
	public int findUniqueElements(String incomingQueryFields[][])
	{
		ArrayList<String> list = new ArrayList<String>();
		int count=0;
		
		for(int i=0; i<incomingQueryFields.length; i++)
		{
			if(!list.contains(incomingQueryFields[i][indexOfElementName]))
			{
				list.add(incomingQueryFields[i][indexOfElementName]);
				count++;
			}
		}
		
		return count;
	}
	
	public void resetMetaDataArrays()
	{
		eqVals = new ArrayList<ArrayList<String>>();
		lessVals = new ArrayList<ArrayList<String>>();
		grtrVals = new ArrayList<ArrayList<String>>();
		
	}
	
	//derived from NSG; used to initialize array that holds all values to check
	//NOT-REDUNDANT
	public void initializeQueryFieldsToCheck(String incomingQueryFields[][])
	{
		resetMetaDataArrays();
		
		queryFieldsToCheck = new String[incomingQueryFields.length][incomingQueryFields[0].length];
		//System.arraycopy(incomingQueryFields, 0, queryFieldsToCheck, 0, incomingQueryFields.length);
		for(int k=0; k<incomingQueryFields.length;k++)
		{
			for(int h=0; h<incomingQueryFields[k].length;h++)
			{
				queryFieldsToCheck[k][h] = incomingQueryFields[k][h];
			}
		}
//		signGenerated = new int[incomingQueryFields.length];
		initializeSignBits(incomingQueryFields.length);
	}

	//used to init signGenerated and set default values to -1
	public void initializeSignBits(int length)
	{
		signGenerated = new int[length];
		for(int i=0; i<length; i++)
			signGenerated[i] = 0;
	}
	
	//function used to print sign bits
	public void printSignBits()
	{
		System.out.println();
		System.out.println("Printing sign");
		
		for(int i=0;i<signGenerated.length; i++)
		{
			System.out.print(signGenerated[i]+" ");
		}
		System.out.println();
	}
	
	//TODO: Delete this
	public void randomDriverFunction()
	{
		ArrayList<String> element = grtrVals.get(0);
		binarySearchOnElement(element,120,2);
		printSignBits();
	}
	
	//this function is used to trigger the signature generation as a whole
	public void triggerSignatureSet(Tuple t)
	{
		//triggering greater than vals
//		long startTime = System.nanoTime();
		for(int i=0; i<grtrVals.size(); i++)
		{
			ArrayList<String> element = grtrVals.get(i);
			String elementNameToSearch = element.get(0);
			int value = Integer.parseInt(t.findMemberValue(elementNameToSearch));
			binarySearchOnElement(element, value, 2);
		}
//		long endTime = System.nanoTime();
//		System.out.println("grtrVals binary search took " + (endTime - startTime) + " nanoseconds");
		
		//trigger lesser than vals
//		startTime = System.nanoTime();
		for(int i=0; i<lessVals.size(); i++)
		{
			ArrayList<String> element = lessVals.get(i);
			String elementNameToSearch = element.get(0);
			int value = Integer.parseInt(t.findMemberValue(elementNameToSearch));
			binarySearchOnElement(element, value, 1);
		}
//		endTime = System.nanoTime();
//		System.out.println("lessVals binary search took " + (endTime - startTime) + " nanoseconds");
		
//		startTime = System.nanoTime();
		for(int i=0; i<eqHash.size(); i++)
		{
			ModHashMap element = eqHash.get(i);
			String elementNameToSearch = element.elementName;
			String value = t.findMemberValue(elementNameToSearch);
			hashSearchEqVals(element,value);
		}
//		endTime = System.nanoTime();
//		System.out.println("eqHash comparison took " + (endTime - startTime) + " nanoseconds");
	}
	
	//this function would search the hash for eqVals
	public void hashSearchEqVals(ModHashMap m, String value)
	{
		int locationOfSignBit = m.findKey(value);
		if(locationOfSignBit!=-1)
		{
			//key exists in the hash
			signGenerated[locationOfSignBit] = 1;
		}
	}
	
	//this function would do binary search and set the signatures accordingly
	public void binarySearchOnElement(ArrayList<String> element, int value, int mode)
	{
		//this function has two modes
		//1 : for values lesser than
		//2 : for values greater than
		
		//TODO: implement mode for when VALUE is found
		int first = 1;
		int last = element.size()-1;
		int middle = (last+first)/2;
		
		while(first<=last)
		{
			int valOfElement = Integer.parseInt(queryFieldsToCheck[Integer.parseInt(element.get(middle))][indexOfElementValue]);
			if(valOfElement<value)
			{
				//value is in the lower half, need to set all signatures prior
				
				if(mode==1)
				{
					//setting signs for values lesser than
//					setSignBits(element,first,middle,0);
				}
				else if(mode==2)
				{
					//setting signs for values greater than
					setSignBits(element, first, middle, 1);
				}
				
				first = middle + 1;
			}
			else if(valOfElement == value)
			{
				//value found
				//TODO: implement the modes for this case
				if(mode==1)
				{
					setSignBits(element,middle+1,last,1);
//					setSignBits(element,first,middle,0);
				}
				else if(mode==2)
				{
//					setSignBits(element,middle,last,0);
					setSignBits(element,first,middle-1,1);
				}
				
				break;
			}
			else
			{
				//value is in the upper half, need to set signatures prior
				
				if(mode==1)
				{
					setSignBits(element, middle, last, 1);
				}
				else if(mode==2)
				{
//					setSignBits(element, middle, last, 0);
				}
				
				last = middle-1;
			}
			
			middle = (last+first)/2;
		}
	}
	
	//this function is used to mass set signature values
	public void setSignBits(ArrayList<String> element, int first, int last, int mode)
	{
		//mode options
		//1 : sets sign to 1
		//0 : sets sign to 0
		
		if(first>last)
			return;
		
		for(int i=first; i<=last; i++)
		{
			int indexOfSign = Integer.parseInt(element.get(i));
			signGenerated[indexOfSign] = mode;
		}
	}
	
	public int[] generateSig(Tuple t)
	{
		for(int i=0; i<queryFieldsToCheck.length; i++)
		{
			String valueOfMember = t.findMemberValue(queryFieldsToCheck[i][0]);
			if(!valueOfMember.equals("not found"))
			{
				//if its a string then definitive search
				if(queryFieldsToCheck[i][indexOfDataType].equals("String"))
				{
					if(valueOfMember.equals(queryFieldsToCheck[i][1]))
					{
						signGenerated[i] = 1;
					}
					else
					{
						signGenerated[i] = 0;
					}
				}
				else if(queryFieldsToCheck[i][indexOfDataType].equals("int"))
				{
					System.out.println("Inside int checker");
					switch(queryFieldsToCheck[i][indexOfIntType])
					{
					case "<":
						
						System.out.println("Inside case <");
					
						int val = Integer.parseInt(valueOfMember);
						int refVal = Integer.parseInt(queryFieldsToCheck[i][1]);
						
						if(val<refVal)
							signGenerated[i] = 1;
						else
							signGenerated[i] = 0;
						
						
						break;
					case ">":
						
						System.out.println("Inside case >");
						
						int val2 = Integer.parseInt(valueOfMember);
						int refVal2 = Integer.parseInt(queryFieldsToCheck[i][1]);
						
						if(val2>refVal2)
							signGenerated[i] = 1;
						else
							signGenerated[i] = 0;
						
						break;
					
					case "=":
						
						if(valueOfMember.equals(queryFieldsToCheck[i][1]))
						{
							signGenerated[i] = 1;
						}
						else
						{
							signGenerated[i] = 0;
						}
						
						break;
					}
				}
				

			}
			else
			{
				signGenerated[i] = -1;
			}
		}
		return signGenerated;
	}

}
