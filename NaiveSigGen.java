package finalprojectdb;

public class NaiveSigGen {
	
	String queryFieldsToCheck[][];
	
	int signGenerated[];
	int indexOfDataType = 2;
	int indexOfIntType = 3;
	
	public void initializeQueryFieldsToCheck(String incomingQueryFields[][])
	{
		queryFieldsToCheck = new String[incomingQueryFields.length][incomingQueryFields[0].length];
		//System.arraycopy(incomingQueryFields, 0, queryFieldsToCheck, 0, incomingQueryFields.length);
		for(int k=0; k<incomingQueryFields.length;k++)
		{
			for(int h=0; h<incomingQueryFields[k].length;h++)
			{
				queryFieldsToCheck[k][h] = incomingQueryFields[k][h];
			}
		}
		signGenerated = new int[incomingQueryFields.length];
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
