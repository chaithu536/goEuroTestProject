package goEuro;

import org.json.JSONObject;
import java.util.Iterator;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.ArrayListMultimap;

public class JSONToMap {
	
	ListMultimap <String, String> multiMap = ArrayListMultimap.create();
	String key;
	String pair;
	
	// The function identifies and stores all the JSON keys and associated values in an array list
	
	public void ConvertToMap(JSONObject jsonObj) {
		
		Iterator<String> iterator = jsonObj.keys();
		
	      while (iterator.hasNext()) {
	    	  
	            key = iterator.next().toString();	
	            if(jsonObj.get(key).getClass() == JSONObject.class){
	            	ConvertToMap((JSONObject) jsonObj.get(key));		// Recursive function call to identify nested JSON objects
	            }
	            
	            else{
	            	pair = String.valueOf(jsonObj.get(key));
	            	multiMap.put(key, pair ); 		
	            }
	      }
	}
	
	public ListMultimap<String, String> Get_multiMap(){		// return the array list
		return multiMap;
	}
}
