package application;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Patient {
	
	
	private DBHandler dbhandler;
	private int patientId;
	private String patientName;
	private String username;
	private String gender;
	private Date dob;
	private String contact;
	private boolean dischargeStatus;
	private PatientRecord record;
	private static Patient instance; 

	
	public void init(int id, String name, String uname,String gen,Date DOB,String con, boolean st) {
		this.patientId=id;
		this.patientName=name;
		this.username=uname;
		this.gender= gen;
		this.dob = DOB;
		
		this.contact=con;
		this.dischargeStatus=st;
		
    }
	public static Patient getInstance() 
	{
		if (instance == null) {
            instance = new Patient();
        }
        return instance;
	}
	
	
	
	Patient()
	{
		dbhandler=new DBHandler();
		record= new PatientRecord();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return patientId;
	}
	public void setId(int id) {
		this.patientId = id;
	}
	public boolean isDischargeStatus() {
		return dischargeStatus;
	}
	public void setDischargeStatus(boolean dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}
	public PatientRecord getRecord() {
		return record;
	}
	public void setRecord(PatientRecord record) {
		this.record = record;
	}
	
	public void addMedications(String medName,int dosage,int pid)
	{
		
		record.addPrescribeMedication(medName,dosage,pid);
	}
	public ObservableList<Medication> showExistingMedication(int pid)
	{
		ObservableList<Medication> list= record.showExistingMedication(pid);
		return list;
	}
	@Override
    public String toString() {
        return patientName + " (" + patientId + ")";
    }

    public ObservableList<String> getPatientIds() {
    	DBHandler db=new DBHandler();
		
        ObservableList<String> pidList = db.getPatientIds();

        return pidList;
    }
    
    public ObservableList<String> findPatientRecord(int pid) {
    	PatientRecord p=new PatientRecord();
    	ObservableList<String> pidList = p.getPatientIds(pid);
		return pidList;

    }
	
	public void dischargePatient(String inst,LocalDate date,int pid)
	{
		record.dischargePatient(inst,date,pid);
	}
	
	public boolean LoginPatient(String username, String password)
	{
		boolean check =dbhandler.LoginPatient(username,password);
		return check;
	}
	
	public String loadPatientName(String username)
	{
		String name= dbhandler.loadPatientName(username);
		return name;
	}
	public int loadPatientId(String username)
	{
		int id=dbhandler.loadPatientId(username);
		return id;
	}
	public String loadPatientGender(String username) {
		String data= dbhandler.loadPatientGender(username);
		return data;
	}
	public String loadPatientDOB(String username) {
		String data= dbhandler.loadPatientDOB(username);
		return data;
	}
	public String loadPatientDischargeStatus(String username) {
		String data = dbhandler.loadPatientDischargeStatus(username) ? "Discharged" : "Not Discharged";
		return data;
	}
	public String loadPatientContact(String username) {
		String data= dbhandler.loadPatientContact(username);
		return data;
	}
	public DBHandler getDbhandler() {
		return dbhandler;
	}
	public void setDbhandler(DBHandler dbhandler) {
		this.dbhandler = dbhandler;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public static void setInstance(Patient instance) {
		Patient.instance = instance;
	}
	
	
	
	
	
	
	
	
//	public void signUpPatient(String name, String user, String pass, String gen, String dob, String cont) 
//	{
//	    System.out.println("Checking username availability.");
//
//	    try (Connection con = DriverManager.getConnection(url)) {
//	        // Check if the username already exists
//	        String checkUserSql = "SELECT COUNT(*) FROM Patient WHERE username = ?";
//	        PreparedStatement checkUserStmt = con.prepareStatement(checkUserSql);
//	        checkUserStmt.setString(1, user);
//	        ResultSet rs = checkUserStmt.executeQuery();
//
//	        if (rs.next() && rs.getInt(1) > 0) {
//	            System.out.println("The username '" + user + "' is already taken. Please choose another.");
//	        } else {
//	            // Parse the date if needed
//	            Date sqlDob = null;
//	            try {
//	                LocalDate parsedDob = LocalDate.parse(dob);  // Assuming dob is in "YYYY-MM-DD" format
//	                sqlDob = Date.valueOf(parsedDob);
//	            } catch (DateTimeParseException e) {
//	                System.out.println("Invalid date format. Use 'YYYY-MM-DD'.");
//	                return;
//	            }
//
//	            // Username is unique, insert the new patient
//	            String insertSql = "INSERT INTO Patient (name, username, password, gender, dob, contact) VALUES (?, ?, ?, ?, ?, ?)";
//	            PreparedStatement insertStmt = con.prepareStatement(insertSql);
//	            insertStmt.setString(1, name);
//	            insertStmt.setString(2, user);
//	            insertStmt.setString(3, pass);
//	            insertStmt.setString(4, gen);
//	            insertStmt.setDate(5, sqlDob);
//	            insertStmt.setString(6, cont);
//
//	            int rowsAffected = insertStmt.executeUpdate();
//	            if (rowsAffected > 0) {
//	                System.out.println("Patient data saved to SQL database.");
//	            }
//
//	            insertStmt.close();
//	        }
//
//	        rs.close();
//	        checkUserStmt.close();
//
//	    } catch (SQLException e) {
//	        System.out.println("Error saving data to SQL database.");
//	        e.printStackTrace();
//	    }
//	}

}