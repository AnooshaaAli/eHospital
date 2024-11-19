package application;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBHandler {
	
   String url = "jdbc:sqlserver://10N5Q8AKAMRA\\SQLEXPRESS01;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
	// String url ="jdbc:sqlserver://FATIMA\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
	   
	private Connection connect() {
	    Connection conn = null;
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Load SQL Server JDBC driver
	        conn = DriverManager.getConnection(url);
	    } catch (ClassNotFoundException e) {
	        System.out.println("SQL Server JDBC Driver not found.");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Database connection failed.");
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	// ------------------------------------------- register a new patient ------------------------------------------------ //
	
	public int registerPatient(String name, String username, String password, String gender, String dob, String contact) {

	    // SQL Insert Query
	    String sql = "INSERT INTO patient (name, username, password, gender, dob, contact) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection connection = this.connect();
	    	 PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        // Setting values to the prepared statement
	        preparedStatement.setString(1, name);
	        preparedStatement.setString(2, username);
	        preparedStatement.setString(3, password);
	        preparedStatement.setString(4, gender);
	        preparedStatement.setString(5, dob);
	        preparedStatement.setString(6, contact);

	        // Execute the query
	        int rowsInserted = preparedStatement.executeUpdate();
	        if (rowsInserted > 0) {
	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    int autoGeneratedId = generatedKeys.getInt(1);
	                    return autoGeneratedId;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return 0;
	}
	
	public void insertPatientRecord(int pid, String temperature, String bloodPressure, String heartRate) {
        // SQL Insert Query
        String sql = "INSERT INTO PATIENTRECORD (pid, temperature, blood_pressure, heart_rate) VALUES (?, ?, ?, ?)";

        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the parameters for the query
            preparedStatement.setInt(1, pid);
            preparedStatement.setString(2, temperature);
            preparedStatement.setString(3, bloodPressure);
            preparedStatement.setString(4, heartRate);

            // Execute the insert query
            int rowsInserted = preparedStatement.executeUpdate();

            // Retrieve the auto-generated recordID
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int recordID = generatedKeys.getInt(1); // Get the auto-incremented ID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	// ---------------------------------------- View Existing Medications ------------------------------------------------ //
	
	public ObservableList<String> getMedications(int rpid) {
        ObservableList<String> medicationList = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(url)) {

            String query = "SELECT medicationname FROM medication where recordId= "+rpid; // Adjust your query if needed
            Statement stmt = conn.createStatement(); // Using java.sql.Statement here
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String medication = rs.getString("medicationname");
                medicationList.add(medication);
            }

            // Print fetched medications for debugging
           // System.out.println("Fetched Medications: " + medicationList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicationList;
    }
	
}
