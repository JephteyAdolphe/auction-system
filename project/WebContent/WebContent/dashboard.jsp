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
		
		//get todays date and time and convert it to a string
		 Date date = new Date();
		 SimpleDateFormat day = new SimpleDateFormat ("yyyy-MM-dd");
		 SimpleDateFormat time = new SimpleDateFormat ("HH:mm:ss");
		 String td = day.format(date);
		 String tim = time.format(date);
		 String today= (td + " " + tim);
		 System.out.println(today);
			
        %>

        <body>

            <form method="get" action="main.jsp"><input type="submit" value="Log Out"></form>
            <form method="get" action="createListing.jsp"><input type="submit" value="Create A Listing">
            </form>

            <table id="table_id" class="display">
                <thead>
                    <tr>
                        <th>Clothing ID</th>
                        <th>Category</th>
                        <th>Size</th>
                        <th>Brand</th>
                        <th>Current Price</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Seller</th>
                        <th>Bid</th>
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
                                    <form name="bid_form" method="get" action="auctionServlet"><input type="hidden" name="bid_form" value="123"><input id="bid_id" name="cid" type="submit" value="Make Bid"></form>
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
        
</body>
</html>