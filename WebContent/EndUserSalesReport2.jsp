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
	 <form method="get" action="Admindashboard.jsp"><input type="submit" value="back to dashboard"></form>
	<% 
	ApplicationDB db = new ApplicationDB();
	
	String searchName =String.valueOf(session.getAttribute("suser"));
	String total = db.userEarnings(searchName); %>
	<p style="font-family:georgia,garamond,serif;font-size:24px;font-style:bold;color:white;">
     Total Earnings for <%=searchName%> is $<%=total%>
    </p>