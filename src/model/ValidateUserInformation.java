package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidateUserInformation {
	
	public String validateLogin(String email, String pass) {
		
		// Get Data from Database
		DaoModel db = new DaoModel();
		ResultSet data = db.validateLogin(email);
		
		String encryptPass = hashPassword(pass);
		
		try {
			
			if(data.next()) {
				
				// Setting data from db to variable
				String password = (String) data.getObject(4);
				String privilege = (String) data.getObject(5);
				
				// Check if password is correct
				if(!password.equals(encryptPass)) {
					return "Error: Invalid Password";
				}
				
				// Show screen based on privilege
				if(privilege.toLowerCase().equals("user")) {
					return "Success: Show user screen";
				}
				else {
					return "Success: Show admin panel";
				}
				
				
			}else {
				return "Error: No user Exist";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Inside validateUserInformtion/validateLogin method");
		}
		
		return null;
	}
	
	// Check pass for hashing
	public void checkPass() {
		
	}
	
	
	// Validating information of Registration form
	public String validateSignup(String ...params) {
		
		// Setting up all the variables
		String name = params[0];
		String email = params[1];
		String pass = params[2]; 
		String confirmPass = params[3];
		
		/* Basic String Validation Starts here */
		
		// Checking if there is any information left blank
		if(name.isEmpty() || email.isEmpty() ||
				pass.isEmpty() || confirmPass.isEmpty()) {
			return "Error: Please fill all fields";
		}
		
		if(!email.contains("@") || !email.contains(".com")) {
			return "Error: Please enter correct email address";
		}
		
		if (!pass.equals(confirmPass)) {
			return "Error: Password Mismatch";
		}
		
		/* Basic String Validation Ends here */
		
		DaoModel db = new DaoModel();
		ResultSet data = db.validateLogin(email);
		
		try {
			
			// Check if the email already exists in the database
			if(data.next()) {
				return "Error: User Already Exists";
			}
			params[2] = hashPassword(pass);
			return db.createUser(params);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Inside validateUserInformtion/validate Signup method");
			e.printStackTrace();
		}
		
		return pass;
		}
	
	public String hashPassword(String pass) {
			
		try {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA");
			messageDigest.update(pass.getBytes());
			
			byte[] result = messageDigest.digest();
			
			StringBuilder sb = new StringBuilder();
			
			for(byte b : result) {
				sb.append(String.format("%2x", b));
			}
			
			return sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pass;
	}

}
