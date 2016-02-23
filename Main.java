package goEuro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.collect.ListMultimap;


public class Main {
	
	public static void main(String[] args) throws IOException {
		
		JSONToMap map = new JSONToMap();
		WriteToFile file = new WriteToFile();							// Creating objects for the classes 
		
		String CityName = args[0];
		String URLInString = "http://api.goeuro.com/api/v2/position/suggest/en/"+CityName;
		
		try{
			InputStream stream = new URL(URLInString).openStream();		// connecting to the Location JSON API
		
			try 
			{
				BufferedReader rd = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
				String jsonText = rd.readLine();								// Reading text from the web page
				
				// Assigning a dummy name to obtained JSON array and creating a JSON object
				JSONObject jsonObj = new JSONObject("{Sample:"+jsonText+"}"); 
				JSONArray jsonObj_Array = jsonObj.getJSONArray("Sample");     // Creating JSON array for the JSON objects
				int json_Array_size = jsonObj_Array.length();
	      
				if (json_Array_size == 0)
				{
					System.out.println("Invalid city name. Please check the spelling");
					System.exit(0);
				}
				JSONObject Each_jsonObj = new JSONObject();
	      
				for (int i = 0; i < json_Array_size; i++) 
				{
					Each_jsonObj = jsonObj_Array.getJSONObject(i);
					map.ConvertToMap(Each_jsonObj);						  // Function for converting JSON to List Map
				}
	      
				ListMultimap<String,String> ObjectMap  = map.Get_multiMap();  // Get the Array list containing JSON - key:[values]
				System.out.println(ObjectMap);
				file.writeToCSV(ObjectMap, CityName);							// write the output to CSV
	      
			}
	    
			finally 
			{
				stream.close();
			} 
		}
		catch (UnknownHostException e)
		{
			System.out.println("Please check your internet connection");
			System.exit(0);
		}
	}

}
