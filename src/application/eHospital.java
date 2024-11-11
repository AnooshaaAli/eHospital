package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class eHospital {
//asd
    @FXML
    private Button PatientButton;
    
    @FXML
    private Button NurseButton;
    
    @FXML
    private Button DoctorButton;
    
    @FXML
    private Button ReceptionistButton;
    
    @FXML
    private Button AdminButton;
    
    @FXML
    public void handleReceptionistButtonClick() {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Receptionist.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Patient");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) PatientButton.getScene().getWindow();
            currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDoctorButtonClick()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Receptionist.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Patient");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) PatientButton.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void handlePatientButtonClick()
    {
    	
    }
    public void handleAdminButtonClick()
    {
    	
    }
    
    public void handleNurseButtonClick()
    {
    	
    }
}

