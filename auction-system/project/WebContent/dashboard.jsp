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
<% ApplicationDB db=new ApplicationDB(); ArrayList<String[]> itemList = new ArrayList<String[]>();
        itemList = db.getListings();
		String account_id = String.valueOf(request.getAttribute("user"));
		String test = String.valueOf(session.getAttribute("user"));
        %>

        <body>

            <form method="get" action="main.jsp"><input type="submit" value="Log Out"></form>
            <form method="get" action="createListing.jsp"><input type="submit" value="Create A Listing"></form>
            <form method="get" action="alert.jsp"><input type="submit" value="Go To Alerts"></form>
            <form method="get" action="personal_history.jsp"><input type="submit" value="My Bid History"></form>
            <form method="get" action="personal_sell_history.jsp"><input type="submit" value="My Selling History"></form>
            <form method="get" action="create_watching_alert.jsp"><input type="submit" value="Create Watch Alert for Item"></form>
            <form method="get" action="my_watching_alerts.jsp"><input type="submit" value="View My Watch Alerts"></form>

            <table id="table_id" class="display">
                <thead>
                    <tr>
                        <th>Clothing ID</th>
                        <th>Category</th>
                        <th>Size</th>
                        <th>Brand</th>
                        <th>Name</th>
                        <th>Current Price</th>
                        <th>Min Bid Increment</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Seller</th>
                        <th>Bid</th>
                        <th>History</th>
                        <th>Similar</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(int i=0; i < itemList.size(); i++) { %>
                        <tr>
                            <% String cid = "";
                            for(int j=0; j < itemList.get(i).length; j++) { 
                            	if (j == 0) {
                            		cid =itemList.get(i)[j];
                            	}
                            %>

                                <td>
                                    <%=itemList.get(i)[j]%>
                                </td>

                                <% } %>
                                <td>
                                	
                                    <form name="bid_form" method="get" action="auctionServlet">
                                    <input type="hidden" name="bid_form" value="123">
                                    <input type="hidden" name="cid" value=<%=itemList.get(i)[0]%>>
                                    <input type="hidden" name="seller" value=<%=itemList.get(i)[9]%>>
                                    <input type="hidden" name="bidIncrementForItem" value=<%=itemList.get(i)[6]%>>
                                    <input type="hidden" name="currPrice" value=<%=itemList.get(i)[5]%>>
                                    <input type="submit" value = "Make Bid"></form>
                                </td>
                                <td>
                                    <form name="history_form" method="get" action="auctionServlet">
                                    <input type="hidden" name="history_form" value="123">
                                    <input type="hidden" name="cid" value=<%=itemList.get(i)[0]%>>
                                    <input type="submit" value = "View History Of This Item"></form>
                                </td>
                                <td>
                                    <form name="similar_form" method="get" action="auctionServlet">
                                    <input type="hidden" name="similar_form" value="123">
                                    <input type="hidden" name="cid" value=<%=itemList.get(i)[0]%>>
                                    <input type="hidden" name="seller" value=<%=itemList.get(i)[7]%>>
                                    <input type="hidden" name="category" value=<%=itemList.get(i)[1]%>>
                                    <input type="submit" value = "View Items Similar To This"></form>
                                </td>
                        </tr>
                        <% } 
                    %>
                </tbody>
            </table>
            <script>
                $(document).ready(function () {
                    $('#table_id').DataTable();
                });
            </script>
            <p>
            <form method="get" action="customer_representative_functions.jsp"><input type="submit" value="Customer Representative Service">
            </form>
            </p>
        
</body>
</html>