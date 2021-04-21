<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
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
		<title>Customer Representative Service</title>
		<link rel="stylesheet" href="mystyle.css" type= "text/css">
	</head>
	
	<body>
	
	<div class="Login">
	<p style="font-family:georgia,garamond,serif;font-size:36px;font-style:bold;color:white;">
         Customer Representative Service
      </p>
	</div>
	
		<form method="get" action="dashboard.jsp"><input type="submit" value="Go Back to Dash Board"></form>
		
		<!-- TODO: users browse questions and answers -->
		<% 
		ApplicationDB db=new ApplicationDB();
		ArrayList<String[]> QAList = new ArrayList<String[]>();
        QAList = db.getQAs();
        
        String account_id = String.valueOf(request.getAttribute("user"));
        String test = String.valueOf(session.getAttribute("user"));
        String targetQuestion;
		%>
		<form method="post" id="QA_servlet" action="auctionServlet">
		<div class="inputs">
		<input type="hidden" name="QA_servlet" value="123">
		
		<p style="font-family:georgia,garamond,serif;font-size:24px;">Common Asked Questions</p>
		<table id="table_id" class="display">
                <thead>
                    <tr>
                        <th>Question</th>
                        <th>Answer</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(int i=0; i < QAList.size(); i++) { %>
                        <tr>
                        <% if(QAList.get(i)[0]!=null){ %>
                                <td><%=QAList.get(i)[0]%></td>
                                <td><%=QAList.get(i)[1]%></td>
                        </tr>
                        <% }} %>
                </tbody>
            </table>
            <script>
                $(document).ready(function () {
                    $('#table_id').DataTable();
                });
            </script>
		
		<!-- users post questions to the customer representatives (i.e. customer service) -->
		
  		
		<label>Post New Question:</label>
		<input type="text" name="question" id="question" size="100">

		<input type="submit" value="submit">
		
		<!-- edit answer function for customer representative -->
		
		<% 
		if (db.isCR(test))
		{
		%>
		<p style="font-family:georgia,garamond,serif;font-size:24px;font-style:bold">
         Edit answer for a question
      	</p>
		<br>
		<label>Question:</label>
		<input type="text" name="targetQuestion" id="targetQuestion" size="100"><br>
		<label>Answer:</label>
		<input type="text" name="answer" id="answer" size="100">
		<input type="submit" value="submit">
		
		
		<%}%>

  		

		<!-- TODO: reps reply to user questions -->
		<!-- TODO: edits and deletes account information -->
		<!-- TODO: removes bids -->
		<!-- TODO: removes auctions -->
	<br>
	</div>
	</form>
</body>
</html>
