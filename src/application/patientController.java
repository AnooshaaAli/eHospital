package application;

import java.awt.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.Bill;
import model.Doctor;
import model.Medication;
import model.Patient;
import model.TimeSlot;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
	@FXML
	private Button ConfirmAppointment;
	
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
    private DatePicker appointmentDate;  
    @FXML
    private Label availabilityStatusLabel;  
    @FXML
    private Label nonAvailabilityStatusLabel;  
    
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
    
	@FXML
	private ComboBox<String> genderComboBox;
	@FXML
	private ComboBox<Integer> DoctorIdComboBox;
	@FXML
	private ComboBox<String> timeslotsComboBox;
	@FXML
	private ComboBox<String> pidComboBox;
	@FXML
	private ComboBox<Integer> appointmentIdsComboBox;
	 
	// ---------------------------------------------- PANES ----------------------------------------------------------- //
	
	@FXML
	private AnchorPane ConfirmationPane;
	
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
    
    // ----------------------------------------------- LABELS ------------------------------------------------------------ //
    
    @FXML
    private Label DoctorLabel;
    @FXML
    private Label PatientLabel;
    @FXML
    private Label DateLabel;
    @FXML
    private Label TimeLabel;
    @FXML
    private Label BillLabel;
    
    // -------------------------------------------- EVENT HANDLERS --------------------------------------------------- /
    
	public void initialize(URL location, ResourceBundle resources) {
		populateDoctorIdComboBox();
       // Populate genders for genderComboBox
       if (genderComboBox != null) 
       {
           ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "Other");
           genderComboBox.setItems(genders);
       }
       
	 }
	
	public void handlePatientViewRecord(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==ViewRecord)
            {
            	fxmlFile = "/patient/ViewPatientRecordPatient.fxml";
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
            Patient p = Patient.getInstance();
            controller.displayPatientRecord(p.getInstance().getPatientId());
                 
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
            	fxmlFile = "/patient/Patient.fxml";
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
            //controller.setPatientId(id);
            
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
	
	public void handlePatientScheduleAppointment(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==scheduleAppointment)
            {
            	fxmlFile = "/patient/ScheduleAppointment.fxml";
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
	
    // -------------------------------------------- MESSAGE ALERT -------------------------------------------------- //
    
	private void showAlert(String title, String header, String content) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(header);
	        alert.setContentText(content);
	        alert.showAndWait();
	}
	
	// ------------------------------------------------- POPULATE DOCTOR IDS ------------------------------------------------------- //
	
	public void populateDoctorIdComboBox() {
		Doctor doctor = new Doctor();
        ObservableList<Integer> docIdsList = doctor.getDoctorIdsList();
        if (DoctorIdComboBox != null) {
            DoctorIdComboBox.setItems(docIdsList);  
        } else {
            //System.out.println("DoctorIdComboBox is null!");  
        }
	}
	
	// -------------------------------------------------- IS DOCTOR AVAILABLE -------------------------------------------------------- //

	public void handleDoctorAvailability() {
	    Integer doctorId = DoctorIdComboBox.getValue(); 
	    if (doctorId == null) {
	        return;  
	    }

	    LocalDate date = appointmentDate.getValue();  
	    if (date == null) {
	        return;  
	    }
	    
	    // Check if the selected date is greater than today's date
	    if (!date.isAfter(LocalDate.now())) {
	    	showAlert("Error", "Invalid Date Selected!", "Please select a date in the future.");
	        return;
	    }
	    
	    Doctor doctor = new Doctor();
	    boolean isAvailable = doctor.checkDoctorAvailability(doctorId, date);
	    ConfirmationPane.setVisible(false);
	    
	    if (isAvailable) {
	    	nonAvailabilityStatusLabel.setVisible(false);
	        availabilityStatusLabel.setVisible(true);
			populateTimeSlotsComboBox();
	    } else {
	    	availabilityStatusLabel.setVisible(false);
	    	nonAvailabilityStatusLabel.setVisible(true);
	    }
	}

	// --------------------------------------------- POPULATE TIMESLOTS COMBO BOX -------------------------------------------------- //
	
	public void populateTimeSlotsComboBox() {
	    Integer doctorId = DoctorIdComboBox.getValue(); 
	    if (doctorId == null) {
	        return;  
	    }

	    LocalDate date = appointmentDate.getValue();  
	    if (date == null) {
	        return;  
	    }
		Doctor doctor = new Doctor();
        ObservableList<String> timeSlotsList = doctor.getDoctorTimeSlotsList(doctorId, date);
        if (timeslotsComboBox != null) {
        	timeslotsComboBox.setItems(timeSlotsList);  
        } else {
            //System.out.println("DoctorIdComboBox is null!");  
        }
	}

	// ------------------------------------------- APPOINTMENT CONFIRMATION HANDLER -------------------------------------------------- //
	
	public void handleAppointmentConfirmation() {
	    Integer doctorId = DoctorIdComboBox.getValue(); 
	    if (doctorId == null) {
	        return;  
	    }
	    
	    int docId = doctorId;
	    
	    LocalDate date = appointmentDate.getValue();  
	    if (date == null) {
	    	System.out.println("What is the date?" + date);
	        return;  
	    }
	    
	    Patient patient = Patient.getInstance();
	    System.out.println(patient.getInstance().getPatientId());
	    int recId = patient.getRecordId(patient.getInstance().getPatientId());
	    TimeSlot time = new TimeSlot();
	    String startTime = timeslotsComboBox.getValue();
	    
	    if (startTime == null) {
	        return;  
	    }
	    
	    double bill = 1500.0; //hardcoded for each doctor
	    int timeslotId = time.findTimeSlotId(startTime);
	    System.out.println("Time Slot Id:" + timeslotId);
	    if (patient.addAppointment(recId, docId, date, timeslotId)) {
	        // Add the time slot to DoctorTimeslot table
	        Doctor doctor = new Doctor(); // Assuming there's a Doctor class to handle this
	        int docTimeSlotId = doctor.getDoctorTimeslotID(timeslotId);
	        boolean added = doctor.addDoctorTimeslot(docId, docTimeSlotId, date);
	        if (added) {
	            Patient p = Patient.getInstance();
	            p.addBill(recId, bill, "unknown");
	            DoctorLabel.setText("Doctor Id: " + docId);
	            PatientLabel.setText("Patient Id: " + patient.getInstance().getPatientId());
	            DateLabel.setText("Date: " + date);
	            TimeLabel.setText("Time: " + startTime);
	            BillLabel.setText("Bill: " + bill);
	            ConfirmationPane.setVisible(true);
	            populateTimeSlotsComboBox();
	        } else {
	            System.out.println("Failed to add time slot to DoctorTimeslot table.");
	        }
	    }
		else {
			System.out.println("Unsuccessful");
		}
	}
	
	// ------------------------------------------- APPOINTMENT CONFIRMATION HANDLER RECEPTIONIST -------------------------------------------------- //
	
	public void handleAppointmentConfirmationReceptionist() {
	    Integer doctorId = DoctorIdComboBox.getValue(); 
	    if (doctorId == null) {
	        return;  
	    }
	    
	    int docId = doctorId;
	    
	    LocalDate date = appointmentDate.getValue();  
	    if (date == null) {
	        return;  
	    }
	    
	    Patient patient = new Patient();
	    
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
        
	    int recId = patient.getRecordId(pid);
	    TimeSlot time = new TimeSlot();
	    String startTime = timeslotsComboBox .getValue();
	    
	    if (startTime == null) {
	        return;  
	    }
	    
	    double bill = 1500.0; //hardcoded for each doctor
	    int timeslotId = time.findTimeSlotId(startTime);
	    if (patient.addAppointment(recId, docId, date, timeslotId)) {
	        // Add the time slot to DoctorTimeslot table
	        Doctor doctor = new Doctor(); // Assuming there's a Doctor class to handle this
	        int docTimeSlotId = doctor.getDoctorTimeslotID(timeslotId);
	        boolean added = doctor.addDoctorTimeslot(docId, docTimeSlotId, date);
	        if (added) {
	            Patient p = Patient.getInstance();
	            p.addBill(recId, bill, "unknown");
	            DoctorLabel.setText("Doctor Id: " + docId);
	            PatientLabel.setText("Patient Id: " + patient.getInstance().getPatientId());
	            DateLabel.setText("Date: " + date);
	            TimeLabel.setText("Time: " + startTime);
	            BillLabel.setText("Bill: " + bill);
	            ConfirmationPane.setVisible(true);
	            populateTimeSlotsComboBox();
	        } else {
	            System.out.println("Failed to add time slot to DoctorTimeslot table.");
	        }
	    }
		else {
			//System.out.println("Unsuccessful");
		}
	}
	
	// ------------------------------------------- FOLLOW UP REMINDER FROM HANDLER --------------------------------------------------------- //
	
	public void handleFollowUpReminder(MouseEvent  event) {
		try {
        	String fxmlFile;
            String stageTitle;
            
        	fxmlFile = "/patient/FollowUpReminder.fxml";
            stageTitle = "Manage Follow Ups";

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newFormRoot = loader.load();
            
            patientController controller = loader.getController();
            
            controller.displayPendingAppointments();
            controller.populateAppointmentIdComboBox();
            
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
	
	// ------------------------------------------- DISPLAY PENDING APPOINTMENTS ------------------------------------------------- //
	
	public void displayPendingAppointments() {
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
        Patient patient = Patient.getInstance();
        appointmentsList = FXCollections.observableArrayList(patient.getPendingAppointments(patient.getInstance().getPatientId()));
        appointmentsTable.setItems(appointmentsList);
	}
	
	// ------------------------------------------------- POPULATE APPOINTMENT IDS ------------------------------------------------------- //
	
	public void populateAppointmentIdComboBox() {
		Patient patient = Patient.getInstance();
        ObservableList<Integer> aptIdsList = patient.getPendingAppointmentIdsList(patient.getInstance().getPatientId());
        if (appointmentIdsComboBox != null) {
        	appointmentIdsComboBox.setItems(aptIdsList);  
        } else {
            System.out.println("AptIdComboBox is null!");  
        }
	}
	
	// -------------------------------------------------- UPDATE APPOINTMENT STATUS ----------------------------------------------------- //
	
	public void updateAptStatus() {
		int aptId = appointmentIdsComboBox.getValue();
		Patient patient = Patient.getInstance();
		patient.setPatientId(patient.getInstance().getPatientId());
		patient.markAptCompleted(aptId);
		displayPendingAppointments();
	}
}


/*
package application;

import java.awt.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.*;
import javafx.scene.control.*;

public class eHospital extends patientController implements Initializable {
	
	NurseController nurseController = new NurseController();
	private Patient patient = new Patient();
	
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
	public void handleLoginButtonReceptionist(MouseEvent event) {
		try {
			
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==ReceptionistSignIn)
            {
            	fxmlFile = "Receptionist.fxml";
                stageTitle = "Receptionist";
            }
            else if(event.getSource()==RegisterPatientByReceptionist)
            {
                String name = nameTextField.getText();
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String gender = genderComboBox.getValue();
                String dob = dobTextField.getText();
                String contact = contactTextField.getText();

                Patient patient = new Patient();
                patient.registerPatient(name, username, password, gender, dob, contact);
                
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
    //use case Update patietn record
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
    
    // ------------------------------ Anooshaa ---------------------------------- //
    
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
             Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
             currentStage.close();
             //handleViewPrescriptionUC(loader);
             
         } catch (IOException e) {
             e.printStackTrace();
         }
    	 
    	 
    	
    }
	
	
	
	

	//DOCTOR
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
			
			if(Username!=null && password != null)
			{
				String username = Username.getText();
		        String password_ = password.getText();
	
		        if (username.isEmpty() || password_.isEmpty()) 
		        {
		            System.out.println("Username or password cannot be empty.");
		            showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
		            return; 
		        }
	
		        Employee a = new Employee();
		        boolean check = a.LoginDoctor(username, password_);
	
		        if (!check) {
		            showAlert("Login Failed", "Invalid Credentials", "The username or password is incorrect.");
		            return; 
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
			
			//=========================
			
			String username = Username.getText();
            String password_ = password.getText();

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
            // Retrieve admin name from the database and set it in the TextField
            Admin b = new Admin();
            String adminName = b.loadAdminName(username);
            int id= b.loadAdminId(username);
            controller.details(adminName, username,id);
            
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
	
	
	
	
	//PM USE CASE
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
	//DH USE CASE
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
				String FollowUPinstructions = instructions.getText();
				String inputDate = date.getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(inputDate, formatter);
				patient.dischargePatient(FollowUPinstructions, date,pid);
			}
		}
		
    }
	//pay bills use case
    public void handlePayBillsUC()
    {
    	
    }
    @FXML
    private TableView<Bill> billTable;
    @FXML
    private TableColumn<Bill,Integer> bid;
    @FXML
    private TableColumn<Bill,Double> amount;
    private ObservableList<Bill> observableBill;
    @FXML
    public void initBillTable()
    {
    	
    }
    
    
    
    
   
    
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
	 @FXML
	 private ComboBox<String> genderComboBox;
	 
	 @Override
	public void initialize(URL location, ResourceBundle resources) {
		 // Populate the ComboBoxes with time options and action options
		 populatePid1ComboBox();
		 populatePidComboBox();
		 populateDoctorIdComboBox();
		
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
        
        // Populate genders for genderComboBox
        if (genderComboBox != null) 
        {
            ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "Other");
            genderComboBox.setItems(genders);
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
		    String url = "jdbc:sqlserver://10N5Q8AKAMRA\\SQLEXPRESS01;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
		    //String url ="jdbc:sqlserver://FATIMA\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";

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

	private void details(String Name, String usrname,int id) {
		  //  System.out.println("Setting admin name: " + Name);
		    if (name != null) {
		        name.setText(Name);
		        Username.setText(usrname);
		        Id.setText(Integer.toString(id));
		    } 
		    else 
		        System.out.println("TextField 'name' is null. Check FXML bindings.");
		    
		}
	 
}

*/