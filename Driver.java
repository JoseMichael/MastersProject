package finalprojectdb;

public class Driver {
	
	public static void main(String args[])
	{
		String tupleValues[][] = {{"Name","Apple"},{"Price","100"}};
		String valuesToFind[][] = {{"Name","Apple","String","="},{"Price","90","int",">"}};
		
		Tuple t = new Tuple();
		NaiveSigGen nsg = new NaiveSigGen();
		
		t.addMembersAndValues(tupleValues);
		nsg.initializeQueryFieldsToCheck(valuesToFind);
		int sign[] = nsg.generateSig(t);
		System.out.println("Sign values are ");
		for(int i=0; i<sign.length;i++)
		{
			System.out.println(sign[i]);
		}
	}

}
