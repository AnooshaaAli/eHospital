package application;

import java.awt.event.ActionEvent;
import javafx.scene.input.MouseEvent;
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
import java.util.List;
import java.util.ResourceBundle;

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

public class eHospital implements Initializable {
	
//	private Patient patient= new Patient();
	
	//RECEPTIONIST
	@FXML 
    private Button ReceptionistSignIn;
	@FXML 
	private Button ReceptionistButton;
	@FXML
	private Button RegisterNewPatient; //calling function of patient
	@FXML
	private Button ScheduleAppointment;
	@FXML
	private Button ScheduleFollowUp;

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
	public void handleLoginButtonReceptionist(MouseEvent event) {
		try {
			
			//=======================
        	String username="";
			String password_ ="";
			Employee employee= Employee.getInstanceReceptionist();
			Receptionist receptionist= Receptionist.getInstance();
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


        	
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==ReceptionistSignIn)
            {
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
	public void handleRegisterPatientButtonClick(MouseEvent  event) {
        try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==RegisterNewPatient)
            {
            	fxmlFile = "RegisterNewPatient.fxml";
                stageTitle = "Register Patient";
            }
            
            else
            	throw new IllegalArgumentException("Unexpected button source");
            
            
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
	public void handleRegisterButtonClick(MouseEvent  event)
	{
		try {
			//======================================================
			String username="";
			String password_=""; 
			Patient patient= Patient.getInstance();
			if(Username!=null && password != null)
			{
				username = Username.getText();
		        password_ = password.getText();
	
		        if (username.isEmpty() || password_.isEmpty()) 
		        {
		            System.out.println("Username or password cannot be empty.");
		            showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
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
                    LocalDateTime localDateTime = localDate.atStartOfDay();
                    java.util.Date utilDob = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                    sqlDob = new java.sql.Date(utilDob.getTime());
                    System.out.println("Converted Date: " + sqlDob);
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
		            System.out.println("Username or password cannot be empty.");
		            showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
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
	public void handlePatientScheduleAppointment(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==scheduleAppointment)
            {
            	fxmlFile = "ScheduleAppointment.fxml";
                stageTitle = "ScheduleAppointment";
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
	
	//to input in sql for patient
	public void registerPatient() {}

	
	
	
	
	
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
	private Button ViewPrescriptionNurse;
	    
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
		                System.out.println("Username or password cannot be empty.");
		                showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
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
    //use case Update patient record
    @FXML
    public void setMedicationDetails(List<String> medicationDetails) {
        if (medicationDetails != null && !medicationDetails.isEmpty()) {
            medicationListView.getItems().addAll(medicationDetails);
        } else {
            medicationListView.getItems().add("No medication details available.");
        }
    }
    //Track medication use case
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
	public void handleManageEmployeesAdmin(MouseEvent  event) {
		try {
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
            currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
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
	public void handleCRUDdoctor(MouseEvent event)
	{
		try {
        	String fxmlFile="";
            String stageTitle="";
            String selectedAction = ActionComboBox.getValue();
            if(event.getSource()==CRUDdoctor)
            {
            	if(selectedAction=="Add")
            	{
            		fxmlFile = "AddDoctor.fxml";
                    stageTitle = "AddDoctor";
            	}
            	else if(selectedAction=="Retrieve")
            	{
            		fxmlFile = "ReadDoctor.fxml";
                    stageTitle = "ReadDoctor";
            	}
            	else if(selectedAction=="Update")
            	{
            		fxmlFile = "UpdateDoctor.fxml";
                    stageTitle = "UpdateDoctor";
            	}
            	else if(selectedAction=="Delete")
            	{
            		fxmlFile = "DeleteDoctor.fxml";
                    stageTitle = "DeleteDoctor";
            	}
            	
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
	public void handleCRUDreceptionist(MouseEvent event)
	{
		try {
        	String fxmlFile="";
            String stageTitle="";
            String selectedAction = ActionComboBox.getValue();
            if(event.getSource()==CRUDReceptionist)
            {
            	if(selectedAction=="Add")
            	{
            		fxmlFile = "AddReceptionist.fxml";
                    stageTitle = "AddReceptionist";
            	}
            	else if(selectedAction=="Retrieve")
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
	public void handleCRUDnurse(MouseEvent event)
	{
		try {
        	String fxmlFile="";
            String stageTitle="";
            String selectedAction = ActionComboBox.getValue();
            if(event.getSource()==CRUDNurse)
            {
            	if(selectedAction=="Add")
            	{
            		fxmlFile = "AddNurse.fxml";
                    stageTitle = "AddNurse";
            	}
            	else if(selectedAction=="Retrieve")
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
	
	
	
	
	//prescribe Medications
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
			Patient patient= Patient.getInstance();
			observableMedication=patient.showExistingMedication(pid);
			medicationTable.setItems(observableMedication);
		   
		}

	}

	//update patient record
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
    //discharge patient
    @FXML
    private Button dischargeSummary;
    @FXML
    private TextField instructions;
    @FXML
    private TextField date;
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
			}
		}
		
    }
	//pay bills use case
    @FXML
    private Button payCash;
    @FXML
    private Button payCard;
    public void handlePayBillsUC(MouseEvent event)
    {
    	Patient p= Patient.getInstance();
    	boolean check=false;
    	if(event.getSource()==payCash)
    	{
    		check=p.payByCash();
    	}
    	else if(event.getSource()==payCard)
    	{
    		check=p.payByCard();
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
    	System.out.println(patient.getId()+ " "+ patient.getRecord().getRecordID());
    	observableBill = FXCollections.observableArrayList();
    	
    	bid.setCellValueFactory(new PropertyValueFactory<>("billId"));
    	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		observableBill =patient.loadBills();
	
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
   //view prescription patient
    
    
    
    
    
    
    
    
    
    
    
    
    //MANAGE INVENTORY   use case
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
    public void handleClose(MouseEvent event)
    {
    	if(event.getSource()==Close)
    	{
    		 Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	         currentStage.close();  
    	}
    }
    //load login 
	private void showAlert(String title, String header, String content) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(header);
	        alert.setContentText(content);
	        alert.showAndWait();
	}
	//LOAD COMBOBOX
	@FXML
 	private ComboBox<String> startTimeComboBox;
	@FXML
	private ComboBox<String> endTimeComboBox;
	@FXML
	private ComboBox<String> ActionComboBox;
	 @Override
	public void initialize(URL location, ResourceBundle resources) {
		 // Populate the ComboBoxes with time options and action options
		 populatePid1ComboBox();
		 populatePidComboBox();
		// loadItemNames();
		
	     // Populate time options for startTimeComboBox and endTimeComboBox
		 if (startTimeComboBox != null) 
		 {
			 ObservableList<String> timeOptions = generateTimeOptions();
	         startTimeComboBox.setItems(timeOptions);
	         endTimeComboBox.setItems(timeOptions);
		 }

        // Populate action options for ActionComboBox
        if (ActionComboBox != null) 
        {
            ObservableList<String> crudOps = FXCollections.observableArrayList("Add", "Retrieve", "Update", "Delete");
            ActionComboBox.setItems(crudOps);
        }
     
    }
	 // Method to generate time options from 8:00 AM to 10:00 PM in 60-minute intervals
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
	//menu page functions
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
}
