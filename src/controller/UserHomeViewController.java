package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.UserViewDBModel;

public class UserHomeViewController implements Initializable{

    @FXML
    private TextField carNameSearchBox;

    @FXML
    private TextField carYearSearchBox;

    @FXML
    private ScrollPane scrollPaneContainer;
    
    @FXML
    private GridPane carGridPane;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button searchBtn;
    
    private List<CardViewController> cardViewList;

    @FXML
    void loginBtnPressed(ActionEvent event) {
    	try {
    		
    		System.out.println("Logout btn pressed");
    		SignupController toLoginPage = new SignupController();
    		toLoginPage.loginBtnPressed();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error occured at loginBTNpressed method");
		}
    }

    @FXML
    void searchBtnPressed(ActionEvent event) {
    	String carYear, carMake;
    	carYear = carYearSearchBox.getText();
    	carMake = carNameSearchBox.getText();
    	
    	System.out.println(carYear + " " + carMake);
    	
//    	// IF both search options are blank then throw an error message box
//    	if(carYear.isEmpty() && carMake.isEmpty()) {
//    		System.out.println("Input cannot be blank");
//    	} else {
//    	
//    	}
    	
    	initialize(null, null);
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		int columns = 0; 
		int row = 1;

		String carNameSearchBoxValue = carNameSearchBox.getText();
		String carYearSearchBoxValue = carYearSearchBox.getText();
		ArrayList<ArrayList<String>> dbData = new ArrayList<ArrayList<String>>();
		
		
		// TO show data based on the search result
		if(carNameSearchBoxValue.isEmpty() && carYearSearchBoxValue.isEmpty()) {
			dbData = getCarData();
		} else {
			// Getting data from database
			UserViewDBModel db = new UserViewDBModel();
			dbData = db.getCarInfo(carYearSearchBoxValue, carNameSearchBoxValue);
			
			// Clearing grid pane to show data
			carGridPane.getChildren().clear();
		}
		
		// Adding all the Car data in our GRID
		try {
			
			for (int i = 0; i < dbData.size(); i++) {
				
				// Setting DB data into variables
				ArrayList<String> carData = dbData.get(i);
				String carID, carImagePath, carYear, carMake, carPrice;
				
				carID = carData.get(0);
				carImagePath = "/assets/test.jpg";
				carYear = carData.get(1);
				carMake = carData.get(2);
				carPrice = carData.get(3);
				
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/view/CardView.fxml"));
				
				VBox box = fxmlLoader.load();
				
				// for adding dynamic Adding data
				CardViewController cardController = fxmlLoader.getController();
				cardController.setData(carImagePath, carYear, carMake);
				
				// Add listener to button
				Button buyNowBtn = cardController.getBuyNowBtn();
				buyNowBtn.setOnAction(e -> {
					buynowListener(carID, carImagePath, carMake, carYear, carPrice);
				});
				
				if(columns == 3) {
					columns = 0;
					++row;
				}
				
				carGridPane.add(box, columns++, row);
				GridPane.setMargin(box, new Insets(7));
			}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public ArrayList<ArrayList<String>> getCarData() {
		
		List<CardViewController> carList = new ArrayList<>();
		
		UserViewDBModel db = new UserViewDBModel();
		ArrayList<ArrayList<String>> data = db.getCarInfo();
		
		return data;
		
	}
	
	public void buynowListener(String ID, String carImage, String carMake, 
			String carYear, String price) {
		
		System.out.println(carMake + " ad " + carYear);
		
		UserViewDBModel updateStatus = new UserViewDBModel();
		updateStatus.carPurchased(ID);
		
		FXMLLoader root;
		try {
			root = new FXMLLoader(getClass().getResource("/view/UserCarDetails.fxml"));
			Scene scene = new Scene(root.load());
			
			UserCarDetailsController controller = root.getController();
			controller.setData(carImage, carMake.trim(), carYear.trim(), price);
			
			Main.stage.setScene(scene);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
