package com.farmfriend.market;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class MarketPricesServlet
 */
@WebServlet("/market") //Annotation
public class MarketPricesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		   System.out.println("inside doGet() of MarketPricesServlet");
		   
			HttpSession  session=request.getSession(false);
			String username=(String)session.getAttribute("username");

			if(username!=null) {
			   MarketPricesRepositoy marketPricesRepositoy=new MarketPricesRepositoy();
			   List<MarketPrice> marketprices=null;
			   String searchCommodity=request.getParameter("commodity");
				System.out.println("search commodity:"+searchCommodity);
			   if(searchCommodity!=null) {
					System.out.println("checking prices for commodity: "+searchCommodity);
					//Fetch market price from database for given commodity
					marketprices=marketPricesRepositoy.getMarketPriceByCommodity(searchCommodity);
				}else {
						System.out.println("checking for all market prices from database");
						//Fetch all market price from database
						marketprices=marketPricesRepositoy.getAllMarketPrices();
				}
			   
			   	String page="marketPrices.jsp";
				request.setAttribute("MarketPricesResponse",marketprices);	  
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				response.setContentType("text/html"); 
						
				try {
						dispatcher.include(request, response);
				} catch (ServletException e) {
						e.printStackTrace();
				} catch (IOException e) {
						e.printStackTrace();
				}
			}else{
				String page="registration.jsp";
				request.setAttribute("errorMessage","Please login to check market data");
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				response.setContentType("text/html");
				try {
					dispatcher.include(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
				
			}
	}
	

	 public MarketPricesServlet() {
	        super();
	    }

}
