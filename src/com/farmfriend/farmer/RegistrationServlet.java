package com.farmfriend.farmer;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrationServlet")//annotation
public class RegistrationServlet extends HttpServlet{
	private static final long serialVersionUID = 3121581301996067017L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("start:doPost of Registration");
		
		Farmer farmer=new Farmer();
		Location location=new Location();
		
		FarmerRepository farmerRepository=new FarmerRepository();
		LocationRespository locationRespository =new LocationRespository();
		
		//get form values using get parameter method
		farmer.setFname(req.getParameter("firstname")); //name field from jsp page
		farmer.setLname(req.getParameter("lastname"));
		farmer.setEmail(req.getParameter("mail"));
		farmer.setPassword(req.getParameter("password"));
		farmer.setUsername(req.getParameter("username"));
		String phoneno=req.getParameter("contactnumber");
		long phone=Long.valueOf(phoneno);// Why Not ParseInt
		farmer.setPhoneNo(phone);
		
		location.setVillage(req.getParameter("village"));
		location.setTaluka(req.getParameter("taluka"));
		location.setDistrict(req.getParameter("district"));
		String pincode=req.getParameter("pincode");
		int pin=Integer.valueOf(pincode);// Why Not ParseInt
		location.setPincode(pin);
		
		int locationid=locationRespository.checkLocationExists(location.getVillage(), location.getTaluka(), location.getDistrict());
		//location>0-->location exists in table, locationid variable contains id of existing location
		
		if(locationid>0){
			farmer.setLocationid(locationid);
		}else{
			locationid=locationRespository.saveLocationDetails(location); 
			farmer.setLocationid(locationid);  //Set Id In Farmer(module/class)
		}
		//IF Location Do not exixt then save in farmer table
		farmerRepository.saveFarmerDetails(farmer);
		
		// After sucessfull  login register page is shown 
		String page="registration.jsp";
		req.setAttribute("sucessMessage","Farmer registered sucessfully!!");
		RequestDispatcher dispatcher = req.getRequestDispatcher(page);
		resp.setContentType("text/html");
		try {
			dispatcher.include(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
