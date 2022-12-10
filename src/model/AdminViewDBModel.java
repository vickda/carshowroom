package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminViewDBModel {
	
	//Declare DB objects 
	static DBConnect conn = null;
	static Statement stmt = null;

	// constructor
	public AdminViewDBModel() { 
		//create DB object instance
		System.out.println("Connecting to database");
		conn = new DBConnect();
		System.out.println("Connection Successful");
	}
	
	// Get All the user records for Admin panel
	public static ObservableList<UserData> setUserTableViewData() {

		
		ObservableList<UserData> rowData = FXCollections.observableArrayList();
		String query = "SELECT * FROM `vcars_user`";
		
		try {
			stmt = conn.connect().createStatement();
			
			ResultSet data = stmt.executeQuery(query);
			
			System.out.println("Data retreived");
			
			
			while(data.next()){
				//Iterate Row
                
				String id = data.getString(1);
				String fullName = data.getString(2);
				String email = data.getString(3);
				String password = data.getString(4);
				String privilege = data.getString(5);
				
                
                rowData.add(new UserData(id, fullName, email, password, privilege));

            }
			
			conn.connect().close();
			
			return rowData;
			
		} catch (SQLException e) {
			System.out.println("Oops something went wrong while retrieving"
					+ "data");
			e.printStackTrace();
		}
		return rowData;
	}
	
	// Get all the Car Inventory data for Admin panel
	public static ObservableList<CarInventoryData> setCarInventoryTableViewData() {
		
		ObservableList<CarInventoryData> rowData = FXCollections.observableArrayList();
		String query = "SELECT * FROM `vcars_inventory`";
		
		try {
			stmt = conn.connect().createStatement();
			
			ResultSet data = stmt.executeQuery(query);
			
			System.out.println("Data retreived");
			
			
			while(data.next()){
				//Iterate Row
                
				String id = data.getString(1);
				String carYear = data.getString(2);
				String carMake = data.getString(3);
				String carPrice = data.getString(4);
				String status = data.getString(5);
				
                rowData.add(new CarInventoryData(id, carYear, carMake, carPrice, status));

            }
			
			conn.connect().close();
			
			return rowData;
			
		} catch (SQLException e) {
			System.out.println("Oops something went wrong while retrieving"
					+ "data");
			e.printStackTrace();
		}
		return rowData;
	}

	
	// Perform CRUD Based on the Admin's action (AdminHomeViewController)

	// Update user information from User table
	public String updateUserInfo(String id, String fullName, String email, String privilege) {

		String query = "UPDATE `vcars_user` "
				+ "SET `Full_Name`=?,`Email`=?,"
				+ "`privilege`=? WHERE ID = ?";
		
		if(id.isEmpty()) {
			return "Error: Please enter an ID";
		}
		
		try {
			// Initializing Prepared statement
			PreparedStatement stmt = conn.connect().prepareStatement(query);
			
			// Setting values in our Prepared statement
			stmt.setString(1, fullName);
			stmt.setString(2, email);
			stmt.setString(3, privilege);
			stmt.setString(4, id);
			
			// Executing query
			stmt.executeUpdate();
			
			conn.connect().close();
			
			System.out.println("Success: Rows Inserted...");
			return "Success: Rows Inserted...";
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Oops something went wrong while creating user");
		}
		
		return null;
	}

	// Deletes user info when delete button is clicked on Admin panel
	public String deleteUserInfo(String id) {
		
		if(id.isEmpty()) {
			return "Error: Please enter an ID";
		}
		
		String query = "DELETE FROM `vcars_user` WHERE ID = ?";
		
		try {
			// Initializing Prepared statement
			PreparedStatement stmt = conn.connect().prepareStatement(query);
			
			// Setting values in our Prepared statement
			stmt.setString(1, id);
			
			// Executing query
			stmt.executeUpdate();
			
			conn.connect().close();
			
			System.out.println("Success: Rows Inserted...");
			return "Success: Rows Inserted...";
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Oops something went wrong while creating user");
		}
		return null;
	}
	
	
	/*
	 * Car Table View code starts here
	 */
	
	

	// Add Car table records coming from Admin panels add button
		public String insertCarTableRecords(String[] userData) {
			
			String car_id = userData[0];
			String car_year = userData[1];
			String car_make = userData[2];
			String car_price = userData[3];
			String car_status = userData[4];
			
			
			String query = "INSERT INTO `vcars_inventory` (`Car_Year`, "
					+ "`Car_Make`, `Car_Price`, `Status`) "
					+ "VALUES (?, ?, ?, ?)";
			
			try {
				// Initializing Prepared statement
				PreparedStatement stmt = conn.connect().prepareStatement(query);
				
				// Setting values in our Prepared statement
				stmt.setString(1, car_year);
				stmt.setString(2, car_make);
				stmt.setString(3, car_price);
				stmt.setString(4, car_status);
				
				// Executing query
				stmt.executeUpdate();
				
				conn.connect().close();
				
				System.out.println("Success: Rows Inserted...");
				return "Success: Rows Inserted...";
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error: Oops something went wrong while creating user");
			}
			
			return null;
		}
	
	
	public String updateCarTableInfo(String ...carData) {
		
		String car_id = carData[0];
		String car_year = carData[1];
		String car_make = carData[2];
		String car_price = carData[3];
		String car_status = carData[4];
		
		if(car_id.isEmpty()) {
			return "Error: Please enter an ID";
		}
		
		
		String query = "UPDATE `vcars_inventory` SET `Car_Year`=?,`Car_Make`=?,`Car_Price`=?,`Status`=? WHERE Car_ID= ?";
		
		try {
			// Initializing Prepared statement
			PreparedStatement stmt = conn.connect().prepareStatement(query);
			
			// Setting values in our Prepared statement
			stmt.setString(1, car_year);
			stmt.setString(2, car_make);
			stmt.setString(3, car_price);
			stmt.setString(4, car_status);
			stmt.setString(5, car_id);
			
			System.out.println(query);
			System.out.println(stmt);
			// Executing query
			stmt.executeUpdate();
			
			conn.connect().close();
			
			System.out.println("Success: Rows Inserted...");
			return "Success: Rows Inserted...";
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Oops something went wrong while "
					+ "Inserting Records in Car Inventory Table");
		}
		
		return null;
	}
	
	
	// Deletes user info when delete button is clicked on Admin panel
		public String deleteCarInfo(String id) {
			// TODO Auto-generated method stub
			
			if(id.isEmpty()) {
				return "Error: Please enter an ID";
			}
			
			String query = "DELETE FROM `vcars_inventory` WHERE Car_ID = ?";
			
			try {
				// Initializing Prepared statement
				PreparedStatement stmt = conn.connect().prepareStatement(query);
				
				// Setting values in our Prepared statement
				stmt.setString(1, id);
				
				// Executing query
				stmt.executeUpdate();
				
				conn.connect().close();
				
				System.out.println("Success: Rows Inserted...");
				return "Success: Rows Inserted...";
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error: Oops something went wrong while creating user");
			}
			return null;
		}
		
	
	
}
