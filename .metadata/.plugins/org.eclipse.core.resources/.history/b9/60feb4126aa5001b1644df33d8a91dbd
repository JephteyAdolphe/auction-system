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
		String connectionUrl = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false"; // project is the database created in mysql
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
		dao.getListings();
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

	public boolean createCRAccount(String givenAccountID, String givenPassword) {
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
				return false;
			}

			// Checks if a valid account id is submitted when creating listing... should be
			// obtained from session object though
			if (!accountIsValid(accountID))
			{
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
			
			String endDateTime = endD[2] + "-" + endD[0] + "-" + endD[1] + " " + endT[0] + ":" + endT[1] + ":"
					+ endT[2];

			// Checks that start date/time is before end date/time
			if (!compareDates(startDateTime, endDateTime))
			{
				return false;
			}

			// Notes item type that is being listed
			String category = null;
			String size = null;
			if (itemType == null) {
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

			String clothingSQL = String.format(
			"insert into clothing (name, brand, bid_increment,"
					+ "cur_price, start_price) values ( '%s', '%s', %f, %f, %f)",
			 name, brand, Float.parseFloat(bidIncrement), Float.parseFloat(startPrice),
			Float.parseFloat(startPrice));
			
			stmt.executeUpdate(clothingSQL);
			

			
			String getCID = String.format("select max(CID) as CID from clothing");
			ResultSet rs = stmt.executeQuery(getCID);
			
			int CID = 0;

			while(rs.next())
			{
				CID = rs.getInt("CID");
			}
			
			String categorySQL = String.format("insert into %s (CID, category, size) values (%d, '%s', '%s')", category, CID,
			 category, size);
			

			stmt.executeUpdate(categorySQL);
			
			String sellsSQL = String.format(
					"insert into sells (account_id, cid, minimum, start_date,"
							+ "end_date) values ('%s',%d, %f, '%s', '%s')",
					accountID, CID, Float.parseFloat(minPrice), startDateTime, endDateTime);
			
			stmt.executeUpdate(sellsSQL);

			// Close the connection with no account match
			con.close();
			return true;

		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	// Creates watch alert for future item
	public boolean createWatchAlert(String name, String user) {
		try {

			if (name.trim().equals("") || user.equals("")) {
				return false;
			}

			// Checks if a valid account id is submitted when creating watch alert
			if (!accountIsValid(user))
			{
				return false;
			}

			// Get the database connection
			Connection con = this.getConnection();
			
			String alertExists = "";

			// Create a SQL statement
			Statement stmt = con.createStatement();
							
			String sqlCheck = String.format("select info from watching_alert where account_id = '%s'", user);

			// Run the query against the DB and retrieves results
			ResultSet rs = stmt.executeQuery(sqlCheck);

			while (rs.next()) {
				alertExists = rs.getString("info");
			}
			rs.close();
			
			if (!alertExists.equals("")) {
				String updateSQL = String.format("UPDATE watching_alert set info = '%s' where account_id = '%s'", name, user);
				stmt.executeUpdate(updateSQL);
				con.close();
				return true;
			}

			// Forms sql insert watch alert
			String sql = String.format("insert into watching_alert (info, account_id) values ('%s', '%s')", name, user);
			stmt.executeUpdate(sql);

			con.close();
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
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
					
					String sql1 = String.format("select max(price) as p, account_id as accid from bids where account_id = '%s' and CID = %d and upper_limit = 0 and bidincrement = 0", accountIDFromRow, Integer.parseInt(CID));
				    ResultSet rs1 = stmt.executeQuery(sql1);
				    
				    Float maxprice = null;
				    String accId = null;
				   	while(rs1.next())
				   	{
				   		maxprice = rs1.getFloat("p");
				   		accId = rs1.getString("accid");
				  	}
				    
				   	
					//checks if the autobid is greater than the current price
					if(priceOfRow >= Float.valueOf(price))
				 	{
						//check if a manual bid was placed with the value of the autobid
					   	//check if the current autobid is the highest price, if so check if there is a manual bid for that user and make it
						if(maxprice == null || maxprice < Float.valueOf(priceOfRow))
					   	{ 	
						   	createBid(String.valueOf(priceOfRow), "0", accountIDFromRow, CID);
						   	String sql2 = String.format("update clothing set cur_price = %f where CID = %d", priceOfRow, Integer.parseInt(CID));
						   	stmt.executeUpdate(sql2);
						   	price = String.valueOf(priceOfRow);
					 		countNumberOfUpdates++;			    	
					    }
					    else if(maxprice > Float.valueOf(priceOfRow) && maxprice < upperlimit)
					    {
					    	String sql2 = String.format("update clothing set cur_price = %f where CID = %d", maxprice, Integer.parseInt(CID));
						   	stmt.executeUpdate(sql2);
						   	price = String.valueOf(maxprice);
					    }
					    else if(maxprice > Float.valueOf(priceOfRow) && maxprice > upperlimit)
					    {
					    	String sql2 = String.format("update clothing set cur_price = %f where CID = %d", upperlimit, Integer.parseInt(CID));
						   	stmt.executeUpdate(sql2);
						   	price = String.valueOf(upperlimit);
					    }
					    else
					   	{
					   		countNumberOfUpdates = 0;
					   	}
					}
					//if the current auto bid is less than the current price check if the update is possible and if so do it
					else if(priceOfRow < Float.valueOf(price))
					{
						//can make a bid 
						if((Float.valueOf(price) + bidincrement) < upperlimit)
						{
							Float newPrice = Float.valueOf(price) + bidincrement;
							createBid(String.valueOf(newPrice), "0", accountIDFromRow, CID);
						   	String sql2 = String.format("update clothing set cur_price = %f where CID = %d", newPrice, Integer.parseInt(CID));
						   	stmt.executeUpdate(sql2);
						   	String sql3 = String.format("update bids set price = %f where Bid_ID = %d", newPrice, bidId);
							stmt.executeUpdate(sql3);
						   	price = String.valueOf(newPrice);
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
							countNumberOfUpdates = 0;
						}
					}
					else 
					{
						countNumberOfUpdates = 0;
					}		
				}
				con.close();
				rs.close();
				if (countNumberOfUpdates != 0)
				{
					updateAutoBid(price, CID);
				}
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

			String sql = String.format("select * from bids where cid = '%s' and upper_limit = 0 and bidincrement = 0 order by price ASC", cid);

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

			String sql = String.format("select *, count(b.account_id) as p from bids b, clothing c where b.account_id = '%s' and c.cid = b.cid  and upper_limit = 0 and bidincrement = 0 group by b.cid", user);

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
	
	public ArrayList<String[]> getPersonalSellHistory(String user) {
		try {
			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();

			// Forms sql to get all auctions
			ArrayList<String[]> auctionList = new ArrayList<String[]>();

			// Forms sql to get all listing data
			String[] cats = { "shoes", "tops", "bottoms" };

			for (int i = 0; i < cats.length; i++) {

				String sql = String.format(
						"select c.cid, cat.category, cat.size, c.brand,c.name, c.cur_price, c.bid_increment, s.start_date, s.end_date,"
								+ " a.account_id from account a, sells s, clothing c, %s cat where s.cid = cat.cid and cat.cid = c.cid"
								+ " and a.account_id = s.account_id and a.account_id = '%s'", cats[i], user);
						

				// Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);

				// Iterates through the returned listings
				while (rs.next()) {
					String[] items = { rs.getString("cid"), rs.getString("category"), rs.getString("size"),
							rs.getString("brand"), rs.getString("name"),rs.getString("cur_price"), rs.getString("bid_increment"), rs.getString("start_date"),
							rs.getString("end_date") };

					auctionList.add(items);
				}
				rs.close();
			}
			
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
			}
			rs.close();
			
			// Loops through the CIDs that the logged in user has participated in to get any potential alert messages
			for (int i = 0; i < cidList.size(); i++) {
				
				String leadUser = "";
				String leadPrice = "";
				String upper = "";
				String upperAlert = "";
				
				// Sql to see if upper_limit has been surpassed
				String upperSQL = String.format("select * from bids where account_id = '%s'", user);
				String leadUserSQL = String.format("select * from bids where cid = '%s'", cidList.get(i));
				
				ResultSet rsLead = stmt.executeQuery(leadUserSQL);

				// Iterates through the returned bids
				while (rsLead.next()) {
					// Last user will be the user who is winning the listing
					leadUser = rsLead.getString("account_id");
					leadPrice = rsLead.getString("price");
				}
				rsLead.close();
				
				ResultSet rsUpper = stmt.executeQuery(upperSQL);

				// Iterates through the returned bids
				while (rsUpper.next()) {
					// Last upper limit will be the most recent
					upper = rsUpper.getString("upper_limit");
				}
				rsUpper.close();
				if(upper.equals("0")) {
					upperAlert = "No Upper Limit Was Set";
				}
				else if (user.equals(leadUser)) {
					upperAlert = "No";
				} else if (!user.equals(leadUser) && Integer.valueOf(leadPrice) > Integer.valueOf(upper)) {
					upperAlert = "Yes";
				} else {
					upperAlert = "No";
				}
		
				int index = 0;
				
				String cid = cidList.get(i);
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
				
				alerts[index] = upperAlert;
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
	
	public ArrayList<String[]> getWatchAlerts(String user) {
		try {

			// Get the database connection
			Connection con = this.getConnection();

			// Create a SQL statement
			Statement stmt = con.createStatement();
						
			ArrayList<String> infoList = new ArrayList<String>();
			ArrayList<String[]> alertList = new ArrayList<String[]>();
			
			 String alertSQL = String.format("select info from watching_alert where account_id = '%s'", user);

			// Run the query against the DB and retrieves results
			ResultSet rs = stmt.executeQuery(alertSQL);

			// Iterates through the returned listings
			while (rs.next()) {
				infoList.add(String.valueOf(rs.getString("info")));
			}
			rs.close();
			
			// Loops through the CIDs that the logged in user has participated in to get any potential alert messages
			for (int i = 0; i < infoList.size(); i++) {
				
				String[] alert = new String[2];
				alert[0] = infoList.get(i);
				
				// Gets the name of all items to see if the watched item has been listed
				String nameSQL = String.format("select name from clothing where name = '%s'", infoList.get(i));
				
				ResultSet rs2 = stmt.executeQuery(nameSQL);
				
				String listed = "";

				while (rs2.next()) {
					listed = rs2.getString("name");
				}
				rs2.close();
				
				if (listed.equals("")) {
					alert[1] = "No";
				} else {
					alert[1] = "Yes";
				}
				alertList.add(alert);
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
			return false;

		} catch (Exception ex) {
			ex.printStackTrace();
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
				
				// Iterates through the returned rows (should only be 1 row) to see if if the account with the correct password exists
				while (rs.next()) {
					if (rs.getString("CID").equals(String.valueOf(CID)) && ( (sdf.parse(today).after(sdf.parse(rs.getString("End_date")))) || (sdf.parse(today).equals(sdf.parse(rs.getString("End_date"))))  )) {
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
				String sql = String.format("select Bid_id from Bids where price =(select  max(price) as p from Bids where CID = '%d') and CID = '%d' and upper_limit = '0' and bidincrement ='0'",CID, CID);//get the max price bid on that cid
				
				//Run the query against the DB and retrieves results
				ResultSet rs = stmt.executeQuery(sql);
				int accountW_highestBid = -1;
				// Iterates through the returned rows (should only be 1 row) to see if if the account with the correct password exists
				while (rs.next()) {
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
				return false;
			}
		
			//get todays date and time and convert it to a string
			 Date date = new Date();
			 SimpleDateFormat day = new SimpleDateFormat ("yyyy-MM-dd");
			 SimpleDateFormat time = new SimpleDateFormat ("HH:mm:ss");
			 String td = day.format(date);
			 String tim = time.format(date);
			 String today= (td + " " + tim);
			 
			//When the user loads the auction, you check if it is expired or not and do the corresponding things
			if (endOfauction(today, cid)) { //call function to determine that the date and time matches a product thats auction time is up
				if ( minPrice(cid) == 0){ //means that there is no min value
					int winnerBidID = highestBidAid(cid);//find the highest bid acc
					setWinner(winnerBidID); //Store account with highest bid as winner in  bids table by setting winner value to 1
					return false;
				}
				else if (minPrice(cid) <= highestBid(cid)){ //means there is a min value
					int winnerBidID = highestBidAid(cid); //find the highest bid acc
					setWinner(winnerBidID);//Store bid_id with highest bid as winner in bids table by setting winner value to 1
					return false;
				}
				//means that auction is closed
				return false;
				
			
			}
			else {
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

				String sql = String.format("Create temporary table items(\n"
						+ "select *,  count(winner) as p from ((Bids join Clothing s using (CID))join shoes i using (CID)) where winner = '1'  group by name, brand\n"
						+ "union \n"
						+ "select *,  count(winner) as p from ((Bids join Clothing s using (CID))join tops i using (CID)) where winner = '1'  group by name, brand\n"
						+ "union \n"
						+ "select *,  count(winner) as p from ((Bids join Clothing s using (CID))join bottoms i using (CID)) where winner = '1'  group by name, brand\n"
						+ ") ;\n");
					
				String sql12 = String.format( "select name,brand, category, p from items where winner = '1'");
					String sql2=String.format("drop table items;");

				// Run the query against the DB and retrieves results
				stmt.executeUpdate(sql);
				ResultSet rs = stmt.executeQuery(sql12);
				
				// Iterates through the returned listings
				while (rs.next()) {
					String[] bidder_row = { rs.getString("name"),rs.getString("brand"), rs.getString("category"), rs.getString("p")};
					bidderList.add(bidder_row);
				}
				rs.close();
				stmt.executeUpdate(sql2);
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
							
							// update the account information
							sql = "update account set password='"+newPasswd+"' where account_id='"+givenAccountID+"'";
							stmt.executeUpdate(sql);
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
								//rs.close();
								// update the account information
							String	sql14 = String.format("delete from Bids where Bid_ID='%s' and account_id='%s' and CID='%s'",bid_id, bid_account, bid_cloth);
								stmt.executeUpdate(sql14);
//								sql = String.format("update Clothing set cur_price = (select price from Bids where cid=%s and price >= all (select price from Bids)) where CID=%s;",bid_cloth,bid_cloth);
//                                stmt.executeUpdate(sql);
								//rs.close();
							} else {
								break;
							}
						}
						rs.close();
						System.out.println("hi1");
						
						String sql1 = String.format("select max(price) as p from bids");
						System.out.println("hi2");
						ResultSet rs1 = stmt.executeQuery(sql1);
						System.out.println("hi3");
						float p = -1;
						while (rs1.next()) {
							p = rs1.getFloat("p");
								//rs1.close();
						}
						rs1.close();
						if (p == -1) {
							String sql2 = String.format("select start_price from clothing where CID = %d", bid_cloth);
							ResultSet rs2 = stmt.executeQuery(sql2);
							while (rs2.next()) {
								p = rs2.getFloat("start_price");
									//rs2.close();
							}
							rs2.close();
							String sql3 = String.format("update Clothing set cur_price = '%f' where CID=%s;",p,bid_cloth);
                        stmt.executeUpdate(sql3);
                        con.close();
                        return true;
						}
						else if( p != -1) {
							String sql3 = String.format("update Clothing set cur_price = '%f' where CID=%s;",p,bid_cloth);
	                        stmt.executeUpdate(sql3);
	                        con.close();
	                        return true;
						}
						else { 
							 con.close();
		                     return false;
						}
						
						
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
								// update the account information
								sql = String.format("delete from Clothing where CID=%s",auc_cloth);
								stmt.execute(sql);
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
				public boolean delAccount(String account_id) 
				{
					try {
						// Get the database connection
						Connection con = this.getConnection();

						// Create a SQL statement
						Statement stmt = con.createStatement();

						// Check if the target bid exist
						String sql = String.format(
								"select account_id from account where account_id='%s'",
								account_id);
						
						// Run the query against the DB and retrieves results
						ResultSet rs = stmt.executeQuery(sql);

						// Iterates through the returned rows (should only be 1 row) to see if if the
						while (rs.next()) {
							if (rs.getString("account_id").equals(account_id)) {			
								// update the account information
								sql = String.format("delete from account where account_id='%s'",account_id);
								stmt.execute(sql);
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
}