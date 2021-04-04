package com.cs336.pkg;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.io.*;
import java.util.Random;

public class ApplicationDB {
	
	public ApplicationDB(){
		
	}

	public Connection getConnection(){
		
		//Create a connection string
		String connectionUrl = "jdbc:mysql://localhost:3306/project";	// project is the database created in mysql
		Connection connection = null;
		
		try {
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//Create a connection to your DB
			connection = DriverManager.getConnection(connectionUrl,"root", "password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	public void closeConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		ApplicationDB dao = new ApplicationDB();
		Connection connection = dao.getConnection();
		
		System.out.println(connection);		
		dao.closeConnection(connection);
	}
	
	// givenAccountID / givenPass refers to the credentials that the user submitted on log in form
	public boolean accountExists(String givenAccountID, String givenPassword){
		try {
			// Log In
			
			if (givenAccountID == null || givenPassword == null) {
				return false;
			}
			
			//Get the database connection
			Connection con = this.getConnection();

			//Create a SQL statement
			Statement stmt = con.createStatement();

			// Forms sql select query with given account id and password
			String sql = String.format("select account_id, password from account where account_id = '%s' and password = '%s'", givenAccountID, givenPassword);
			
			//Run the query against the DB and retrieves results
			ResultSet rs = stmt.executeQuery(sql);
			
			// Iterates through the returned rows (should only be 1 row) to see if if the account with the correct password exists
			while (rs.next()) {
				if (rs.getString("account_id").equals(givenAccountID) && rs.getString("password").equals(givenPassword)) {
					System.out.println("ACCOUNT EXISTS - Logged In");
					con.close();
					rs.close();
					return true;
				} else {
					break;
				}
			}

			//Close the connection with no account match
			rs.close();
			con.close();
			return false;
								
		} catch (Exception ex) {
			System.out.println(ex);
			//System.out.println("Account Does Not Exist");
			return false;
		}
	}
	
	// givenAccountID / givenPass refers to the credentials that the user submitted on sign up form
	// DOES NOT ACCOUNT FOR CUSTOMER REP ACCOUNT CREATION
	public boolean createAccount(String givenAccountID, String givenPassword){
		try {
			// Sign Up
			String id = givenAccountID.trim();
			String pass = givenAccountID.trim();
			
			if (id.equals("") || pass.equals("")) {
				return false;
			}
			
			if (accountIsValid(id)) {
				return false;
			} else {
			
				//Get the database connection
				Connection con = this.getConnection();
	
				//Create a SQL statement
				Statement stmt = con.createStatement();
	
				// Forms sql insert query with given account id and password
				String sql = String.format("insert into account values ('%s', 0, '%s')", id, pass);
				String sellerSQL = String.format("insert into seller values ('%s')", id);
				String buyerSQL = String.format("insert into buyer values ('%s')", id);
				
				//Run the query against the DB
				stmt.executeUpdate(sql);
				stmt.executeUpdate(sellerSQL);
				stmt.executeUpdate(buyerSQL);
	
				//Close the connection with no account match
				con.close();
				return true;
			}
								
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
		// Converts the form submitted by the seller into an item listing - returns true if query went through
		public boolean createListing(String brand, String itemType, String clothingSize, String shoeSize, String accountID,
				String bidIncrement, String startPrice, String startDate, String startTime, String endDate, String endTime, String minPrice){
			try {
				
				if (startDate.trim().equals("") || endDate.trim().equals("") || startTime.trim().equals("") || endTime.trim().equals("") ||
						bidIncrement.trim().equals("") || startPrice.trim().equals("") || accountID.trim().equals("")) {
					return false;
				}
				
				// Checks if a valid account id is submitted when creating listing... should be obtained from session object though
				if (!accountIsValid(accountID.trim())) return false;
				
				// Parses date and time submitted
				String[] startD = startDate.trim().split("/");
				String[] endD = endDate.trim().split("/");
				
				String[] startT = startTime.trim().split(":");
				String[] endT = endTime.trim().split(":");
				
				String startDateTime = startD[2] + "-" + startD[0] + "-" + startD[1] + " " + startT[0] + ":" + startT[1] + ":" + startT[2];
				String endDateTime = endD[2] + "-" + endD[0] + "-" + endD[1] + " " + endT[0] + ":" + endT[1] + ":" + endT[2];		
				
				// Have to check that start date/time is before end date/time
				
				
				// Notes item type that is being listed
				String category = null;
				String size = null;
				
				if (itemType.equals("Tops")) {
					category = "tops";
					size = clothingSize.trim();
				} else if (itemType.equals("Bottoms")) {
					category = "bottoms";
					size = clothingSize.trim();
				} else {
					category = "shoes";
					size = shoeSize.trim();
				}
								
				//Get the database connection
				Connection con = this.getConnection();
				
				//Create a SQL statement
				Statement stmt = con.createStatement();
				
				// Generate random CID
				Random rand = new Random();
				int upper = 10000;
				int cid = -1;
				
				// Generates CID and checks that it is not a duplicate
				while (cid == -1) {
					cid = rand.nextInt(upper);

					// Forms sql select query with given account id and password
					String sql = String.format("select cid from clothing");
					
					//Run the query against the DB and retrieves results
					ResultSet rs = stmt.executeQuery(sql);
					
					// Iterates through the returned rows (should only be 1 row) to see if if the account with the correct password exists
					while (rs.next()) {
						if (rs.getInt("cid") == cid) {
							System.out.println("CID ALREADY EXISTS");
							cid = -1;
							break;
							
						} else {
							continue;
						}
					}
					rs.close();			
				}
				
				// Forms sql insert query for Clothing, Sells, and ISA Category tables with data
				String clothingSQL = String.format("insert into clothing (cid, brand, bid_increment,"
						+ "cur_price, start_price) values (%d, '%s', %f, %f, %f)", cid, brand.trim(), Float.parseFloat(bidIncrement), Float.parseFloat(startPrice), Float.parseFloat(startPrice));
				
				String sellsSQL = String.format("insert into sells (account_id, cid, minimum, start_date,"
						+ "end_date) values ('%s', %d, %f, '%s', '%s')", accountID.trim(), cid,
						Float.parseFloat(minPrice), startDateTime, endDateTime);
				
				String categorySQL = String.format("insert into %s (cid, category, size) values (%d, '%s', '%s')", category, cid, category, size);
				
				stmt.executeUpdate(clothingSQL);
				stmt.executeUpdate(categorySQL);
				stmt.executeUpdate(sellsSQL);
				
				//Close the connection with no account match
				con.close();
				return true;
													
			} catch (Exception ex) {
				System.out.println(ex);
				return false;
			}
		}
		
		public boolean accountIsValid(String accountID) {
			try {
				
				if (accountID.trim().equals("")) {
					return false;
				}
				
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given account id
				String sql = String.format("select account_id from account where account_id = '%s'", accountID);
				
				//Run the query against the DB and retrieves accounts
				ResultSet rs = stmt.executeQuery(sql);
				
				// Iterates through the returned rows (should only be 1 row) to see if if the account exists
				while (rs.next()) {
					if (rs.getString("account_id").equals(accountID)) {
						con.close();
						rs.close();
						return true;
					} else {
						continue;
					}
				}

				//Close the connection with no account match
				rs.close();
				con.close();
				return false;
									
			} catch (Exception ex) {
				System.out.println(ex);
				return false;
			}
		}
		
}
