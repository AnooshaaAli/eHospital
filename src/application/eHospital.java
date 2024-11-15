package application;

import java.awt.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

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
	private Button ViewPrescription;
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
	
	 @FXML
	 private ComboBox<String> startTimeComboBox;
	 @FXML
	 private ComboBox<String> endTimeComboBox;
	 @FXML
	 private ComboBox<String> ActionComboBox;

	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 // Populate the ComboBoxes with time options and action options

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
	
}
