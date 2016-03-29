package finalprojectdb;

import java.util.ArrayList;
import java.util.Collections;

public class MetaDataHolder {
	
	String elementName;
	
	ArrayList<AddressAndValue> listOfValues;
	
	public MetaDataHolder(String elementName)
	{
		this.elementName = elementName;
		listOfValues = new ArrayList<AddressAndValue>();
	}
	
	public void addAddressValue(AddressAndValue toAdd)
	{
		listOfValues.add(toAdd);
	}
	
	public boolean isListEmpty()
	{
		if(listOfValues.isEmpty())
			return true;
		else
			return false;
	}
	
	public void printList()
	{
		for(int i=0; i<listOfValues.size(); i++)
		{
			AddressAndValue element = listOfValues.get(i);
			System.out.print("Address : "+element.address+" Value : "+element.value);
			
		}
	}
	
	
	public void sortValues()
	{
		Collections.sort(listOfValues);
	}

}
