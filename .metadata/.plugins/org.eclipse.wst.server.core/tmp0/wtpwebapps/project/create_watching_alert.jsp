<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>BuyMe</title>
		<link rel="stylesheet" href="mystyle.css" type= "text/css">
	</head>
	
	<body style="background-color:#1F1F1F;">
	
	<div class="Login">
	<p style="font-family:georgia,garamond,serif;font-size:24px;font-style:bold;color:white;">
         Item Watch Alert
      </p>
	</div>
	
		<form method="get" action="dashboard.jsp"><input type="submit" value="Go Back to Dash Board"></form>
		
		<form method="post" id="watch_alert_form" action="auctionServlet">
		<div class="inputs">
		<input type="hidden" name="watch_alert_form" value="123">
			<table>
				<tr>    
					<td> <p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Item Name:</p> </td> <td><input type="text" name="item_name"></td>
				</tr>
			</table>
			</div>
			<div class="submit">
			<input type="submit" value="Set Alert">
			<p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">If an alert already exists it will be overwritten</p>
			</div>
		</form>
	<br>

</body>
</html>