package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CardViewController {

    
    @FXML
    private ImageView cardImage;
    
    @FXML
    private Text carName;

    @FXML
    private Text carYear;
    
    @FXML
    private AnchorPane detailsAnchorPane;
    
    @FXML
    private Button buyNowBtn;
    
	public void setData(String imagePath, String year, String carMake){
    	Image image = new Image(getClass().getResourceAsStream(imagePath));
    	cardImage = new ImageView(image);
//    	cardImage.setImage(image);
    	
    	System.out.println(year + " " + carMake);
    	
    	carYear.setText(year);
    	carName.setText(carMake);
    	
    }
	
    
    public Button getBuyNowBtn() {
		return buyNowBtn;
	}

	public void setBuyNowBtn(Button buyNowBtn) {
		this.buyNowBtn = buyNowBtn;
	}


}
