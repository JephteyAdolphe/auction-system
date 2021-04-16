<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<!--Import some libraries that have classes that we need -->
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@  page import= "java.util.Date" %>
<%@  page import= "java.text.SimpleDateFormat" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
</head>
<body>
	<%
	//execute this when a user 
	ApplicationDB db = new ApplicationDB();
	
	//get todays date and time and convert it to a string
	 Date date = new Date();
	 SimpleDateFormat day = new SimpleDateFormat ("yyyy-MM-dd");
	 SimpleDateFormat time = new SimpleDateFormat ("hh:mm:ss");
	 String td = day.format(date);
	 String tim = time.format(date);
	 
	//When the user loads the auction, you check if it is expired or not and do the corresponding things
	if (db.endOfautction(td, tim, account_id, cid)) { //call funcion to determine that the date and time matches a product thats auction time is up
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
		//means that acution is closed
	
	} 
	//TO Do
	//Need to figure out how to get Accountid and cid into method
	
%>


<form method="get" action="main.jsp"><input type="submit" value="vgym Out"></form>
</body>
</html>