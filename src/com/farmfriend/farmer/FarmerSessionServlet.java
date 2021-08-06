package com.farmfriend.farmer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FarmerSessionServlet") //Annotation
public class FarmerSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FarmerSessionServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession  session=request.getSession(false);
		
		String message=null;
		String page="registration.jsp";
		
		if(session!=null) {
			message=(String)session.getAttribute("message");
			
			if(message.equalsIgnoreCase("Registration")) {
				page="registration.jsp";
			}else if(message.equalsIgnoreCase("logout")) {
				page="home.jsp";
				session.removeAttribute("username");
				session.removeAttribute("password");
			}
		}
		
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
