package application;

import java.awt.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class patientController implements Initializable{
	
	@FXML
	private Button PatientButton;
	@FXML 
	private Button PatientSignIn;
	@FXML 
	private Button LoginPagePatient;
	@FXML 
    private Button RegisterPatient;
	@FXML
    private Button scheduleAppointment;
	@FXML
    private Button ViewRecord;
	@FXML
	private Button PayBills;
	@FXML
	private Button ViewPrescription;
	@FXML
	private Button showBills;
	
	// ----------------------------------------------- TEXT FIELDS ---------------------------------------------------- //
	
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField dobTextField;
    @FXML
    private TextField contactTextField;
    @FXML
    
    // -------------------------------------------- COMBO BOXES ------------------------------------------------------ //
    
	private ComboBox<String> genderComboBox;
	 
    // -------------------------------------------- TABLES ------------------------------------------------------------//
    
    @FXML
    private TableView<Medication> medicationTable;
    @FXML
    private TableColumn<Medication, Integer> idColumn;
    @FXML
    private TableColumn<Medication, String> nameColumn;
    @FXML
    private TableColumn<Medication, Integer> dosageColumn;
    
    private ObservableList<Medication> medicationList = FXCollections.observableArrayList();
    
    // -------------------------------------------- EVENT HANDLERS --------------------------------------------------- /
    
	public void initialize(URL location, ResourceBundle resources) {
		 
       // Populate genders for genderComboBox
       if (genderComboBox != null) 
       {
           ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "Other");
           genderComboBox.setItems(genders);
       }
       
       displayMedications();
	 }
	 
	public void handleRegisterButtonClick(MouseEvent  event)
	{
		try {
        	String fxmlFile;
            String stageTitle;
            String name = nameTextField.getText();
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            String gender = genderComboBox.getValue();
            String dob = dobTextField.getText();
            String contact = contactTextField.getText();

            Patient patient = new Patient();
            patient.registerPatient(name, username, password, gender, dob, contact);
        	fxmlFile = "Patient.fxml";
            stageTitle = "Patient";
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle(stageTitle);

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void displayMedications() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("mid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("medicationName"));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        
        Patient patient = new Patient():
        patient.viewRecord();
        
        medicationTable.setItems(medicationList); // Set the data
	}
}
