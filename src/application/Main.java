package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


//----------------------------------------------- Anooosha's Branch --------------------------------------------------- //

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane mainLayout;
	@FXML
	private Button PatientButton;
	
	private Connection connect() {
	   //String url = "jdbc:sqlserver://10N5Q8AKAMRA\\SQLEXPRESS01;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
		 String url ="jdbc:sqlserver://FATIMA\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
	    Connection conn = null;
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Load SQL Server JDBC driver
	        conn = DriverManager.getConnection(url);
	        System.out.println("Connected to the database successfully!");
	    } catch (ClassNotFoundException e) {
	        System.out.println("SQL Server JDBC Driver not found.");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Database connection failed.");
	        e.printStackTrace();
	    }
	    return conn;
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		//Connection conn = this.connect();
		this.primaryStage = primaryStage;
		showHomePage();
	}
    
	public void showHomePage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("HomePage.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		this.primaryStage.setTitle("Home");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
