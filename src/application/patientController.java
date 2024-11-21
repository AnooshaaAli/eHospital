package application;

import java.awt.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class patientController implements Initializable{
	
	// ------------------------------------------------ ATTRIBUTES --------------------------------------------------------//
	
	private int patId;
	
	// ----------------------------------------------- BUTTONS ----------------------------------------------------------- //
	
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
    
    // --------------------------------------------- FOR PROFILE --------------------------------------------------------//
    
	@FXML
	private TextField Username;
	@FXML
	private TextField password;
	@FXML
	private TextField name;
	@FXML
	private TextField Id;
	@FXML
	private TextField gender;
	@FXML
	private TextField DOB;
	@FXML
	private TextField contact;
	@FXML
	private TextField dischargeStatus;
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
    
    @FXML
    private TableView<Appointment> appointmentsTable;
    @FXML
    private TableColumn<Appointment, Integer> aptIdcolumn;
    @FXML
    private TableColumn<Appointment, String> startTimeColumn;
    @FXML
    private TableColumn<Appointment, String> endTimeColumn;
    @FXML
    private TableColumn<Appointment, Date> dateColumn;
    @FXML
    private TableColumn<Appointment, Boolean> statusColumn;
    
    private ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Bill> billsTable;
    @FXML
    private TableColumn<Bill, Integer> billIdColumn;
    @FXML
    private TableColumn<Bill, Boolean> billStatusColumn;
    @FXML
    private TableColumn<Bill, String> paymentMethodColumn;
    @FXML
    private TableColumn<Bill, Double> amountColumn;
    
    private ObservableList<Bill> billsList = FXCollections.observableArrayList();
    
    //----------------------------------------------- GET PATIENT ID ----------------------------------------------------//
    
    public int getPatientId() {
    	return patId;
    }
    
    public void setPatientId(int patientId) {
    	patId = patientId;
    }
    
    // -------------------------------------------- EVENT HANDLERS --------------------------------------------------- /
    
	public void initialize(URL location, ResourceBundle resources) {
		 
       // Populate genders for genderComboBox
       if (genderComboBox != null) 
       {
           ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "Other");
           genderComboBox.setItems(genders);
       }
       
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
            int id = patient.getPatientId(username);
            setPatientId(id);
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
	
	public void handlePatientViewRecord(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==ViewRecord)
            {
            	fxmlFile = "ViewPatientRecordPatient.fxml";
                stageTitle = "ViewPatientRecord";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();
            
            // Get the controller of the new FXML
            patientController controller = loader.getController();
            
            // Pass the patId to the new controller
            int patId = getPatientId();
            controller.displayPatientRecord(patId);
                 
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
    
	public void displayPatientRecord(int patId) {
	    // Set up cell factories with the correct types corresponding to the Medication class fields
	    idColumn.setCellValueFactory(new PropertyValueFactory<Medication, Integer>("medicationId")); // Property mid is of type Integer
	    nameColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("medicineName")); // Property medicationName is of type String
	    dosageColumn.setCellValueFactory(new PropertyValueFactory<Medication, Integer>("dosage")); // Property dosage is of type Integer
	    
	    aptIdcolumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId")); 
	    startTimeColumn.setCellValueFactory(cellData -> {
	        // Access the Time object from the Appointment object
	        Time startTime = cellData.getValue().getTime().getStart_time();
	        // If startTime is not null, return it as a string. Otherwise, return "N/A"
	        return new SimpleStringProperty(startTime != null ? startTime.toString() : "N/A");
	    });

	    endTimeColumn.setCellValueFactory(cellData -> {
	        // Access the Time object from the Appointment object
	        Time endTime = cellData.getValue().getTime().getEnd_time();
	        // If endTime is not null, return it as a string. Otherwise, return "N/A"
	        return new SimpleStringProperty(endTime != null ? endTime.toString() : "N/A");
	    });

	    dateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("appointmentDate")); 
	    statusColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Boolean>("status")); 
	    
	    billIdColumn.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("billId")); 
	    billStatusColumn.setCellValueFactory(new PropertyValueFactory<Bill, Boolean>("status")); 
	    paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<Bill, String>("paymentType")); 
	    amountColumn.setCellValueFactory(new PropertyValueFactory<Bill, Double>("amount")); 
	    
	    // Assuming the Patient class fetches the list of medications
	    Patient patient = new Patient();
	    medicationList = FXCollections.observableArrayList(patient.viewRecord(patId).getMedicine());
	    appointmentsList = FXCollections.observableArrayList(patient.viewRecord(patId).getAppointments());
	    billsList = FXCollections.observableArrayList(patient.viewRecord(patId).getBills());
	    
	    // Set the list of medications into the TableView
	    medicationTable.setItems(medicationList);
	    appointmentsTable.setItems(appointmentsList);
	    billsTable.setItems(billsList);
	}
	
	public void handleLoginButtonPatinet(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            String username = Username.getText();
	       
            if(event.getSource()==PatientSignIn)
            {
            	fxmlFile = "Patient.fxml";
                stageTitle = "Patient";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();

            patientController controller = loader.getController();
            Patient b = new Patient();
            String patientName = b.loadPatientName(username);
            int id= b.loadPatientId(username);
            String gender = b.loadPatientGender(username);
            String contact = b.loadPatientContact(username);
            String discharge = b.loadPatientDischargeStatus(username);
            String dob = b.loadPatientDOB(username);
            controller.detailsPatient(patientName, username,id,gender,dob,contact,discharge);
            controller.setPatientId(id);
            
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
	
	private void detailsPatient(String Name, String usrname,int id,String Gender, String dob,String Contact,String st) {
		  //  System.out.println("Setting admin name: " + Name);
		    if (name != null) {
		        name.setText(Name);
		        Username.setText(usrname);
		        Id.setText(Integer.toString(id));
		        DOB.setText(dob);
		        contact.setText(Contact);
		        gender.setText(Gender);
		        dischargeStatus.setText(st);
		    } 
		    else 
		        System.out.println("TextField  is null. Check FXML bindings.");
		    
	}
}
