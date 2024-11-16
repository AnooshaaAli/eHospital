package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBHandler {
	
   //String url = "jdbc:sqlserver://10N5Q8AKAMRA\\SQLEXPRESS01;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
   String url ="jdbc:sqlserver://FATIMA\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
	
   DBHandler()
   {}
	private Connection connect() {
	    Connection conn = null;
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Load SQL Server JDBC driver
	        conn = DriverManager.getConnection(url);
	        System.out.println("Connected to the database successfully!");
	    } catch (ClassNotFoundException e) {
	        System.out.println("SQL Server JDBC Driver not found.");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Database connection failed.");
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	//prescribe medications
	public void addPrescribeMedication(String name, int dosage, int pid) {
	    String query1 = "SELECT recordID FROM PATIENTRECORD WHERE pid = " + pid;
	    String query2 = "INSERT INTO MEDICATION (recordID, MedicationName, Dosage) " +
	                    "VALUES (%d, '%s', %d)";
	    
	    try (Connection conn = connect(); // Replace with your connection method
	         Statement stmt = conn.createStatement()) {

	        // Execute the first query to retrieve the recordID for the given pid
	        ResultSet rs = stmt.executeQuery(query1);

	        if (rs.next()) {
	            int recordID = rs.getInt("recordID");

	            // Construct the second query by replacing placeholders
	            String finalQuery = String.format(query2, recordID, name, dosage);

	            // Execute the second query to insert the medication
	            int rowsAffected = stmt.executeUpdate(finalQuery);

	            if (rowsAffected > 0) {
	                System.out.println("Medication successfully prescribed.");
	            } else {
	                System.out.println("Failed to prescribe medication.");
	            }
	        } else {
	            System.out.println("No record found for the given patient ID.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error while prescribing medication.");
	    }
	}
	public ObservableList<Medication> showExistingMedication(int pid)
	{
		ObservableList<Medication> medications = FXCollections.observableArrayList();
		String recordIdQuery = "SELECT recordID FROM PATIENTRECORD WHERE pid = " + pid;
	    
	    try (Connection conn = connect(); 
	         Statement stmt = conn.createStatement()) {
	        
	        // First, get the recordID
	        ResultSet rsRecordId = stmt.executeQuery(recordIdQuery);

	        if (rsRecordId.next()) {
	            int recordID = rsRecordId.getInt("recordID");

	            // Now, query the MEDICATION table using the recordID to get medications
	            String medicationQuery = "SELECT * FROM MEDICATION WHERE recordID = " + recordID;
	            ResultSet rsMedication = stmt.executeQuery(medicationQuery);

	            // Fetch medication details and add to ObservableList
	            while (rsMedication.next()) {
	                int medicationId = rsMedication.getInt("mid");
	                String medicationName = rsMedication.getString("MedicationName");
	                int dosage = rsMedication.getInt("Dosage");

	                Medication medication = new Medication(medicationId, medicationName, dosage);
	                medications.add(medication);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		return medications;
	}

}
