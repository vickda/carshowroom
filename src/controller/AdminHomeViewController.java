package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AdminViewDBModel;
import model.CarInventoryData;
import model.UserData;
import model.ValidateUserInformation;

public class AdminHomeViewController implements Initializable {

	
	// ***** All User Tab Input Variables *****
	@FXML
	private TextField userId;
	
	@FXML
	private TextField userFullName;
	
	@FXML
	private TextField userEmail;
	
	@FXML
	private TextField userPass;

	@FXML
	private TextField userPrivilege;

	@FXML
	private Button userAddBtn;
	
	@FXML
	private Button userUpdateBtn;
	
	@FXML
	private Button userDeleteBtn;
	
	@FXML
	private TableView<UserData> userTableView;
	
	@FXML
	private TableColumn<UserData, Integer> col_id;
	
	@FXML
	private TableColumn<UserData, String> col_fullName;
	
	@FXML
	private TableColumn<UserData, String> col_email;
	
	@FXML
	private TableColumn<UserData, String> col_pass;
	
	@FXML
	private TableColumn<UserData, String> col_privilege;
	
	private ObservableList<UserData> userData;

	// ***** All Car Inventory Tab Input Variables *****
	
	@FXML
	private TextField carId;
	
	@FXML
	private TextField carYear;
	
	@FXML
	private TextField carMake;

	@FXML
	private TextField carPrice;
	
	@FXML
	private TextField status;

	@FXML
	private Button carAddBtn;
	
	@FXML
	private Button carUpdateBtn;
	
	@FXML
	private Button carDeleteBtn;
	
	@FXML
	private TableView<CarInventoryData> carTableView;
	
	@FXML
	private TableColumn<CarInventoryData, String> col_carID;
	
	@FXML
	private TableColumn<CarInventoryData, String> col_carYear;
	
	@FXML
	private TableColumn<CarInventoryData, String> col_carMake;
	
	@FXML
	private TableColumn<CarInventoryData, String> col_carPrice;
	
	@FXML
	private TableColumn<CarInventoryData, String> col_status;
	
	private ObservableList<CarInventoryData> carInventoryData;
	

	// Set Data into both table views
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		new AdminViewDBModel(); // Initializing DB model to access static methods
//		
//		// User Table View Column Schema
		col_id.setCellValueFactory(new PropertyValueFactory<UserData, Integer>("ID"));
		col_fullName.setCellValueFactory(new PropertyValueFactory<UserData, String>("Full_Name"));
		col_email.setCellValueFactory(new PropertyValueFactory<UserData, String>("Email"));
		col_pass.setCellValueFactory(new PropertyValueFactory<UserData, String>("password"));
		col_privilege.setCellValueFactory(new PropertyValueFactory<UserData, String>("privilege"));
		
		userData = AdminViewDBModel.setUserTableViewData();
		userTableView.setItems(userData);

		// Car Inventory Table View Column Schema
		col_carID.setCellValueFactory(new PropertyValueFactory<CarInventoryData, String>("ID"));
		col_carYear.setCellValueFactory(new PropertyValueFactory<CarInventoryData, String>("carYear"));
		col_carMake.setCellValueFactory(new PropertyValueFactory<CarInventoryData, String>("carMake"));
		col_carPrice.setCellValueFactory(new PropertyValueFactory<CarInventoryData, String>("carPrice"));
		col_status.setCellValueFactory(new PropertyValueFactory<CarInventoryData, String>("Status"));
		
		carInventoryData = AdminViewDBModel.setCarInventoryTableViewData();
		
		carTableView.setItems(carInventoryData);
	}
	
	// Event Listeners for adding values in database
	public void BtnPressed(ActionEvent event) {
		
		// User Data
		String id = userId.getText();
		String name = userFullName.getText();
		String email = userEmail.getText();
		String pass = userPass.getText();
		String privilege = userPrivilege.getText();
		
		// Car Data
		String car_id = carId.getText();
		String car_year = carYear.getText();
		String car_Make = carMake.getText();
		String car_Price = carPrice.getText();
		String car_status = status.getText();
		
		// Button Source
		String source = ((Button) event.getSource()).getId();
		
		String dbName; // to set DBname
		
		
		if(source.contains("user")) {
			dbName = "vcars_user";
			validateInputDataForQuery(dbName, source, id, name, email, pass, privilege);
		}
		else if (source.contains("car")) {
			dbName = "vcars_inventory";
			validateInputDataForQuery(dbName, source,
					car_id, car_year, car_Make, car_Price, car_status);
		}
		
		
	}
		
	
	public void validateInputDataForQuery(String dbName, String source, String ...queryData) {
		
//		String operationType = source.replaceAll("user|car|Btn", "").trim();
		AdminViewDBModel performCRUD = new AdminViewDBModel();

		String isCRUDSuccessfull = null;
		
		// Switch Case for validating data coming from different types of CRUD Operation
		switch (source) {
		
		case "userAddBtn":
			ValidateUserInformation validateData = new ValidateUserInformation();
			isCRUDSuccessfull = validateData.validateSignup(
					queryData[1], queryData[2], queryData[3], queryData[3]);
			
			System.out.println("User Add Button" + isCRUDSuccessfull);
			initialize(null, null);
			break;
		
		case "userUpdateBtn":
			
			isCRUDSuccessfull = performCRUD.updateUserInfo(queryData[0], 
					queryData[1], queryData[2], queryData[4]);
			
			initialize(null, null);
			// Show dialog with the status
			System.out.println("User Update Btn "+ isCRUDSuccessfull);
			break;
			
		case "userDeleteBtn":
			isCRUDSuccessfull = performCRUD.deleteUserInfo(queryData[0]);
			
			// Show dialog with the status
			System.out.println("user Delete Btn " + isCRUDSuccessfull);
			break;
		
		case "carAddBtn":
			isCRUDSuccessfull = performCRUD.insertCarTableRecords(queryData);
			
			// Show dialog with the status
			System.out.println("car Add Btn " + isCRUDSuccessfull);
			break;
		
		case "carUpdateBtn":
			isCRUDSuccessfull = performCRUD.updateCarTableInfo(queryData);
			
			// Show dialog with the status
			System.out.println("Car Update Btn " + isCRUDSuccessfull);
			break;
			
		case "carDeleteBtn":
			isCRUDSuccessfull = performCRUD.deleteCarInfo(queryData[0]);

			// Show dialog with the status
			System.out.println("car Delete Btn " + isCRUDSuccessfull);
			break;

		default:
			break;
		}
		
		if (isCRUDSuccessfull.contains("Error")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Oops CRUD Unsuccessful");
			alert.setHeaderText(isCRUDSuccessfull);
//			alert.setContentText();
			alert.showAndWait().ifPresent(rs -> {
			    if (rs == ButtonType.OK) {
			        System.out.println("Pressed OK.");
			    }
			});
		} 
		else initialize(null, null);
		
	}
}
