<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"
    %>
<!--Import some libraries that have classes that we need -->
<%@ page import="java.io.*,java.util.*,java.sql.*,java.util.ArrayList,java.util.Date,java.text.SimpleDateFormat" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<link rel="stylesheet" type="text/css"
    href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" charset="utf8"
    src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>

     <body>
     <form method="get" action="dashboard.jsp"><input type="submit" value="Back to Dashboard"></form>
     
     
     <%-- <%
     ApplicationDB db = new ApplicationDB();
     
    // String account_id = String.valueOf(request.getAttribute("user"));
     //int cid = Integer.valueOf(request.getParameter("cid"));
     
     String account_id = String.valueOf(session.getAttribute("user"));
     int cid = Integer.valueOf((String)session.getAttribute("cid"));
     
	
	//get todays date and time and convert it to a string
	 /* Date date = new Date();
	 SimpleDateFormat day = new SimpleDateFormat ("yyyy-MM-dd");
	 SimpleDateFormat time = new SimpleDateFormat ("HH:mm:ss");
	 String td = day.format(date);
	 String tim = time.format(date);
	 String today= (td + " " + tim);
	 
	 System.out.println(today);
	//When the user loads the auction, you check if it is expired or not and do the corresponding things
	if (db.endOfauction(today, cid)) { //call funcion to determine that the date and time matches a product thats auction time is up
		if ( db.minPrice(cid) == 0){ //means that there is no min value
			float winBid = db.highestBid(cid);
			int winnerBidID = db.highestBidAid(cid);//find the highest bid acc
			db.setWinner(winnerBidID); //Store acount with highest bid as winner in  bids table by setting winner value to 1
		}
		else if (db.minPrice(cid) <= db.highestBid(cid)){ //means ther is a min value
			float winBid = db.highestBid(cid);
			int winnerBidID = db.highestBidAid(cid); //find the highest bid acc
			db.setWinner(winnerBidID);//Store bid_id with highest bid as winner in bids table by setting winner value to 1
		}
		//means that auction is closed
	
	} */
	//TO Do
	//Need to figure out how to get Accountid and cid into method
%> --%>
	<form method="post" id="make_bid" action="auctionServlet">
	
	<input type="hidden" name="make_bid" value="1234">
  	Bid Price: <input type="number" step="0.01" name="price">
  	<p>Upper Limit of Automatic Bid (optional): <input type="number" step="0.01" name="upperLimit"></p>
  	<p>Automatic Bid increment (optional): <input type="number" step="0.01" name="bidIncrement"></p>
  	<p><input type="submit" value="Create Bid"></p></form>
	</body>
</html>
