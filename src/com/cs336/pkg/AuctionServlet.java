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

					if (db.isAdmin(request.getParameter("account_id").trim(), request.getParameter("password").trim()) == true ) {
						if (db.isAdminTable(request.getParameter("account_id").trim())== true) { //if admin ( acc id in admin table and bool = 1)
						session.setAttribute("user", request.getParameter("account_id").trim());
						response.sendRedirect("Admindashboard.jsp");
					}
						else {
							session.setAttribute("user", request.getParameter("account_id").trim());
							response.sendRedirect("dashboard.jsp");
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
			
		else if (request.getParameter("Slogin_form") != null){
			try {
				// LOG IN
				session = request.getSession();
				// Checks if the given account id and password exists in the user account table
				if (db.searchAccountExists(request.getParameter("Saccount_id").trim())) {
							
					session.setAttribute("suser", request.getParameter("Saccount_id").trim());
					response.sendRedirect("EndUserSalesReport2.jsp");
					
				} else {
					response.sendRedirect("wrong.jsp");
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendRedirect("wrong.jsp");
			}
		}
		else if (request.getParameter("Ilogin_form") != null){
			try {
				// LOG IN
				session = request.getSession();
				// Checks if the given account id and password exists in the user account table
				if (db.searchItem(request.getParameter("Sname").trim())) {
					
					session.setAttribute("Item", request.getParameter("Sname").trim());
					response.sendRedirect("PerItemSalesReport2.jsp");
					
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
				    String currPrice = (String)request.getParameter("currPrice");
				    session.setAttribute("currPrice", currPrice);
				    String bidIncrementItem = (String)request.getParameter("bidIncrementForItem");
				    session.setAttribute("bidIncrementForItem", bidIncrementItem);
				    session.setAttribute("cid", cid);
				    
				 // If true send user to wrong.jsp since auction is over, else send user to bid.jsp so they can bid
					if (db.checkIfListingValid(Integer.valueOf(cid), String.valueOf(session.getAttribute("user")))) {
						if(String.valueOf(session.getAttribute("user")).equals(seller))
					    {
					    	response.sendRedirect("SellerError.jsp");
					    }
					    else
					    {
					    	response.sendRedirect("bid.jsp");
					    }
					} else {
						response.sendRedirect("AuctionNotValid.jsp");
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
				if (db.createListing(request.getParameter("brand").trim(), request.getParameter("name").trim(), request.getParameter("item_type"), request.getParameter("clothing_size"), request.getParameter("shoe_size"), String.valueOf(session.getAttribute("user")),
						request.getParameter("bid_increment").trim(), request.getParameter("start_price").trim(),
		        		request.getParameter("end_date").trim(), request.getParameter("end_time").trim(), request.getParameter("min_price").trim())) {
		        	response.sendRedirect("dashboard.jsp");
		        } else {
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
				String currPrice = String.valueOf(session.getAttribute("currPrice"));
				String bidIncrementForItem = String.valueOf(session.getAttribute("bidIncrementForItem"));
				float bidIncrement = Float.valueOf(bidIncrementForItem);
				float currentPrice = Float.valueOf(currPrice);
				//Make BID
			    String upperLimit = "-1";
			    
			    //check if manual bid
			    if(request.getParameter("upperLimit").equals("") && request.getParameter("bidIncrement").equals(""))
			    {
			    	//if upperLimit isn't typed in set it to 0
			    	if(request.getParameter("upperLimit").equals("")) 
			    		upperLimit = "0";
			    	else //get upperlimit
			    		upperLimit = request.getParameter("upperLimit").trim();
			    	//check if the bid price inputted is greater than the current price + min bidIncrement
			    	if(Float.valueOf(request.getParameter("price")) >= bidIncrement + currentPrice)
			    	{
			    		//checks to make sure price is higher than current price and create bid
				    	if(Float.valueOf(request.getParameter("price")) > currentPrice && db.createBid(request.getParameter("price").trim(), upperLimit, 
								String.valueOf(session.getAttribute("user")), cid))
						{
							response.sendRedirect("dashboard.jsp");
						} //error bidprice isn't higher than current price
						else if (Float.valueOf(request.getParameter("price")) <= currentPrice)
						{
							response.sendRedirect("BidPriceError.jsp");
						}//bid not made successfully
						else
						{
							response.sendRedirect("BidFailed.jsp");
						}
			    	}
			    	else
			    	{
			    		response.sendRedirect("BidIncrementError.jsp");
			    	}
			    	//update the autobids
			    	if(Float.valueOf((String)session.getAttribute("currPrice")) > Float.valueOf(request.getParameter("price")))
				    {
			    		db.updateAutoBid((String)session.getAttribute("currPrice"), cid);
				    }
				    else
				    {
				    	db.updateAutoBid(String.valueOf(request.getParameter("price")), cid);
				    } 
			    }//fields not typed in correctly
			    else if(request.getParameter("upperLimit").equals("") && !request.getParameter("bidIncrement").equals(""))
			    {
			    	response.sendRedirect("BidInputError.jsp");
			    }
			    else if(!request.getParameter("upperLimit").equals("") && request.getParameter("bidIncrement").equals(""))
			    {
			    	response.sendRedirect("BidInputError.jsp");
			    }
			    else //make an autobid
			    {
			    	
			    	if(request.getParameter("bidIncrement").equals("") || request.getParameter("upperLimit").equals("") || request.getParameter("bidIncrement").equals(""))
			    	{
			    		response.sendRedirect("wrong.jsp");
			    	}
			    	if(Float.valueOf(request.getParameter("price")) >= bidIncrement + currentPrice && Float.valueOf(request.getParameter("upperLimit")) > Float.valueOf(request.getParameter("price").trim()) && Float.valueOf(request.getParameter("bidIncrement").trim()) >= bidIncrement)
			    	{
			    		if(Float.valueOf(request.getParameter("price")) > currentPrice && db.createAutoBid(request.getParameter("price").trim(), request.getParameter("upperLimit").trim(), 
				    			request.getParameter("bidIncrement").trim(), String.valueOf(session.getAttribute("user")), cid))
						{
							response.sendRedirect("dashboard.jsp");
						} //error bidprice isn't higher than current price
						else if (Float.valueOf(request.getParameter("price")) <= currentPrice)
						{
							response.sendRedirect("BidPriceError.jsp");
						}//bid not made successfully
						else
						{
							response.sendRedirect("BidFailed.jsp");
						}
			    	}
			    	else
			    	{
			    		response.sendRedirect("InvalidInput.jsp");
			    	}
			    	//update autobids
			    	if(Float.valueOf((String)session.getAttribute("currPrice")) > Float.valueOf(request.getParameter("price")))
				    {
			    		db.updateAutoBid((String)session.getAttribute("currPrice"), cid);
				    }
				    else
				    {
				    	db.updateAutoBid(String.valueOf(request.getParameter("price")), cid);
				    }
			    }
			} 
			catch(Exception ex) 
			{
				ex.printStackTrace();
				response.sendRedirect("wrong.jsp");
			}
		}
		else if (request.getParameter("create_CRuser_form") != null){
			 try {
					// Sign Up
					// If account creation is successful then user is sent to the main login page else they are sent to an error page
					if (db.createCRAccount(request.getParameter("account_id").trim(), request.getParameter("password").trim())) {
						response.sendRedirect("Admindashboard.jsp");
					} else{
						response.sendRedirect("CRAccountFailed.jsp");
					}
					
				} catch (Exception ex) {
					ex.printStackTrace();
					response.sendRedirect("wrong.jsp");
				}
		 }
		else if(request.getParameter("QA_servlet")!=null)
		{
			if (db.createQuestion(request.getParameter("question").trim())) {}
		    if (db.EditAnswer(request.getParameter("targetQuestion"), request.getParameter("answer"))){}
		    if (db.editAccount(request.getParameter("account_id"), request.getParameter("newPassword"))) {}
		    if (db.removeBid(request.getParameter("bid_id"), request.getParameter("bid_account") , request.getParameter("bid_cloth"))){}
		    if (db.removeAuction(request.getParameter("auc_cloth"))) {}
		
				response.sendRedirect("customer_representative_functions.jsp");
			
		}
		else if(request.getParameter("watch_alert_form") != null)
		{
			try {
				// If listing creation is successful then user is sent to the dash board else they are sent to an error page
				if (db.createWatchAlert(request.getParameter("item_name").trim(), String.valueOf(session.getAttribute("user")))) {
		        	response.sendRedirect("dashboard.jsp");
		        } else {
		        	response.sendRedirect("CreateAlertError.jsp");
		        }
				
			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendRedirect("wrong.jsp");
			}
			
		}
		else if (request.getParameter("del_account")!=null) 
		 {
			 db.delAccount(request.getParameter("del_account"));
	 
			 response.sendRedirect("main.jsp");
		 }
		
		else {System.out.println("No form was submitted");}		
	}

}