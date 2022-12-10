package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DaoModel {

	//Declare DB objects 
	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public DaoModel() { 
		//create db object instance
		System.out.println("Connecting to database");
		conn = new DBConnect();
		System.out.println("Connection Successful");
	}

	// To create a table in our DB
	public void createTable() {
		
		System.out.println("Create table method called");
		System.out.println("Creating a Table...");
		
		// Our Query
		String query = "CREATE TABLE vcars_user "
				+ "(ID INTEGER not NULL AUTO_INCREMENT, Full_Name VARCHAR(255),Email VARCHAR(255),"
				+ " password VARCHAR(255), privilege VARCHAR(255), PRIMARY KEY ( ID ))";
		
		String query1 = "CREATE TABLE vcars_inventory "
				+ "(Car_ID INTEGER not NULL AUTO_INCREMENT, Car_Year VARCHAR(255)"
				+ ",Car_Make VARCHAR(255),"
				+ " Car_Price VARCHAR(255), Status VARCHAR(255), PRIMARY KEY ( Car_ID ))";
		 
		 try {
			stmt = conn.connect().createStatement();
			stmt.executeUpdate(query);
			stmt.executeUpdate(query1);
			
			System.out.println("Table Created...");
			
			// Close connection
			conn.connect().close();
		} catch (SQLException e) {
			System.out.println("Oops something went wrong creating table");
			e.printStackTrace();
		}

	};
	
	// Validate user data from login page (Login & Signup Controller)
	public ResultSet validateLogin(String email) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM vcars_user WHERE Email='" + email.toLowerCase() + "'";
		
		try {
			stmt = conn.connect().createStatement();
			ResultSet data = stmt.executeQuery(query);
			
			System.out.println("Data retreived");
			conn.connect().close();
			
			return data;
			
		} catch (SQLException e) {
			System.out.println("Oops something went wrong while retrieving"
					+ "data");
			e.printStackTrace();
		}
		return null;
	}
	
	
	// Create user by inserting data into database (Signup Controller)
	public String createUser(String[] userData) {
		
		String fullName = userData[0];
		String email = userData[1];
		String pass = userData[2];
		
		String query = "INSERT INTO `vcars_user` (`Full_Name`, `Email`, `password`, "
				+ "`privilege`) "
				+ "VALUES (?, ?, ?, ?)";
		
		try {
			// Initializing Prepared statement
			PreparedStatement stmt = conn.connect().prepareStatement(query);
			
			// Setting values in our Prepared statement
			stmt.setString(1, fullName);
			stmt.setString(2, email);
			stmt.setString(3, pass);
			stmt.setString(4, "user");
			
			// Executing query
			stmt.executeUpdate();
			
			conn.connect().close();
			
			return "Success: Rows Inserted...";
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Oops something went wrong while creating user");
		}
		
		return null;
	}
	
	
}
