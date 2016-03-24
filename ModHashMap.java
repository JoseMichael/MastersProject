package finalprojectdb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ModHashMap {
	
	String elementName;
	HashMap<String,Integer> map;
	
	/*public void setElementName(String name)
	{
		elementName = name;
	}
	*/
	
	public ModHashMap(String elementName)
	{
		this.elementName = elementName;
		map = new HashMap<String,Integer>();
	}
	
	public void addToHash(String key, int value)
	{
		map.put(key, value);
	}
	
	public int findKey(String key)
	{
		
		int value;
		
		if(map.containsKey(key))
		{
			value = map.get(key);
		}
		else
		{
			value = -1;
		}
		
		return value;
	}
	
	public void printHashMap()
	{
		    Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<Integer,Integer> pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
	}

}
