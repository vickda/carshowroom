package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UserCarDetailsController {

    @FXML
    private Button backToHomeBtn;

    @FXML
    private Button buyNowBtn;

    @FXML
    private ImageView carBannerImage;

    @FXML
    private Label carMake;

    @FXML
    private Text carYear;
    
    @FXML
    private Text carPrice;

    @FXML
    void backToHomeBtnPressed(ActionEvent event) throws IOException {
    	Pane root = (Pane) FXMLLoader.load(getClass().getResource("/view/UserHomeView.fxml"));
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
    }

    
    
    public void setData(String carImage, String carMake, String carYear, String carPrice) {
    	
    	System.out.println(carMake + " ad " + carYear);
    	
    	this.carMake.setText(carMake);
    	this.carYear.setText(carYear);
    	this.carPrice.setText(carPrice);
    }

}
