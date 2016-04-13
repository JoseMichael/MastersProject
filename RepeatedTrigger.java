package finalprojectdb;

public class RepeatedTrigger {
	
	public static void main(String args[])
	{
		int runs=0;
		while(runs<23)
		{
		int count = (int)Math.pow(2,runs);
//		int count = 4194304;
		NewDriver obj = new NewDriver();

		obj.runCode(count);
		
		runs++;
		
		}
		
	}

}
