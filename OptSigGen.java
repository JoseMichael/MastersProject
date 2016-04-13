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
	
	ArrayList<MetaDataHolder> eqVals;
	ArrayList<MetaDataHolder> lessVals;
	ArrayList<MetaDataHolder> grtrVals;
	
	ArrayList<ModHashMap> eqHash;
	
	//function to chop incoming data and feed to above data structs
	public void valueDistiller(String incomingQueryFields[][])
	{
		ArrayList<String> list = new ArrayList<String>();
		int initalCounter = 0;
		
//		long startTime = System.nanoTime();
		while(initalCounter!=incomingQueryFields.length)
		{
			if(!list.contains(incomingQueryFields[initalCounter][indexOfElementName]))
			{
				MetaDataHolder eqValsHolder = new MetaDataHolder(incomingQueryFields[initalCounter][indexOfElementName]); 
				MetaDataHolder lessValsHolder = new MetaDataHolder(incomingQueryFields[initalCounter][indexOfElementName]); 
				MetaDataHolder grtrValsHolder = new MetaDataHolder(incomingQueryFields[initalCounter][indexOfElementName]);
				
//				eqValsHolder.add(incomingQueryFields[initalCounter][indexOfElementName]);
//				lessValsHolder.add(incomingQueryFields[initalCounter][indexOfElementName]);
//				grtrValsHolder.add(incomingQueryFields[initalCounter][indexOfElementName]);
				
				for(int iter=initalCounter; iter<incomingQueryFields.length; iter++)
				{
				//checks if condition is "=" and element name is the same as the one being checked
					if(incomingQueryFields[iter][indexOfIntType].equals("=")&&
							(incomingQueryFields[iter][indexOfElementName]==incomingQueryFields[initalCounter][indexOfElementName]))
					{
						//eqValsHolder.add(String.valueOf(iter));
						int address = iter;
						String value = incomingQueryFields[iter][indexOfElementValue];
						AddressAndValue anv = new AddressAndValue(address, value);
						eqValsHolder.addAddressValue(anv);
					}
					else if(incomingQueryFields[iter][indexOfIntType].equals("<")&&
					(incomingQueryFields[iter][indexOfElementName]==incomingQueryFields[initalCounter][indexOfElementName]))
					{
//						lessValsHolder.add(String.valueOf(iter));
						int address = iter;
						String value = incomingQueryFields[iter][indexOfElementValue];
						
						//TODO: Delete debugging junk
//						if(value.equals("ZIOP"))
//							System.out.println("WHAAAAAAAAAAAT");
						
						AddressAndValue anv = new AddressAndValue(address, value);
						lessValsHolder.addAddressValue(anv);
					}
					else if(incomingQueryFields[iter][indexOfIntType].equals(">")&&
					(incomingQueryFields[iter][indexOfElementName]==incomingQueryFields[initalCounter][indexOfElementName]))
					{
//						grtrValsHolder.add(String.valueOf(iter));
						int address = iter;
						String value = incomingQueryFields[iter][indexOfElementValue];
						AddressAndValue anv = new AddressAndValue(address, value);
						grtrValsHolder.addAddressValue(anv);
					}
				
				}
				
				eqVals.add(eqValsHolder);
				lessVals.add(lessValsHolder);
				grtrVals.add(grtrValsHolder);
				
				list.add(incomingQueryFields[initalCounter][indexOfElementName]);
			}
			
			
			
			initalCounter++;
		}
//		long endTime = System.nanoTime();
//		System.out.println("valueDistiller initial part took " + (endTime - startTime) + " nanoseconds");
		
//		startTime = System.nanoTime();
		//TODO:Remove debugging statements
//		System.out.println("Before removing empty shit : ");
//		printMetaDataHolders();
		
		removeEmptyListsFromMetaData();
		
//		System.out.println("After removing empty shit : ");
//		printMetaDataHolders();
//		endTime = System.nanoTime();
//		System.out.println("removeEmptyListsFromMetaData part took " + (endTime - startTime) + " nanoseconds");
		
//		startTime = System.nanoTime();
		sortMetaDataHolders();
//		endTime = System.nanoTime();
//		System.out.println("sortMetaDataHolders part took " + (endTime - startTime) + " nanoseconds");
		
//		startTime = System.nanoTime();
		convertEqValsToEqHash();
//		endTime = System.nanoTime();
//		System.out.println("convertEqValsToEqHash part took " + (endTime - startTime) + " nanoseconds");
	}
	
	//this function is used to remove lists that only contain the name of the element
	public void removeEmptyListsFromMetaData()
	{
		//used for printing val for eqVals
		int eqValSize = eqVals.size();
		for(int i=0; i<eqValSize; i++)
		{
			MetaDataHolder element = eqVals.get(i);
			if(element.isListEmpty())
			{
				eqVals.remove(i);
				i--;
				eqValSize--;
			}
		}
		
		int lessValsSize = lessVals.size();
		for(int i=0; i<lessValsSize; i++)
		{
			MetaDataHolder element = lessVals.get(i);
			if(element.isListEmpty())
			{
				lessVals.remove(i);
				i--;
				lessValsSize--;
			}
		}
		
		int grtrValsSize = grtrVals.size();
		for(int i=0; i<grtrValsSize; i++)
		{
			MetaDataHolder element = grtrVals.get(i);
			if(element.isListEmpty())
			{
				grtrVals.remove(i);
				i--;
				grtrValsSize--;
			}
		}
		
		//TODO: Remove the junk below; used only for debugging
//		for(int i=0; i<grtrVals.size(); i++)
//		{
//			MetaDataHolder element = grtrVals.get(i);
//			element.printList();
//		}
		
	}
	
	//this function is used to show the values of eqVals lessvals and grtrvals
	public void printMetaDataHolders()
	{
		//used for printing val for eqVals
		System.out.println("Printing eqvals ");
		
		for(int i=0; i<eqVals.size(); i++)
		{
			MetaDataHolder element = eqVals.get(i);
			element.printList();
		}
		
		System.out.println();
		
		System.out.println("Printing lessVals ");
//		for(int i=0; i<lessVals.size(); i++)
//		{
//			ArrayList<String> element = lessVals.get(i);
//			for(int j=0; j<element.size(); j++)
//			{
//				System.out.print(element.get(j)+" ");
//			}
//		}
		
		for(int i=0; i<lessVals.size(); i++)
		{
			MetaDataHolder element = lessVals.get(i);
			element.printList();
		}
		
		
		System.out.println();
		
		System.out.println("Printing grtrVals ");
//		for(int i=0; i<grtrVals.size(); i++)
//		{
//			ArrayList<String> element = grtrVals.get(i);
//			for(int j=0; j<element.size(); j++)
//			{
//				System.out.print(element.get(j)+" ");
//			}
//		}
		
		for(int i=0; i<grtrVals.size(); i++)
		{
			MetaDataHolder element = grtrVals.get(i);
			element.printList();
		}
		
	}
	
	//this function is used to sort the values of lessvals and grtrvals
	public void sortMetaDataHolders()
	{
//		System.out.println("Sorting lessVals ");
//		long startTime = System.nanoTime();
		for(int i=0; i<lessVals.size(); i++)
		{
			MetaDataHolder element = lessVals.get(i);
			element.sortValues();
		}
//		long endTime = System.nanoTime();
//		System.out.println("lessVals sorting took " + (endTime - startTime) + " nanoseconds");
		
//		System.out.println("Sorting lessVals ");
		for(int i=0; i<grtrVals.size(); i++)
		{
//			ArrayList<String> element = grtrVals.get(i);
//			element = sortArrayList(element);
//			grtrVals.set(i, element);
			
			MetaDataHolder element = grtrVals.get(i);
			element.sortValues();
		}
		
	}
	
	//this function is used to convert the arraylist of arraylist of strings into
	//arraylist of ModHashMaps
	public void convertEqValsToEqHash()
	{
		eqHash = new ArrayList<ModHashMap>();
		
		for(int i=0; i<eqVals.size(); i++)
		{
			MetaDataHolder element = eqVals.get(i);
			
			ModHashMap m = new ModHashMap(element.elementName);
			for(int j=0; j<element.listOfValues.size(); j++)
			{
//				String key = queryFieldsToCheck[Integer.parseInt(element.get(j))][indexOfElementValue];
//				int value = Integer.parseInt(element.get(j));
//				m.addToHash(key, value);
				
				//key is the value of the element and value is the position of the sign bit
				AddressAndValue adv = element.listOfValues.get(j);
				String key = adv.value;
				int value = adv.address;
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
		
	public void resetMetaDataArrays()
	{
		eqVals = new ArrayList<MetaDataHolder>();
		lessVals = new ArrayList<MetaDataHolder>();
		grtrVals = new ArrayList<MetaDataHolder>();
		
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
	
	public int[] returnSignBits()
	{
		return signGenerated;
	}
	
	//this function is used to trigger the signature generation as a whole
	public void triggerSignatureSet(Tuple t)
	{
		//triggering greater than vals
//		long startTime = System.nanoTime();
//		for(int i=0; i<grtrVals.size(); i++)
//		{
//			ArrayList<String> element = grtrVals.get(i);
//			String elementNameToSearch = element.get(0);
//			int value = Integer.parseInt(t.findMemberValue(elementNameToSearch));
//			binarySearchOnElement(element, value, 2);
//		}
		for(int i=0; i<grtrVals.size(); i++)
		{
			MetaDataHolder element = grtrVals.get(i);
			String elementNameToSearch = element.elementName;
			int value = Integer.parseInt(t.findMemberValue(elementNameToSearch));
			
			
			//TODO: Del dbg commands
//			long startTime = System.nanoTime();
			
			binarySearchOnElement(element, value, 2);
			
//			long endTime = System.nanoTime();
//			System.out.println("Binary Search took " + (endTime - startTime) + " nanoseconds");
			
			
		}
//		long endTime = System.nanoTime();
//		System.out.println("grtrVals binary search took " + (endTime - startTime) + " nanoseconds");
		
		//trigger lesser than vals
//		startTime = System.nanoTime();
//		for(int i=0; i<lessVals.size(); i++)
//		{
//			ArrayList<String> element = lessVals.get(i);
//			String elementNameToSearch = element.get(0);
//			int value = Integer.parseInt(t.findMemberValue(elementNameToSearch));
//			binarySearchOnElement(element, value, 1);
//		}
		for(int i=0; i<lessVals.size(); i++)
		{
			MetaDataHolder element = lessVals.get(i);
			String elementNameToSearch = element.elementName;
			
			//TODO:Delete these debugging junk
//			if(elementNameToSearch.equals("Name"))
//				System.out.println("WHAT THE FUCK IS HAPPENINGGGGGGGGGGGGGGGG");
			
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
		ArrayList<Integer> locationOfSignBit = m.findKey(value);
		if(locationOfSignBit!=null)
		{
			//key exists in the hash
			for(int i=0; i<locationOfSignBit.size(); i++)
			{
				int address = locationOfSignBit.get(i);
				signGenerated[address] = 1;
			}
		}
	}
	
	//this function would do binary search and set the signatures accordingly
	public void binarySearchOnElement(MetaDataHolder element, int value, int mode)
	{
		//this function has two modes
		//1 : for values lesser than
		//2 : for values greater than
		
		int first = 0;
		int last = element.listOfValues.size()-1;
		int middle = (last+first)/2;
		
		while(first<=last)
		{
			//int valOfMiddlePred = Integer.parseInt(queryFieldsToCheck[Integer.parseInt(element.get(middle))][indexOfElementValue]);
			AddressAndValue anv = element.listOfValues.get(middle);
			int valOfMiddlePred = Integer.parseInt(anv.value);
//			System.out.println("Middle value is "+valOfMiddlePred);
			if(valOfMiddlePred<value)
			{
				//value is in the lower half, need to set all signatures prior
				
//				long startTime = System.nanoTime();
				
				if(mode==2)
				{
					//setting signs for values greater than
					setSignBits(element, first, middle, 1);
				}
				
				first = middle + 1;
				
//				long endTime = System.nanoTime();
//				System.out.println("Time in upper part is : "+(endTime-startTime));
			}
			else if(valOfMiddlePred == value)
			{
				//need to add condition here to check if multiple matches exist
				//need to find if upper elements and lower elements are same as middle
				
				//TODO: Delete start and end time counters
//				long startTime = System.nanoTime();
				
				int upper = middle-1;
				if(!(upper<first))
				{
					//this flag checks if the upper crosses first
					int firstCrossFlag=0;
					AddressAndValue anvOfUpper = element.listOfValues.get(upper);
					int valOfUpperPred = Integer.parseInt(anvOfUpper.value);
					while(valOfUpperPred==valOfMiddlePred)
					{
						if(upper-1!=-1)
							upper--;
						else
						{
							firstCrossFlag = 1;
							break;
						}
						//valOfMiddlePred = valOfUpperPred;
						anvOfUpper = element.listOfValues.get(upper);
						valOfUpperPred = Integer.parseInt(anvOfUpper.value);
						
					}
					if(firstCrossFlag!=1)
						upper++;
				}
				else
				{
					upper=middle;
				}
				
				
				int lower = middle+1;
				if(!(lower>last))
				{
					//this flag checks if the lower crosses last
					int lastCrossFlag=0;
					AddressAndValue anvOfLower = element.listOfValues.get(lower);
					int valOfLower = Integer.parseInt(anvOfLower.value);
					while(valOfLower==valOfMiddlePred)
					{
						if(!(lower+1>last))
							lower++;
						else
						{
							lastCrossFlag=1;
							break;
						}
						valOfMiddlePred = valOfLower;
						anvOfLower = element.listOfValues.get(lower);
						valOfLower = Integer.parseInt(anvOfLower.value);
						
					}
					if(lastCrossFlag!=1)
						lower--;
				}
				else
				{
					lower = middle;
				}
				
				//value found
				if(mode==1)
				{
					setSignBits(element,lower+1,last,1);
				}
				else if(mode==2)
				{
					setSignBits(element,first,upper-1,1);
				}
				
				
				
//				long endTime = System.nanoTime();
//				System.out.println("Time in middle part is : "+(endTime-startTime));
				
				break;
			}
			else
			{
				//value is in the upper half, need to set signatures prior
//				long startTime = System.nanoTime();
				if(mode==1)
				{
					setSignBits(element, middle, last, 1);
				}				
				last = middle-1;
//				long endTime = System.nanoTime();
//				System.out.println("Time in botton part is : "+(endTime-startTime));
			}
			
			middle = (last+first)/2;
		}
	}
	
	//this function is used to mass set signature values
	public void setSignBits(MetaDataHolder element, int first, int last, int mode)
	{
		//mode options
		//1 : sets sign to 1
		//0 : sets sign to 0
		
		if(first>last)
			return;
		
		for(int i=first; i<=last; i++)
		{
			int indexOfSign = element.listOfValues.get(i).address;
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
