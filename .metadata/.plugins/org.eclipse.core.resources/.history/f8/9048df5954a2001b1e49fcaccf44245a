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
    <title>Top Buyers</title>
</head>
<%
	ApplicationDB db=new ApplicationDB();
	ArrayList<String[]> itemList = new ArrayList<String[]>();
	itemList = db.topItems();	
        %>

        <body>

          
            <form method="get" action="Admindashboard.jsp"><input type="submit" value="Go To Admin Dash Board">
            </form>

            <table id="table_id" class="display">
                <thead>
                    <tr>
                        <th>Item</th>
                        <th># of sales</th>

                    </tr>
                </thead>
                <tbody>
                    <% for(int i=0; i < itemList.size(); i++) { %>
                        <tr>
                            <%
                            for(int j=0; j < itemList.get(i).length; j++) {       	
                            %>
                                <td>
                                    <%=itemList.get(i)[j]%>
                                </td>
                                <% } %>
                                <%-- <td>
                                    <form name="bidder_history_form" method="get" action="auctionServlet">
                                    <input type="hidden" name="bidder_history_form" value="123">
                                    <input type="hidden" name="bidder" value=<%=bidderList.get(i)[2]%>>
                                    <input type="hidden" name="cid" value=<%=bidderList.get(i)[3]%>>
                                    <input type="submit" value = "View"></form>
                                </td> --%>
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