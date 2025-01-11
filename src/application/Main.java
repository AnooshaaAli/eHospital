package application;
import java.io.IOException;
import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

// ----------------------------------------------- Anooosha's Branch --------------------------------------------------- //

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane mainLayout;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		
		showHomePage();
	}
    
	public void showHomePage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/Home/HomePage.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		this.primaryStage.setTitle("eHospital | Home");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}