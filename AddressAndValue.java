package finalprojectdb;

public class AddressAndValue implements Comparable<AddressAndValue>{
	
	String address;
	String value;
	
	public int getValue()
	{
		return Integer.parseInt(value);
	}
	
//	public void setValue(String address, int value)
//	{
//		this.address = address;
//		this.value = value;
//	}
	
	public AddressAndValue(String address, String value)
	{
		this.address = address;
		this.value = value;
	}
	
	@Override
	public int compareTo(AddressAndValue addandval) {
	    return (this.getValue() < addandval.getValue() ? -1 : 

            (this.getValue() == addandval.getValue() ? 0 : 1)); 
	}
	
	

}
