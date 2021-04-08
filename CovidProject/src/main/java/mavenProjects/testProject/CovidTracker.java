package mavenProjects.testProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class CovidTracker {
	
	private static HttpURLConnection connection;

	public static void main(String[] args) {
		
		
		  BufferedReader reader; 
		  String line; 
		  StringBuffer responseContent = new StringBuffer(); 
		  try { 
			  URL url = new URL("https://disease.sh/v2/all"); //URL
			  connection = (HttpURLConnection) url.openConnection(); 
			  connection.setRequestMethod("GET");
			  connection.setConnectTimeout(5000); 
			  int status = connection.getResponseCode(); 
			  
			  if(status > 299) { 
				  reader = new BufferedReader(new InputStreamReader(connection.getErrorStream())); 
				  while((line = reader.readLine()) != null) { 
					  responseContent.append(line); 
					  } 
				  reader.close();
			  }else { 
				  reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
				  while((line = reader.readLine()) != null) { 
					  responseContent.append(line); 
					  } 
				  reader.close();
			  } 
			  //System.out.println(responseContent.toString()); 
			  } 
		  catch(MalformedURLException e) { 
			  e.printStackTrace(); 
			  } 
		  catch (IOException e) { //TODO Auto-generated catch block 
			  e.printStackTrace(); 
			  }
		  parse(responseContent.toString());
		  }
		
	
	public static String parse(String responseBody) {
		
		//JSONArray data = new JSONArray(responseBody);
		JSONObject json = new JSONObject(responseBody);
		 Iterator<String> keys = json.keys();

		    while (keys.hasNext()) {
		        String key = keys.next();
		        System.out.println(key + " : " + json.get(key));
		    }
		
		return null;
		
	}

}
