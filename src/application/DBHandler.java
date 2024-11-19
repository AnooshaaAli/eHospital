package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
	public ObservableList<String> findPatientRecord(int pid) {
	        int rpid = -1; // Default value if no record is found
	        try (Connection conn = DriverManager.getConnection(url)) {

	            String query = "SELECT recordId FROM patientRecord WHERE pid = ?"; // Query to get recordId
	            try (PreparedStatement stmt = conn.prepareStatement(query)) {
	                stmt.setInt(1, pid); // Set the parameter for the query

	                ResultSet rs = stmt.executeQuery();
	                if (rs.next()) {
	                    rpid = rs.getInt("recordId"); // Retrieve the recordId
	                }
	            }
	            if (rpid == -1) {
	                return null; // Return null if no record found
	            } else {
	                Medication m = new Medication();
	                return m.getMedications(rpid); // Fetch medications for the recordId
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return null; // Return null if an error occurs
	    }
	 
	public ObservableList<String> getMedications() {
        ObservableList<String> medicationList = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(url)) {

            String query = "SELECT medicationname FROM medication "; // Adjust your query if needed
            Statement stmt = conn.createStatement(); // Using java.sql.Statement here
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String medication = rs.getString("medicationname");
                medicationList.add(medication);
            }

            // Print fetched medications for debugging
      //      System.out.println("Fetched Medications: " + medicationList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicationList;
    }
	
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
	
	public ObservableList<String> getPatientIds() {
		
        ObservableList<String> pidList = FXCollections.observableArrayList();

        // Connect to the database and fetch the PIDs
        try (Connection conn = DriverManager.getConnection(url)) {

            String query = "SELECT pid FROM patient"; 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String pid = rs.getString("pid");
                pidList.add(pid); // Add each PID to the ObservableList
            }

            // Print fetched PIDs for debugging
        //    System.out.println("Fetched PIDs: " + pidList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pidList;
    }
	
	public void EnterMedicationDetails(int pid, String medicationName, int dosage) {
	    
	    String upsertQuery = """
	        MERGE INTO medication AS target
	        USING (VALUES (?, ?, ?)) AS source (recordID, medicationName, dosage)
	        ON target.recordID = source.recordID
	        WHEN MATCHED THEN
	            UPDATE SET 
	                medicationName = source.medicationName,
	                dosage = source.dosage
	        WHEN NOT MATCHED THEN
	            INSERT (recordID, medicationName, dosage)
	            VALUES (source.recordID, source.medicationName, source.dosage);
	    """;

	    try (Connection conn = DriverManager.getConnection(url);
	         PreparedStatement pstmt = conn.prepareStatement(upsertQuery)) {

	        // Set query parameters
	        pstmt.setInt(1, pid);
	        pstmt.setString(2, medicationName);
	        pstmt.setInt(3, dosage);

	        // Execute the query
	        int rowsAffected = pstmt.executeUpdate();

	        // Print feedback for debugging
	        if (rowsAffected > 0) {
	            System.out.println("Medication details successfully inserted/updated for PID: " + pid);
	        } else {
	            System.out.println("No changes made for PID: " + pid);
	        }

	    } catch (SQLException e) {
	        // Handle SQL exceptions
	        System.err.println("Error inserting/updating medication details in the database:");
	        e.printStackTrace();
	    }
	}
	 
	 public List<String> FindMedicationDetails(int pid) {
		    
		    String selectQuery = "SELECT medicationName, dosage FROM medication WHERE recordID = ?";
		    List<String> medicationDetails = new ArrayList<>();

		    try (Connection conn = DriverManager.getConnection(url);
		         PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {

		        // Set query parameter
		        pstmt.setInt(1, pid);

		        // Execute the query
		        try (ResultSet rs = pstmt.executeQuery()) {
		            // Process results and add to the list
		            while (rs.next()) {
		                String medicationName = rs.getString("medicationName");
		                int dosage = rs.getInt("dosage");
		                medicationDetails.add("Medication Name: " + medicationName + ", Dosage: " + dosage);
		            }
		        }

		    } catch (SQLException e) {
		        // Handle SQL exceptions
		        System.err.println("Error retrieving medication details from the database:");
		        e.printStackTrace();
		    }

		    return medicationDetails;
	 }
	 
	 public void updatePatientRecord(int pid, String patientRecord, String bloodPressureText, String heartRateText) {
		   
		    String checkQuery = "SELECT COUNT(*) FROM patientrecord WHERE pID = ?";
		    String updateQuery = "UPDATE patientrecord SET temperature = ?, blood_Pressure = ?, heart_Rate = ? WHERE pID = ?";
		    String insertQuery = "INSERT INTO patientrecord (pID, temperature, blood_Pressure, heart_Rate) VALUES (?, ?, ?, ?)";

		    try (Connection conn = DriverManager.getConnection(url);
		         PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

		        // Check if the patient record exists
		        checkStmt.setInt(1, pid);
		        ResultSet rs = checkStmt.executeQuery();
		        rs.next();
		        int count = rs.getInt(1);

		        if (count > 0) {
		            // Record exists; update it
		            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
		                updateStmt.setString(1, patientRecord);
		                updateStmt.setString(2, bloodPressureText);
		                updateStmt.setString(3, heartRateText);
		                updateStmt.setInt(4, pid);

		                int rowsUpdated = updateStmt.executeUpdate();
		                if (rowsUpdated > 0) {
		                    System.out.println("Patient record updated successfully.");
		                } else {
		                    System.out.println("Failed to update the patient record.");
		                }
		            }
		        } else {
		            // Record does not exist; insert a new one
		            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
		                insertStmt.setInt(1, pid);
		                insertStmt.setString(2, patientRecord);
		                insertStmt.setString(3, bloodPressureText);
		                insertStmt.setString(4, heartRateText);

		                int rowsInserted = insertStmt.executeUpdate();
		                if (rowsInserted > 0) {
		                    System.out.println("New patient record inserted successfully.");
		                } else {
		                    System.out.println("Failed to insert the new patient record.");
		                }
		            }
		        }

		    } catch (SQLException e) {
		        System.err.println("Error occurred while updating/inserting the patient record:");
		        e.printStackTrace();
		    }
		}
	 //discharge patient
	 public void createDischargePatient(String inst,LocalDate date, int pid)
	 {
		 Connection connection = null;

		    try (Connection conn = connect(); 
		         Statement statement = conn.createStatement()) {

		        // Step 1: Retrieve the recordId corresponding to the given pid
		        String fetchRecordIdSql = "SELECT recordId FROM PatientRecord WHERE pid = " + pid;
		        ResultSet resultSet = statement.executeQuery(fetchRecordIdSql);

		        // Check if a record was found
		        if (resultSet.next()) {
		            int recordId = resultSet.getInt("recordId");

		            // Step 2: Insert into DischargeSummary using the fetched recordId
		            String insertSql = "INSERT INTO DischargeSummary (recordId, instructions, date) " +
		                               "VALUES (" + recordId + ", '" + inst + "', '" + date.toString() + "')";
		            statement.executeUpdate(insertSql);

		        }
		        else 
		            System.out.println("No record found for patient ID: " + pid);
		        
		    } 
		    catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("Error processing discharge summary.");
		    }
	 }
	 //login admin 
	 public boolean LoginAdmin(String username, String password) 
	 {
		  // String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		   String sql = "SELECT aid, username FROM admin WHERE username = ? AND password = ?";

		    try (Connection con = connect();
		         PreparedStatement stmt = con.prepareStatement(sql)) {

		        // Set the username and password parameters
		        stmt.setString(1, username);
		        stmt.setString(2, password);

		        // Execute the query
		        ResultSet rs = stmt.executeQuery();

		        if (rs.next())
		        {
		            String adminName = rs.getString("username");
		            int adminId = rs.getInt("aid");
		            System.out.println("Login successful for Admin: " + adminName + " (ID: " + adminId + ")");

		            return true; 
		        } 
		        else 
		            return false; 
		     
		    }
		    catch (SQLException e) {
		        System.out.println("Database error during admin lookup.");
		        e.printStackTrace();
		        return false; // Return false when there is a database error
		    }

	 }
	 //load admin details 
	 public String loadAdminName(String username) {
		    String adminName = null;
		    String sql = "SELECT name FROM ADMIN WHERE username = '" + username + "'";

		    try (Connection con = connect();
		         Statement stmt = con.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {

		        if (rs.next()) {
		            // Retrieve the name from the result set
		            adminName = rs.getString("name");
		        } else {
		            System.out.println("Admin not found for username: " + username);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving admin name.");
		        e.printStackTrace();
		    }
		    return adminName; // Return the retrieved name or null
		}
	 public int loadAdminId(String username) {
		    int adminID =-10;
		    String sql = "SELECT aid FROM ADMIN WHERE username = '" + username + "'";

		    try (Connection con = connect();
		         Statement stmt = con.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {

		        if (rs.next()) {
		            // Retrieve the name from the result set
		        	adminID = rs.getInt("aid");
		        } else {
		            System.out.println("Admin not found for username: " + username);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving admin id.");
		        e.printStackTrace();
		    }
		    return adminID; // Return the retrieved name or null
		}
	 //login doctor 
	 public boolean LoginDoctor(String username, String password)
	 {
		  // String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		   String sql = "SELECT empid, username FROM EMPLOYEE WHERE username = ? AND password = ?";

		    try (Connection con = connect();
		         PreparedStatement stmt = con.prepareStatement(sql)) {

		        // Set the username and password parameters
		        stmt.setString(1, username);
		        stmt.setString(2, password);

		        // Execute the query
		        ResultSet rs = stmt.executeQuery();

		        if (rs.next())
		        {
		            String docName = rs.getString("username");
		            int empId = rs.getInt("empid");
		            System.out.println("Login successful for Doctor: " + docName + " (ID: " + empId + ")");

		            return true; 
		        } 
		        else 
		            return false; 
		     
		    }
		    catch (SQLException e) {
		        System.out.println("Database error during admin lookup.");
		        e.printStackTrace();
		        return false; // Return false when there is a database error
		    }

	 }
	 //login patient 
	 public boolean LoginPatient(String username, String password)
	 {
		 String sql = "SELECT pid, username FROM PATIENT WHERE username = ? AND password = ?";

		    try (Connection con = connect();
		         PreparedStatement stmt = con.prepareStatement(sql)) {

		        // Set the username and password parameters
		        stmt.setString(1, username);
		        stmt.setString(2, password);

		        // Execute the query
		        ResultSet rs = stmt.executeQuery();

		        if (rs.next())
		        {
		            String patientName = rs.getString("username");
		            int pid = rs.getInt("pid");
		            System.out.println("Login successful for Patient: " + patientName + " (ID: " + pid + ")");

		            return true; 
		        } 
		        else 
		            return false; 
		     
		    }
		    catch (SQLException e) {
		        System.out.println("Database error during admin lookup.");
		        e.printStackTrace();
		        return false; // Return false when there is a database error
		    }

	 }
	 //patient details 
	 public String loadPatientName(String username)
	 {
		 String name="";
		 String sql = "SELECT name FROM PATIENT WHERE username = '" + username + "'";

		    try (Connection con = connect();
		         Statement stmt = con.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {

		        if (rs.next()) {
		            // Retrieve the name from the result set
		        	name = rs.getString("name");
		        } else {
		            System.out.println("Patient not found for username: " + username);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving patient name.");
		        e.printStackTrace();
		    }
		    return name;
		 
	 }
	 public int loadPatientId(String username)
	 {
		 int id=-20;
		 String sql = "SELECT pid FROM PATIENT WHERE username = '" + username + "'";

		    try (Connection con = connect();
		         Statement stmt = con.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {

		        if (rs.next()) {
		            // Retrieve the name from the result set
		        	id = rs.getInt("pid");
		        } else {
		            System.out.println("Patient not found for username: " + username);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving patient id.");
		        e.printStackTrace();
		    }
		 return id;
	 }
	 public String loadPatientGender(String username)
	 {
		 String data="";
		 String sql = "SELECT gender FROM PATIENT WHERE username = '" + username + "'";

		    try (Connection con = connect();
		         Statement stmt = con.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {

		        if (rs.next()) {
		            // Retrieve the name from the result set
		        	data = rs.getString("gender");
		        } else {
		            System.out.println("Patient not found for username: " + username);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving patient gender.");
		        e.printStackTrace();
		    }
		 return data;
	 }
	 public String loadPatientDOB(String username)
	 {
		 String data="";
		 String sql = "SELECT DOB FROM PATIENT WHERE username = '" + username + "'";

		    try (Connection con = connect();
		         Statement stmt = con.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {

		        if (rs.next()) {
		            // Retrieve the name from the result set
		        	data = rs.getString("DOB");
		        } else {
		            System.out.println("Patient not found for username: " + username);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving patient DOB.");
		        e.printStackTrace();
		    }
		 return data;
	 }
	 public String loadPatientContact (String username)
	 {
		 String data="";
		 String sql = "SELECT contact FROM PATIENT WHERE username = '" + username + "'";

		    try (Connection con = connect();
		         Statement stmt = con.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {

		        if (rs.next()) {
		            // Retrieve the name from the result set
		        	data = rs.getString("contact");
		        } else {
		            System.out.println("Patient not found for username: " + username);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving patient contact.");
		        e.printStackTrace();
		    }
		 return data;
	 }
	 public boolean loadPatientDischargeStatus(String username)
	 {
		 int data=0;
		 String sql = "SELECT isDischarge FROM PATIENT WHERE username = '" + username + "'";

		    try (Connection con = connect();
		         Statement stmt = con.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {

		        if (rs.next()) {
		            // Retrieve the name from the result set
		        	data = rs.getInt("isDischarge");
		        } else {
		            System.out.println("Patient not found for username: " + username);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving patient Discharge status.");
		        e.printStackTrace();
		    }
		    if(data==0)
		    	return false;
		    else if(data==1)
		    	return true;
		    return false;
	 }
	 
}
