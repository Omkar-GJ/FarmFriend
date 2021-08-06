package com.farmfriend.weather;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.farmfriend.farmer.LocationRespository;

@WebServlet("/weatherData")
public class WeatherDataServlet extends HttpServlet{

	private static final long serialVersionUID = 7315596187284710483L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		//get session object and check if user is logged 
		HttpSession  session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		LocationRespository locationRespository=new LocationRespository();
		
		if(username!=null) {
			String page="weather.jsp";
			String locationParam=request.getParameter("location");
			
			if(locationParam==null)
				/*if locationparam is empty then fetch weather data for taluka 
				of logged in user*/{
				com.farmfriend.farmer.Location locationObject=locationRespository.getLocation(username);
				locationParam=locationObject.getTaluka();
			}
			
			WeatherClient WeatherClient=new WeatherClient();
			WeatherRepository WeatherRepository=new WeatherRepository();
			
			
			WeatherResponse weatherResponse = WeatherClient.getWeatherData(locationParam);
			if(weatherResponse!=null) {
				WeatherRepository.saveWeather(weatherResponse);
				Map<String,String> map=new LinkedHashMap<String,String>();
				Current current = weatherResponse.getCurrent();
				
				String description="";
				String[] des = current.getWeather_descriptions();
				if(des.length>=1) {
					for(int i=0;i<des.length;i++) {
						if(description=="") {
							description=des[i];
						}else {
							description=description+", "+ des[i];
						}
					}
				}
				
			
				String temperature=Integer.toString(current.getTemperature());
				String winddirection=current.getWind_dir();
				String windspeed=Integer.toString(current.getWind_speed());	
				String humidity=Integer.toString(current.getHumidity());
				
				map.put("Location", locationParam);
				map.put("Description", description);
				map.put("Temperature", temperature);
				map.put("Windspeed", windspeed);
				map.put("Humidity", humidity);
				map.put("Wind direction", winddirection);
				  
				request.setAttribute("weatherMap",map);
				request.setAttribute("location",weatherResponse.getLocation().getName());
				
					  
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				response.setContentType("text/html"); 
					
				try {
						dispatcher.include(request, response); //send execution flow to  front end ie page="weather.jsp"
				} catch (ServletException e) {
						e.printStackTrace();
				} catch (IOException e) {
						e.printStackTrace();
				}
			}else {
// if location not found then this code
				request.setAttribute("errorMessage","Data not found for "+locationParam+". Please try with another nearby location");
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				response.setContentType("text/html"); 
					
				try {
						dispatcher.include(request, response);
				} catch (ServletException e) {
						e.printStackTrace();
				} catch (IOException e) {
						e.printStackTrace();
				}
			}
		}
		else {
			String page="registration.jsp";
			request.setAttribute("errorMessage","Please login to check weather data");
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			response.setContentType("text/html");
			try {
				dispatcher.include(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
