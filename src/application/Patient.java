package application;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
	
	Patient() {
		dbhandler = new DBHandler();
		record = new PatientRecord();
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
	
	// Use Cases
	// Use Case: Register Patient
	public void registerPatient(String name, String username, String password, String gender, String dob, String contact) {
		int id = dbhandler.registerPatient(name, username, password, gender, dob, contact);
		record.insertDefaultRecord(id);
	}
	
	public void viewRecord(int patId){
		record = record.getRecord(patId);
	}
}