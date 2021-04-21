<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin</title>
		<link rel="stylesheet" href="mystyle.css" type= "text/css">
	</head>
	
	<body>
	<body style="background-color:#1F1F1F;">
	<form method="get" action="TopBuyers.jsp"><input type="submit" value="Best Buyers"></form>
	<form method="get" action="TopItems.jsp"><input type="submit" value="Best Selling Items"></form>
	<form method="get" action="EndUserSalesReport.jsp"><input type="submit" value="End User Sales Report"></form>
	<form method="get" action="PerItemSalesReport.jsp"><input type="submit" value="Per Item Sales Report"></form>
	<% ApplicationDB db=new ApplicationDB(); String total = db.totalEarnings(); String totalTops = db.topsEarnings();String totalShoes = db.shoesEarnings(); String totalBottoms = db.bottomsEarnings();%>
	<p style="font-family:georgia,garamond,serif;font-size:24px;font-style:bold;color:white;">
     Total Earnings: $<%=total%>
     <br> 
     <br> 
     Total Eanings By Our 3 Item Types:
     <br> 
     Shoes:   $<%=totalShoes%>
     <br> 
     Bottoms: $<%=totalBottoms%>
     <br> 
     Tops:    $<%=totalTops%>

     
    </p>