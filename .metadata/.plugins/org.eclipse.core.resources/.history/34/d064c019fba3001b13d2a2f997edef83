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
         List an Item
      </p>
	</div>
	
		<form method="get" action="dashboard.jsp"><input type="submit" value="Go Back to Dash Board"></form>
		
		<form method="post" id="listing_form" action="auctionServlet">
		<div class="inputs">
		<input type="hidden" name="listing_form" value="123">
			<table>
				<tr>    
					<td> <p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Brand:</p> </td> <td><input type="text" name="brand"></td>
				</tr>
				<tr>
				<td> <p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Item Name:</p> </td> <td><input type="text" name="name"></td>
         		</tr>
				<tr>
					<td><p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Item Type:</p></td><td>
         			
						<select name="item_type" id="item_type" >
						  <option value="shoes">Shoes</option>
						  <option value="bottoms">Bottoms</option>
						  <option value="tops">Tops</option>
						</select>
					</td>
         			
				</tr>
				<tr>
					<td><p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Clothing Size:</p></td><td>
         			
						<select name="clothing_size" id="size" >
						<option value="null">null</option>
						  <option value="small">Small</option>
						  <option value="medium">Medium</option>
						  <option value="large">Large</option>
						</select>
					</td>
         			
				</tr>
				<tr>
					<td><p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Shoes Size:</p></td><td>
         			
						<select name="shoe_size" id="size" >
						<option value="null">null</option>
						  <option value="8">8</option>
						  <option value="8.5">8.5</option>
						  <option value="9">9</option>
						  <option value="9.5">9.5</option>
						  <option value="10">10</option>
						  <option value="10.5">10.5</option>
						  <option value="11">11</option>
						</select>
					</td>
         			
				</tr>
				<tr>
					<td><p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Bid Increment:</p></td><td><input type="number" min="0.00" step="0.01" name="bid_increment"></td>
				</tr>
				<tr>    
					<td> <p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Start Price:</p> </td> <td><input type="number" min="0.00" step="0.01" name="start_price"></td>
				</tr>
				<tr>    
					<td> <p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			End date:</p> </td> <td><input type="text" name="end_date" placeholder="MM/DD/YYYY"><input type="text" name="end_time" placeholder="HH:MM:SS"></td>
				</tr>
				<tr>
					<td><p style="font-family:georgia,garamond,serif;font-size:16px;color:white;">
         			Minimum Reserve Price:</p></td><td><input type="number" min="0.00" step="0.01" value="0.00" name="min_price"></td>
				</tr>
			</table>
			</div>
			<div class="submit">
			<input type="submit" value="Create Listing">
			</div>
		</form>
	<br>

</body>
</html>