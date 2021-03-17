<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<!--Import some libraries that have classes that we need -->
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	try {
		// LOG IN

		/*Connection con = db.getConnection();

		//Create a SQL statement
		//Statement stmt = con.createStatement();

		//Get parameters (username and password) from the HTML form at the HelloWorld.jsp (should rename)
		String accountID = request.getParameter("account_id");
		String password = request.getParameter("passwd");


		//Check if account exists:
		String getAccount = "select * from account where account_id = ? and password = ?";
		//Create a Prepared SQL statement allowing you to introduce the parameters of the query
		PreparedStatement ps = con.prepareStatement(getAccount);

		//Add parameters of the query. Start with 1, the 0-parameter is the SELECT statement itself
		ps.setString(1, accountID);
		ps.setString(2, password);
		
		//Run the query against the DB
		ResultSet rs = ps.executeQuery(getAccount);
		out.println(rs.getString(0));
		out.println("Logged In");
		out.println(accountID);
		out.println(password);

		//Close the connection. Don't forget to do it, otherwise you're keeping the resources of the server allocated.
		con.close();*/

		//Get the database connection
		ApplicationDB db = new ApplicationDB();	
		
		// Checks if the given account id and password exists in the user account table
		if (db.accountExists(request.getParameter("account_id"), request.getParameter("password"))) {
			System.out.println("Account matches");
		} else {
			throw new Exception("nooo");
		}
		
	} catch (Exception ex) {
		System.out.println(ex);
	}
%>
<button onclick="goBack()">Go Back</button>
<script>
function goBack() {
  window.history.back();
}
</script>
</body>
</html>