<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Create CR</title>
		<link rel="stylesheet" href="mystyle.css" type= "text/css">
	</head>
	
	<body>
	<body style="background-color:#1F1F1F;">
	<div class="Login">
	
	<p style="font-family:georgia,garamond,serif;font-size:24px;font-style:bold;color:white;">
         Create Account
      </p>
     
	</div>
		<form method="post" action="auctionServlet">
		<input type="hidden" name="create_CRuser_form" value="123jksdj">
		<div class="inputs">
			<table>
				<tr>    
					<td> <p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Username:</p> </td> <td><input type="text" name="account_id"></td>
				</tr>
				<tr>
					<td><p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Password:</p></td><td><input type="text" name="password"></td>
				</tr>
			</table>
			</div>
			<div class="submit">
			<input type="submit" value="Create">
			</div>
		</form>