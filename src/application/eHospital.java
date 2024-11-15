package application;

//import java.awt.TextField;
import java.io.IOException;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class eHospital {

	//functionalities for the homepage 
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReceptionistLogin.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Receptionist");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) ReceptionistButton.getScene().getWindow();
            currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDoctorButtonClick()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorLogin.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Doctor");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) DoctorButton.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void handlePatientButtonClick()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientSignUp.fxml"));
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
    public void handleAdminButtonClick()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Admin");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) AdminButton.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void handleNurseButtonClick()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NurseLogin.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Nurse");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) NurseButton.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    //functionalities for register page of patient 
    @FXML 
    private Button RegisterPatient;
    
    @FXML 
    private Button LoginPagePatient;
    
    @FXML
    private Button AdminSignIn;
    
    @FXML
    private Button DoctorSignIn;
    
    @FXML 
    private Button NurseSignIn;
    
    @FXML 
    private Button ReceptionistSignIn;

    @FXML 
    private Button PatientSignIn;

    public void handleRegisterButtonClick()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Patinet");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) RegisterPatient.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }

    public void handleSignInClick()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientLogin.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("PatinetLogin");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) LoginPagePatient.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    //functionalities of login page 
    public void handleLoginButtonDoctor()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Doctor.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Doctor");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) DoctorSignIn.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    public void handleLoginButtonNurse()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Nurse.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Nurse");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) NurseSignIn.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    public void handleLoginButtonAdmin()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Admin");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) AdminSignIn.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    public void handleLoginButtonReceptionist()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Receptionist.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Receptionist");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) ReceptionistSignIn.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    public void handleLoginButtonPatinet()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("Patient");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) PatientSignIn.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    private Button scheduleAppointment;
    public void handlePatientScheduleAppointment()
    {
    	try {
    		// Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScheduleAppointment.fxml"));
            Parent newFormRoot = loader.load();

            // Create a new scene and stage for the new form
            Scene newFormScene = new Scene(newFormRoot);
            Stage newFormStage = new Stage();
            newFormStage.setScene(newFormScene);
            newFormStage.setTitle("ScheduleAppointment");

            // Show the new form
            newFormStage.show();

            // Close the current form
            Stage currentStage = (Stage) scheduleAppointment.getScene().getWindow();
            currentStage.close();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }

    
    
    //saras
    @FXML
    private TextField Name;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private CheckBox genderM;
    @FXML
    private CheckBox genderF;
    @FXML
    private CheckBox genderO;
    
    @FXML
    private TextField DOB;
    @FXML
    private TextField contact;
    
    public void registerPatient()
    {
    	String name = Name.getText();
        String user = username.getText();
        String pass = password.getText();
        String gender;
        if (genderM.isSelected()) {
            gender = "Male";
        } else if (genderF.isSelected()) {
            gender = "Female";
        } else {
            gender = "Other"; // or handle this case if neither is selected
        }
        String dob = DOB.getText();
        String cont = contact.getText();

        // Process or display the input values as needed
        System.out.println("Name: " + name);
        System.out.println("Username: " + user);
        System.out.println("Password: " + pass);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + dob);
        System.out.println("Contact: " + cont);
        
        if( name.isEmpty() || user.isEmpty() ||  pass.isEmpty() || gender.isEmpty() || dob.isEmpty() || cont.isEmpty())
        {
        	//yahan par input Validation bhi hogi
        	//input validation par passe howe hai
        	//form add hoga if any field left empty
        }
        
        /*
        Patient p=new Patient();
        p.signUpPatient(name,user,pass,gender,dob,cont);
        */
    }
    
}