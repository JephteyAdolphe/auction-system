package com.cs336.pkg;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
// javax. was changed to jakarta.
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AuctionServlet
 */
@WebServlet("/auctionServlet")
public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    ApplicationDB db = new ApplicationDB();
    HttpSession session;

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
		
		if (request.getParameter("login_form") != null){
			try {
				// LOG IN
				
				// Checks if the given account id and password exists in the user account table
				if (db.accountExists(request.getParameter("account_id").trim(), request.getParameter("password").trim())) {
					System.out.println("Account matches");
					
					session = request.getSession();
				    String username = (String)request.getParameter("account_id");
				    session.setAttribute("user", username);
				    System.out.println("in the do get method");
				    System.out.println(username);
				    response.sendRedirect("dashboard.jsp");
					
				} 
				
				else {
					response.sendRedirect("wrong.jsp");
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendRedirect("wrong.jsp");
			}
		}
			else if(request.getParameter("bid_form") != null) {
				try 
				{
					session = request.getSession();
					
				    String cid = (String)request.getParameter("cid");
				    session.setAttribute("cid", cid);
				    System.out.println("in the do get method for bid form");
				    System.out.println(cid);
				    response.sendRedirect("bid.jsp");
					
				} 
				catch(Exception ex) 
				{
					ex.printStackTrace();
					response.sendRedirect("wrong.jsp");
				}
			}
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
				if (db.createListing(request.getParameter("brand").trim(), request.getParameter("item_type"), request.getParameter("clothing_size"), request.getParameter("shoe_size"),
						request.getParameter("account").trim(), request.getParameter("bid_increment").trim(), request.getParameter("start_price").trim(), request.getParameter("start_date").trim(), request.getParameter("start_time").trim(),
		        		request.getParameter("end_date").trim(), request.getParameter("end_time").trim(), request.getParameter("min_price").trim())) {
		        	response.sendRedirect("dashboard.jsp");
		        } else {
		        	response.sendRedirect("invalidListing.jsp");
		        }
				
			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendRedirect("invalidListing.jsp");
			}
			
			
		} else if (request.getParameter("login_form") != null){
			try {
				// LOG IN
				
				// Checks if the given account id and password exists in the user account table
				if (db.accountExists(request.getParameter("account_id").trim(), request.getParameter("password").trim())) {
					System.out.println("Account matches");
					
					/*HttpSession session = request.getSession(true);
					String user = "user";
					String pass = "pass";
					session.setAttribute("myData", request.getParameter("account_id").trim());
					session.setAttribute("myData", request.getParameter("password").trim());*/
					
					request.setAttribute("user", request.getParameter("account_id").trim());
					RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
					rd.forward(request, response);
					
					//response.sendRedirect("dashboard.jsp");
				} else {
					response.sendRedirect("wrong.jsp");
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
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
				ex.printStackTrace();
				response.sendRedirect("wrong.jsp");
			}
			
			
		} else if (request.getParameter("bid_form") != null){
			try {
				
				System.out.println("bid servlet");
				System.out.println(request.getParameter("cid"));
				request.setAttribute("cid", request.getParameter("cid"));
				RequestDispatcher rd = request.getRequestDispatcher("/bid.jsp");
				rd.forward(request, response);
				
			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendRedirect("wrong.jsp");
			}
			
		} 
		else if(request.getParameter("make_bid") != null) {
			try 
			{
				session = request.getSession();
				
			    String cid = String.valueOf(session.getAttribute("cid"));
				
				
				//Make BID
				//need to get account_id and CID
				//is working when i hard code account_id and cid but currently null because idk how to get values
				//price may need to be updated because idk if i should make it the starting price seller put + bid price or
				//force bid price to above previous bids and starting price or in general how to do it
				if(db.createBid(request.getParameter("price").trim(), request.getParameter("upperLimit").trim(), 
						String.valueOf(session.getAttribute("user")), cid))
				{
					System.out.println("Bid made successfully");
				} 
				else
				{
					System.out.println("1st wrong");
					response.sendRedirect("wrong.jsp");
				}
			} 
			catch(Exception ex) 
			{
				ex.printStackTrace();
				response.sendRedirect("wrong.jsp");
			}
		}
		else {System.out.println("No form was submitted");}		
	}

}
