package finalprojectdb;

public class Tuple {
	
	String membersAndValues[][];
	
	public void addMembersAndValues(String incomingData[][])
	{
		membersAndValues = new String[incomingData.length][incomingData[0].length];
		
		for(int i=0; i<incomingData.length; i++)
		{
			for(int k=0; k<incomingData[0].length;k++)
			{
				membersAndValues[i][k] = incomingData[i][k];
			}
		}
	}

	public String findMemberValue(String memberName)
	{
		for(int j=0; j<membersAndValues.length; j++)
		{
			if(memberName.equals(membersAndValues[j][0]))
			{
				System.out.println("Checking if "+memberName+" and "+membersAndValues[j][0]+" are equal");
				return membersAndValues[j][1];
			}
		}
		
		return "not found";
	}
	
}
