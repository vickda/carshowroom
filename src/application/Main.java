package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage stage; // set global stage object!!!

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
		    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Login.fxml"));
		    Pane root = (Pane) loader.load();
//		    BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Login View");
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}


//public class Main  {
//
//	
////	public static void main(String[] args) {
////		LoginController.main(args);
////	}
//	
//	
//}
