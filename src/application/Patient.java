package application;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Patient {
	
	private int patientId;
	private String patientName;
	private String username;
	private String password;
	private String gender;
	private Date dob;
	private String contact;
	private boolean dischargeStatus;
	private PatientRecord record;
	private DBHandler dbhandler;
	
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
	
	// --------------------------------------------------------- ANOOSHAAAAA -------------------------------------------------------------------------//
	// ------------------------------------------ Get patient Id on the basis of username ---------------------------------------------------//
	
	public int getPatientId(String username) {
		int id = dbhandler.getPatientId(username);
		return id;
	}
	
	// ---------------------------------------------------------- Use Cases -------------------------------------------------------------------//
	
	// -------------------------------------------------- Use Case: Register Patient --------------------------------------------------------//
	
	public void registerPatient(String name, String username, String password, String gender, String dob, String contact) {
		int id = dbhandler.registerPatient(name, username, password, gender, dob, contact);
		record.insertDefaultRecord(id);
	}
	
	// -------------------------------------------------- Use Case: View Patient Record --------------------------------------------------------//
	
	public PatientRecord viewRecord(int patId){
		record = record.getRecord(patId);
		return record;
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
}