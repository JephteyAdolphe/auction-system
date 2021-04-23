package com.cs336.pkg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Date;

public class ApplicationDB {

	public ApplicationDB() {

	}

	public Connection getConnection() {

		// Create a connection string
		String connectionUrl = "jdbc:mysql://localhost:3306/BuyMe?autoReconnect=true&useSSL=false"; // project is the database created in mysql
		Connection connection = null;

		try {
			// Load JDBC driver - the interface standardizing the connection procedure. Look
			// at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
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
			// Create a connection to your DB
			connection = DriverManager.getConnection(connectionUrl, "root", "687OAI62");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;

	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ApplicationDB dao = new ApplicationDB();
		// Connection connection = dao.getConnection();

		// System.out.println(connection);
		dao.getListings();
		// dao.closeConnection(connection);
	}

	// givenAccountID / givenPass refers to the credentials that the user submitted
	// on log in form
	public boolean accountExists(String givenAccountID, String givenPassword) {
		try {
			// Log In

			if (givenAccountID == null || givenPassword == null) {
				return false;
			}

			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();

			// Forms sql select query with given account id and password
			String sql = String.format(
					"select account_id, password from account where account_id = '%s' and password = '%s'",
					givenAccountID, givenPassword);

			// Run the query against the DB and retrieves results
			ResultSet rs = stmt.executeQuery(sql);

			// Iterates through the returned rows (should only be 1 row) to see if if the
			// account with the correct password exists
			while (rs.next()) {
				if (rs.getString("account_id").equals(givenAccountID)
						&& rs.getString("password").equals(givenPassword)) {
					System.out.println("ACCOUNT EXISTS - Logged In");
					con.close();
					rs.close();
					return true;
				} else {
					break;
				}
			}

			// Close the connection with no account match
			rs.close();
			con.close();
			return false;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// givenAccountID / givenPass refers to the credentials that the user submitted
	// on sign up form
	// DOES NOT ACCOUNT FOR CUSTOMER REP ACCOUNT CREATION
	public boolean createAccount(String givenAccountID, String givenPassword) {
		try {
			// Sign Up

			if (givenAccountID.equals("") || givenPassword.equals("")) {
				return false;
			}

			if (accountIsValid(givenAccountID)) {
				return false;
			} else {

				// Get the database connection
				Connection con = this.getConnection();

				// Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql insert query with given account id and password
				String sql = String.format("insert into account values ('%s', 0, '%s')", givenAccountID, givenPassword);
				String sellerSQL = String.format("insert into seller values ('%s')", givenAccountID);
				String buyerSQL = String.format("insert into buyer values ('%s')", givenAccountID);

				// Run the query against the DB
				stmt.executeUpdate(sql);
				stmt.executeUpdate(sellerSQL);
				stmt.executeUpdate(buyerSQL);

				// Close the connection with no account match
				con.close();
				return true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			;
			return false;
		}
	}

	public boolean createCRAccount(String givenAccountID, String givenPassword) { /////////////////////////////
		try {
			// Sign Up

			if (givenAccountID.equals("") || givenPassword.equals("")) {
				return false;
			}

			if (accountIsValid(givenAccountID)) {
				return false;
			} else {

				// Get the database connection
				Connection con = this.getConnection();

				// Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql insert query with given account id and password
				String sql = String.format("insert into account values ('%s', 1, '%s')", givenAccountID, givenPassword);
				String crSQL = String.format("insert into customer_representative values ('%s')", givenAccountID);
				

				// Run the query against the DB
				stmt.executeUpdate(sql);
				stmt.executeUpdate(crSQL);
				

				// Close the connection with no account match
				con.close();
				return true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			;
			return false;
		}
	}
	public boolean isAdmin(String givenAccountID, String givenPassword) {//////////////////////////
		try {
			// Log In

			if (givenAccountID == null || givenPassword == null) {
				return false;
			}

			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();

			// Forms sql select query with given account id and password
			String sql = String.format(
					"select  manager , account_id  from account where account_id = '%s' and password = '%s' and manager = '1' ",
					givenAccountID, givenPassword);

			// Run the query against the DB and retrieves results
			ResultSet rs = stmt.executeQuery(sql);

			// Iterates through the returned rows (should only be 1 row) to see if if the
			// account with the correct password exists
			while (rs.next()) {
				if (rs.getString("manager").equals("1")
						&& rs.getString("account_id").equals(givenAccountID)) {
					
					con.close();
					rs.close();
					return true;
				} else {
					break;
				}
			}

			// Close the connection with no account match
			rs.close();
			con.close();
			return false;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public boolean isAdminTable(String givenAccountID) {//////////////////////////////////
		try {
			// Log In

			if (givenAccountID == null) {
				return false;
			}

			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();

			// Forms sql select query with given account id and password
			String sql = String.format(
					"select account_id  from administrator where account_id = '%s' ",
					givenAccountID);

			// Run the query against the DB and retrieves results
			ResultSet rs = stmt.executeQuery(sql);

			// Iterates through the returned rows (should only be 1 row) to see if if the
			// account with the correct password exists
			while (rs.next()) {
				if (rs.getString("account_id").equals(givenAccountID)) {
					
					con.close();
					rs.close();
					return true;
				} else {
					break;
				}
			}

			// Close the connection with no account match
			rs.close();
			con.close();
			return false;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Converts the form submitted by the seller into an item listing - returns true
	// if query went through
	public boolean createListing(String brand, String name,String itemType, String clothingSize, String shoeSize, String accountID,
			String bidIncrement, String startPrice, String endDate, String endTime,
			String minPrice) {
		try {

			if (endDate.equals("") || endTime.equals("")
					|| bidIncrement.equals("") || startPrice.equals("") || accountID.equals("")) {
				System.out.println("Is something empty");
				return false;
			}

			// Checks if a valid account id is submitted when creating listing... should be
			// obtained from session object though
			if (!accountIsValid(accountID))
			{
				System.out.println("Is the account valid?");
				return false;
			}

			// Parses date and time submitted
			String[] endD = endDate.split("/");
			String[] endT = endTime.split(":");
			
			//get todays date and time and convert it to a string
			 Date date = new Date();
			 SimpleDateFormat day = new SimpleDateFormat ("yyyy-MM-dd");
			 SimpleDateFormat time = new SimpleDateFormat ("HH:mm:ss");
			 String td = day.format(date);
			 String tim = time.format(date);
			 String startDateTime = (td + " " + tim);
			 //System.out.println(startDateTime);
			
			
			String endDateTime = endD[2] + "-" + endD[0] + "-" + endD[1] + " " + endT[0] + ":" + endT[1] + ":"
					+ endT[2];

			// Checks that start date/time is before end date/time
			if (!compareDates(startDateTime, endDateTime))
			{
				System.out.println("Are the dates correct?");
				return false;
			}

			// Notes item type that is being listed
			String category = null;
			String size = null;
			if (itemType == null) {
				//System.out.println("hi");
			}
			else if (itemType.equals("tops")) {
				category = "tops";
				size = clothingSize;
			} else if (itemType.equals("bottoms")) {
				category = "bottoms";
				size = clothingSize;
			} else {
				category = "shoes";
				size = shoeSize;
			}

			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
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

				// Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);

				// Iterates through the returned rows (should only be 1 row) to see if if the
				// account with the correct password exists
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
			
			String clothingSQL = String.format(
					"insert into clothing (cid,name, brand, bid_increment,"
							+ "cur_price, start_price) values (%d, '%s', '%s', %f, %f, %f)",
					cid, name, brand, Float.parseFloat(bidIncrement), Float.parseFloat(startPrice),
					Float.parseFloat(startPrice));

			String sellsSQL = String.format(
					"insert into sells (account_id, cid, minimum, start_date,"
							+ "end_date) values ('%s', %d, %f, '%s', '%s')",
					accountID, cid, Float.parseFloat(minPrice), startDateTime, endDateTime);

			String categorySQL = String.format("insert into %s (cid, category, size) values (%d, '%s', '%s')", category,
					cid, category, size);

//			String clothingSQL = String.format(
//			"insert into clothing (name, brand, bid_increment,"
//					+ "cur_price, start_price) values ( '%s', '%s', %f, %f, %f)",
//			 name, brand, Float.parseFloat(bidIncrement), Float.parseFloat(startPrice),
//			Float.parseFloat(startPrice));
//
//	String sellsSQL = String.format(
//			"insert into sells (account_id, minimum, start_date,"
//					+ "end_date) values ('%s', %f, '%s', '%s')",
//			accountID, Float.parseFloat(minPrice), startDateTime, endDateTime);
//
//	String categorySQL = String.format("insert into %s ( category, size) values ( '%s', '%s')", category,
//			 category, size);
			
			
			stmt.executeUpdate(clothingSQL);
			stmt.executeUpdate(categorySQL);
			stmt.executeUpdate(sellsSQL);

			// Close the connection with no account match
			con.close();
			return true;

		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println("Try catch failed");
			return false;
		}
	}
	
	//Allows the user to bid on an item
		public boolean createBid(String price, String upperLimit, String accountID, String CID)
		{
			try
			{
				if (price.equals("") || upperLimit.equals("") || accountID.equals("") || CID.equals("")) {
					return false;
				}
				
				//uncomment this, its needed, only commented for testing !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
				if (!accountIsValid(accountID))
					return false;
				
				// Get the database connection
				Connection con = this.getConnection();

				// Create a SQL statement
				Statement stmt = con.createStatement();
				
				
				//update current price of clothing
				String sql1 = String.format("update clothing set cur_price = %f where CID = %d", Float.parseFloat(price), Integer.parseInt(CID));
				stmt.executeUpdate(sql1);
				
				//inserting bid into bid table
				String BIDSql = String.format("insert into bids (price, upper_limit, account_id, CID) values "
						+ "('%f', '%f', '%s', '%s')", Float.parseFloat(price), Float.parseFloat(upperLimit), accountID, CID);
				
				stmt.executeUpdate(BIDSql);
				
				// Close the connection with no account match
				con.close();
				return true;
			}
			catch(Exception ex)
			{
				System.out.println(ex);
				return false;
			}
		}
		
		public boolean createAutoBid(String price, String upperLimit, String bidIncrement, String accountID, String CID)
		{
			System.out.println("Are we here");
			try
			{
				if (price.equals("") || upperLimit.equals("") || bidIncrement.equals("") || accountID.equals("") || CID.equals("")) {
					return false;
				}
				
				if (!accountIsValid(accountID))
					return false;
				
				// Get the database connection
				Connection con = this.getConnection();

				// Create a SQL statement
				Statement stmt = con.createStatement();
				
				//update current price of clothing
				//String sql1 = String.format("update clothing set cur_price = %f where CID = %d", Float.parseFloat(price), Integer.parseInt(CID));
				//stmt.executeUpdate(sql1);
				
				
				//inserting autobid into bid table
				String BIDSql = String.format("insert into bids (price, upper_limit, account_id, CID, bidincrement) values "
						+ "('%f', '%f', '%s', '%s','%f')", Float.parseFloat(price), Float.parseFloat(upperLimit), accountID, CID, Float.parseFloat(bidIncrement));
				stmt.executeUpdate(BIDSql);
				
				// Close the connection with no account match
				con.close();
				return true;
			}
			catch (Exception ex)
			{
				System.out.println(ex);
				return false;
			}
		}
		//0 false 1 true 2 needs to be run again
		public boolean updateAutoBid(String price, String CID)
		{
			try
			{
				// Get the database connection
				Connection con = this.getConnection();

				// Create a SQL statement
				Statement stmt = con.createStatement();
				
				String sql = String.format("select * from bids where bidincrement <> 0 and CID = %d", Integer.parseInt(CID));
				ResultSet rs = stmt.executeQuery(sql);
				ArrayList<String[]> autoBid= new ArrayList<String[]>();
				int countNumberOfUpdates = 0;
				while(rs.next()) 
				{
					String[] autobids = {rs.getString("Bid_ID"), rs.getString("price"), rs.getString("upper_limit"), rs.getString("account_id"), rs.getString("bidincrement")};
					autoBid.add(autobids);
				}
				for(int i = 0; i < autoBid.size(); i++)
				{
					String[] row = autoBid.get(i);
					Integer bidId = null;
					Float priceOfRow = null;
					Float upperlimit = null;
					String accountIDFromRow = "";
					Float bidincrement = null;
					//get the values we need from the row
					for(int j = 0; j<row.length;j++)
					{
						bidId = Integer.valueOf(row[0]);
						priceOfRow = Float.valueOf(row[1]);
						upperlimit = Float.valueOf(row[2]);
						accountIDFromRow = row[3];
						bidincrement = Float.valueOf(row[4]);
					}
					System.out.println(bidId);
					System.out.println(priceOfRow);
					System.out.println(upperlimit);
					System.out.println(accountIDFromRow);
					System.out.println(bidincrement);
					System.out.println();
					System.out.println("priceOfRow:" + priceOfRow);
					System.out.println("price:" + Float.valueOf(price));
					System.out.println();
					//checks if the autobid is greater than the current price
					if(priceOfRow > Float.valueOf(price))
				 	{
						//check if a manual bid was placed with the value of the autobid
					    String sql1 = String.format("select max(price) as p from bids where account_id = '%s' and CID = %d and upper_limit = 0 and bidincrement = 0", accountIDFromRow, Integer.parseInt(CID));
					    ResultSet rs1 = stmt.executeQuery(sql1);
					    Float maxprice = null;
					   	while(rs1.next())
					   	{
					   		maxprice = rs1.getFloat("p");
					  	}
					   	//check if the current autobid is the highest price, if so check if there is a manual bid for that user and make it
						if(maxprice == null || maxprice < Float.valueOf(priceOfRow))
					   	{ 	
						   	createBid(String.valueOf(priceOfRow), "0", accountIDFromRow, CID);
						   	String sql2 = String.format("update clothing set cur_price = %f where CID = %d", priceOfRow, Integer.parseInt(CID));
						   	stmt.executeUpdate(sql2);
						   	price = String.valueOf(priceOfRow);
						   	System.out.println(price);
					 		countNumberOfUpdates++;			    	
					    }
					    else
					   	{
					    	System.out.println("There is a manual bid made for this winning autobid");
					   		//countNumberOfUpdates = 0;
					   	}
					}
					//if the current auto bid is less than the current price check if the update is possible and if so do it
					else if(priceOfRow < Float.valueOf(price))
					{
						System.out.println("Can it tell that the first bid is lower");
						//can make a bid 
						if((Float.valueOf(price) + bidincrement) < upperlimit)
						{
							System.out.println("Did i make it here to update Float.valueOf(price) + bidincrement");
							Float newPrice = Float.valueOf(price) + bidincrement;
							createBid(String.valueOf(newPrice), "0", accountIDFromRow, CID);
						   	String sql2 = String.format("update clothing set cur_price = %f where CID = %d", newPrice, Integer.parseInt(CID));
						   	stmt.executeUpdate(sql2);
						   	String sql3 = String.format("update bids set price = %f where Bid_ID = %d", newPrice, bidId);
							stmt.executeUpdate(sql3);
						   	price = String.valueOf(newPrice);
						   	System.out.println(price);
					 		countNumberOfUpdates++;	
						}
						else if((Float.valueOf(price) + bidincrement) > upperlimit)
						{
							Float newPrice = upperlimit;
							if(newPrice > Float.valueOf(price))
							{
								createBid(String.valueOf(newPrice), "0", accountIDFromRow, CID);
							   	String sql2 = String.format("update clothing set cur_price = %f where CID = %d", newPrice, Integer.parseInt(CID));
							   	stmt.executeUpdate(sql2);
							   	price = String.valueOf(newPrice);
							   	String sql3 = String.format("update bids set price = %f where Bid_ID = %d", newPrice, bidId);
							   	stmt.executeUpdate(sql3);
							   	countNumberOfUpdates++;	
							}
							else
							{
								String sql3 = String.format("update bids set price = %f where Bid_ID = %d", newPrice, bidId);
							   	stmt.executeUpdate(sql3);
							   	countNumberOfUpdates = 0;
							}
						}
						else
						{
							System.out.println("Reached upper limit of this autobid, alert in this case");
							countNumberOfUpdates = 0;
						}
					}
					else 
					{
						System.out.println("Autobid price is equal to the current price");
						countNumberOfUpdates = 0;
						System.out.println("Count number of updates:" + countNumberOfUpdates);
					}		
				}
				con.close();
				rs.close();
				//rs1.close();
				System.out.println("Value of countNumberOfUpdates before returning" + countNumberOfUpdates);
				if (countNumberOfUpdates != 0)
				{
					System.out.println("Recursion happens");
					updateAutoBid(price, CID);
				}
				//MAY be issues here
				System.out.println("Count number of updates for it to be true" + countNumberOfUpdates);
				return true;
			}
			catch (Exception ex)
			{
				System.out.println(ex);
				return false;
			}
		}

	// Retrieve all item listings
	public ArrayList<String[]> getListings() {
		try {

			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();

			// Forms sql to get all listing data
			String[] cats = { "shoes", "tops", "bottoms" };
			ArrayList<String[]> itemList = new ArrayList<String[]>();

			for (int i = 0; i < cats.length; i++) {

				String sql = String.format(
						"select c.cid, cat.category, cat.size, c.brand,c.name, c.cur_price, c.bid_increment, s.start_date, s.end_date,"
								+ "a.account_id from account a, sells s, clothing c, %s cat where s.cid = cat.cid and cat.cid = c.cid and a.account_id = s.account_id",
						cats[i]);

				// Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);

				// Iterates through the returned listings
				while (rs.next()) {
					String[] items = { rs.getString("cid"), rs.getString("category"), rs.getString("size"),
							rs.getString("brand"), rs.getString("name"),rs.getString("cur_price"), rs.getString("bid_increment"), rs.getString("start_date"),
							rs.getString("end_date"), rs.getString("account_id") };

					itemList.add(items);
				}
				rs.close();
			}

			// Close the connection
			con.close();
			return itemList;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	// Retrieve all similar item listings
		public ArrayList<String[]> getSimilarListings(String category) {
			try {

				// Get the database connection
				Connection con = this.getConnection();

				// Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql to get all listing data
				ArrayList<String[]> itemList = new ArrayList<String[]>();


				String sql = String.format(
						"select c.cid, cat.category, cat.size, c.brand, c.cur_price, s.start_date, s.end_date, a.account_id from account a, sells s, clothing c, %s cat where s.cid = cat.cid and cat.cid = c.cid and a.account_id = s.account_id and s.start_date between date_add(Now(), Interval -1 MONTH) and Now()", category);

				// Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);

				// Iterates through the returned listings
				while (rs.next()) {
					String[] items = { rs.getString("cid"), rs.getString("category"), rs.getString("size"),
							rs.getString("brand"), rs.getString("cur_price"), rs.getString("start_date"),
							rs.getString("end_date"), rs.getString("account_id") };

					itemList.add(items);
				}
				rs.close();

				// Close the connection
				con.close();
				return itemList;

			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
	
	public ArrayList<String[]> getHistory(String cid) {
		try {

			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();

			// Forms sql to get all bids
			ArrayList<String[]> bidList = new ArrayList<String[]>();

			String sql = String.format("select * from bids where cid = '%s' order by price ASC", cid);

			// Run the query against the DB and retrieves results
			ResultSet rs = stmt.executeQuery(sql);

			// Iterates through the returned listings
			while (rs.next()) {
				String[] bid_row = { rs.getString("BID_ID"), rs.getString("price"), rs.getString("account_id"), rs.getString("cid")};
				bidList.add(bid_row);
			}
			rs.close();
			
			// Close the connection
			con.close();
			return bidList;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<String[]> getBidderHistory(String bidder) {
		try {
			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();

			// Forms sql to get all bids
			ArrayList<String[]> bidderList = new ArrayList<String[]>();

			String sql = String.format("select * from bids where account_id='%s' and upper_limit = 0 and bidincrement = 0 and price in (select max(price) from bids where account_id='%s' group by cid)", bidder, bidder);

			// Run the query against the DB and retrieves results
			ResultSet rs = stmt.executeQuery(sql);

			// Iterates through the returned listings
			while (rs.next()) {
				String[] bidder_row = { rs.getString("BID_ID"), rs.getString("price"), rs.getString("account_id"), rs.getString("cid")};
				bidderList.add(bidder_row);

			}
			rs.close();

			// Close the connection
			con.close();
			return bidderList;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<String[]> getPersonalHistory(String user) {
		try {
			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();

			// Forms sql to get all auctions
			ArrayList<String[]> auctionList = new ArrayList<String[]>();

			String sql = String.format("select *, count(b.account_id) as p from bids b, clothing c where b.account_id = '%s' and c.cid = b.cid group by b.cid", user);

			// Run the query against the DB and retrieves results
			ResultSet rs = stmt.executeQuery(sql);
			String numOfBids = "";

			// Iterates through the returned listings
			while (rs.next()) {
				String cid = rs.getString("cid");
				
				String[] row = { cid, rs.getString("brand"), rs.getString("name"), rs.getString("p")};

				auctionList.add(row);

			}
			rs.close();
			
			// Close the connection
			con.close();
			return auctionList;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<String[]> getAlerts(String user) {
		try {
			
			//get todays date and time and convert it to a string
			 Date date = new Date();
			 SimpleDateFormat day = new SimpleDateFormat ("yyyy-MM-dd");
			 SimpleDateFormat time = new SimpleDateFormat ("HH:mm:ss");
			 String td = day.format(date);
			 String tim = time.format(date);
			 String today= (td + " " + tim);
			 
			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();
			
			ArrayList<String> cidList = new ArrayList<String>();
			
			// Forms sql to get all info necessary for creating user alerts
			ArrayList<String[]> alertList = new ArrayList<String[]>();
			
			 String cidSQL = String.format("select CID from Bids where account_id = '%s' group by CID", user);

			// Run the query against the DB and retrieves results
			ResultSet rs = stmt.executeQuery(cidSQL);

			// Iterates through the returned listings
			while (rs.next()) {
				cidList.add(String.valueOf(rs.getString("CID")));
				System.out.println(String.valueOf(rs.getString("CID")));
			}
			rs.close();
			
			// Loops through the CIDs that the logged in user has participated in to get any potential alert messages
			for (int i = 0; i < cidList.size(); i++) {
		
				int index = 0;
				
				String cid = cidList.get(i);
				System.out.println(cid);
				checkIfListingValid(Integer.valueOf(cid), user);
				
				// String array of alerts that will accumulate any alert messages specific to the logged in user
				String[] alerts = new String[4];
				alerts[index] = cid;
				index++;
				
				// Gets the user who holds the highest bid on a specific item
				String maxSQL = String.format("select account_id from bids where CID = '%d' and price ="
						+ "(select max(price) from bids where CID = '%d')", Integer.valueOf(cid), Integer.valueOf(cid));
				
				ResultSet rs2 = stmt.executeQuery(maxSQL);
				String userWinning = "";

				while (rs2.next()) {
					userWinning = rs2.getString("account_id");
				}
				rs2.close();
				
				if (user.equals(userWinning)) {
					alerts[index] = "You have the highest bid";
					index++;
				} else {
					alerts[index] = "There has been a higher bid placed";
					index++;
				}
				System.out.println("herehrehrh");
				if (endOfauction(today, Integer.valueOf(cid))) {
					String winnerSQL = String.format("select account_id from bids where CID = '%d' and winner = 1", Integer.valueOf(cid));
					
					ResultSet rsWinner = stmt.executeQuery(winnerSQL);
					String winner = "";

					// Checks winner of the auction
					while (rsWinner.next()) {
						winner = rsWinner.getString("account_id");
					}
					rsWinner.close();
										
					if (user.equals(winner)) {
						alerts[index] = "You have WON";
						index++;
					} else {
						alerts[index] = "You did not win";
						index++;
					}
				} else {
					alerts[index] = "Auction is not over";
					index++;
				}
				
				alerts[index] = "upper limit";
				alertList.add(alerts);
			}
			
			// Close connection
			con.close();
			return alertList;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private boolean accountIsValid(String accountID) {
		try {
			if (accountID.equals("")) {
				System.out.println("Is the account empty");
				return false;
			}

			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();

			// Forms sql select query with given account id
			String sql = String.format("select account_id from account where account_id = '%s'", accountID);

			// Run the query against the DB and retrieves accounts
			ResultSet rs = stmt.executeQuery(sql);

			// Iterates through the returned rows (should only be 1 row) to see if if the
			// account exists
			while (rs.next()) {
				if (rs.getString("account_id").equals(accountID)) {
					con.close();
					rs.close();
					return true;
				} else {
					continue;
				}
			}

			// Close the connection with no account match
			rs.close();
			con.close();
			System.out.println("Account did not match");
			return false;

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Try catch failed");
			return false;
		}
	}

	private static boolean compareDates(String d1, String d2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = (Date) sdf.parse(d1);
			Date date2 = (Date) sdf.parse(d2);

			// after() will return false if date1 is after date 2
			if (date1.after(date2)) {
				return false;
			}
			// before() will return true if and only if date1 is before date2
			if (date1.before(date2)) {
				return true;
			}

			// equals() returns false if both the dates are equal
			if (date1.equals(date2)) {
				return false;
			}
			return false;
		} catch (ParseException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	//rob
		//checks if the we have reached the end of the auction time that the user set
		public boolean endOfauction (String today, int CID ){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given time
				String sql = String.format("select CID, End_date from Sells where CID = '%d' ", CID);
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
			//	System.out.println ("HERE: ");
				
				// Iterates through the returned rows (should only be 1 row) to see if if the account with the correct password exists
				while (rs.next()) {
					//System.out.println ("end date: " + sdf.parse(rs.getString("End_date")));
					if (rs.getString("CID").equals(String.valueOf(CID)) && ( (sdf.parse(today).after(sdf.parse(rs.getString("End_date")))) || (sdf.parse(today).equals(sdf.parse(rs.getString("End_date"))))  )) {
						//System.out.println("Time is up");
						con.close();
						rs.close();
						//System.out.println ("HERE: worked");
						return true;
					} else {
						//System.out.println ("HERE: midb");
						break;
					}
				}
				//System.out.println ("HERE: end ");
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
		
		//checks if there is a min price set by the seller and if so it returns the price else it returns 0
		public float minPrice (int CID){
			try {
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given time
				String sql = String.format("select Minimum from Sells where CID = '%d'", CID); //gets the lowest price set by seller
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
				
				// Iterates through the returned rows (should only be 1 row) to see if if the account with the correct password exists
				while (rs.next()) {
					if (rs.getString("Minimum") != null) {
						//System.out.println ("HERE: worked2");
						float minprice = Float.parseFloat(rs.getString("Minimum")); //this is the min price in our table as float
						con.close();
						rs.close();
						return minprice;
					} else {
						break;
					}
				}

				//Close the connection with no account match
				rs.close();
				con.close();
				return 0;
									
			} catch (Exception ex) {
				System.out.println(ex);
				//System.out.println("Account Does Not Exist");
				return 0;
			}
		}
		
		public float highestBid(int CID){ //finds the highest bider account id on a cid
			try {
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given time
				String sql = String.format("select  max(price) as p from Bids where CID = '%d'", CID);//get the max price bid on that cid
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
				float highestBid = 0;
				// Iterates through the returned rows (should only be 1 row) to see if if the account with the correct password exists
				while (rs.next()) {
					
					if (rs.getString("p") != null) {
						//System.out.println ("HERE: worked3");
						 highestBid = Float.parseFloat(rs.getString("p")); //this is the max bid price in our table
						con.close();
						rs.close();
						return highestBid;
					} else {
						break;
					}
				}

				//Close the connection with no account match
				rs.close();
				con.close();
				return highestBid;
									
			} catch (Exception ex) {
				System.out.println(ex);
				//System.out.println("Account Does Not Exist");
				return 0;
			}
		}
		
		
		public int highestBidAid (int CID){ //finds the highest bider account id on a cid
			try {
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given time
				String sql = String.format("select Bid_id from Bids where price =(select  max(price) as p from Bids where CID = '%d')", CID);//get the max price bid on that cid
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
				int accountW_highestBid = -1;
				// Iterates through the returned rows (should only be 1 row) to see if if the account with the correct password exists
				while (rs.next()) {
					//System.out.println ("HERE: worked5");
						 accountW_highestBid = Integer.parseInt(rs.getString("Bid_id")); //this is the min price in our table
						con.close();
						rs.close();
						return accountW_highestBid;
				}

				//Close the connection with no account match
				rs.close();
				con.close();
				return accountW_highestBid;
									
			} catch (Exception ex) {
				System.out.println(ex);
				//System.out.println("Account Does Not Exist");
				return -1;
			}
		}
		
		public void setWinner (int Bid_id) {
			Connection con = this.getConnection();
			Statement stmt = null;
			try {
				stmt = con.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		String setsWinner = String.format("update Bids set winner = '1' where Bid_id = '%d'", Bid_id);
		
		//System.out.println ("HERE: worked8");
		try { //update 
			stmt.executeUpdate(setsWinner);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Close the connection with no account match
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	//Close the connection with no account match
	try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
		public boolean checkIfListingValid(int cid, String accountID) {
			try {
			
			if (accountID.equals("")) {
				System.out.println("Is the account empty");
				return false;
			}
		
			//get todays date and time and convert it to a string
			 Date date = new Date();
			 SimpleDateFormat day = new SimpleDateFormat ("yyyy-MM-dd");
			 SimpleDateFormat time = new SimpleDateFormat ("HH:mm:ss");
			 String td = day.format(date);
			 String tim = time.format(date);
			 String today= (td + " " + tim);
			 
			 //System.out.println(today);
			//When the user loads the auction, you check if it is expired or not and do the corresponding things
			if (endOfauction(today, cid)) { //call function to determine that the date and time matches a product thats auction time is up
				if ( minPrice(cid) == 0){ //means that there is no min value
					float winBid = highestBid(cid);
					int winnerBidID = highestBidAid(cid);//find the highest bid acc
					setWinner(winnerBidID); //Store account with highest bid as winner in  bids table by setting winner value to 1
					return false;
				}
				else if (minPrice(cid) <= highestBid(cid)){ //means there is a min value
					float winBid = highestBid(cid);
					int winnerBidID = highestBidAid(cid); //find the highest bid acc
					setWinner(winnerBidID);//Store bid_id with highest bid as winner in bids table by setting winner value to 1
					return false;
				}
				//means that auction is closed
				return false;
				
			
			}
			else {
				//System.out.println("auction is not over");
				return true;
			}
			
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	//////////ROB	
		public String totalEarnings (){ //finds total sales
			try {
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given time
				String sql = String.format("select sum(price) as p from Bids where winner = '1'");
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
				String total = "";
				while (rs.next()) {
					//System.out.println ("HERE: worked5");
						 total = rs.getString("p"); 
						 if (total == null) {
							 total = "0";
						 }
						con.close();
						rs.close();
						return total;	
				}

				//Close the connection with no account match
				rs.close();
				con.close();
				return "0";
									
			} catch (Exception ex) {
				System.out.println(ex);
				//System.out.println("Account Does Not Exist");
				return "0";
			}
		}
		
		public String bottomsEarnings (){ //finds total sales for bottoms
			try {
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given time
				String sql = String.format("select sum(price) as p from Bids join bottoms using (CID) where winner = '1'");
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
				String total = "";
				while (rs.next()) {
					//System.out.println ("HERE: worked5");
						 total = rs.getString("p"); 
						 if (total == null) {
							 total = "0";
						 }
						con.close();
						rs.close();
						return total;	
				}

				//Close the connection with no account match
				rs.close();
				con.close();
				return "0";
									
			} catch (Exception ex) {
				System.out.println(ex);
				//System.out.println("Account Does Not Exist");
				return "0";
			}
		}
		public String shoesEarnings (){ //finds total sales for shoes
			try {
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given time
				String sql = String.format("select sum(price) as p from Bids join Shoes using (CID) where winner = '1'");
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
				String total = "";
				while (rs.next()) {
					//System.out.println ("HERE: worked5");
						 total = rs.getString("p"); 
						 if (total == null) {
							 total = "0";
						 }
						con.close();
						rs.close();
						return total;
				}
				//Close the connection with no account match
				rs.close();
				con.close();
				return "0";
									
			} catch (Exception ex) {
				System.out.println(ex);
				//System.out.println("Account Does Not Exist");
				return "0";
			}
		}
		
		public String topsEarnings (){ //finds total sales for tops
			try {
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given time
				String sql = String.format("select sum(price) as p from Bids join Tops using (CID) where winner = '1'");
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
				String total = "";
				while (rs.next()) {
					//System.out.println ("HERE: worked5");
						 total = rs.getString("p");
						 //System.out.println ("HERE: worked89");
						 if (total == null) {
							 total = "0";
							// System.out.println ("HERE: sdsfewfr");
						 }
						con.close();
						rs.close();
						//System.out.println ("HERE: sxsaxsxaxas");
						return total;	
				}

				//Close the connection with no account match
				rs.close();
				con.close();
				return "0";
									
			} catch (Exception ex) {
				System.out.println(ex);
				//System.out.println("Account Does Not Exist");
				return "0";
			}
		}
		
		public String userEarnings (String acc){ //finds total sales for shoes
			try {
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given time
				String sql = String.format("select sum(price) as p from Bids join Sells s using (CID) where winner = '1' and s.account_id = '%s'", acc);
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
				String total = "";
				while (rs.next()) {
					//System.out.println ("HERE: worked5");
						 total = rs.getString("p"); 
						 if (total == null) {
							 total = "0";
						 }
						con.close();
						rs.close();
						return total;	
				}

				//Close the connection with no account match
				rs.close();
				con.close();
				return "0";
									
			} catch (Exception ex) {
				System.out.println(ex);
				//System.out.println("Account Does Not Exist");
				return "0";
			}
		}

		public boolean searchAccountExists(String givenAccountID) { //in the admin functions check if a user they are searching for exist
			try {
				// Log In
				if (givenAccountID == null) {
					return false;
				}

				// Get the database connection
				Connection con = this.getConnection();

				// Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given account id and password
				String sql = String.format(
						"select account_id from account where account_id = '%s'",
						givenAccountID);

				// Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);

				// Iterates through the returned rows (should only be 1 row) to see if if the
				// account with the correct password exists
				while (rs.next()) {
					if (rs.getString("account_id").equals(givenAccountID)
							) {
						//System.out.println("ACCOUNT EXISTS - Logged In");
						con.close();
						rs.close();
						return true;
					} else {
						break;
					}
				}
				// Close the connection with no account match
				rs.close();
				con.close();
				return false;

			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
		public ArrayList<String[]> topBuyers () {
			try {

				// Get the database connection
				Connection con = this.getConnection();

				// Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql to get all bids
				ArrayList<String[]> bidderList = new ArrayList<String[]>();
				
				String sql = String.format("select account_id, count(account_id) as p from Bids where winner = '1'  group by account_id ORDER BY p DESC");

				// Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);

				// Iterates through the returned listings
				while (rs.next()) {
					String[] bidder_row = { rs.getString("account_id"), rs.getString("p")};
					bidderList.add(bidder_row);
				}
				rs.close();
				
				// Close the connection
				con.close();
				return bidderList;

			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		public boolean searchItem(String item) { //in the admin functions check if a user they are searching for exist
			try {
				// Log In

				if (item == null) {
					return false;
				}

				// Get the database connection
				Connection con = this.getConnection();

				// Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given account id and password
				String sql = String.format(
						"select name from Clothing where name = '%s'",
						item);

				// Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);

				// Iterates through the returned rows (should only be 1 row) to see if if the
				// account with the correct password exists
				while (rs.next()) {
					if (rs.getString("name").equals(item)
							) {
						//System.out.println("ACCOUNT EXISTS - Logged In");
						con.close();
						rs.close();
						return true;
					} else {
						break;
					}
				}
				// Close the connection with no account match
				rs.close();
				con.close();
				return false;

			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
		public String itemEarnings (String item){ //finds total sales for shoes
			try {		
				//Get the database connection
				Connection con = this.getConnection();

				//Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql select query with given time
				String sql = String.format("select sum(price) as p from Bids join Clothing s using (CID) where winner = '1' and s.name = '%s'", item);
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
				String total = "";
				while (rs.next()) {
					//System.out.println ("HERE: worked5");
						 total = rs.getString("p"); 
						 if (total == null) {
							 total = "0";
						 }
						con.close();
						rs.close();
						return total;
				}
				//Close the connection with no account match
				rs.close();
				con.close();
				return "0";
									
			} catch (Exception ex) {
				System.out.println(ex);
				//System.out.println("Account Does Not Exist");
				return "0";
			}
		}
		public ArrayList<String[]> topItems() {
			try {
				// Get the database connection
				Connection con = this.getConnection();

				// Create a SQL statement
				Statement stmt = con.createStatement();

				// Forms sql to get all bids
				ArrayList<String[]> bidderList = new ArrayList<String[]>();

				String sql = String.format("select name, count(winner) as p from Bids join Clothing s using (CID) where winner = '1'  group by name ORDER BY p DESC");

				// Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);

				// Iterates through the returned listings
				while (rs.next()) {
					String[] bidder_row = { rs.getString("name"), rs.getString("p")};
					bidderList.add(bidder_row);
				}
				rs.close();
				
				// Close the connection
				con.close();
				return bidderList;
				
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		// TODO: Ignore this method, havent finish yet.
				public boolean EditAnswer(String Q, String A) {
					try {
								
						Connection con = this.getConnection();
						Statement stmt = con.createStatement();
						
						String sql1 = String.format("update QA set A='%s' where Q='%s'", A, Q);
						//System.out.println(sql1);
						stmt.executeUpdate(sql1);
								
								
						con.close();
						return true;

					} catch (Exception ex) {
						System.out.println(ex);
						return false;
					}
				}

				public ArrayList<String[]> getQAs() {
					try {

						// Get the database connection
						Connection con = this.getConnection();

						// Create a SQL statement
						Statement stmt = con.createStatement();
						ArrayList<String[]> QAList = new ArrayList<String[]>();

						String sql = "select Q, A from QA;";
						// Run the query against the DB and retrieves results
						ResultSet rs = stmt.executeQuery(sql);

						// Iterates through the returned listings
						while (rs.next()) {
						String[] QAs = {rs.getString("Q"), rs.getString("A")};
									
									
						QAList.add(QAs);
						System.out.println(rs.getString("Q"));
						}
						rs.close();
								

						// Close the connection
						con.close();
						return QAList;

					} catch (Exception ex) {
						ex.printStackTrace();
						return null;
					}
				}

				public boolean createQuestion(String Q)
				{
					try {

						if (Q.equals("")){
							return false;
						}

						Connection con = this.getConnection();

						Statement stmt = con.createStatement();

						String A = "Waiting for answer ...";
						// Forms sql insert query for Clothing, Sells, and ISA Category tables with data
						String instruction = "insert into QA (Q, A) values (\""+Q+"\",\""+A+"\");";

						stmt.executeUpdate(instruction);
								
						// Close the connection with no account match
						con.close();
						return true;

					} catch (Exception ex) {
						System.out.println(ex);
						return false;
					}
				}

				public boolean isCR(String givenAccountID) {
					try {
						// Log In

						if (givenAccountID == null) {
							return false;
						}

						// Get the database connection
						Connection con = this.getConnection();

						// Create a SQL statement
						Statement stmt = con.createStatement();

						// Forms sql select query with given account
						String sql = String.format(
								"select account_id  from customer_representative where account_id = '%s'",
								givenAccountID);

						// Run the query against the DB and retrieves results
						ResultSet rs = stmt.executeQuery(sql);

						// Iterates through the returned rows (should only be 1 row) to see if if the
						// account with the correct password exists
						while (rs.next()) {
							if (rs.getString("account_id").equals(givenAccountID)) {
								
								con.close();

								return true;
							} else {
								break;
							}
						}

						// Close the connection with no account match
						rs.close();
						con.close();
						return false;

					} catch (Exception ex) {
						ex.printStackTrace();
						return false;
					}
				}

				public boolean editAccount(String givenAccountID, String newPasswd)
				{
					try {
					if (givenAccountID == null || newPasswd == null) {
						return false;
					}

					// Get the database connection
					Connection con = this.getConnection();

					// Create a SQL statement
					Statement stmt = con.createStatement();

					// Forms sql select query with given account id and password
					String sql = String.format(
							"select account_id, password from account where account_id = '%s'",
							givenAccountID);

					// Run the query against the DB and retrieves results
					ResultSet rs = stmt.executeQuery(sql);

					// Iterates through the returned rows (should only be 1 row) to see if if the
					while (rs.next()) {
						if (rs.getString("account_id").equals(givenAccountID)) {
							System.out.println("ACCOUNT EXISTS - Can do edit account information operation");
							
							// update the account information
							sql = "update account set password='"+newPasswd+"' where account_id='"+givenAccountID+"'";
							stmt.executeUpdate(sql);
							System.out.println(givenAccountID+" password update to "+ newPasswd);
							rs.close();
							con.close();
							return true;
						} else {
							break;
						}
					}

					// Close the connection with no account match
					rs.close();
					con.close();
					return false;
					}
				catch (Exception ex) {
					ex.printStackTrace();
					return false;
					}
				}

				public boolean removeBid(String bid_id, String bid_account, String bid_cloth)
				{
					try {
						if (bid_id == null || bid_account == null || bid_cloth == null) {
							return false;
						}

						// Get the database connection
						Connection con = this.getConnection();

						// Create a SQL statement
						Statement stmt = con.createStatement();

						// Check if the target bid exist
						String sql = String.format(
								"select Bid_ID from Bids where Bid_ID='%s' and account_id='%s' and CID='%s'",
								bid_id, bid_account, bid_cloth);

						// Run the query against the DB and retrieves results
						ResultSet rs = stmt.executeQuery(sql);

						// Iterates through the returned rows (should only be 1 row) to see if if the
						while (rs.next()) {
							if (rs.getString("Bid_ID").equals(bid_id)) {
								System.out.println("Bid EXISTS - Can do remove bid operation");
								
								// update the account information
								sql = String.format("delete from Bids where Bid_ID='%s' and account_id='%s' and CID='%s'",bid_id, bid_account, bid_cloth);
								stmt.execute(sql);
								System.out.println(bid_id+" has been deleted.");
								rs.close();
								con.close();
								return true;
							} else {
								break;
							}
						}

						// Close the connection with no account match
						rs.close();
						con.close();
						return false;
						}
					catch (Exception ex) {
						ex.printStackTrace();
						return false;
						}
				}

				public boolean removeAuction(String auc_cloth) 
				{
					try {
						
						// Get the database connection
						Connection con = this.getConnection();

						// Create a SQL statement
						Statement stmt = con.createStatement();

						// Check if the target bid exist
						String sql = String.format(
								"select CID from Clothing where CID='%s'",
								auc_cloth);

						// Run the query against the DB and retrieves results
						ResultSet rs = stmt.executeQuery(sql);

						// Iterates through the returned rows (should only be 1 row) to see if if the
						while (rs.next()) {
							if (rs.getString("CID").equals(String.valueOf(auc_cloth))) {
								System.out.println("Cloth EXISTS - Can do remove auction operation");
								
								// update the account information
								sql = String.format("delete from Clothing where CID=%s",auc_cloth);
								stmt.execute(sql);
								
								System.out.println("Auction with cloth("+auc_cloth+") has been deleted.");
								rs.close();
								con.close();
								return true;
							} else {
								break;
							}
						}

						// Close the connection with no account match
						rs.close();
						con.close();
						return false;
						}
					catch (Exception ex) {
						ex.printStackTrace();
						return false;
						}
				}
				public void loopListing() {
					try {
					

					// Get the database connection
					Connection con = this.getConnection();

					// Create a SQL statement
					Statement stmt = con.createStatement();

					// Forms sql select query with given account id and password
					String sql = String.format("select CID from Bids where winner = 0 group by CID");

					// Run the query against the DB and retrieves results
					ResultSet rs = stmt.executeQuery(sql);

					// Iterates through the returned rows (should only be 1 row) to see if if the
					while (rs.next()) {
						checkIfListingValid(Integer.parseInt(rs.getString("CID")), "x");
							
					}
							rs.close();
							con.close();
							
					// Close the connection with no account match
					rs.close();
					con.close();
				
					}
				catch (Exception ex) {
					ex.printStackTrace();
				
					}
				}
}