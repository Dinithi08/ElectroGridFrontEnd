package com;

import java.sql.*;

public class Staff {
	private static final String StaffID = null;

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String readStaffs() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer Name</th> <th>Customer ID</th><th>Staff Name</th>"
					+ " <th>Assign Work</th><th>Mobile</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from staffs";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				
				String CustomerName = rs.getString("CustomerName");
				String CustomerID = Integer.toString(rs.getInt("CustomerID"));
				String StaffName = rs.getString("StaffName");
			    String AssignWork = rs.getString("AssignWork");
				String Mobile = rs.getString("Mobile");
			
				// Add into the html table
				output += "<tr><td><input id='hidStaffIDUpdate' name='hidStaffIDUpdate' type='hidden' value='" + StaffID
						+ "'>" + CustomerName + "</td>";
				output += "<td>" + CustomerID + "</td>";
				output += "<td>" + StaffName + "</td>";
				
				output += "<td>" + AssignWork + "</td>";
				output += "<td>" + Mobile + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-staffid='"
						+ StaffID + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertStaff(String customerName, String customerID, String staffName,  String assignWork, String mobile) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into staffs (`staffID`,`customerName`,`customerID`,`staffName`,`assignWork`,`mobile`,)"
					+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerName);
			preparedStmt.setString(3, customerID);
			preparedStmt.setString(4, staffName);
			
			preparedStmt.setString(6, assignWork);
			preparedStmt.setString(7, mobile);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newStaffs = readStaffs();
			output = "{\"status\":\"success\", \"data\": \"" + newStaffs + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":	 \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateStaff(String ID, String customerName, String customerID, String staffName, String assignWork, String mobile) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE staffs SET customerName=?,customerID=?,staffName=?,assignWork=?,mobile=? WHERE StaffID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, customerName);
			preparedStmt.setString(2, customerID);
			preparedStmt.setString(1, staffName);
			
			preparedStmt.setString(4, assignWork);
			preparedStmt.setString(4, mobile);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newStaffs = readStaffs();
			output = "{\"status\":\"success\", \"data\": \"" + newStaffs + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the staff.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteStaff(String staffID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from staffs where staffID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(staffID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newStaffs = readStaffs();
			output = "{\"status\":\"success\", \"data\": \"" + newStaffs + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the staff.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}