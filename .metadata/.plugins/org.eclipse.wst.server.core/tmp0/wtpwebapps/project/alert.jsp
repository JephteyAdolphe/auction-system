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
<%
	ApplicationDB db=new ApplicationDB(); ArrayList<String[]> alertList = new ArrayList<String[]>();
	db.loopListing();
	String user = String.valueOf(session.getAttribute("user"));
	System.out.println(user);
		
    alertList = db.getAlerts(user);
        %>

        <body>

            <form method="get" action="main.jsp"><input type="submit" value="Log Out"></form>
            <form method="get" action="dashboard.jsp"><input type="submit" value="Go To Dash Board">
            </form>

            <table id="table_id" class="display">
                <thead>
                    <tr>
                        <th>Clothing ID</th>
                        <th>Am I The Highest Bidder?</th>
                        <th>Did I win?</th>
                        <th>Upper Limit Surpassed?</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(int i=0; i < alertList.size(); i++) { %>
                        <tr>
                            <%
                            for(int j=0; j < alertList.get(i).length; j++) {       	
                            %>
                                <td>
                                    <%=alertList.get(i)[j]%>
                                </td>
                                <% } %>
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