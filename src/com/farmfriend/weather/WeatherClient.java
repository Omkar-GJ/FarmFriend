package com.farmfriend.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.google.gson.Gson;

public class WeatherClient {

	public WeatherResponse getWeatherData(String location) {
		
		String uri="http://api.weatherstack.com/current?access_key=e00255dc5e9c003a164148e3fdda0954&query="+location;
		URL url;
		WeatherResponse weatherResponse=null;
		try {
			url = new URL(uri);
			
			// to open conn. to URL
			HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
			httpconn.setRequestMethod("GET");
			httpconn.setRequestProperty("Accept", "application/json");
			
			// TO Get Weather data from conn
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(httpconn.getInputStream())));

				String output;
				String json="";
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
		      // To concat Json string which are in multipal lines we use BUFFER 
					System.out.println(output);
					json=json+output;
				}
				
				System.out.println("json:"+json);
				
				if(json.contains("request_failed")) {
					System.out.println("Error while calling weather API.");
				}else {
					Gson g = new Gson();
					// to convert json response API into Java object = weatherResponse
					weatherResponse = g.fromJson(json, WeatherResponse.class);	
					System.out.println("Temprarure"+weatherResponse.getCurrent().getTemperature());
				}
				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return weatherResponse;
		
			
	}
}
