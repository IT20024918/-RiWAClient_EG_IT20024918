package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	
	public Connection connect()
	{
		Connection con = null;

		try
		{
		 Class.forName("com.mysql.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid","root", "");
		 
		 //For testing whether it is connected with the database
		 
		 System.out.print("Successfully connected with the database");
	 	}
		catch(Exception e)
	 	{
	 		e.printStackTrace();
		 }

	return con;
	}

	
	
	public String RegisterUser(String accountNo,String name,String address,String NIC, String email, String phone, String username, String password) {
	   	
		String output = "";
				try
				 { Connection con = connect();
				 if (con == null)
				 {
				 return "Error while connecting to the database";
				 }
		    		
		    	    String sql = "insert into user(`userId`,`accountNo`,`name`,`address`,`NIC`,`email`,`phone`,`username`,`password`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    	    PreparedStatement preparedStmt = con.prepareStatement(sql);
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, accountNo);
					 preparedStmt.setString(3, name);
					 preparedStmt.setString(4, address);
					 preparedStmt.setString(5, NIC);
					 preparedStmt.setString(6, email); 
					 preparedStmt.setString(7, phone);
					 preparedStmt.setString(8, username); 
					 preparedStmt.setString(9, password); 
					//execute the statement
					 preparedStmt.execute();
					 con.close();
					 String newUsers = readUserDetails();
						output = "{\"status\":\"success\", \"data\": \"" +
								newUsers + "\"}";
		    		
		    	}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}";
				System.err.println(e.getMessage());
			}
	return output;
	}

	
	public String readUserDetails()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1' style=\"font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; radius: 10px\">"
					+ "<tr><th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">User ID</th>"
					+ "<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">Account Number</th>"
					+"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">Name</th><th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">Address</th><th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">NIC</th>"
					+ "<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">Email</th>"
					+ "<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">Phone</th>"
					+ "<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">UserName</th>"
					+ "<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">Password</th> <th>Update</th><th>Remove</th></tr>";
			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String userId= Integer.toString(rs.getInt("userId"));
				String accountNo = rs.getString("accountNo");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String NIC = rs.getString("NIC");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String username = rs.getString("username");
				String password = rs.getString("password");
				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + userId
						+ "'>" +  "</td></tr>";
				output += "<td>" + userId + "</td>";
				output += "<td>" + accountNo + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + email + "</td>"; 
				output += "<td>" + phone + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + password + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='"+ userId + "'>" + "</td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"+ userId + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the user";
			System.err.println(e.getMessage());
		}
		return output;
	}

	

	 public String updateUserDetails(String userId,String accountNo,String name,String address,String NIC, String email, String phone, String username, String password)
	   {
		   String output = "";
		   try
			   {
			   Connection con = connect();
			   if (con == null)
			   {
				   return "Error while connecting to the database for updating"; 
			   }
			   // create a prepared statement
			   String query = "UPDATE user SET accountNo=?,name=?,address=?,NIC=?,email=?,phone=?,username=?,password=?WHERE userId=?";
			   PreparedStatement preparedStmt = con.prepareStatement(query);
			   // binding values
			   preparedStmt.setString(1, accountNo);
			   preparedStmt.setString(2, name);
			   preparedStmt.setString(3, address);
			   preparedStmt.setString(4, NIC);
			   preparedStmt.setString(5, email);
			   preparedStmt.setString(6, phone);
			   preparedStmt.setString(7, username);
			   preparedStmt.setString(8, password);
			   preparedStmt.setInt(9, Integer.parseInt(userId));
			   // execute the statement
			   preparedStmt.execute();
			   con.close();
			   String newUsers = readUserDetails();
				output = "{\"status\":\"success\", \"data\": \"" +
						newUsers + "\"}";
			   }
		    catch (Exception e)
			{
		    	output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";
				System.err.println(e.getMessage());
			}
		    return output;
		    }
	 
	  
	 public String deleteUser(String userId)
	 {
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for deleting."; 
			 }
			 
			 // create a prepared statement
			 String query = "delete from user where userId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(userId));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newUsers = readUserDetails();
				output = "{\"status\":\"success\", \"data\": \"" +
						newUsers + "\"}";
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the user.\"}";
				System.err.println(e.getMessage());
		 }
		 return output;
	 }

}
