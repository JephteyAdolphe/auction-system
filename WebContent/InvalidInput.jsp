<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<!--Import some libraries that have classes that we need -->
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Invalid Input</title>
</head>
<body>
Bad input
Check to make sure the initial price is greater than or equal to bidincrement + current price of the item
Upperlimit is greater than input price and/or
Bidincrement is equal or greater than the bid increment requested by the seller
<form method="get" action="bid.jsp"><input type="submit" value="Back"></form>
</body>
</html>