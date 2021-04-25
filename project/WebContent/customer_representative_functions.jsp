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
		
		<style>
* {
  box-sizing: border-box;
}
body {
  font-family: Arial, Helvetica, sans-serif;
}
/* Style the header */
header {
  background-color: #0073B1;
  height: 300px;
  padding: 20px;
  text-align: center;
  font-size: 35px;
  color: white;
}
/* Create two columns/boxes that floats next to each other */
nav {
  float: left;
  width: 30%;
  height: 300px; /* only for demonstration, should be removed */
  background: #81f6ff;
  text-color: white;
  padding: 30px;
  color: white;
}
/* Style the list inside the menu */
nav ul {
  list-style-type: none;
  padding: 0;
}
article {
  float: left;
  padding: 20px;
  width: 70%;
  background-color: #f1f1f1; /* only for demonstration, should be removed */
}
/* Clear floats after the columns */
section::after {
  content: "";
  display: table;
  clear: both;
}
/* Style the footer */
footer {
  background-color: #777;
  padding: 10px;
  text-align: center;
  color: white;
}
/* Responsive layout - makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */
@media (max-width: 600px) {
  nav, article {
    width: 100%;
    height: auto;
  }
}
</style>
	</head>
	
	<body>
	
	<div class="Login">
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
		
		
		
		<% 
		if (db.isCR(test))
		{
		%>
		<section>
  <nav>
    <ul>
      <li><a href="#editAnswer">Edit Answer For A Question</a></li>
      <br><br>
      <li><a href="#editAccount">Edit Account Information</a></li>
      <br><br>
      <li><a href="#delBid">Remove an existing bid</a></li>
      <br><br>
      <li><a href="#delAuction">Remove all auction for a cloth</a></li>
    </ul>
  </nav>
  <header>
  <h1>Customer Representative Service</h1>
	</header>
</section>
	<br>
	
<section>
  <article>
    <h2 id="editAnswer">Edit Answer For A Question</h2>
    <label>Question:</label>
		<input type="text" name="targetQuestion" id="targetQuestion" size="50"><br>
		<label>Answer:</label>
		<input type="text" name="answer" id="answer" size="50">
		<input type="submit" value="submit">
  </article>
</section>
  <br>
  
<section>
  <article>
    <h2 id="editAccount">Edit Account Information</h2>
    <label>Account ID:</label>
		<input type="text" name="account_id" id="account_id" size="50"><br>
		<label>New Password:</label>
		<input type="text" name="newPassword" id="newPassword" size="50"><br>
		<input type="submit" value="submit">
  </article>
</section>
	<br>
	
<section>
  <article>
    <h2 id="delBid">Remove an existing bid</h2>
    <label>Bid id:</label>
		<input type="text" name="bid_id" id="bid_id" size="50"><br>
		<label>Account id:</label>
		<input type="text" name="bid_account" id="bid_account" size="50"><br>
		<label>Cloth id</label>
		<input type="text" name="bid_cloth" id="bid_cloth" size="50"><br>
		<input type="submit" value="submit">
  </article>
</section>
	<br>
	
<section>
  <article>
    <h2 id="delAuction">Remove all auction for a cloth</h2>
    <label>Cloth id:</label>
		<input type="text" name="auc_cloth" id="auc_cloth" size="50"><br>
		<input type="submit" value="submit">
  </article>
</section>
	<br>
		
		<%}%>
		
	</div>
	</form>
</body>
</html>
