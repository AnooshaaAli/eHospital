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
	private String gender;
	private Date dob;
	private String contact;
	private boolean dischargeStatus;
	private PatientRecord record;
	
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