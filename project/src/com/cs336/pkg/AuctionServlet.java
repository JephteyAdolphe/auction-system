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
		
		// Gets account id from user and puts it in session object
		if (request.getParameter("login_form") != null){
			try {
				// LOG IN
				session = request.getSession();
				// Checks if the given account id and password exists in the user account table
				if (db.accountExists(request.getParameter("account_id").trim(), request.getParameter("password").trim())) {
					System.out.println("Account matches");
					
					/*HttpSession session = request.getSession(true);
					String user = "user";
					String pass = "pass";
					session.setAttribute("myData", request.getParameter("account_id").trim());
					session.setAttribute("myData", request.getParameter("password").trim());*/
					if (db.isAdmin(request.getParameter("account_id").trim(), request.getParameter("password").trim()) == true ) {
						if (db.isAdminTable(request.getParameter("account_id").trim())== true) { //if admin ( acc id in admin table and bool = 1)
						//System.out.print("here");
						session.setAttribute("user", request.getParameter("account_id").trim());
						response.sendRedirect("Admindashboard.jsp");
					}
					}
					else { //regular user
					session.setAttribute("user", request.getParameter("account_id").trim());
					response.sendRedirect("dashboard.jsp");
					}
					//response.sendRedirect("dashboard.jsp");
				} else {
					response.sendRedirect("wrong.jsp");
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendRedirect("wrong.jsp");
			}
		}
			
		// Gets cid from dashboard and puts it in session object
			else if(request.getParameter("bid_form") != null) {
				try 
				{
					session = request.getSession();
					
				    String cid = (String)request.getParameter("cid");
				    String seller = (String)request.getParameter("seller");
				    session.setAttribute("cid", cid);
				    
				 // If true send user to wrong.jsp since auction is over, else send user to bid.jsp so they can bid
					if (db.checkIfListingValid(Integer.valueOf(cid), String.valueOf(session.getAttribute("user")))) {
						if(String.valueOf(session.getAttribute("user")).equals(seller))
					    {
							System.out.println("Seller cannot bid on their own item");
					    	response.sendRedirect("wrong.jsp");
					    }
					    else
					    {
					    	response.sendRedirect("bid.jsp");
					    }
					} else {
						System.out.println("The auction is no longer valid");
						response.sendRedirect("wrong.jsp");
					}
					
				} 
				catch(Exception ex) 
				{
					ex.printStackTrace();
					response.sendRedirect("wrong.jsp");
				}
				
			} else if(request.getParameter("history_form") != null) {
				try 
				{
					session = request.getSession();
					
				    String cid = (String)request.getParameter("cid");
				    session.setAttribute("cid", cid);

				    
				 // Send user to bid history page
				    response.sendRedirect("history.jsp");
					
				} 
				catch(Exception ex) 
				{
					ex.printStackTrace();
					response.sendRedirect("wrong.jsp");
				}
			} else if(request.getParameter("bidder_history_form") != null) {
				try 
				{
					session = request.getSession();
					String cid = (String)request.getParameter("cid");
				    session.setAttribute("cid", cid);
				    String bidder = (String)request.getParameter("bidder");
				    session.setAttribute("bidder", bidder);

				    
				 // Send user to bid history page
				    response.sendRedirect("bidder_history.jsp");
					
				} 
				catch(Exception ex) 
				{
					ex.printStackTrace();
					response.sendRedirect("wrong.jsp");
				}
				
			} else if(request.getParameter("similar_form") != null) {
				try 
				{
					session = request.getSession();
					
				    String cid = (String)request.getParameter("cid");
				    String seller = (String)request.getParameter("seller");
				    String category = (String)request.getParameter("category");
				    session.setAttribute("cid", cid);
				    session.setAttribute("seller", seller);
				    session.setAttribute("category", category);
				    
				    response.sendRedirect("similar.jsp");
				    
					
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
				if (db.createListing(request.getParameter("brand").trim(), request.getParameter("item_type"), request.getParameter("clothing_size"), request.getParameter("shoe_size"), String.valueOf(session.getAttribute("user")),
						request.getParameter("bid_increment").trim(), request.getParameter("start_price").trim(),
		        		request.getParameter("end_date").trim(), request.getParameter("end_time").trim(), request.getParameter("min_price").trim())) {
		        	response.sendRedirect("dashboard.jsp");
		        } else {
		        	System.out.println("Are we here?");
		        	response.sendRedirect("invalidListing.jsp");
		        }
				
			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendRedirect("invalidListing.jsp");
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
			
			
		} else if(request.getParameter("make_bid") != null) {
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
					response.sendRedirect("dashboard.jsp");
				} 
				else
				{
					//System.out.println("1st wrong");
					response.sendRedirect("wrong.jsp");
				}
			} 
			catch(Exception ex) 
			{
				ex.printStackTrace();
				response.sendRedirect("wrong.jsp");
			}
		}
		else if (request.getParameter("create_CRuser_form") != null){ ////
			 try {
					// Sign Up

					// If account creation is successful then user is sent to the main login page else they are sent to an error page
					if (db.createCRAccount(request.getParameter("account_id").trim(), request.getParameter("password").trim())) {
						System.out.println("Account Successfully Created!");
						response.sendRedirect("Admindashboard.jsp");
					} else{
						response.sendRedirect("wrong.jsp");
					}
					
				} catch (Exception ex) {
					ex.printStackTrace();
					response.sendRedirect("wrong.jsp");
				}
		 }
		
		else {System.out.println("No form was submitted");}		
	}

}
