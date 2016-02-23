package goEuro;

import java.io.FileWriter;
import java.io.IOException;

import com.google.common.collect.ListMultimap;

public class WriteToFile {

	public void writeToCSV(ListMultimap<String,String> ObjectMap, String CityName) throws IOException{
		
		final String CSV_HEADER[] = {"_id", "name", "type","latitude","longitude"};
		
		FileWriter writer = new FileWriter("City_details_"+CityName+".csv");
		
		for (int i=0; i< CSV_HEADER.length-1; i++){
	    	  writer.write(CSV_HEADER[i]+", ");  
	    }
	    writer.write(CSV_HEADER[CSV_HEADER.length-1]);
	    writer.write("\n");
	      
	    for (int row=0; row< ObjectMap.get(CSV_HEADER[0]).size(); row++){			// get values of each key in a row

	    	for (int EachHeader=0; EachHeader< CSV_HEADER.length-1; EachHeader++){  // write value of each JSON key

	    		writer.write(ObjectMap.get(CSV_HEADER[EachHeader]).get(row)+", ");  
	    	  }
	    	writer.write(ObjectMap.get(CSV_HEADER[CSV_HEADER.length-1]).get(row)+"\n");
	    }
	    
	    writer.flush();
	    writer.close();
	    System.out.println("Successfully written to 'City_details_"+CityName+".csv' file");
	}

}

