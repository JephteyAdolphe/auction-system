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
		
		// Checks which form called the POST method
		
		if (request.getParameter("listing_form") != null) {
			try {
				// If listing creation is successful then user is sent to the dash board else they are sent to an error page
				if (db.createListing(request.getParameter("brand").trim(), request.getParameter("item_type").trim(), request.getParameter("clothing_size").trim(), request.getParameter("shoe_size").trim(),
						request.getParameter("account").trim(), request.getParameter("bid_increment").trim(), request.getParameter("start_price").trim(), request.getParameter("start_date").trim(), request.getParameter("start_time").trim(),
		        		request.getParameter("end_date").trim(), request.getParameter("end_time").trim(), request.getParameter("min_price").trim())) {
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
				if (db.accountExists(request.getParameter("account_id").trim(), request.getParameter("password").trim())) {
					System.out.println("Account matches");
					response.sendRedirect("dashboard.jsp");
				} else {
					response.sendRedirect("wrong.jsp");
				}
				
			} catch (Exception ex) {
				System.out.println(ex);
				response.sendRedirect("wrong.jsp");
			}
			
		} else if (request.getParameter("create_user_form") != null){
			try {
				// Sign Up

				// If account creation is successful then user is sent to the main login page else they are sent to an error page
				if (db.createAccount(request.getParameter("account_id").trim(), request.getParameter("password").trim())) {
					System.out.println("Account Successfully Created!");
					response.sendRedirect("main.jsp");
				} else{
					response.sendRedirect("wrong.jsp");
				}
				
			} catch (Exception ex) {
				response.sendRedirect("wrong.jsp");
			}
			
		} else {System.out.println("No form was submitted");}		
	}

}
