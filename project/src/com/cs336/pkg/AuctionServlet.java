package com.cs336.pkg;

import java.io.*;

// javax. was changed to jakarta.
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuctionServlet
 */
@WebServlet("/auctionServlet")
public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CreateAccount = "createAccount";
    private static final String CreateListing = "createListing";
    ApplicationDB db = new ApplicationDB();

    /**
     * Default constructor. 
     */
    public AuctionServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        /*String action = request.getParameter("action");
        System.out.println(action);
        //using if assuming you work with Java SE 6
        if (CreateAccount.equals(action)) {
            db.createAccount(action, action);
        	//add(request, response);
        } else if (CreateListing.equals(action)) {
            if (db.createListing(request.getParameter("brand"), request.getParameter("item_type"), request.getParameter("clothing_size"), request.getParameter("shoe_size"),
            		request.getParameter("bid_increment"), request.getParameter("start_price"), request.getParameter("start_date"), request.getParameter("start_time"),
            		request.getParameter("end_date"), request.getParameter("end_time"), request.getParameter("min_price"))) {
            	System.out.println("yayyy");
            	response.setContentType("text/plain");  
            	response.setCharacterEncoding("UTF-8");
            	response.getWriter().write("hello world"); 
            } else {
            	System.out.println("noooooooooooo");
            	response.setContentType("text/plain");  
            	response.setCharacterEncoding("UTF-8");
            	response.getWriter().write("hkhsdkhd"); 
            }
        } else {
            //submitted action can't be interpreted
            //or no action was submitted
            //errorForward(request, response);
        	System.out.println("submitted action can't be interpreted");
        }*/
		
		// Checks which form called the POST method
		
		if (request.getParameter("listing_form") != null) {
			try {
				// If listing creation is successful then user is sent to the dash board else they are sent to an error page
				if (db.createListing(request.getParameter("brand"), request.getParameter("item_type"), request.getParameter("clothing_size"), request.getParameter("shoe_size"),
						request.getParameter("account"), request.getParameter("bid_increment"), request.getParameter("start_price"), request.getParameter("start_date"), request.getParameter("start_time"),
		        		request.getParameter("end_date"), request.getParameter("end_time"), request.getParameter("min_price"))) {
		        	response.sendRedirect("dashboard.jsp");
		        } else {
		        	response.sendRedirect("invalidListing.jsp");
		        }
				
			} catch (Exception ex) {
				System.out.println(ex);
				response.sendRedirect("invalidListing.jsp");
			}
			
			
		} else if (request.getParameter("login_form") != null){
			try {
				// LOG IN
				
				// Checks if the given account id and password exists in the user account table
				if (db.accountExists(request.getParameter("account_id"), request.getParameter("password"))) {
					System.out.println("Account matches");
					response.sendRedirect("dashboard.jsp");
				} else {
					response.sendRedirect("wrong.jsp");
				}
				
			} catch (Exception ex) {
				System.out.println(ex);
				response.sendRedirect("wrong.jsp");
			}
			
		} else {System.out.println("No form was submitted");}		
	}

}