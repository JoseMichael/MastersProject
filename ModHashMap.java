package finalprojectdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ModHashMap {
	
	String elementName;
	HashMap<String,ArrayList<Integer>> map;
	
	/*public void setElementName(String name)
	{
		elementName = name;
	}
	*/
	
	public ModHashMap(String elementName)
	{
		this.elementName = elementName;
		map = new HashMap<String,ArrayList<Integer>>();
	}
	
	public void addToHash(String key, int value)
	{
		if(!map.containsKey(key))
		{
			//if key is not present in hash map
			ArrayList<Integer> valueToAdd = new ArrayList<Integer>();
			valueToAdd.add(value);
			map.put(key, valueToAdd);
		}
		else
		{
			//when key is present in hash map
			ArrayList<Integer> valueToMod = map.get(key);
			valueToMod.add(value);
			map.put(key, valueToMod);
		}
	}
	
	public ArrayList<Integer> findKey(String key)
	{
		
		ArrayList<Integer> value;
		
		if(map.containsKey(key))
		{
			value = map.get(key);
		}
		else
		{
			//if value is not present then value should be null
			value = null;
			//value.add(-1);
		}
		
		return value;
	}
	
	//TODO: check function
	//function may not work since now value is an arraylist
	public void printHashMap()
	{
		    Iterator<Entry<String, ArrayList<Integer>>> it = map.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<Integer,Integer> pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
	}

}
