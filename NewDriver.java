package finalprojectdb;

public class NewDriver {
	
	public static void main(String args[])
	{
		String tupleValues[][] = {{"Name","Apple"},{"Price","100"}};
		String valuesToFind[][] = {
				{"Name","Apple","String","="},
				{"Price","90","int",">"},
				{"Price","100","int","="},
				{"Price","40","int",">"},
				{"Price","120","int",">"},
				{"Price","60","int","<"},
				{"Price","130","int","="},
				};
		
		Tuple t = new Tuple();
//		NaiveSigGen nsg = new NaiveSigGen();
		OptSigGen osg = new OptSigGen();
		
		t.addMembersAndValues(tupleValues);
//		osg.resetMetaDataArrays();
		osg.initializeQueryFieldsToCheck(valuesToFind);
		osg.valueDistiller(valuesToFind);
		osg.printMetaDataHolders();
		osg.randomDriverFunction();
		osg.convertEqValsToEqHash();
		osg.printEqHash();
//		nsg.initializeQueryFieldsToCheck(valuesToFind);
//		int sign[] = nsg.generateSig(t);
//		System.out.println("Sign values are ");
//		for(int i=0; i<sign.length;i++)
//		{
//			System.out.println(sign[i]);
//		}
	}

}
