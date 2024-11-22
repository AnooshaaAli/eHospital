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
import java.sql.Time;
import java.time.LocalDate;
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
	
	private static int patId;
	
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
    
    //----------------------------------------------- GET PATIENT ID ----------------------------------------------------//
    
    public int getPatientId() {
    	return patId;
    }
    
    public void setPatientId(int patientId) {
    	patId = patientId;
    }
    
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
	    
	    Doctor doctor = new Doctor();
	    boolean isAvailable = doctor.checkDoctorAvailability(doctorId, date);
	    
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
	        return;  
	    }
	    
	    Patient patient = new Patient();
	    System.out.println(patId);
	    int recId = patient.getRecordId(patId);
	    TimeSlot time = new TimeSlot();
	    String startTime = timeslotsComboBox .getValue();
	    
	    if (startTime == null) {
	        return;  
	    }
	    
	    double bill = 1500.0; //hardcoded for each doctor
	    int timeslotId = time.findTimeSlotId(startTime);
		if(patient.addAppointment(recId, docId, date, timeslotId)) {
			//System.out.println("Successful");
			Patient p = new Patient();
			p.addBill(recId, bill, "unknown");
			DoctorLabel.setText("Doctor Id: " + docId);
			PatientLabel.setText("Patient Id: " + patId);
			DateLabel.setText("Date: " + date);
			TimeLabel.setText("Time: " + startTime);
			BillLabel.setText("Bill: " + bill);
			ConfirmationPane.setVisible(true);
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
		if(patient.addAppointment(recId, docId, date, timeslotId)) {
			//System.out.println("Successful");
			Patient p = new Patient();
			p.addBill(recId, bill, "unknown");
			DoctorLabel.setText("Doctor Id: " + docId);
			PatientLabel.setText("Patient Id: " + pid);
			DateLabel.setText("Date: " + date);
			TimeLabel.setText("Time: " + startTime);
			BillLabel.setText("Bill: " + bill);
			ConfirmationPane.setVisible(true);
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
            
        	fxmlFile = "FollowUpReminder.fxml";
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
        Patient patient = new Patient();
        appointmentsList = FXCollections.observableArrayList(patient.getPendingAppointments(patId));
        appointmentsTable.setItems(appointmentsList);
	}
	
	// ------------------------------------------------- POPULATE APPOINTMENT IDS ------------------------------------------------------- //
	
	public void populateAppointmentIdComboBox() {
		Patient patient = new Patient();
        ObservableList<Integer> aptIdsList = patient.getPendingAppointmentIdsList(patId);
        if (appointmentIdsComboBox != null) {
        	appointmentIdsComboBox.setItems(aptIdsList);  
        } else {
            System.out.println("AptIdComboBox is null!");  
        }
	}
	
	// -------------------------------------------------- UPDATE APPOINTMENT STATUS ----------------------------------------------------- //
	
	public void updateAptStatus() {
		int aptId = appointmentIdsComboBox.getValue();
		Patient patient = new Patient();
		patient.setPatientId(patId);
		patient.markAptCompleted(aptId);
		displayPendingAppointments();
	}
}
