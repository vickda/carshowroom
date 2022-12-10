package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserViewDBModel {

	//Declare DB objects 
	static DBConnect conn = null;
	static Statement stmt = null;

	// constructor
	public UserViewDBModel() { 
		//create DB object instance
		System.out.println("Connecting to database");
		conn = new DBConnect();
		System.out.println("Connection Successful");
	}
	
	public ArrayList<ArrayList<String>> getCarInfo(){
		
		String query = "SELECT * FROM `vcars_inventory` WHERE Status != \"Sold\"";
		ArrayList<ArrayList<String>> allCarData = new ArrayList<ArrayList<String>>();
		
		return executeQuery(query);
		
	}
	
	// Get All Data from car database
	public ArrayList<ArrayList<String>> getCarInfo(String carYear, String carMake) {
		
		String query = "SELECT * FROM `vcars_inventory` WHERE `Car_Year` = \"%" + carYear + "%\" OR `Car_Make` like \"%" + carMake + "%\" AND Status != \"Sold\"";
		
		ArrayList<ArrayList<String>> allCarData = new ArrayList<ArrayList<String>>();
		
		return executeQuery(query);
	}
	
	// Get Specific data from database
	public static ArrayList<ArrayList<String>> executeQuery(String query) {
		
		ArrayList<ArrayList<String>> allCarData = new ArrayList<ArrayList<String>>();
		
		try {
			stmt = conn.connect().createStatement();
			
			ResultSet data = stmt.executeQuery(query);
			
			System.out.println("Data retreived");
			
			
			while(data.next()){
				//Iterate Row
				ArrayList<String> carData = new ArrayList<>();
                
				String id = data.getString(1);
				String carYear = data.getString(2);
				String carMake = data.getString(3);
				String carPrice = data.getString(4);
//				String status = data.getString(5);
				
				carData.add(id);
				carData.add(carYear);
				carData.add(carMake);
				carData.add(carPrice);
				
				allCarData.add(carData);
            }
			
			conn.connect().close();
			
			return allCarData;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Oops something went wrong in getCarInfo under"
					+ "UserViewDBmodel");
		}
		
		return null;
	}
	
	
	public String carPurchased (String id) {

		String query = "UPDATE `vcars_inventory` SET `Status`= \"Sold\" WHERE Car_ID = ?";
		
		
		try {
			// Initializing Prepared statement
			PreparedStatement stmt = conn.connect().prepareStatement(query);
			
			// Setting values in our Prepared statement
			stmt.setString(1, id);
			
			// Executing query
			stmt.executeUpdate();
			
			conn.connect().close();
			
			System.out.println("Success: Rows Updated...");
			return "Success: Rows Inserted...";
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Oops something went wrong while creating user");
		}
		
		return null;
	}
}
