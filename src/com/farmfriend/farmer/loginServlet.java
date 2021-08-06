package com.farmfriend.farmer;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginServlet") //Annotation
public class loginServlet extends HttpServlet{

	
	private static final long serialVersionUID = 3121581301996067017L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside dopost() of loginServlet");
		
		FarmerRepository farmerRepository=new FarmerRepository();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();//Why false ??***********
		
		boolean result=farmerRepository.verifyCredentials(username, password);
		
		if (result==true) {
			String page="home.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);////******
			response.setContentType("text/html"); 
			session.setAttribute("username", username);
		    session.setAttribute("password", password);
		    System.out.println("logged in user is:"+username);
				
			try {
					dispatcher.include(request, response);////******
			} catch (ServletException e) {
					e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			}
		}else
		{
	          try {
	        	    //send message using req obj to register.jsp page
	        		request.setAttribute("errorMessage","Login failed");
	        		//if login fail go to register.jsp
	        	    RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
	  				response.setContentType("text/html"); 
	  				dispatcher.include(request, response);////******
			} catch (IOException e) {
				e.printStackTrace();
			} 
	    }
	}
}
