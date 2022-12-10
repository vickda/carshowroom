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
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import model.ValidateUserInformation;

public class LoginController {

	
	// All input variables
	@FXML
	private TextField email;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label errorMsg;
	
	@FXML
	private Button login_btn;
	
	@FXML
	private TextFlow signup_btn;
	
	@FXML
	private Scene scene;
	
	
	// On click of submit button
	public void loginBtnPressed() {
		
		String emailID = email.getText();
		String pass = password.getText();
		String fileName;
		
		// Validate Login Data
		ValidateUserInformation validateData = new ValidateUserInformation();
		String result = validateData.validateLogin(emailID, pass);
		
		// If validation is unsuccessful show the user error in label
		if(result.contains("Error")) {
			String msg = result.split(":")[1].trim();
			
			errorMsg.setText(msg);
		}
		// Else Change the screen based on the user privilege
		else if(result.contains("Success")) {
			errorMsg.setText(null);
			
			String msg = result.split(":")[1].trim();
			
			// Setting FXML filename to change screens
			if(msg.equals("Show user screen")) {
				fileName = "/view/UserHomeView.fxml";
			}
			else {
				fileName = "/view/AdminHomeView.fxml";
			}
			
			// Change the screens based on user privilege
			Pane root;
			try {
				root = (Pane) FXMLLoader.load(getClass().getResource(fileName));
				Scene scene = new Scene(root);
				Main.stage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	// On click of submit button
	public void signupBtnPressed() throws IOException {
			
		String emailID = email.getText();
		String pass = password.getText();
		
		System.out.println("Signup button clicked");
		
		AnchorPane root;
//		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signup.fxml"));
	    root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Signup.fxml"));
	    Scene scene = new Scene(root);
		Main.stage.setScene(scene);
	}

}
