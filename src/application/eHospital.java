package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

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
import javafx.stage.Stage;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class eHospital implements Initializable {

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
        	String fxmlFile;
            String stageTitle;
            
            if(event.getSource()==RegisterPatient)
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
        	String fxmlFile;
            String stageTitle;
            
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
             Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
             currentStage.close();
             
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
	
	@FXML
    private ComboBox<String> ActionComboBox;

	@FXML
	    private TextField AdminUsername;
	    @FXML
	    private TextField Adminpassword;
	 
	    public void handleLoginButtonAdmin(MouseEvent event) {
	        try {
	            String username = AdminUsername.getText();
	            String password = Adminpassword.getText();

	            if (username.isEmpty() || password.isEmpty()) {
	                System.out.println("Username or password cannot be empty.");
	                showAlert("Error", "Invalid Input", "Username or password cannot be empty.");
	                return; 
	            }

	            Admin a = new Admin();
	            boolean check = a.LoginAdmin(username, password);

	            if (!check) {
	                showAlert("Login Failed", "Invalid Credentials", "The username or password is incorrect.");
	                return; 
	            }

	            if (event.getSource() == AdminSignIn) {
	                FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
	                Parent newFormRoot = loader.load();


//.setText(.getText());
	                Scene newFormScene = new Scene(newFormRoot);
	                Stage newFormStage = new Stage();
	                newFormStage.setScene(newFormScene);
	                newFormStage.setTitle("Admin");

	                newFormStage.show();

	                // Close the current stage
	                Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	                currentStage.close();
	            } else {
	                throw new IllegalArgumentException("Unexpected button source");
	            }


	        } catch (IOException e) {
	            e.printStackTrace();
	            showAlert("Error", "Unexpected Error", "An error occurred while loading the next stage.");
	        }
	    }
	    private void showAlert(String title, String header, String content) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(header);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	    
	   // @FXML
	   // private TextLabel AdminComboBox;
	    @FXML
	    private ComboBox<String> didComboBox;
	    @FXML
	    private ComboBox<String> AdminRightComboBox;
	    @FXML
	    public void handleManageEmployeesAdmin(MouseEvent event) {
	        try {
	        	if (AdminRightComboBox == null) {
	        	    System.out.println("AdminRightComboBox is null!");
	        	} else {
	        	    System.out.println("AdminRightComboBox value: " + AdminRightComboBox.getValue());
	        	}

	        	

	            String fxmlFile;
	            String stageTitle;

	            if (event.getSource() == ManageEmployees) {
	                fxmlFile = "ManageEmployees.fxml";
	                stageTitle = "Manage Employees";
	            } else {
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
	    @FXML
	    private TextField nameTextField;
	    
	    @FXML
	    private TextField usernameTextField;
	    
	    @FXML
	    private TextField passwordField;
	    
	    @FXML
	    private ComboBox<String> genderComboBox;

	    
	    @FXML
	    private TextField experienceTextField;
	    
	    @FXML
	    private TextField contactTextField;
	    
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
	        
	        DB_Handler db=new DB_Handler();
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


	    
 	public void handleCRUDopAdminToDoc(MouseEvent event) {
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
	@FXML
	public void handleCRUDdoctor(MouseEvent event) {
	    try {
	        // Initialize options for AdminRightComboBox
	        ObservableList<String> options = FXCollections.observableArrayList("Add", "Retrieve", "Update", "Delete");
	        AdminRightComboBox.setItems(options);

	        // Add listener to AdminRightComboBox to monitor selection changes
	        AdminRightComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
	            System.out.println("Selected value changed: " + newValue);
	        });
	        String selectedAction = AdminRightComboBox.getValue(); 

	        if (selectedAction == null) {
	            System.out.println("No action selected. Ensure an action is chosen.");
	            showAlert("Error", "Nn action selected", "Please Select action from Combobox");
	            
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
	                case "Retrieve":
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

	        // Create a new scene and stage for the new form
	        Scene newFormScene = new Scene(newFormRoot);
	        Stage newFormStage = new Stage();
	        newFormStage.setScene(newFormScene);
	        newFormStage.setTitle(stageTitle);

	        // Show the new form
	        newFormStage.show();

	        // Close the current stage
	        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	        currentStage.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


    @FXML
    private CheckBox morningCheckBox;
  
    @FXML
    private CheckBox eveningCheckBox;
    
    @FXML
    private CheckBox nightCheckbox;
    
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
    
    @FXML
    public void handleCRUDreceptionist(MouseEvent event)
	{
    	try {
			 ObservableList<String> options = FXCollections.observableArrayList("Add", "Retrieve", "Update", "Delete");
		        AdminRightComboBox.setItems(options);

		        // Add listener to AdminRightComboBox to monitor selection changes
		        AdminRightComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
		            System.out.println("Selected value changed: " + newValue);
		        });
		        String selectedAction = AdminRightComboBox.getValue(); 

		        if (selectedAction == null) {
		            System.out.println("No action selected. Ensure an action is chosen.");
		            showAlert("Error", "Nn action selected", "Please Select action from Combobox");
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
			 ObservableList<String> options = FXCollections.observableArrayList("Add", "Retrieve", "Update", "Delete");
		        AdminRightComboBox.setItems(options);

		        // Add listener to AdminRightComboBox to monitor selection changes
		        AdminRightComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
		            System.out.println("Selected value changed: " + newValue);
		        });
		        String selectedAction = AdminRightComboBox.getValue(); 

		        if (selectedAction == null) {
		            System.out.println("No action selected. Ensure an action is chosen.");
		            showAlert("Error", "Nn action selected", "Please Select action from Combobox");
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
	
	
	//LOAD COMBOBOX
	 @FXML
 	 private ComboBox<String> startTimeComboBox;
	 @FXML
	 private ComboBox<String> endTimeComboBox;
	
	    //sara code 
	 	@FXML
	    private Button updatePatientRecordView;
	    
	    @FXML
	    private Button updatePatientRecordupdate;
	    
	 	@FXML
	    private ComboBox<String> pid1ComboBox;
	 
	 	@FXML
	    private TextField temp;
	 
	 	@FXML
	    private TextField blood_p;
	 
	 	@FXML
	    private TextField heart_b;
	 
	    @FXML
	    private TextField Dosage;

	    @FXML
	    private ComboBox<String> pidComboBox;

	    @FXML
	    private Button trackMedicationUpdate;
	    
	    @FXML
	    private Button trackMedicationView;
@FXML
	    private ComboBox<String> ActionRightComboBox;
	    @FXML
	    private ComboBox<String> MedicationNameComboBox;
	    
	    @FXML
	    private ListView<String> medicationListView;
	    
	    @FXML
	    public void handleupdatePatientRecordViewNurse() {
	    	try {
	    		System.out.println("dance");
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	 
	    }
	    
	    @FXML
	    public void handleupdatePatientRecordupdateNurse() {
	    	String pidString = pidComboBox.getSelectionModel().getSelectedItem();
	    	int pid = 0;
	    	 String tempText = temp.getText();
	    	 String bloodPressureText = blood_p.getText();
	    	 String heartRateText = heart_b.getText();

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
            //jab patient combobox se pid select karo ge then the patient record will be added
            PatientRecord p=new PatientRecord();
            p.updatePatientRecord(pid,tempText,bloodPressureText,heartRateText);
            System.out.println("done");
            
	    }
	    
	    @FXML
	    public void setMedicationDetails(List<String> medicationDetails) {
	        if (medicationDetails != null && !medicationDetails.isEmpty()) {
	            medicationListView.getItems().addAll(medicationDetails);
	        } else {
	            medicationListView.getItems().add("No medication details available.");
	        }
	    }
	    
	    @FXML 
	    public void handleNurseTrackMedicationView(MouseEvent event) {
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
            Medication medicationService = new Medication();
            List<String> medicationDetails = medicationService.GetMedicationDetails(pid);
            System.out.println("in the controller");
            for (String detail : medicationDetails) {
                System.out.printf(detail);
            }

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

	            System.out.println("Track Medication Update button clicked");
	            System.out.println("Selected PID: " + pid);
	            System.out.println("Selected Medication: " + medicationName);
	            System.out.println("Dosage: " + dosage);

	            Medication medicationService = new Medication();
	            medicationService.EnterMedicationDetails(pid, medicationName, dosage);

	            System.out.println("Medication details successfully entered.");
	        } catch (Exception e) {
	            System.err.println("An unexpected error occurred:");
	            e.printStackTrace();
	        }
	    }

	    
	    @FXML
	    public void populateMediComboBox() {
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
	            System.out.println("No medications found for this patient");
	        }
	    }
	  
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
			showAlert("Success","Success", "The Nurse has been Added");
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
	    private void populatePidComboBox() {
	        Patient patientService = new Patient();
	        ObservableList<String> pidList = patientService.getPatientIds();

	        if (pidComboBox != null) {
	            pidComboBox.setItems(pidList);

	            pidComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	                populateMediComboBox(); 
	            });
	        }
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
AtomicReference<String> selectedEndTime = new AtomicReference<>(null);
AtomicReference<String> selectedStartTime = new AtomicReference<>(null);


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

    // Call other initialization methods if needed
    populatePidComboBox();
    populateDidComboBox();
    populateNidComboBox();
    populateRidComboBox();
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
	 
}