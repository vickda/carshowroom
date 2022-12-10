package controller;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.ValidateUserInformation;

public class SignupController {
	
	// All input variables
	@FXML
	private TextField fullName;
	
	@FXML
	private TextField email;
	
	@FXML
	private PasswordField password;
		
	@FXML
	private PasswordField confirmPassword;
		
	@FXML
	private Label errorMsg;
	
	@FXML
	private Button signup_btn;
		
	@FXML
	private TextFlow goToLogin_btn;
		
	@FXML
	private Scene scene;
	
	// Register the user
	public void signupBtnPressed() {
		String name, emailID, pass, confirmPass;
		
		name = fullName.getText();
		emailID = email.getText();
		pass = password.getText();
		confirmPass = confirmPassword.getText();
		
		// Validate Registration Data & Create user
		ValidateUserInformation validateData = new ValidateUserInformation();
		String result = validateData.validateSignup(name, emailID, pass, confirmPass);
		
		System.out.println("Signupbtn pressed & result => " + result);
		
		if(result.contains("Error")) {
			String msg = result.split(":")[1].trim();
			errorMsg.setStyle("-fx-text-fill: red");
			errorMsg.setText(msg);
		}
		// Else Change the screen based on the user privilege
		else if(result.contains("Success")) {
			errorMsg.setText("User Successfully Created");
			errorMsg.setStyle("-fx-text-fill: green");
			System.out.println("User created");
		}
		
	}
	
	
	// Go to Login page once Login button clicked
	public void loginBtnPressed() throws IOException {
		
		System.out.println("go to login button clicked");
		
		BorderPane root;
//	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signup.fxml"));
	    root = (BorderPane) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
	    Scene scene = new Scene(root);
		Main.stage.setScene(scene);
	}

}
