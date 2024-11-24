// ---------------------------------------------- NOOR KA EHOSPITAL ------------------------------------------------------- //

package application;

import java.awt.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;

public class eHospital extends patientController implements Initializable {
	
//	private Patient patient= new Patient();
	
	//RECEPTIONIST
	@FXML 
    private Button ReceptionistSignIn;
	@FXML 
	private Button ReceptionistButton;
	@FXML
	private Button RegisterNewPatient; //calling function of patient
	@FXML
	private Button RegisterPatientByReceptionist; //calling function of receptionist
	@FXML
	private Button ScheduleAppointment;
	@FXML
	private Button ScheduleFollowUp;

	
	//TEXTFIELDS
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
    
    // ------------------------------------------------------------------ RECEPTIONIST BUTTON HANDLER ---------------------------------------------------------------------------- //
	
    public void handleReceptionistButtonClick(MouseEvent  event) {
	        try {
	        	
	        	String fxmlFile;
	            String stageTitle;
	            
	            if(event.getSource()==ReceptionistButton)
	            {
	            	fxmlFile = "ReceptionistLogin.fxml";
	                stageTitle = "Receptionist";
	            }
	            else
	            {
	            	throw new IllegalArgumentException("Unexpected button source");
	            }
	            
	            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
	            Parent newFormRoot = loader.load();

	           
	            
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
    
    // ------------------------------------------------------------------ RECEPTIONIST LOGIN HANDLER ---------------------------------------------------------------------------- //
    
    // ------------------------------------------------------------------ RECEPTIONIST LOGIN HANDLER ---------------------------------------------------------------------------- //
    
	public void handleLoginButtonReceptionist(MouseEvent event) {
		try {

        	String fxmlFile;
            String stageTitle;
            Employee employee= Employee.getInstanceReceptionist();
            Receptionist receptionist= Receptionist.getInstance();
            
            if(event.getSource()==ReceptionistSignIn)
            {
            	fxmlFile = "Receptionist.fxml";
                stageTitle = "Receptionist";
                
    			//=======================
            	String username="";
    			String password_ = "";
    			
    			if(Username!=null && password !=null)
    			{
    				username = Username.getText();
    	            password_ = password.getText();
    	
    	            if (username.isEmpty() || password_.isEmpty()) 
    	            {
    	              //  System.out.println("Username or password cannot be empty.");
    	                showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
    	                return; 
    	            }
    	            //input validation
    	            if(!username.matches("^[a-zA-Z0-9]+$")) //uses only alphanumeric 
		            {
		            	showAlert("Error", "Invalid Username", "Username can only contain letters and numbers.");
		                return;
		            }
    	
    	            Employee a = new Employee();
    	            boolean check = a.LoginReceptionist(username, password_);
    	
    	            if (!check) {
    	                showAlert("Login Failed", "Invalid Credentials", "The username or password is incorrect.");
    	                return; 
    	            }
    	            
    	            //================
    	            String name= employee.loadName(username);
    	            int id= receptionist.loadReceptionistId(username);
    	            int empid= employee.loadEmployeeId(username);
    	            String gender= employee.loadGender(username);
    	            int exp= employee.loadExperience(username);
    	            String con= employee.loadContact(username);
    	            String workingHrs=employee.loadWorkingHours(username); 
    	            employee.initReceptionist(empid,name,username,password_,gender,exp,workingHrs,con);
    	            receptionist.init(id, "");
    	            //================
    			}
    			else 
    			{
    				 if (employee == null) {
    		                showAlert("Error", "Missing Data", "No user is logged in.");
    		            }
    			}

            }
            
            else if(event.getSource()==RegisterPatientByReceptionist)
            {
                String name = nameTextField.getText();
                String p_username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String gender = genderComboBox.getValue();
                String dob = dobTextField.getText();
                String contact = contactTextField.getText();
                //input validation 
                if (name.isEmpty() || p_username.isEmpty() || password.isEmpty() || gender == null || dob.isEmpty() || contact.isEmpty()) {
                    showAlert("Error", "Missing Input", "All fields are required. Please fill in all fields.");
                    return;
                }
                if (!name.matches("^[a-zA-Z ]+$")) { // Name: Only alphabets and spaces
                    showAlert("Error", "Invalid Name", "Name can only contain alphabets and spaces.");
                    return;
                }
                if (!p_username.matches("^[a-zA-Z0-9]+$")) { // Username: Only alphanumeric
                    showAlert("Error", "Invalid Username", "Username can only contain letters and numbers.");
                    return;
                }
                if (gender == null || gender.isEmpty()) {
                    showAlert("Error", "Invalid Gender", "Please select a gender from the dropdown.");
                    return;
                }
                if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) { // DOB: Must match YYYY-MM-DD format
                    showAlert("Error", "Invalid DOB", "Date of Birth must be in the format YYYY-MM-DD.");
                    return;
                }
                try {
                    LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Validate if the format is correct
                } catch (DateTimeParseException e) {
                    showAlert("Error", "Invalid DOB", "Date of Birth must be a valid date in the format YYYY-MM-DD.");
                    return;
                }

                if (!contact.matches("\\d+")) { // Contact: Only numeric values allowed
                    showAlert("Error", "Invalid Contact", "Contact number can only contain digits.");
                    return;
                }

                
                Patient patient = new Patient();
                patient.registerPatient(name, p_username, password, gender, dob, contact);
                
            	fxmlFile = "Receptionist.fxml";
                stageTitle = "Receptionist";
            }
            
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();
            eHospital controller = loader.getController();
            controller.detailsNurse(
                    employee.getName(),
                    employee.getUsername(),
                    receptionist.getReceptionistId(),
                    employee.getEmployeeId(), 
                    employee.getGender(),
                    employee.getExperience(),
                    employee.getContact(),
                    employee.getWorkingHours()
                );
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
	
    // ------------------------------------------------------------------ SCHEDULE FOLLOW UP RECEPTIONIST ---------------------------------------------------------------------------- //
	
	public void handleScheduleFollowUpReceptionist(MouseEvent event)
	{
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==ScheduleFollowUp)
            {
            	fxmlFile = "ScheduleFollowUp.fxml";
                stageTitle = "ScheduleFollowUp";
            }
            
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
    // ------------------------------------------------------------------ SCHEDULE APPOINTMENT RECEPTIONIST ---------------------------------------------------------------------------- //
	
	public void handleScheduleAppointmentReceptionist(MouseEvent event)
	{
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==ScheduleAppointment)
            {
            	fxmlFile = "ScheduleAppointmentReceptionist.fxml";
                stageTitle = "ScheduleAppointmentReceptionist";
            }
            
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
    // ------------------------------------------------------------------ REGISTER PATIENT RECEPTIONIST ---------------------------------------------------------------------------- //
	
	public void handleRegisterPatientButtonClick(MouseEvent  event) {
        try {
        	
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==RegisterNewPatient)
            {
            	fxmlFile = "RegisterNewPatient.fxml";
                stageTitle = "Register Patient";
            }
            
            else {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	
	//PATIENT
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
	@FXML
	private Button updatePatientRecordViewUC;
    @FXML
    private Button updatePatientRecordupdateUC;
 	@FXML
    private TextField temp;
 	@FXML
    private TextField blood_p;
 	@FXML
    private TextField heart_b;
    @FXML
    private Button trackMedicationUpdate;
    @FXML
    private Button trackMedicationView;
    @FXML
    private ComboBox<String> MedicationNameComboBox;
    @FXML
    private ListView<String> medicationListView;

 // ------------------------------------------------------------------ PATIENT BUTTON HANDLER ---------------------------------------------------------------------------- //
    
	public void handlePatientButtonClick(MouseEvent  event) {
        try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==PatientButton)
            {
            	fxmlFile = "PatientSignUp.fxml";
                stageTitle = "Patient";
            }
            else if(event.getSource()==RegisterNewPatient)
            {
            	fxmlFile = "PatientSignUp.fxml";
                stageTitle = "Patient";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	// ------------------------------------------------------------------ REGISTER PATIENT BY PATIENT ---------------------------------------------------------------------------- //
	
	public void handleRegisterButtonClick(MouseEvent  event)
	{
		try {
			
			// Anoosha
        	String fxmlFile;
            String stageTitle;
            String name = nameTextField.getText();
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            String gender = genderComboBox.getValue();
            String dob = dobTextField.getText();
            String contact = contactTextField.getText();
            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || gender == null || dob.isEmpty() || contact.isEmpty()) {
                showAlert("Error", "Missing Input", "All fields are required. Please fill in all fields.");
                return;
            }
            if (!name.matches("^[a-zA-Z ]+$")) { // Name: Only alphabets and spaces
                showAlert("Error", "Invalid Name", "Name can only contain alphabets and spaces.");
                return;
            }
            if (!username.matches("^[a-zA-Z0-9]+$")) { // Username: Only alphanumeric
                showAlert("Error", "Invalid Username", "Username can only contain letters and numbers.");
                return;
            }
            if (gender == null || gender.isEmpty()) {
                showAlert("Error", "Invalid Gender", "Please select a gender from the dropdown.");
                return;
            }
            if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) { // DOB: Must match YYYY-MM-DD format
                showAlert("Error", "Invalid DOB", "Date of Birth must be in the format YYYY-MM-DD.");
                return;
            }
            try {
                LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Validate if the format is correct
            } catch (DateTimeParseException e) {
                showAlert("Error", "Invalid DOB", "Date of Birth must be a valid date in the format YYYY-MM-DD.");
                return;
            }

            if (!contact.matches("\\d+")) { // Contact: Only numeric values allowed
                showAlert("Error", "Invalid Contact", "Contact number can only contain digits.");
                return;
            }

            
            Patient patient = new Patient();
            patient.registerPatient(name, username, password, gender, dob, contact);
            int id = patient.getPatientId(username);
            //setPatientId(id);
        	fxmlFile = "Patient.fxml";
            stageTitle = "Patient";
            
            // Anoosha
            
			//======================================================

			Patient p = Patient.getInstance();
			
			if (usernameTextField != null && passwordTextField != null)
			{
		        if (username.isEmpty() || password.isEmpty()) 
		        {
		            System.out.println("Username or password cannot be empty.");
		            showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
		            return; 
		        }
		        //alpha numeric input validation 
		        if(!username.matches("^[a-zA-Z0-9]+$")) //uses only alphanumeric 
	            {
	            	showAlert("Error", "Invalid Username", "Username can only contain letters and numbers.");
	                return;
	            }
	
		        boolean check = patient.LoginPatient(username, password);
	
		        if (!check) {
		            showAlert("Login Failed", "Invalid Credentials", "The username or password is incorrect.");
		            return; 
		        }
		        
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = null; 
                java.sql.Date sqlDob = null;
                try {
                    // Parse the string into a LocalDate
                    localDate = LocalDate.parse(dob, formatter);
                    LocalDateTime localDateTime = localDate.atStartOfDay();
                    java.util.Date utilDob = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                    sqlDob = new java.sql.Date(utilDob.getTime());
                    System.out.println("Converted Date: " + sqlDob);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Invalid date format.");
                }
                
	            patient.init(id, username, name, gender, sqlDob, contact, check);
		
		}
		else 
		{
			 if (patient == null) 
	                showAlert("Error", "Missing Data", "No user is logged in.");
		}
			
		//=========================================

            if(event.getSource()==RegisterPatient)
            {
            	fxmlFile = "Patient.fxml";
                stageTitle = "Patient";
            }
            else
            	throw new IllegalArgumentException("Unexpected button source");
            
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();

            
          //==========================================
            eHospital controller = loader.getController();
            String st = patient.isDischargeStatus()? "Discharged":"Not Discharged";
            Date dob1 = patient.getDob();
            String dobString ="";
            if (dob1 != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dobString = formatter.format(dob1);
                controller.detailsPatient(patient.getPatientName(), patient.getUsername(), patient.getPatientId(), 
                                           patient.getGender(), dobString, patient.getContact(), st);
            } else {
                System.out.println("Date of Birth is null.");
                showAlert("Error", "Invalid Data", "Date of Birth is missing or invalid.");
            }

            controller.detailsPatient(patient.getPatientName(), patient.getUsername(),patient.getPatientId(),patient.getGender()
            		,dobString,patient.getContact(),st);
            //==========================================
            
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
	
	// ------------------------------------------------------------------ PATIENT PROFILE BUTTON ---------------------------------------------------------------------------- //
	
	public void handleProfileButtonClick(MouseEvent  event)
	{
        try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==RegisterPatient)
            {
            	fxmlFile = "Patient.fxml";
                stageTitle = "My Profile";
            }
            else if(event.getSource()==RegisterNewPatient)
            {
            	fxmlFile = "Patient.fxml";
                stageTitle = "My Profile";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();

            Patient patient = Patient.getInstance();
            eHospital controller = loader.getController();
            String st = patient.isDischargeStatus()? "Discharged":"Not Discharged";
            Date dob1 = patient.getDob();
            String dobString ="";
            if (dob1 != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dobString = formatter.format(dob1);
                controller.detailsPatient(patient.getPatientName(), patient.getUsername(), patient.getPatientId(), 
                                           patient.getGender(), dobString, patient.getContact(), st);
            } else {
                System.out.println("Date of Birth is null.");
                showAlert("Error", "Invalid Data", "Date of Birth is missing or invalid.");
            }

            controller.detailsPatient(patient.getPatientName(), patient.getUsername(),patient.getPatientId(),patient.getGender()
            		,dobString,patient.getContact(),st);
            
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
	
	// ------------------------------------------------------------------ PATIENT SIGN IN ---------------------------------------------------------------------------- //
	
	public void handleSignInClick(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==LoginPagePatient)
            {
            	fxmlFile = "PatientLogin.fxml";
                stageTitle = "PatientLogin";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	// ------------------------------------------------------------------ LOGIN PATIENT ---------------------------------------------------------------------------- //
	
	public void handleLoginButtonPatinet(MouseEvent  event) {
		try {
			//===============================
			
			String username="";
			String password_=""; 
			Patient patient= Patient.getInstance();
			if(Username!=null && password != null)
			{
				username = Username.getText();
		        password_ = password.getText();
	
		        if (username.isEmpty() || password_.isEmpty()) 
		        {
		          //  System.out.println("Username or password cannot be empty.");
		            showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
		            return; 
		        }
		        //input validation 
		        if(!username.matches("^[a-zA-Z0-9]+$")) //uses only alphanumeric 
	            {
	            	showAlert("Error", "Invalid Username", "Username can only contain letters and numbers.");
	                return;
	            }
	
		        Patient a = new Patient();
		        boolean check = a.LoginPatient(username, password_);
	
		        if (!check) {
		            showAlert("Login Failed", "Invalid Credentials", "The username or password is incorrect.");
		            return; 
		        }
		        
			       //======================================
		            String patientName = patient.loadPatientName(username);
		            int id= patient.loadPatientId(username);
		            String gender = patient.loadPatientGender(username);
		            String contact = patient.loadPatientContact(username);
	                String discharge = patient.loadPatientDischargeStatus(username);
	                String dob1 = patient.loadPatientDOB(username); // e.g., "2024-11-20"
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                LocalDate localDate = null; // Initializes to null
	                java.sql.Date sqlDob=null;
	                try {
	                    // Parse the string into a LocalDate
	                    localDate = LocalDate.parse(dob1, formatter);
	                    
	                    // Convert LocalDate to LocalDateTime (at the start of the day)
	                    LocalDateTime localDateTime = localDate.atStartOfDay();
	                    
	                    // Convert LocalDateTime to java.util.Date
	                    java.util.Date utilDob = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	                    
	                    // Convert java.util.Date to java.sql.Date
	                    sqlDob = new java.sql.Date(utilDob.getTime());

	                    System.out.println("Converted Date: " + sqlDob);

	                    // Now you can pass sqlDob (java.sql.Date) to your init method
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    System.out.println("Invalid date format.");
	                }
		            patient.init(id, username, patientName, gender, sqlDob, contact, check);
			
			}
			else 
			{
				 if (patient == null) 
		                showAlert("Error", "Missing Data", "No user is logged in.");
			}
			//=========================================
        	String fxmlFile;
            String stageTitle;
            //String username = Username.getText();
	       
            if(event.getSource()==PatientSignIn)
            {
            	fxmlFile = "Patient.fxml";
                stageTitle = "Patient";
            }
            else
            	throw new IllegalArgumentException("Unexpected button source");
            
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();

            //==========================================
            eHospital controller = loader.getController();
            String st= patient.isDischargeStatus()? "Discharged":"Not Discharged";
            Date dob= patient.getDob();
            String dobString ="";
            if (dob != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dobString = formatter.format(dob);
                controller.detailsPatient(patient.getPatientName(), patient.getUsername(), patient.getPatientId(), 
                                           patient.getGender(), dobString, patient.getContact(), st);
            } else {
                System.out.println("Date of Birth is null.");
                showAlert("Error", "Invalid Data", "Date of Birth is missing or invalid.");
            }

            controller.detailsPatient(patient.getPatientName(), patient.getUsername(),patient.getPatientId(),patient.getGender()
            		,dobString,patient.getContact(),st);
            //controller.setPatientId(patient.loadPatientId(username));
            
            //==========================================
            
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
	
	// ------------------------------------------------------------------ VIEW PRESCRIPTION OF PATIENT ---------------------------------------------------------------------------- //
	
	public void handlePatientViewPrescription(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==ViewPrescription)
            {
            	fxmlFile = "Medications.fxml";
                stageTitle = "Medications";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();
            if (event.getSource()==ViewPrescription)
            {
            	eHospital controller = loader.getController(); // Get the same controller
            	Patient p= Patient.getInstance();
            	controller.pidComboBox= new ComboBox<String>();
            	
            	if (controller.pidComboBox != null) 
            	    controller.pidComboBox.setValue(String.valueOf(Patient.getInstance().getId()));
 	            controller.initTable();
            }
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
	
	// ------------------------------------------------------------------ PAY BILLS HANDLER ---------------------------------------------------------------------------- //
	
	public void handlePatientPayBills(MouseEvent event)
	{
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==PayBills)
            {
            	fxmlFile = "PayBill.fxml";
                stageTitle = "PayBill";
            }
            else
            	throw new IllegalArgumentException("Unexpected button source");
            
            ;
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();
            if (event.getSource()==PayBills)
            {
            	eHospital controller = loader.getController(); // Get the same controller
 	            controller.loadBillID();
            }
            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle(stageTitle);
            newFormStage.show();
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// ------------------------------------------------------------------ SHOW PATIENT BILLS ---------------------------------------------------------------------------- //
	
	public void handleshowBills(MouseEvent event)
	{
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==showBills)
            {
            	fxmlFile = "Bills.fxml";
                stageTitle = "Bills";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();
            if (event.getSource()==showBills)
            {
            	eHospital controller = loader.getController(); // Get the same controller
 	            controller.initBillTable();
            }
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
	
	//NURSE
	@FXML 
    private Button NurseSignIn;
	@FXML
	private Button NurseButton;
	@FXML
	private Button UpdatePatientRecord;
	@FXML
	private Button TrackMedication;
	@FXML
	private Button ViewPatientRecord;
	@FXML
	private Button ViewPatientRecordNurse;
	@FXML
	private Button ViewPrescriptionNurse;
	@FXML
	private AnchorPane RecordAnchorPane;
	   
	// ------------------------------------------------------------------ NURSE BUTTON HANDLER ---------------------------------------------------------------------------- //
	
	public void handleNurseButtonClick(MouseEvent  event) {
        try {
        	
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==NurseButton)
            {
            	fxmlFile = "NurseLogin.fxml";
                stageTitle = "Nurse";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	// ------------------------------------------------------------------ LOGIN NURSE ---------------------------------------------------------------------------- //
	
	public void handleLoginButtonNurse(MouseEvent  event) {
		 try {
			//=======================
	        	String username="";
				String password_ ="";
				Employee employee= Employee.getInstanceNurse();
				Nurse nurse= Nurse.getInstance();
				if(Username!=null && password !=null)
				{
					username = Username.getText();
		            password_ = password.getText();
		
		            if (username.isEmpty() || password_.isEmpty()) 
		            {
		               // System.out.println("Username or password cannot be empty.");
		                showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
		                return; 
		            }
		            //validation for username 
		            if(!username.matches("^[a-zA-Z0-9]+$")) //uses only alphanumeric 
		            {
		            	showAlert("Error", "Invalid Username", "Username can only contain letters and numbers.");
		                return;
		            }
		            
		            
		            Employee a = new Employee();
		            boolean check = a.LoginNurse(username, password_);
		
		            if (!check) {
		                showAlert("Login Failed", "Invalid Credentials", "The username or password is incorrect.");
		                return; 
		            }
		            
		            //================
		            String name= employee.loadName(username);
		            int id= nurse.loadNurseId(username);
		            int empid= employee.loadEmployeeId(username);
		            String gender= employee.loadGender(username);
		            int exp= employee.loadExperience(username);
		            String con= employee.loadContact(username);
		            String workingHrs=employee.loadWorkingHours(username); 
		            employee.initNurse(empid,name,username,password_,gender,exp,workingHrs,con);
		            nurse.init(id, "");
		            //================
				}
				else 
				{
					 if (employee == null) {
			                showAlert("Error", "Missing Data", "No user is logged in.");
			            }
				}
	        	//=========================
	        	
	        	String fxmlFile;
	            String stageTitle;
	            
	            if(event.getSource()==NurseSignIn)
	            {
	            	fxmlFile = "Nurse.fxml";
	                stageTitle = "Nurse";
	            }
	            else
	            {
	            	throw new IllegalArgumentException("Unexpected button source");
	            }
	            
	            // Load the new FXML file
	            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
	            Parent newFormRoot = loader.load();
	            eHospital controller = loader.getController();
	            controller.detailsNurse(
	                    employee.getName(),
	                    employee.getUsername(),
	                    nurse.getNurseId(),
	                    employee.getEmployeeId(), 
	                    employee.getGender(),
	                    employee.getExperience(),
	                    employee.getContact(),
	                    employee.getWorkingHours()
	                );
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
	
	// ------------------------------------------------------------------ UPDATE PATIENT RECORD ---------------------------------------------------------------------------- //
	
	public void handleUpdatePatientRecordNurse(MouseEvent  event)
    {
		 try {
	        	String fxmlFile;
	            String stageTitle;
	            
	            if(event.getSource()==UpdatePatientRecord)
	            {
	            	fxmlFile = "UpdatePatientRecord.fxml";
	                stageTitle = "UpdatePatientRecord";
	            }
	            else
	            {
	            	throw new IllegalArgumentException("Unexpected button source");
	            }
	            
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
	
	// ------------------------------------------------------------------ TRACK MEDICATIONS ---------------------------------------------------------------------------- //
	
    public void handleTrackMedication(MouseEvent  event)
    {
    	 try {
         	String fxmlFile;
             String stageTitle;
             
             if(event.getSource()==TrackMedication)
             {
             	fxmlFile = "TrackMedication.fxml";
                 stageTitle = "TrackMedication";
             }
             else
             {
             	throw new IllegalArgumentException("Unexpected button source");
             }
             
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
    
    // ------------------------------------------------------------------ SET MEDICATION DETAILS ---------------------------------------------------------------------------- //
    
    @FXML
    public void setMedicationDetails(List<String> medicationDetails) {
        if (medicationDetails != null && !medicationDetails.isEmpty()) {
            medicationListView.getItems().addAll(medicationDetails);
        } else {
            medicationListView.getItems().add("No medication details available.");
        }
    }
    
    // -------------------------------------------------------------- TRACK MEDICATIONS USE CASE ---------------------------------------------------------------------------- //
    
    @FXML 
    public void handleNurseTrackMedicationView(MouseEvent event) {
    	String pidString = pidComboBox.getSelectionModel().getSelectedItem();
    	int pid = 0;

        if (pidString != null && !pidString.isEmpty())
        {
            try {
                pid = Integer.parseInt(pidString);
                if(event.getSource()==existingMed)
                {
                	try {
	                	String fxmlFile= "ViewExistingMedications.fxml";
	    	            String stageTitle="ViewExistingMedications";
	    	            // Load the new FXML file
	    	            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
	    	            Parent newFormRoot = loader.load();
	    	            
	    	            
	    	            eHospital controller = loader.getController(); // Get the same controller
	    	            controller.pidComboBox= this.pidComboBox;
	    	            controller.initTable(); // Initialize table after form is loaded
	    	            // Create a new scene and stage for the new form
	    	            Scene newFormScene = new Scene(newFormRoot);
	    	            Stage newFormStage = new Stage();
	    	            newFormStage.setScene(newFormScene);
	    	            newFormStage.setTitle(stageTitle);
	    	            
	    	            // Show the new form
	    	            newFormStage.show();
	    	            
//	    	             // Close the current form
//	    	            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//	    	            currentStage.close();
                	}
                	catch (IOException e) {
        	            e.printStackTrace();
        	        }
    	         
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Patient ID format. Please enter a valid number.");
                return; 
            }
          
        } 
        else 
        {
            System.out.println("Patient ID is not selected.");
            return; 
        }
        Medication medicationService = new Medication();
        List<String> medicationDetails = medicationService.GetMedicationDetails(pid);
        System.out.println("in the controller");
        for (String detail : medicationDetails) 
            System.out.printf(detail);
        

    }
    
    // ------------------------------------------------------------------ HANDLE TRACK MEDICATIONS ---------------------------------------------------------------------------- //
    
    @FXML
    public void handleNurseTrackMedication(MouseEvent event) {
        try {
            String pidString = pidComboBox.getSelectionModel().getSelectedItem();
            String medicationName = MedicationNameComboBox.getSelectionModel().getSelectedItem();

            int dosage = 0;
            int pid = 0;
            String dosageString = Dosage.getText();

            if (pidString != null && !pidString.isEmpty()) {
                try {
                    pid = Integer.parseInt(pidString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Patient ID format. Please enter a valid number.");
                    return; 
                }
            } else {
                System.out.println("Patient ID is not selected.");
                return; 
            }
            if (dosageString != null && !dosageString.isEmpty()) {
                try {
                    dosage = Integer.parseInt(dosageString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid dosage format. Please enter a valid number.");
                    return; 
                }
            } else {
                System.out.println("Dosage is not entered.");
                return; 
            }
            if (medicationName == null || medicationName.isEmpty()) {
                System.out.println("No medication name selected.");
                return; 
            }
            Medication medicationService = new Medication();
            medicationService.EnterMedicationDetails(pid, medicationName, dosage);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        }
    }
    
    // ------------------------------------------------------------------ POPULATE MEDICATIONS BOX ---------------------------------------------------------------------------- //
    
    @FXML
    public void populateMediComboBox() {
        Medication medicationService = new Medication();
        String selectedPidString = pidComboBox.getSelectionModel().getSelectedItem();

        int pid = -1;

        if (selectedPidString != null && !selectedPidString.isEmpty()) {
            try {
                pid = Integer.parseInt(selectedPidString); 
            } catch (NumberFormatException e) {
                System.out.println("Invalid PID format");
            }
        }

        Patient patientService = new Patient();
        ObservableList<String> medList = patientService.findPatientRecord(pid); 
        if (medList != null && MedicationNameComboBox != null) {
            MedicationNameComboBox.setItems(medList); 
        } else {
           // System.out.println("No medications found for this patient");
        }
    }
    @FXML
    private void populatePid1ComboBox() {
        Patient patientService = new Patient();
        ObservableList<String> pidList = patientService.getPatientIds();

        if (pidComboBox != null) {
            pidComboBox.setItems(pidList);

            pidComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                populateMediComboBox(); 
            });
        }
    }
    //end
    
    // ------------------------------------------------------------------ VIEW PATIENT PRESCRIPTION NURSE ---------------------------------------------------------------------------- //
    
    public void handleViewPatientRecord(MouseEvent  event)
    {
    	 try {
         	String fxmlFile;
             String stageTitle;
             
             if(event.getSource()==ViewPatientRecord)
             {
             	fxmlFile = "ViewPatientRecordNurse.fxml";
                 stageTitle = "ViewPatientRecordNurse";
             }
             else
             {
             	throw new IllegalArgumentException("Unexpected button source");
             }
             
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
    
    // ------------------------------------------------------------------ VIEW PATIENT RECORD NURSE ---------------------------------------------------------------------------- //
    
    public void handleViewPatientRecordNurse(MouseEvent event) {
        try {
            String pidString = pidComboBox.getSelectionModel().getSelectedItem();
            int pid = 0;
            if (pidString != null && !pidString.isEmpty()) {
                try {
                    pid = Integer.parseInt(pidString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Patient ID format. Please enter a valid number.");
                    return; 
                }
            } else {
                System.out.println("Patient ID is not selected.");
                return; 
            }

            // Call the function to update the existing table
            displayPatientRecord(pid); // Update the tables in the existing RecordAnchorPane

        } catch (Exception e) {
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        }
    }
    
    // ------------------------------------------------------------------ VIEW PATIENT PRESCRIPTION NURSE ---------------------------------------------------------------------------- //
    
    public void handleViewPrescriptionNurse(MouseEvent  event)
    {
    	 try {
         	String fxmlFile;
             String stageTitle;
             
             if(event.getSource()==ViewPrescriptionNurse)
             {
             	fxmlFile = "ViewPrescription.fxml";
                stageTitle = "ViewPrescription";
                
             }
             else
             {
             	throw new IllegalArgumentException("Unexpected button source");
             }
             
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
            // Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
           //  currentStage.close();
             //handleViewPrescriptionUC(loader);
             
         } catch (IOException e) {
             e.printStackTrace();
         }
    	 
    	 
    	
    }
	
	
	
	//DOCTOR
    @FXML
    private Button DoctorSignIn;
	@FXML
	private Button DoctorButton;
	@FXML
    private Button PrescribeMedications;
	@FXML
    private Button DischargePatient;
	@FXML
    private Button PatientRecord;
	
	// ------------------------------------------------------------------ DOCTOR BUTTON HANDLER ---------------------------------------------------------------------------- //
	
	public void handleDoctorButtonClick(MouseEvent  event) {
        try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==DoctorButton)
            {
            	fxmlFile = "DoctorLogin.fxml";
                stageTitle = "Doctor";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	// ------------------------------------------------------------------ LOGIN DOCTOR ---------------------------------------------------------------------------- //
	
	public void handleLoginButtonDoctor(MouseEvent  event) {
		try {	
			String username="";
			String password_ ="";
			Employee employee = Employee.getInstance();
			Doctor doctor = Doctor.getInstance();
			if(Username!=null && password != null)
			{
				username = Username.getText();
		        password_ = password.getText();
	
		        if (username.isEmpty() || password_.isEmpty()) 
		        {
		            //System.out.println("Username or password cannot be empty.");
		            showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
		            return; 
		        }
		        //input validation 
		        if(!username.matches("^[a-zA-Z0-9]+$")) //uses only alphanumeric 
	            {
	            	showAlert("Error", "Invalid Username", "Username can only contain letters and numbers.");
	                return;
	            }
		        
		        Employee a = new Employee();
		        boolean check = a.LoginDoctor(username, password_);
	
		        if (!check) {
		            showAlert("Login Failed", "Invalid Credentials", "The username or password is incorrect.");
		            return; 
		        }
		        //=====================
		       
	            String DoctorName = employee.loadName(username);
	            int id= employee.loadDoctorId(username);
	            int empid=employee.loadEmployeeId(username);
	            int exp= employee.loadExperience(username);
	            String con=employee.loadContact(username);
	            String wHrs=employee.loadWorkingHours(username);
	            String days= employee.loadDoctorWorkingDays(username);
	            boolean[] workdays= doctor.convertDaysToBooleanArray(days);
	            String gen=employee.loadGender(username);
	            employee.init(empid, DoctorName, username, password_, gen, exp, wHrs, con);
	            doctor.init(id, workdays, 0);
	            //===========================
			
			}
			else 
			{
				 if (employee == null) {
		                showAlert("Error", "Missing Data", "No user is logged in.");
		            }
			}
			
			
			
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==DoctorSignIn)
            {
            	fxmlFile = "Doctor.fxml";
                stageTitle = "Doctor";
            }
            else
            	throw new IllegalArgumentException("Unexpected button source");
            
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();
            
            eHospital controller = loader.getController();
            controller.detailsDoctor(
                    employee.getName(),
                    employee.getUsername(),
                    doctor.getDoctorId(),
                    employee.getEmployeeId(),
                    employee.getExperience(),
                    doctor.convertBooleanArrayToDays(doctor.getAvailableDays())
                );
            
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
	
	// ------------------------------------------------------------------ PRESCRIBE MEDICATIONS ---------------------------------------------------------------------------- //
	
	public void handlePrescribeMedicationsDoctor(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==PrescribeMedications)
            {
            	fxmlFile = "PrescribeMedications.fxml";
                stageTitle = "PrescribeMedications";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	// ------------------------------------------------------------------ DISCHARGE PATIENT ---------------------------------------------------------------------------- //
	
	public void handleDischargePatientDoctor(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==DischargePatient)
            {
            	fxmlFile = "DischargePatient.fxml";
                stageTitle = "DischargePatient";
            }
            
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	// ------------------------------------------------------------------ VIEW PATIENT RECORD DOCTOR ---------------------------------------------------------------------------- //
	
	public void handlePatientRecordDoctor(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==PatientRecord)
            {
            	fxmlFile = "ViewPatientRecordDoctor.fxml";
                stageTitle = "PatientRecord";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	
	
	
	
	//ADMIN
	@FXML
    private Button AdminSignIn;
	@FXML
	private Button AdminButton;
	@FXML
    private Button ManageEmployees;
	@FXML
	private Button CRUDdoctor;
	@FXML
	private Button CRUDNurse;
	@FXML
	private Button CRUDReceptionist;
	@FXML
	private Button ManageInventory;
	@FXML
	private Button HandlePayrolls;
	@FXML
	private Button addInventory;
	@FXML
	private Button deleteInventory;
	@FXML
	private Button currentInventory;
	@FXML
	private Button retrieveInventory;
	
	// ------------------------------------------------------------------ ADMIN BUTTON HANDLER ---------------------------------------------------------------------------- //
	
	public void handleAdminButtonClick(MouseEvent  event) {
        try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==AdminButton)
            {
            	fxmlFile = "AdminLogin.fxml";
                stageTitle = "Admin";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	// ------------------------------------------------------------------ LOGIN BUTTON ADMIN ---------------------------------------------------------------------------- //
	
	public void handleLoginButtonAdmin(MouseEvent  event) {
		try {
			
			String username="";
			String password_ ="";
			Admin admin= Admin.getInstance();
			//=========================
			if(Username!=null && password !=null)
			{
				username = Username.getText();
	            password_ = password.getText();
	
	            if (username.isEmpty() || password_.isEmpty()) 
	            {
	                System.out.println("Username or password cannot be empty.");
	                showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
	                return; 
	            }
	            //input validation 
	            if(!username.matches("^[a-zA-Z0-9]+$")) //uses only alphanumeric 
	            {
	            	showAlert("Error", "Invalid Username", "Username can only contain letters and numbers.");
	                return;
	            }
	            Admin a = new Admin();
	            boolean check = a.LoginAdmin(username, password_);
	
	            if (!check) {
	                showAlert("Login Failed", "Invalid Credentials", "The username or password is incorrect.");
	                return; 
	            }
	            
	            
	            //==================
	            
	            String adminName = admin.loadAdminName(username);
	            int id= admin.loadAdminId(username);
	            admin.init(id, adminName, username, password_);
	            //====================
			}
			else 
			{
				 if (admin == null) {
		                showAlert("Error", "Missing Data", "No user is logged in.");
		            }
			}

			//=========================
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==AdminSignIn)
            {
            	fxmlFile = "Admin.fxml";
                stageTitle = "Admin";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();
            
            eHospital controller = loader.getController();
            controller.details(admin.getAdminName(), admin.getUsername(),admin.getAdminId());
            
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
	
	// ------------------------------------------------------------------ MANAGE EMPLOYEES ---------------------------------------------------------------------------- //
	
	public void handleManageEmployeesAdmin(MouseEvent  event) {
		try {
			
        	if (AdminRightComboBox == null) {
        	    System.out.println("AdminRightComboBox is null!");
        	} else {
        	    System.out.println("AdminRightComboBox value: " + AdminRightComboBox.getValue());
        	}

        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==ManageEmployees)
            {
            	fxmlFile = "ManageEmployees.fxml";
                stageTitle = "ManageEmployees";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	// ------------------------------------------------------------------ HANDLE DOCTOR CRUD OPS ---------------------------------------------------------------------------- //
	
	public void handleCRUDopAdminToDoc(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==AdminButton)
            {
            	fxmlFile = "AdminLogin.fxml";
                stageTitle = "Admin";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
            //currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// ------------------------------------------------------------------ MANAGE INVENTORY ---------------------------------------------------------------------------- //
	
	public void handleManageInventoryAdmin(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==ManageInventory)
            {
            	fxmlFile = "ManageInventory.fxml";
                stageTitle = "ManageInventory";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	// ------------------------------------------------------------------ HANDLE DOCTOR CRUDS ---------------------------------------------------------------------------- //
	
	@FXML
	public void handleCRUDdoctor(MouseEvent event) {
	    try {
	        // Initialize options for AdminRightComboBox
	        ObservableList<String> options = FXCollections.observableArrayList("Add", "Read", "Update", "Delete");
	        AdminRightComboBox.setItems(options);

	        // Add listener to AdminRightComboBox to monitor selection changes
	        AdminRightComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
	            System.out.println("Selected value changed: " + newValue);
	        });
	        String selectedAction = AdminRightComboBox.getValue(); 

	        if (selectedAction == null) {
	            System.out.println("No action selected. Ensure an action is chosen.");
	            showAlert("Error", "No action selected", "Please Select action from Combobox");
	            
	            return;
	        }
	        
    	   
	        String fxmlFile = "";
	        String stageTitle = "";

	        // Determine action based on ComboBox value and event source
	        if (event.getSource() == CRUDdoctor) {
	            switch (selectedAction) {
	                case "Add":
	                    fxmlFile = "AddDoctor.fxml";
	                    stageTitle = "Add Doctor";
	                    break;
	                case "Read":
	                    fxmlFile = "ReadDoctor.fxml";
	                    stageTitle = "Retrieve Doctor";
	                    break;
	                case "Update":
	                    fxmlFile = "UpdateDoctor.fxml";
	                    stageTitle = "Update Doctor";
	                    break;
	                case "Delete":
	                    fxmlFile = "DeleteDoctor.fxml";
	                    stageTitle = "Delete Doctor";
	                    break;
	                default:
	                    System.out.println("Invalid action selected: " + selectedAction);
	                    return;
	            }
	        } else {
	            throw new IllegalArgumentException("Unexpected button source");
	        }

	        // Load the FXML file
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
	        Parent newFormRoot = loader.load();
	        if ("Read".equals(selectedAction))
	        {
	        	eHospital controller = loader.getController(); // Get the same controller
 	            controller.initDoctorTable();
	        }
	        // Create a new scene and stage for the new form
	        Scene newFormScene = new Scene(newFormRoot);
	        Stage newFormStage = new Stage();
	        newFormStage.setScene(newFormScene);
	        newFormStage.setTitle(stageTitle);

	        // Show the new form
	        newFormStage.show();

	        // Close the current stage
	        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	        //currentStage.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	// ------------------------------------------------------------------ HANDLE RECEPTIONIST CRUDS ---------------------------------------------------------------------------- //
	
    @FXML
    public void handleCRUDreceptionist(MouseEvent event)
	{
    	try {
			 ObservableList<String> options = FXCollections.observableArrayList("Add", "Read", "Update", "Delete");
		        AdminRightComboBox.setItems(options);

		        // Add listener to AdminRightComboBox to monitor selection changes
		        AdminRightComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
		            System.out.println("Selected value changed: " + newValue);
		        });
		        String selectedAction = AdminRightComboBox.getValue(); 

		        if (selectedAction == null) {
		            System.out.println("No action selected. Ensure an action is chosen.");
		            showAlert("Error", "No action selected", "Please Select action from Combobox");
		            return;
		        }
       	String fxmlFile="";
           String stageTitle="";
            if(event.getSource()==CRUDReceptionist)
            {
            	if(selectedAction=="Add")
            	{
            		fxmlFile = "AddReceptionist.fxml";
                    stageTitle = "AddReceptionist";
            	}
            	else if(selectedAction=="Read")
            	{
            		fxmlFile = "ReadReceptionist.fxml";
                    stageTitle = "ReadReceptionist";
            	}
            	else if(selectedAction=="Update")
            	{
            		fxmlFile = "UpdateReceptionist.fxml";
                    stageTitle = "UpdateReceptionist";
            	}
            	else if(selectedAction=="Delete")
            	{
            		fxmlFile = "DeleteReceptionist.fxml";
                    stageTitle = "DeleteReceptionist";
            	}
            	
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();
            if ("Read".equals(selectedAction))
	        {
	        	eHospital controller = loader.getController(); // Get the same controller
 	            controller.initReceptionistTable();
	        }
            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle(stageTitle);

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            //currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// ------------------------------------------------------------------ HANDLE NURSE CRUDS ---------------------------------------------------------------------------- //
	
	public void handleCRUDnurse(MouseEvent event)
	{
		try {
			 ObservableList<String> options = FXCollections.observableArrayList("Add", "Read", "Update", "Delete");
		        AdminRightComboBox.setItems(options);

		        // Add listener to AdminRightComboBox to monitor selection changes
		        AdminRightComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
		            System.out.println("Selected value changed: " + newValue);
		        });
		        String selectedAction = AdminRightComboBox.getValue(); 

		        if (selectedAction == null) {
		            System.out.println("No action selected. Ensure an action is chosen.");
		            showAlert("Error", "No action selected", "Please Select action from Combobox");
		            return;
		        }
        	String fxmlFile="";
            String stageTitle="";
            
            if(event.getSource()==CRUDNurse)
            {
            	if(selectedAction=="Add")
            	{
            		fxmlFile = "AddNurse.fxml";
                    stageTitle = "AddNurse";
            	}
            	else if(selectedAction=="Read")
            	{
            		fxmlFile = "ReadNurse.fxml";
                    stageTitle = "ReadNurse";
            	}
            	else if(selectedAction=="Update")
            	{
            		fxmlFile = "UpdateNurse.fxml";
                    stageTitle = "UpdateNurse";
            	}
            	else if(selectedAction=="Delete")
            	{
            		fxmlFile = "DeleteNurse.fxml";
                    stageTitle = "DeleteNurse";
            	}
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();
            if ("Read".equals(selectedAction))
	        {
	        	eHospital controller = loader.getController(); // Get the same controller
 	            controller.initNurseTable();
	        }
            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle(stageTitle);

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            //currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// ------------------------------------------------------------------ HANDLE PAYROLLS ---------------------------------------------------------------------------- //
	
	public void handleHandlePayrollsAdmin(MouseEvent event)
	{
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==HandlePayrolls)
            {
            	fxmlFile = "HandlePayrollProcessing.fxml";
                stageTitle = "HandlePayrollProcessing";
            }
            else
            {
            	throw new IllegalArgumentException("Unexpected button source");
            }
            
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
	
	// ------------------------------------------------------------------ HANDLE INVENTORY CRUD OPS ---------------------------------------------------------------------------- //
	
	public void handleInventoryCRUDadmin(MouseEvent event)
	{
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==retrieveInventory)
            {
            	fxmlFile = "UpdateInventoryItem.fxml";
                stageTitle = "UpdateInventoryItem";
            }
            else if (event.getSource()==currentInventory)
            {
            	fxmlFile = "CurrentInventory.fxml";
                stageTitle = "CurrentInventory";
                
            }
            else if(event.getSource()==deleteInventory)
            {
            	fxmlFile = "DeleteInventory.fxml";
                stageTitle = "DeleteInventory";
            }
            else if(event.getSource()==addInventory)
            {
            	fxmlFile = "AddInventoryItem.fxml";
                stageTitle = "AddInventoryItem";
            }
            
            else
            	throw new IllegalArgumentException("Unexpected button source");
            
            
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();

            if (event.getSource()==currentInventory)
            {
            	eHospital controller = loader.getController(); // Get the same controller
 	            controller.initInventoryTable();
            }
            else if(event.getSource()==retrieveInventory || event.getSource()==deleteInventory)
            {
            	eHospital controller = loader.getController(); // Get the same controller
 	            controller.loadItemNames();
            }
            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle(stageTitle);

            // Show the new form
            newFormStage.show();

            // Close the current form
            //Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            //currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	// ------------------------------------------------------------------ USE CASE : HANDLE PRESCRIBE MEDICATIONS ---------------------------------------------------------------------------- //
	
	@FXML
	private Button AddMed;
	@FXML
	private TextField medName;
	@FXML
	private TextField Dosage;
	@FXML 
	private Button existingMed;
	
	public void handlePrescribeMedicationUC(MouseEvent event)
	{
		int pid = Integer.parseInt(pidComboBox.getValue());
		if (pidComboBox != null && pidComboBox.getValue() != null) {
			//initTable();
		}
		if(event.getSource()==existingMed)
		{
			try {
	        	String fxmlFile= "ViewExistingMedications.fxml";
	            String stageTitle="ViewExistingMedications";
	            // Load the new FXML file
	            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
	            Parent newFormRoot = loader.load();
	            
	            
	            eHospital controller = loader.getController(); // Get the same controller
	            controller.pidComboBox= this.pidComboBox;
	            controller.initTable(); // Initialize table after form is loaded
	            // Create a new scene and stage for the new form
	            Scene newFormScene = new Scene(newFormRoot);
	            Stage newFormStage = new Stage();
	            newFormStage.setScene(newFormScene);
	            newFormStage.setTitle(stageTitle);
	            
	            // Show the new form
	            newFormStage.show();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		else if(event.getSource()==AddMed)
		{
			Patient patient= Patient.getInstance();
			String medicationName = medName.getText();
	        int dosageValue = Integer.parseInt(Dosage.getText());
	        
	        patient.addMedications(medicationName, dosageValue,pid);
	        medName.clear();
	        Dosage.clear();
		}
		
	}
	@FXML
	private TableView<Medication> medicationTable;
	@FXML
	private TableColumn<Medication, Integer> medicationId ;
	@FXML
	private TableColumn<Medication,String> medicineName;
	@FXML
	private TableColumn<Medication,Integer> dosage;
	private ObservableList<Medication> observableMedication;	
	@FXML
	private void initTable() {

		int pid;
		if (pidComboBox != null && pidComboBox.getValue() != null) {
			pid =Integer.parseInt(pidComboBox.getValue());
			observableMedication = FXCollections.observableArrayList();
			medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
			medicationId.setCellValueFactory(new PropertyValueFactory<>("medicationId"));
			dosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
			Patient patient = Patient.getInstance();
			observableMedication=patient.showExistingMedication(pid);
			medicationTable.setItems(observableMedication);
		   
		}

	}

	// ------------------------------------------------------------------ UPDATE PATIENT RECORD BY NURSE ---------------------------------------------------------------------------- //
	
	@FXML
    public void handleupdatePatientRecordViewNurseUC() {
    	try {
    		System.out.println("dance");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	 
    }
	
    @FXML
    public void handleupdatePatientRecordupdateNurseUC() {
    	String pidString = pidComboBox.getSelectionModel().getSelectedItem();
    	int pid = 0;
    	 String tempText = temp.getText();
    	 String bloodPressureText = blood_p.getText();
    	 String heartRateText = heart_b.getText();

        if (pidString != null && !pidString.isEmpty()) {
            try {
                pid = Integer.parseInt(pidString);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Patient ID format. Please enter a valid number.");
                return; 
            }
        }
        else {
            System.out.println("Patient ID is not selected.");
            return; 
        }
        //jab patient combobox se pid select karo ge then the patient record will be added
        PatientRecord p=new PatientRecord();
        p.updatePatientRecord(pid,tempText,bloodPressureText,heartRateText);
      //  System.out.println("done");
        
    }


    // ------------------------------------------------------------------ USE CASE: DISCHARGE PATIENT  ---------------------------------------------------------------------------- //
    
    @FXML
    private Button dischargeSummary;
    @FXML
    private TextField instructions;
    @FXML
    private TextField date;
	@FXML
	private AnchorPane DischargeConfirmationPane;
	
    public void handleDischargePatientUC(MouseEvent event)
    {
    	int pid = Integer.parseInt(pidComboBox.getValue());
		if (pidComboBox != null && pidComboBox.getValue() != null) 
		{
			if(event.getSource()==existingMed)
			{
				try {
		        	String fxmlFile= "ViewExistingMedications.fxml";
		            String stageTitle="ViewExistingMedications";
		            // Load the new FXML file
		            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
		            Parent newFormRoot = loader.load();
		            
		            
		            eHospital controller = loader.getController(); // Get the same controller
		            controller.pidComboBox= this.pidComboBox;
		            controller.initTable(); // Initialize table after form is loaded
		            // Create a new scene and stage for the new form
		            Scene newFormScene = new Scene(newFormRoot);
		            Stage newFormStage = new Stage();
		            newFormStage.setScene(newFormScene);
		            newFormStage.setTitle(stageTitle);
		            
		            // Show the new form
		            newFormStage.show();
		            
//		             // Close the current form
//		            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//		            currentStage.close();    
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
			}
			else if(event.getSource()==dischargeSummary)
			{
				Patient patient= Patient.getInstance();
				String FollowUPinstructions = instructions.getText();
				String inputDate = date.getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(inputDate, formatter);
				patient.dischargePatient(FollowUPinstructions, date,pid);
				DischargeConfirmationPane.setVisible(true);
			}
		}
		
    }
    
    // ------------------------------------------------------------------  USE CASE: PAY BILLS ---------------------------------------------------------------------------- //
    
    @FXML
    private Button payCash;
    @FXML
    private Button payCard;
    public void handlePayBillsUC(MouseEvent event)
    {
    	int id = billID.getValue();
    	Patient p= Patient.getInstance();
    	boolean check=false;
    	if(event.getSource()==payCash)
    	{
    		check = p.payByCash(id);
    	}
    	else if(event.getSource()==payCard)
    	{
    		check = p.payByCard(id);
    	}
    	if(check)
    		showAlert("Successful","Payment received","Status updated");
    	else
    		showAlert("Unsuccessful","Payment cancelled","No changes made");
    	
    	
    }
    
    @FXML
    private TableView<Bill> billTable;
    @FXML
    private TableColumn<Bill,Integer> bid;
    @FXML
    private TableColumn<Bill,Double> amount;
    private ObservableList<Bill> observableBill;
    @FXML
    private ComboBox<Integer> billID;
    
    public void initBillTable()
    {
    	Patient patient= Patient.getInstance();
    	//System.out.println(patient.getId()+ " "+ patient.getRecord().getRecordID());
    	observableBill = FXCollections.observableArrayList();
    	
    	bid.setCellValueFactory(new PropertyValueFactory<>("billId"));
    	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		observableBill = patient.loadBills();
	
		billTable.setItems(observableBill);
    }
    
    public void loadBillID()
    {
    	Patient p= Patient.getInstance();
    	ObservableList<Integer> id= p.loadBillID();
    	if (billID!=null && id != null && !id.isEmpty()) {
    		billID.setItems(id);
        } else {
            System.out.println("No bills to load.");
            billID.getItems().clear();
        }
    }
    
    
     
    
    
    // ------------------------------------------------------------------ USE CASE: MANAGE INVENTORY ---------------------------------------------------------------------------- //
    
    private ObservableList<InventoryItem> observableInventory;
    @FXML
    private TableView<InventoryItem> inventoryTable;
    @FXML
    private TableColumn<InventoryItem,Integer> invID;
    @FXML
    private TableColumn<InventoryItem,Integer> quantity;
    @FXML
    private TableColumn<InventoryItem,String> itemname;
    @FXML
    private TableColumn<InventoryItem,String> category;
    @FXML
    private TextField itemQuantity;
    @FXML
    private TextField itemCategory;
    
    @FXML
    public void initInventoryTable()
    {
    	observableInventory = FXCollections.observableArrayList();
    	itemname.setCellValueFactory(new PropertyValueFactory<>("name"));
		invID.setCellValueFactory(new PropertyValueFactory<>("invID"));
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
		InventoryItem invItem= new InventoryItem();
		observableInventory=invItem.displayCurrentInventory();
		//System.out.println(observableInventory.size());
		inventoryTable.setItems(observableInventory);
    }
    
    public void handleManageInventoryAddUC(MouseEvent event)
    {
    	if(event.getSource()==addInventory)
    	{
    		String itemname= name.getText();
    		String category= itemCategory.getText();
    		int quantity= Integer.parseInt(itemQuantity.getText());
    		InventoryItem inv= new InventoryItem();
    		boolean check =inv.addInventoryItem(quantity,category,itemname);
    		if(check)
    			showAlert("Successful","Item added","Your inventory is updated.");
    		else
    			showAlert("Unsuccessful","Unable to add item","Error occured.");
    	}
    }
    
    @FXML
    private ComboBox<String> itemNameBox;
    public void handleManagerInventoryUpdateUC(MouseEvent event)
    {
    	if(event.getSource()==retrieveInventory)
    	{
    		String itemname= itemNameBox.getValue();
    		int amt= Integer.parseInt(itemQuantity.getText());
    		String category=itemCategory.getText();
    		InventoryItem inv= new InventoryItem();
    		boolean check =inv.updateInventoryItem(amt,category,itemname);
    		if(check)
    			showAlert("Successful","Item updated","Your inventory is updated.");
    		else
    			showAlert("Unsuccessful","Unable to update item","Error occured.");
    		
    	}
    }
    
    public void loadItemNames()
    {
    	InventoryItem inv=new InventoryItem();
    	ObservableList<String> names= inv.loadItemNames();
    	if (itemNameBox!=null && names != null && !names.isEmpty()) {
            itemNameBox.setItems(names);
        } else {
            System.out.println("No items to load.");
            itemNameBox.getItems().clear();
        }
    	
    }
    
    public void handleManageInventoryDeleteUC(MouseEvent event)
    {
    	String itemname= itemNameBox.getValue();
		int amt= Integer.parseInt(itemQuantity.getText());
		InventoryItem inv= new InventoryItem();
		boolean check =inv.deleteInventoryItem(amt,itemname);
		if(check)
			showAlert("Successful","Item deleted","Your inventory is updated.");
		else
			showAlert("Unsuccessful","Unable to delete item","The quantity to be removed exceeds the available stock.");


    }
 
    
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
    @FXML
    private Button Close;
    
    // ------------------------------------------------------------------ CLOSE BUTTON HANDLER ---------------------------------------------------------------------------- //
    
    public void handleClose(MouseEvent event)
    {
    	if(event.getSource()==Close)
    	{
    		 Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	         currentStage.close();  
    	}
    }
    
    // ------------------------------------------------------------------ MESSAGE ALERT ---------------------------------------------------------------------------- //
    
	private void showAlert(String title, String header, String content) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(header);
	        alert.setContentText(content);
	        alert.showAndWait();
	}
	
	// ------------------------------------------------------------------ LOAD COMBO BOXES ---------------------------------------------------------------------------- //
	
	@FXML
 	private ComboBox<String> startTimeComboBox;
	@FXML
	private ComboBox<String> endTimeComboBox;
	@FXML
	private ComboBox<String> ActionComboBox;
	@FXML
	 private ComboBox<String> genderComboBox;
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	    // Set gender options in genderComboBox
	    if (genderComboBox != null) {
	        ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "Other");
	        genderComboBox.setItems(genders);
	        genderComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
	            System.out.println("Gender selected: " + newValue);
	        });
	    } else {
	        System.out.println("genderComboBox is not injected!");
	    }

	    // Set admin rights options in AdminRightComboBox
	    if (AdminRightComboBox != null) {
	        ObservableList<String> options = FXCollections.observableArrayList("Add", "Read", "Update", "Delete");
	        AdminRightComboBox.setItems(options);
	        AdminRightComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
	            System.out.println("Admin right selected: " + newValue);
	        });
	    } else {
	        System.out.println("AdminRightComboBox is not injected!");
	    }

	    // Set start time options in startTimeComboBox
	    if (startTimeComboBox != null) {
	        ObservableList<String> timeOptions = generateTimeOptions();
	        startTimeComboBox.setItems(timeOptions);
	        startTimeComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
	            selectedStartTime.set(newValue);  // Store the selected start time
	            System.out.println("Start time selected: " + newValue);
	        });
	    } else {
	        System.out.println("startTimeComboBox is not injected!");
	    }
	    if (endTimeComboBox != null) {
	        ObservableList<String> timeOptions = generateTimeOptions();
	        endTimeComboBox.setItems(timeOptions);
	        endTimeComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
	            selectedEndTime.set(newValue);  // Store the selected end time
	            System.out.println("End time selected: " + newValue);
	        });
	    } else {
	        System.out.println("endTimeComboBox is not injected!");
	    }

	    populateDidComboBox();
	    populateNidComboBox();
	    populateRidComboBox();
		 populatePid1ComboBox();
		 populatePidComboBox();
		 populateDoctorIdComboBox();
	}

	
	private ObservableList<String> generateTimeOptions() {
        ObservableList<String> timeOptions = FXCollections.observableArrayList();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        // Start from 8:00 AM
        LocalTime startTime = LocalTime.of(8, 0);
        // End at 10:00 PM
        LocalTime endTime = LocalTime.of(22, 0);

        while (!startTime.isAfter(endTime))
        {
            timeOptions.add(startTime.format(timeFormatter));
            startTime = startTime.plusMinutes(60); // Increment by 60 minutes
        }

        return timeOptions;
    }
	
	@FXML
	private ComboBox<String> pidComboBox;
	private void populatePidComboBox() {
		    // Create an ObservableList to hold patient IDs
		    ObservableList<String> pidList = FXCollections.observableArrayList();
		   // String url = "jdbc:sqlserver://10N5Q8AKAMRA\\SQLEXPRESS01;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
		    String url ="jdbc:sqlserver://FATIMA\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";

		    // Connect to the database and fetch the PIDs
		    try (Connection conn = DriverManager.getConnection(url)) {
		        String query = "SELECT pid FROM patient"; // Query to get patient ids
		        Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);

		        // Iterate through the result set and add PIDs to the ObservableList
		        while (rs.next()) {
		            String pid = rs.getString("pid");
		            pidList.add(pid);  // Add each pid to the ObservableList
		        }

		        // Set the ObservableList to the ComboBox if it's not null
		        if (pidComboBox != null) {
		            pidComboBox.setItems(pidList);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	
	// ------------------------------------------------------------------ STORE ADMIN DETAILS ---------------------------------------------------------------------------- //
	
	private void details(String Name, String usrname,int id) {
		  //  System.out.println("Setting admin name: " + Name);
		    if (name != null) {
		        name.setText(Name);
		        Username.setText(usrname);
		        Id.setText(Integer.toString(id));
		    } 
		    else 
		        System.out.println("TextField  is null. Check FXML bindings.");
		    
	}
	
	// ------------------------------------------------------------------ STORE PATIENT DETAILS ---------------------------------------------------------------------------- //
	
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
				showAlert("Error"," ","TextField  is null. Check FXML bindings.");
		    
	}
	
	// ------------------------------------------------------------------ STORE DOCTOR DETAILS ---------------------------------------------------------------------------- //
	
	private void detailsDoctor(String Name, String username, int id,int empid,int exp, String days)
	{
		if (name != null) {
	        name.setText(Name);
	        Username.setText(username);
	        Id.setText(Integer.toString(id));
	        empID.setText(Integer.toString(empid));
	        workingDays.setText(days);
	        experience.setText(Integer.toString(exp));
	       
	    } 
	    else 
	    	showAlert("Error"," ","TextField  is null. Check FXML bindings.");
	}
	
	// ------------------------------------------------------------------ STORE NURSE DETAILS ---------------------------------------------------------------------------- //
	
	private void detailsNurse(String nme,String username,int id, int empid, String gen,int exp,String con, String workinghrs)
	{
		if(name!=null)
		{
			 name.setText(nme);
		     Username.setText(username);
		     Id.setText(Integer.toString(id));
		     empID.setText(Integer.toString(empid));
		     gender.setText(gen);
		     experience.setText(Integer.toString(exp));
		     contact.setText(con);
		     workingHours.setText(workinghrs);
		}
		else 
			showAlert("Error"," ","TextField  is null. Check FXML bindings.");
	}
	
	@FXML
	private TextField workingHours;
	@FXML
	private TextField experience;
	@FXML
	private TextField workingDays;
	@FXML
	private TextField empID;
	@FXML
	private TextField salary;
	//-------------------------------------------------------------- SARA AKBAR USE CASES ------------------------------------------------------------------------------- //
	
	AtomicReference<String> selectedEndTime = new AtomicReference<>(null);
	AtomicReference<String> selectedStartTime = new AtomicReference<>(null);
	
    @FXML
    private void AdminDeleteNurse(MouseEvent event)
    {
    	 String nidString = nidComboBox.getSelectionModel().getSelectedItem();
    	 int nid=0;
		if (nidString != null && !nidString.isEmpty()) {
		    try {
		         nid = Integer.parseInt(nidString);
		        System.out.println("Selected Nurse ID as Integer: " + nid);
		       
		        
		    } catch (NumberFormatException e) {
		        System.err.println("Error: Selected Nurse ID is not a valid integer.");
		        showAlert("Error","Failure", "The Error occured");
		        e.printStackTrace();
		        return;
		    }
		} else {
		    System.err.println("No Nurse ID selected or the selected value is empty.");
		    showAlert("Error","Failure", "The Error occured");
		    return;
		}
		Admin a=new Admin();
		a.DeleteNurse(nid);
		System.out.println("Done");
		showAlert("Success","Success", "The Nurse has been deleted");
    }
    
    public void UpdateNurse(MouseEvent event)
    {
    	String nidString = nidComboBox.getSelectionModel().getSelectedItem();

        if (nidString == null || nidString.isEmpty()) {
            System.out.println("Nurse ID is not selected.");
            showAlert("Error", "Nurse ID", "Select Proper ID");
            return;
        }

        int nid = Integer.parseInt(nidString);
        System.out.println("Nurse ID to update: " + nid);

        // Check if either both start and end times are selected or neither is selected
        if ((selectedStartTime.get() != null && selectedEndTime.get() == null) || 
            (selectedStartTime.get() == null && selectedEndTime.get() != null)) {
            System.out.println("Both start and end time must be selected together.");
            return;
        }
        int workingHours=0;
        if (selectedStartTime.get() != null && selectedEndTime.get() != null) {
            try {
                workingHours = calculateWorkingHours(selectedStartTime.get(), selectedEndTime.get());
                System.out.println("Working hours: " + workingHours);
                if (workingHours<0)
                {
                	showAlert("Error", "Working Hours", "Invalid WorkingHours");
                	return;
                }
            } catch (Exception e) {
                System.err.println("Error calculating working hours: " + e.getMessage());
                return;
            }
        }

        // Proceed with the form submission logic
        String gender = genderComboBox.getValue();
        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String experience = experienceTextField.getText();
        String contact = contactTextField.getText();

        int experienceInt = 0;
        if(!experience.isEmpty())
        {
        	 try {
 	        	
 	            experienceInt = Integer.parseInt(experience);
 	            if(experienceInt<-1)
 	            {
 	            	showAlert("Error", "experience", "Invalid experience");
 	            	return;
 	            }
 	        } catch (NumberFormatException e) {
 	            System.err.println("Invalid input for experience. Please enter a valid number.");
 	        }

        }
        List<String>  ShiftTime  = new ArrayList<>();
        if (morningCheckBox.isSelected())  ShiftTime .add("morning");
        if (eveningCheckBox.isSelected())  ShiftTime .add("evening");
        if (nightCheckbox.isSelected())  ShiftTime .add("night");
      
        
        Admin admin = new Admin();
		int empid=admin.GetnidFromDid(nid);
        System.out.println("empid " +empid);
        if (name != null && !name.isEmpty()) {
        	  System.out.println("Name: " + name);
        	    admin.updateNameEmployee(empid,name);
        }
        
        if (gender != null && !gender.isEmpty()) {
            System.out.println("Gender: " + gender);
            admin.updateGenderEmployee(empid, gender);
        }

        if (username != null && !username.isEmpty()) {
            System.out.println("Username: " + username);
            admin.updateUserNameEmployee(empid,username);
        }

        if (password != null && !password.isEmpty()) {
            System.out.println("Password: " + password);
            admin.updatePasswordEmployee(empid,password);
        }

        if (workingHours != 0) { 
            System.out.println("Experience: " + experienceInt); 
            admin.updateworkingHourseNurse(empid, workingHours); 
        }
        
        if (contact != null && !contact.isEmpty()) {
            System.out.println("Contact: " + contact);
            admin.updateContactEmployee(empid,contact);
        }

        if (experienceInt != 0 ) {
            System.out.println("Contact: " + experienceInt);
            admin.updateExperienceEmployee(empid,experienceInt);
        }

       if (!ShiftTime.isEmpty()) {
            System.out.println("ShiftTime " + ShiftTime);
            admin.updateShiftTimeNurse(nid,ShiftTime);
        }
        
        System.out.println("done");
        showAlert("Success", "Updation", "Updation has been done");
    }
   
    @FXML
    private void populateDidComboBox() {
        Admin a= new Admin();
        ObservableList<String> didList = a.getDoctorIds();


        if (didComboBox != null) {
            // Set the items in the ComboBox
            didComboBox.setItems(didList);

            // Add a listener for selection changes
            didComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("Selected Doctor ID: " + newValue); 
               
            });
        }
    }
    @FXML
    public void populateNidComboBox() {
        Nurse nurse = new Nurse(); 
        ObservableList<String> NidList = nurse.getNurseIds();

        if (nidComboBox != null) {
            nidComboBox.setItems(NidList); 
            nidComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("Selected Nurse ID: " + newValue); 
            });
        } 
    }
    @FXML
    private ComboBox<String> RidComboBox;
    
    public void populateRidComboBox()
    {
    	 Receptionist r = new Receptionist(); 
	        ObservableList<String> RidList = r.getReceptionistIds();

	        if (RidComboBox != null) {
	            RidComboBox.setItems(RidList); 
	            RidComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	                System.out.println("Selected Receptionist ID: " + newValue); 
	            });
	        } 
    }

    @FXML
    private Button handleSubmitRecpetionist;
    //ADD RECEPTIONIST
    public void handleSubmitRecpetionist(MouseEvent event)
    {
    	  if (selectedStartTime.get() != null && selectedEndTime.get() != null) {
	            int workingHours = calculateWorkingHours(selectedStartTime.get(), selectedEndTime.get());
	            System.out.println("Working hours: " + workingHours);

	            if(workingHours<=0)
		        {
		        	showAlert("Error", "Failed ", "Invalid Working hours");
		        	return;
		        }
	            String gender = genderComboBox.getValue();
	            String name = nameTextField.getText();
	            String username = usernameTextField.getText();
	            String password = passwordField.getText();
	            String experience = experienceTextField.getText();
	            String contact = contactTextField.getText();
	            //start validation 
	            // Gender validation
	            if (gender == null) {
	                showAlert("Error", "Gender Not Selected", "Please select a gender.");
	                return;
	            }

	            // Name validation (only alphabets and spaces)
	            if (!name.matches("^[a-zA-Z\\s]+$")) {
	                showAlert("Error", "Invalid Name", "Name can only contain alphabets and spaces.");
	                return;
	            }
	            // Username validation (only alphanumeric)
	            if (!username.matches("^[a-zA-Z0-9]+$")) {
	                showAlert("Error", "Invalid Username", "Username can only contain alphanumeric characters.");
	                return;
	            }
	         // Contact validation (only digits)
	            if (!contact.matches("\\d+")) {
	                showAlert("Error", "Invalid Contact", "Contact can only contain digits.");
	                return;
	            }

	            //end validation 
	            
	            
	            if (gender == null || name.isEmpty() || username.isEmpty() || password.isEmpty() || contact.isEmpty()) {
		            showAlert("Error", "Missing Input", "Please fill in all required fields.");
		            return;
		        }
	            Admin admin = new Admin();
		        if (admin.isUsernameTaken(username)) {
		            showAlert("Error", "Username Taken", "The username is already taken. Please choose a different username.");
		            return;
		        }
	            int experienceInt = 0;
	            try {
	                experienceInt = Integer.parseInt(experience);
	                if(experienceInt <0)
		            {
		            	showAlert("Error", "Invalid Experience", "Please enter a valid number for experience.");
		            	return;
		            }
	            } catch (NumberFormatException e) {
	            	 System.err.println("Invalid input for experience. Please enter a valid number.");
		                showAlert("Error", "Invalid Experience", "Please enter a valid number for experience.");
	            }

	            // Add Doctor to database logic
	            List<String> ShiftTime = new ArrayList<>();
	            if (morningCheckBox.isSelected()) ShiftTime.add("Morning");
	            if (eveningCheckBox.isSelected()) ShiftTime.add("Evening");
	            if (nightCheckbox.isSelected()) ShiftTime.add("Night");

	            System.out.println("Shift Time: " + ShiftTime);
	            
	            
	           Admin a = new Admin();
	         boolean check= a.AddReceptionist(name, username, password, gender, experienceInt, contact,ShiftTime, workingHours);
	           
	            if (check) {
		            showAlert("Success", "Receptionist Added", "The receptionist was added successfully.");
		            System.out.println("Receptionist Added");
		        }
	        } else {
	            System.out.println("Error: Either Start or End time is not selected.");
	            showAlert("Error", "Fields left", "Fields can't be left empty");
	        }
    }
    
    //DELETE RECEPTIONIST
    public void deleteReceptionist(MouseEvent event)
    {
    	String ridString = RidComboBox.getSelectionModel().getSelectedItem();

        if (ridString == null || ridString.isEmpty()) {
            System.out.println("Recpetionist ID is not selected.");
            showAlert("Error","Failure", "Select Proper ID");
            return;
        }

        try {
            int rid = Integer.parseInt(ridString);
            System.out.println("Receptionist ID to delete: " + rid);
            Admin a=new Admin();
            a.deleteReceptionist(rid);
            System.out.println("Deletion Done");
            showAlert("Success","Success", "The Deletion has been Done");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Receptionist ID format. Please enter a valid number.");
            showAlert("Failure","Failure", "The Deletion can't happen");
        }
    }     
    //UPDATE RECEPTIONIST
    public void UpdateReceptionist(MouseEvent event)
    {
    	String ridString = RidComboBox.getSelectionModel().getSelectedItem();

        if (ridString == null || ridString.isEmpty()) {
            System.out.println("Nurse ID is not selected.");
            return;
        }

        int rid = Integer.parseInt(ridString);
        System.out.println("Receptionist ID to update: " + rid);

        // Check if either both start and end times are selected or neither is selected
        if ((selectedStartTime.get() != null && selectedEndTime.get() == null) || 
            (selectedStartTime.get() == null && selectedEndTime.get() != null)) {
            System.out.println("Both start and end time must be selected together.");
            return;
        }
        int workingHours=0;
        if (selectedStartTime.get() != null && selectedEndTime.get() != null) {
            try {
                workingHours = calculateWorkingHours(selectedStartTime.get(), selectedEndTime.get());
                System.out.println("Working hours: " + workingHours);
            } catch (Exception e) {
                System.err.println("Error calculating working hours: " + e.getMessage());
                return;
            }
        }

        // Proceed with the form submission logic
        String gender = genderComboBox.getValue();
        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String experience = experienceTextField.getText();
        String contact = contactTextField.getText();

        int experienceInt = 0;
        if(!experience.isEmpty())
        {
        	 try {
 	        	
 	            experienceInt = Integer.parseInt(experience);
 	        } catch (NumberFormatException e) {
 	            System.err.println("Invalid input for experience. Please enter a valid number.");
 	        }

        }
        List<String>  ShiftTime  = new ArrayList<>();
        if (morningCheckBox.isSelected())  ShiftTime .add("morning");
        if (eveningCheckBox.isSelected())  ShiftTime .add("evening");
        if (nightCheckbox.isSelected())  ShiftTime .add("night");
      
        
        Admin admin = new Admin();
		int empid=admin.GetempidFromrid(rid);
        System.out.println("empid " +empid);
        if (name != null && !name.isEmpty()) {
        	  System.out.println("Name: " + name);
        	    admin.updateNameEmployee(empid,name);
        }
        
        if (gender != null && !gender.isEmpty()) {
            System.out.println("Gender: " + gender);
            admin.updateGenderEmployee(empid, gender);
        }

        if (username != null && !username.isEmpty()) {
            System.out.println("Username: " + username);
            admin.updateUserNameEmployee(empid,username);
        }

        if (password != null && !password.isEmpty()) {
            System.out.println("Password: " + password);
            admin.updatePasswordEmployee(empid,password);
        }

        if (workingHours != 0) { 
            System.out.println("Experience: " + experienceInt); 
            admin.updateworkingHourseReceptionist(empid, workingHours); 
        }
        
        if (contact != null && !contact.isEmpty()) {
            System.out.println("Contact: " + contact);
            admin.updateContactEmployee(empid,contact);
        }

        if (experienceInt != 0 ) {
            System.out.println("Contact: " + experienceInt);
            admin.updateExperienceEmployee(empid,experienceInt);
        }

       if (!ShiftTime.isEmpty()) {
            System.out.println("ShiftTime " + ShiftTime);
           admin.updateShiftTimeReceptionist(rid,ShiftTime);
        }
        
        System.out.println("done");
    }
    
    @FXML
    private CheckBox morningCheckBox;
  
    @FXML
    private CheckBox eveningCheckBox;
    
    @FXML
    private CheckBox nightCheckbox;
    //ADD NURSE
    @FXML
    private void handleSubmitNurse(MouseEvent event)
    {
    	   if (selectedStartTime.get() != null && selectedEndTime.get() != null) {
	            int workingHours = calculateWorkingHours(selectedStartTime.get(), selectedEndTime.get());
	            System.out.println("Working hours: " + workingHours);
	          
	            if(workingHours<=0)
		        {
		        	showAlert("Error", "Failed ", "Invalid Working hours");
		        	return;
		        }
	            String gender = genderComboBox.getValue();
	            String name = nameTextField.getText();
	            String username = usernameTextField.getText();
	            String password = passwordField.getText();
	            String experience = experienceTextField.getText();
	            String contact = contactTextField.getText();
	            //start validation 
	            // Name validation (only alphabets and spaces)
	            if (!name.matches("^[a-zA-Z\\s]+$")) {
	                showAlert("Error", "Invalid Name", "Name can only contain alphabets and spaces.");
	                return;
	            }

	            // Username validation (only alphanumeric)
	            if (!username.matches("^[a-zA-Z0-9]+$")) {
	                showAlert("Error", "Invalid Username", "Username can only contain alphanumeric characters.");
	                return;
	            }
	            // Contact validation (only digits)
	            if (!contact.matches("\\d+")) {
	                showAlert("Error", "Invalid Contact", "Contact can only contain digits.");
	                return;
	            }
	            //end validation 
	            
		        if (gender == null || name.isEmpty() || username.isEmpty() || password.isEmpty() || contact.isEmpty()) {
		            showAlert("Error", "Missing Input", "Please fill in all required fields.");
		            return;
		        }
		        Admin admin = new Admin();
		        if (admin.isUsernameTaken(username)) {
		            showAlert("Error", "Username Taken", "The username is already taken. Please choose a different username.");
		            return;
		        }
	            int experienceInt = 0;
	            try {
	                experienceInt = Integer.parseInt(experience);
	                if(experienceInt <0)
		            {
		            	showAlert("Error", "Invalid Experience", "Please enter a valid number for experience.");
		            	return;
		            }
	            } catch (NumberFormatException e) {
	                System.err.println("Invalid input for experience. Please enter a valid number.");
	                showAlert("Error", "Invalid Experience", "Please enter a valid number for experience.");
	            }
	            
	            // Add Doctor to database logic
	            List<String> ShiftTime = new ArrayList<>();
	            if (morningCheckBox.isSelected()) ShiftTime.add("Morning");
	            if (eveningCheckBox.isSelected()) ShiftTime.add("Evening");
	            if (nightCheckbox.isSelected()) ShiftTime.add("Night");

	            System.out.println("Shift Time: " + ShiftTime);
	            
	           Admin a = new Admin();
	           boolean check=a.AddNurse(name, username, password, gender, experienceInt, contact,ShiftTime, workingHours);
	           if (check) {
		            showAlert("Success", "Nurse Added", "The nurse was added successfully.");
		            System.out.println("Nurse Added");
		        }
	        
	        } else {
	            System.out.println("Error: Either Start or End time is not selected.");
	            showAlert("Error", "Error Occured", "Please select all Fields");
	        }
    	
    }
    
	    @FXML
	    private ComboBox<String> nidComboBox;
	
	   // @FXML
	   // private TextLabel AdminComboBox;
	    @FXML
	    private ComboBox<String> didComboBox;
	    
	    @FXML
	    private ComboBox<String> AdminRightComboBox;
	   
	    @FXML
	    private TextField passwordField;
	    
	    @FXML
	    private TextField experienceTextField;
	    
	    @FXML
	    private CheckBox mondayCheckBox;
	    
	    @FXML
	    private CheckBox tuesdayCheckBox;
	    
	    @FXML
	    private CheckBox wednesdayCheckBox;
	    
	    @FXML
	    private CheckBox thursdayCheckBox;
	    
	    @FXML
	    private CheckBox fridayCheckBox;
	    
	    @FXML
	    private CheckBox saturdayCheckBox;
	    
	    
	    @FXML
	    private Button submitButton;

	    @FXML
	    private Button deleteButton;
	    
	    
	    @FXML
	    public void deleteDoctor(MouseEvent event) 
	    { 
	       String didString = didComboBox.getSelectionModel().getSelectedItem();

	        if (didString == null || didString.isEmpty()) {
	            System.out.println("Doctor ID is not selected.");
	            showAlert("Error","ID Selection", "Select an ID");
	            return;
	        }

	        try {
	            int did = Integer.parseInt(didString);
	            System.out.println("Doctor ID to delete: " + did);
	            Admin a=new Admin();
	            a.deleteDctor(did);
	            showAlert("Success","Doctor Deleted", "The doctor has been removed successfully");
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid Doctor ID format. Please enter a valid number.");
	        }
	    }
	   
	    @FXML
	    public void updateDoctor() {
	        String didString = didComboBox.getSelectionModel().getSelectedItem();

	        if (didString == null || didString.isEmpty()) {
	            System.out.println("Doctor ID is not selected.");
	            showAlert("Error","ID Selection", "Select an ID");
	            return;
	        }

	        int did = Integer.parseInt(didString);
	        System.out.println("Doctor ID to update: " + did);
	        if ((selectedStartTime.get() != null && selectedEndTime.get() == null) || 
	            (selectedStartTime.get() == null && selectedEndTime.get() != null)) {
	            System.out.println("Both start and end time must be selected together.");
	            return;
	        }
	        int workingHours=0;
	        if (selectedStartTime.get() != null && selectedEndTime.get() != null) {
	            try {
	                workingHours = calculateWorkingHours(selectedStartTime.get(), selectedEndTime.get());
	                System.out.println("Working hours: " + workingHours);
	            } catch (Exception e) {
	                System.err.println("Error calculating working hours: " + e.getMessage());
	                return;
	            }
	        }
	        String gender = genderComboBox.getValue();
	        String name = nameTextField.getText();
	        String username = usernameTextField.getText();
	        String password = passwordField.getText();
	        String experience = experienceTextField.getText();
	        String contact = contactTextField.getText();

	        int experienceInt = 0;
	        if(!experience.isEmpty())
	        {
	        	 try {
	 	        	
	 	            experienceInt = Integer.parseInt(experience);
	 	        } catch (NumberFormatException e) {
	 	            System.err.println("Invalid input for experience. Please enter a valid number.");
	 	        }

	        }
	        if(workingHours<=0)
	        {
	        	showAlert("Error", "Failed ", "Invalid Working hours");
	        	return;
	        }
	        List<String> availableDays = new ArrayList<>();
	        if (mondayCheckBox.isSelected()) availableDays.add("Monday");
	        if (tuesdayCheckBox.isSelected()) availableDays.add("Tuesday");
	        if (wednesdayCheckBox.isSelected()) availableDays.add("Wednesday");
	        if (thursdayCheckBox.isSelected()) availableDays.add("Thursday");
	        if (fridayCheckBox.isSelected()) availableDays.add("Friday");
	        
	        DBHandler db = new DBHandler();
			int empid=db.GetempidFromDid(did);
	        System.out.println("empid " +empid);
	        Admin admin = new Admin();
	        if (name != null && !name.isEmpty()) {
	        	  System.out.println("Name: " + name);
	        	    admin.updateNameEmployee(empid,name);
	        }
	        
	        if (gender != null && !gender.isEmpty()) {
	            System.out.println("Gender: " + gender);
	            admin.updateGenderEmployee(empid, gender);
	        }

	        if (username != null && !username.isEmpty()) {
	            System.out.println("Username: " + username);
	            admin.updateUserNameEmployee(empid,username);
	        }

	        if (password != null && !password.isEmpty()) {
	            System.out.println("Password: " + password);
	            admin.updatePasswordEmployee(empid,password);
	        }

	        if (workingHours != 0) { 
	            System.out.println("Experience: " + experienceInt); 
	            admin.updateworkingHourseEmployee(did,empid, selectedStartTime.get(), selectedEndTime.get(), workingHours); 
	        }
	        
	        if (contact != null && !contact.isEmpty()) {
	            System.out.println("Contact: " + contact);
	            admin.updateContactEmployee(empid,contact);
	        }

	        if (experienceInt != 0 ) {
	            System.out.println("Contact: " + experienceInt);
	            admin.updateExperienceEmployee(empid,experienceInt);
	        }

	        if (!availableDays.isEmpty()) {
	            System.out.println("Available Days: " + availableDays);
	            admin.updateAvailabeDaysDoctor(did,availableDays);
	        }
	        System.out.println("done");
	        showAlert("Success", "Updation", "Updation has been done");
	    }

	    @FXML
	    private CheckBox startTimeCheckBox;
	  
	    @FXML
	    private CheckBox endTimeCheckBox;
	    public static int calculateWorkingHours(String startTimeStr, String endTimeStr) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

	        LocalTime startTime = LocalTime.parse(startTimeStr, formatter);
	        LocalTime endTime = LocalTime.parse(endTimeStr, formatter);

	        long minutes = ChronoUnit.MINUTES.between(startTime, endTime);
	        int hours = (int) (minutes / 60);
	        return hours;
	    }
	    //ADD DOCTOR 
	@FXML
		public void handleSubmit() {
	        // Ensure that the start and end times are selected
	        if (selectedStartTime.get() == null || selectedEndTime.get() == null) {
	            showAlert("Error", "Time Selection Missing", "Please select both start and end times.");
	            return;
	        }

	        // Calculate working hours if both start and end times are selected
	        int workingHours = calculateWorkingHours(selectedStartTime.get(), selectedEndTime.get());
	        if (workingHours <= 0) {
	            showAlert("Error", "Invalid Working Hours", "Working hours must be greater than zero.");
	            return;
	        }
	        System.out.println("Working hours: " + workingHours);

	        // Validate form inputs
	        String gender = genderComboBox.getValue();
	        String name = nameTextField.getText();
	        String username = usernameTextField.getText();
	        String password = passwordField.getText();
	        String experience = experienceTextField.getText();
	        String contact = contactTextField.getText();
	        //new input validation 
	        if (gender == null) {
	            showAlert("Error", "Missing Input", "Please select a gender.");
	            return;
	        }

	        // Username validation
	        if (!username.matches("^[a-zA-Z0-9]+$")) {
	            showAlert("Error", "Invalid Username", "Username can only contain alphanumeric characters.");
	            return;
	        }

	        // Contact validation
	        if (!contact.matches("\\d+")) {
	            showAlert("Error", "Invalid Contact", "Contact can only contain digits.");
	            return;
	        }

	        // General input validation
	        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || contact.isEmpty()) {
	            showAlert("Error", "Missing Input", "Please fill in all required fields.");
	            return;
	        }
	        //end here
	        
	        if (gender == null || name.isEmpty() || username.isEmpty() || password.isEmpty() || contact.isEmpty()) {
	            showAlert("Error", "Missing Input", "Please fill in all required fields.");
	            return;
	        }

	        // Check if the username is already taken
	        Admin admin = new Admin();
	        if (admin.isUsernameTaken(username)) {
	            showAlert("Error", "Username Taken", "The username is already taken. Please choose a different username.");
	            return;
	        }

	        int experienceInt = 0;
	        try {
	            experienceInt = Integer.parseInt(experience);
	            if (experienceInt < 0) {
	                showAlert("Error", "Invalid Experience", "Experience must be a non-negative number.");
	                return;
	            }
	        } catch (NumberFormatException e) {
	            showAlert("Error", "Invalid Experience", "Please enter a valid number for experience.");
	            return;
	        }

	        // Collect available days
	        List<String> availableDays = new ArrayList<>();
	        if (mondayCheckBox.isSelected()) availableDays.add("Monday");
	        if (tuesdayCheckBox.isSelected()) availableDays.add("Tuesday");
	        if (wednesdayCheckBox.isSelected()) availableDays.add("Wednesday");
	        if (thursdayCheckBox.isSelected()) availableDays.add("Thursday");
	        if (fridayCheckBox.isSelected()) availableDays.add("Friday");

	        if (availableDays.isEmpty()) {
	            showAlert("Error", "No Days Selected", "Please select at least one available day.");
	            return;
	        }

	        // Attempt to add the doctor to the database
	        boolean check = admin.AddDoctor(name, username, password, gender, experienceInt, contact, availableDays, selectedStartTime.get(), selectedEndTime.get(), workingHours);

	        if (check) {
	            showAlert("Success", "Doctor Added", "The doctor was added successfully.");
	            System.out.println("Doctor Added");
	        } else {
	            showAlert("Error", "Database Error", "Failed to add the doctor. Please try again.");
	        }
	    }
		//READ NURSE
		//AdminRightComboBox
		//CRUD DOCTOR button 
		//handleCRUDdoctor this loads fxml files 
	
		
		
		//READ DOCTOR 
		@FXML		
		TableView<Doctor> DoctorsList;
		@FXML
		TableColumn<Doctor, Integer> docEmpID;
		@FXML
		TableColumn<Doctor, Integer> docID;
		@FXML
		TableColumn<Doctor, String> docName;
		@FXML
		TableColumn<Doctor, String> hrsWorking;
		@FXML
		TableColumn<Doctor, Integer> experienced;
		
		ObservableList<Doctor> doctorsDBlist;
		private void initDoctorTable()
		{
			
	    	//System.out.println(patient.getId()+ " "+ patient.getRecord().getRecordID());
	    	doctorsDBlist = FXCollections.observableArrayList();
	    	Doctor doc=new Doctor();
	    	doctorsDBlist=doc.listDoctors();
    	    System.out.println(doctorsDBlist.size());
	    	docEmpID.setCellValueFactory(new PropertyValueFactory<>("employeeId")); // From Employee class
    	    docID.setCellValueFactory(new PropertyValueFactory<>("doctorId"));      // From Doctor class
    	    docName.setCellValueFactory(new PropertyValueFactory<>("name"));        // From Employee class
    	    hrsWorking.setCellValueFactory(new PropertyValueFactory<>("workingHours")); // From Employee class or Doctor
    	    experienced.setCellValueFactory(new PropertyValueFactory<>("experience"));       // Assuming you want gender as status for example
    	    
	    	DoctorsList.setItems(doctorsDBlist);
			
		}
		
		//READ NURSE 
		@FXML
		TableView<Nurse> NurseList;
		@FXML
		TableColumn<Nurse, Integer> NurseEmpID;
		@FXML
		TableColumn<Nurse, Integer> NurseID;
		@FXML
		TableColumn<Nurse, String> NurseName;
		@FXML
		TableColumn<Nurse, String> NursehrsWorking;
		@FXML
		TableColumn<Nurse, Integer> Nurseexperienced;
		
		ObservableList<Nurse> NurseDBlist;
		private void initNurseTable()
		{
			
	    	//System.out.println(patient.getId()+ " "+ patient.getRecord().getRecordID());
			NurseDBlist = FXCollections.observableArrayList();
			Nurse doc=new Nurse();
	    	NurseDBlist=doc.listNurse();
    	    System.out.println("sdf"+NurseDBlist.size());
    	    NurseEmpID.setCellValueFactory(new PropertyValueFactory<>("employeeId")); // From Employee class
    	    NurseID.setCellValueFactory(new PropertyValueFactory<>("nurseId"));      // From Doctor class
    	    NurseName.setCellValueFactory(new PropertyValueFactory<>("name"));        // From Employee class
    	    NursehrsWorking.setCellValueFactory(new PropertyValueFactory<>("workingHours")); // From Employee class or Doctor
    	    Nurseexperienced.setCellValueFactory(new PropertyValueFactory<>("experience"));       // Assuming you want gender as status for example
    	    
    	    NurseList.setItems(NurseDBlist);
			
		}
		
		//READ RECEPTIONIST
		@FXML
		TableView<Receptionist> ReceptionistList;
		@FXML
		TableColumn<Receptionist, Integer> ReceptionistEmpID;
		@FXML
		TableColumn<Receptionist, Integer> ReceptionistID;
		@FXML
		TableColumn<Receptionist, String> ReceptionistName;
		@FXML
		TableColumn<Receptionist, String> ReceptionisthrsWorking;
		@FXML
		TableColumn<Receptionist, Integer> Receptionistexperienced;
		
		ObservableList<Receptionist> ReceptionistDBlist;
		private void initReceptionistTable()
		{
			
	    	//System.out.println(patient.getId()+ " "+ patient.getRecord().getRecordID());
			ReceptionistDBlist = FXCollections.observableArrayList();
			Receptionist doc=new Receptionist();
			ReceptionistDBlist=doc.listReceptionist();
    	    System.out.println("sdf"+ReceptionistDBlist.size());
    	    ReceptionistEmpID.setCellValueFactory(new PropertyValueFactory<>("employeeId")); // From Employee class
    	    ReceptionistID.setCellValueFactory(new PropertyValueFactory<>("receptionistId"));      // From Doctor class
    	    ReceptionistName.setCellValueFactory(new PropertyValueFactory<>("name"));        // From Employee class
    	    ReceptionisthrsWorking.setCellValueFactory(new PropertyValueFactory<>("workingHours")); // From Employee class or Doctor
    	    Receptionistexperienced.setCellValueFactory(new PropertyValueFactory<>("experience"));       // Assuming you want gender as status for example
    	    
    	    ReceptionistList.setItems(ReceptionistDBlist);
			
		}
}


