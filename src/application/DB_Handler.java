package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DB_Handler {

    private Connection conn;
	DB_Handler()
	{
		
	}
	
	 public ObservableList<String> findPatientRecord(int pid) {
	        int rpid = -1; // Default value if no record is found
	        try (Connection conn = DriverManager.getConnection(
	                "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false")) {

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

        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false")) {

            String query = "SELECT medicationname FROM medication "; // Adjust your query if needed
            Statement stmt = conn.createStatement(); // Using java.sql.Statement here
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String medication = rs.getString("medicationname");
                medicationList.add(medication);
            }

            // Print fetched medications for debugging
            System.out.println("Fetched Medications: " + medicationList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicationList;
    }
	
	public ObservableList<String> getMedications(int rpid) {
        ObservableList<String> medicationList = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false")) {

            String query = "SELECT medicationname FROM medication where recordId= "+rpid; // Adjust your query if needed
            Statement stmt = conn.createStatement(); // Using java.sql.Statement here
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String medication = rs.getString("medicationname");
                medicationList.add(medication);
            }

            // Print fetched medications for debugging
            System.out.println("Fetched Medications: " + medicationList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicationList;
    }
	public ObservableList<String> getDoctorIds()
	{
		  ObservableList<String> didlist = FXCollections.observableArrayList();

	        try (Connection conn = DriverManager.getConnection(
	                "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false")) {

	        	String query = "SELECT did FROM Doctor WHERE active != 0";

	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query);

	            while (rs.next()) {
	                String did = rs.getString("did");
	                didlist.add(did); 
	            }

	            System.out.println("Fetched DIDs: " + didlist);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return didlist;
	}
	public ObservableList<String> getPatientIds() {
		
        ObservableList<String> pidList = FXCollections.observableArrayList();

        // Connect to the database and fetch the PIDs
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false")) {

            String query = "SELECT pid FROM patient"; 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String pid = rs.getString("pid");
                pidList.add(pid); // Add each PID to the ObservableList
            }

            // Print fetched PIDs for debugging
            System.out.println("Fetched PIDs: " + pidList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pidList;
    }
	
	 public void EnterMedicationDetails(int pid, String medicationName, int dosage) {
	        // Database connection details
	        String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";

	        String insertQuery = "INSERT INTO medication (recordID, medicationName, dosage) VALUES (?, ?, ?)";

	        try (Connection conn = DriverManager.getConnection(url);
	             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

	            // Set query parameters
	            pstmt.setInt(1, pid);
	            pstmt.setString(2, medicationName);
	            pstmt.setInt(3, dosage);

	            // Execute the query
	            int rowsAffected = pstmt.executeUpdate();

	            // Print feedback for debugging
	            if (rowsAffected > 0) {
	                System.out.println("Medication details successfully entered for PID: " + pid);
	            } else {
	                System.out.println("Failed to insert medication details for PID: " + pid);
	            }

	        } catch (SQLException e) {
	            // Handle SQL exceptions
	            System.err.println("Error inserting medication details into the database:");
	            e.printStackTrace();
	        }
	    }
	 
	 public List<String> FindMedicationDetails(int pid) {
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
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
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";

		    // SQL queries for checking existence, updating, and inserting
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

	 
	 
	 //ayaha se new
	 public boolean loginAdmin(String username, String password) {
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		    String sql = "SELECT aid, username FROM admin WHERE username = ? AND password = ?";

		    try (Connection con = DriverManager.getConnection(url);
		         PreparedStatement stmt = con.prepareStatement(sql)) {

		        // Set the username and password parameters
		        stmt.setString(1, username);
		        stmt.setString(2, password);

		        // Execute the query
		        ResultSet rs = stmt.executeQuery();

		        if (rs.next()) {
		            // If admin exists, retrieve and print admin details (optional)
		            String adminName = rs.getString("username");
		            int adminId = rs.getInt("aid");
		            System.out.println("Login successful for Admin: " + adminName + " (ID: " + adminId + ")");

		            return true; 
		        } else {
		            return false; 
		        }
		    } catch (SQLException e) {
		        System.out.println("Database error during admin lookup.");
		        e.printStackTrace();
		        return false; // Return false in case of a database error
		    }
		}
	  public void initializeConnection() {
	        try {
	            // Replace with your actual DB credentials and URL
	            conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false");
	            System.out.println("Database connection established!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.err.println("Error establishing database connection!");
	        }
	    }

	    public Connection getConnection() {
	        if (conn == null) {
	            initializeConnection();
	        }
	        return conn;
	    }
	    
	 
	   public int AddEmployee(String name, String username, String password, String gender, int experience, String contact, int workingHours) {
		   Connection conn=getConnection();
		   String insertQuery = "INSERT INTO EMPLOYEE (name, username, password, gender, experience, contact, working_hours) " +
	                             "VALUES (?, ?, ?, ?, ?, ?, ?)";
	        int generatedEmpId = -1;

	        try (PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
	            // Set parameters
	            stmt.setString(1, name);
	            stmt.setString(2, username);
	            stmt.setString(3, password);
	            stmt.setString(4, gender);
	            stmt.setInt(5, experience);
	            stmt.setString(6, contact);
	            stmt.setInt(7, workingHours);

	            // Execute the insert
	            stmt.executeUpdate();

	            // Retrieve the generated keys
	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    generatedEmpId = generatedKeys.getInt(1); // The first column is the generated key
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return generatedEmpId;
	    }
	   public int AddDoctor(int empid, boolean isMonday, boolean isTuesday, boolean isWednesday, boolean isThursday, boolean isFriday, boolean isSaturday, int noOfApp) {
		    int doctorID = -1; // Default value in case insertion fails
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		    String insertDoctorQuery = "INSERT INTO DOCTOR (empid, monday, tuesday, wednesday, thursday, friday, saturday, noOfapp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		    
		    try (Connection con = DriverManager.getConnection(url);
		         PreparedStatement pst = con.prepareStatement(insertDoctorQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
		         
		        // Set the parameters for the query
		        pst.setInt(1, empid);
		        pst.setBoolean(2, isMonday);
		        pst.setBoolean(3, isTuesday);
		        pst.setBoolean(4, isWednesday);
		        pst.setBoolean(5, isThursday);
		        pst.setBoolean(6, isFriday);
		        pst.setBoolean(7, isSaturday);
		        pst.setInt(8, noOfApp);

		        // Execute the query
		        int rowsAffected = pst.executeUpdate();

		        // If rows are affected, retrieve the generated doctorID (Primary Key)
		        if (rowsAffected > 0) {
		            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
		                if (generatedKeys.next()) {
		                    doctorID = generatedKeys.getInt(1); // Retrieve the auto-generated doctorID
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return doctorID; 
		}

	   public int AddTimeSlot(String starttime, String endtime) {
		    int timeSlotID = -1; // Default value in case insertion fails
		    String insertTimeSlotQuery = "INSERT INTO timeslot (starttime, endtime) VALUES (?, ?)";
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";

		    try (Connection con = DriverManager.getConnection(url);
		         PreparedStatement pst = con.prepareStatement(insertTimeSlotQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
		        
		        // Set the parameters for the query
		        pst.setString(1, starttime);
		        pst.setString(2, endtime);

		        // Execute the query
		        int rowsAffected = pst.executeUpdate();

		        // If rows are affected, retrieve the generated timeSlotID (Primary Key)
		        if (rowsAffected > 0) {
		            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
		                if (generatedKeys.next()) {
		                    timeSlotID = generatedKeys.getInt(1); // Retrieve the auto-generated timeSlotID (tid)
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return timeSlotID; // Return the generated timeSlotID (tid)
		}


	   public boolean AddDoctorTimeSlot(int did, String starttime, String endtime) {
		    // First, make sure the timeslot exists and get the tid
		    int tid = AddTimeSlot(starttime, endtime);
		    
		    if (tid != -1) { // If tid is valid, insert into DoctorTimeslot
		        String insertDoctorTimeSlotQuery = "INSERT INTO DoctorTimeslot (did, tid) VALUES (?, ?)";
		        String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		        
		        try (Connection con = DriverManager.getConnection(url);
		             PreparedStatement pst = con.prepareStatement(insertDoctorTimeSlotQuery)) {

		            // Set the parameters for the query
		            pst.setInt(1, did);
		            pst.setInt(2, tid);

		            // Execute the query
		            int rowsAffected = pst.executeUpdate();
		            
		            if (rowsAffected > 0) {
		                System.out.println("Time slot successfully assigned to Doctor ID: " + did);
		                return true;
		            } else {
		                System.out.println("Failed to assign time slot to Doctor.");
		                return false;
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		            return false;
		        }
		    } else {
		        System.out.println("Invalid Time Slot, please try again.");
		        return false;
		    }
		}

	   public void deleteDoctor(int did) {
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		    String updateQuery = "UPDATE DOCTOR SET active = 0 WHERE did = ?";

		    try (Connection con = DriverManager.getConnection(url);
		         PreparedStatement pst = con.prepareStatement(updateQuery)) {
		         
		        // Set the `did` parameter
		        pst.setInt(1, did);

		        // Execute the update query
		        int rowsAffected = pst.executeUpdate();

		        if (rowsAffected > 0) {
		            System.out.println("Doctor with ID " + did + " marked as inactive.");
		        } else {
		            System.out.println("No doctor found with ID " + did + ".");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	   
	   public int GetempidFromDid(int did) {
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    int empid = -1;  // Default value if no result found
		    
		    try {
		        // Initialize the database connection
		        conn = DriverManager.getConnection(url);

		        // Prepare the SQL query
		        String query = "SELECT empid FROM DOCTOR WHERE did = ?";
		        stmt = conn.prepareStatement(query);
		        stmt.setInt(1, did);
		        
		        // Execute the query
		        rs = stmt.executeQuery();
		        
		        // Check if a result was returned
		        if (rs.next()) {
		            empid = rs.getInt("empid");
		        } else {
		            System.out.println("No doctor found with the given ID.");
		        }

		    } catch (SQLException e) {
		        // Handle SQL exception
		        System.err.println("Error fetching empid from did: " + e.getMessage());
		    } finally {
		        // Close the resources
		        try {
		            if (rs != null) rs.close();
		            if (stmt != null) stmt.close();
		            if (conn != null && !conn.isClosed()) conn.close();
		        } catch (SQLException e) {
		            System.err.println("Error closing resources: " + e.getMessage());
		        }
		    }

		    return empid;
		}

	   public void updateNameDoctor(int empid, String name) { 
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		    
		    // Query to update the doctor's name by using empid
		    String query = "UPDATE EMPLOYEE SET name = ? WHERE empid = ?";
		    
		    // Try-with-resources to ensure connection and statement are closed after execution
		    try (Connection con = DriverManager.getConnection(url);
		         PreparedStatement pst = con.prepareStatement(query)) {
		         
		        // Set the parameters for the query
		        pst.setString(1, name);
		        pst.setInt(2, empid);
		        
		        // Execute the update query
		        int rowsUpdated = pst.executeUpdate();
		        
		        // Check if the update was successful
		        if (rowsUpdated > 0) {
		            System.out.println("Doctor's name updated successfully.");
		        } else {
		            System.out.println("No doctor found with the given empid.");
		        }
		    } catch (SQLException e) {
		        System.err.println("Error updating doctor's name: " + e.getMessage());
		    }
		}
	   
	   public void updateUserNameDoctor(int empid, String username) { 
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		    
		    // Query to update the username for the given empid in the EMPLOYEE table
		    String query = "UPDATE EMPLOYEE SET username = ? WHERE empid = ?";
		    
		    // Try-with-resources to ensure connection and statement are closed after execution
		    try (Connection con = DriverManager.getConnection(url);
		         PreparedStatement pst = con.prepareStatement(query)) {
		         
		        // Set the parameters for the query
		        pst.setString(1, username);
		        pst.setInt(2, empid);
		        
		        // Execute the update query
		        int rowsUpdated = pst.executeUpdate();
		        
		        // Check if the update was successful
		        if (rowsUpdated > 0) {
		            System.out.println("Doctor's username updated successfully.");
		        } else {
		            System.out.println("No doctor found with the given empid.");
		        }
		    } catch (SQLException e) {
		        System.err.println("Error updating doctor's username: " + e.getMessage());
		    }
		}

	   public void updatePasswordDoctor(int empid, String password) {
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		    
		    // Query to update the password for the given empid
		    String query = "UPDATE EMPLOYEE SET password = ? WHERE empid = ?";
		    
		    try (Connection conn = DriverManager.getConnection(url);
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		         
		        // Set the parameters for the query
		        stmt.setString(1, password);
		        stmt.setInt(2, empid);
		        
		        // Execute the update query
		        int rowsUpdated = stmt.executeUpdate();
		        
		        // Check if the update was successful
		        if (rowsUpdated > 0) {
		            System.out.println("Password updated successfully.");
		        } else {
		            System.out.println("No doctor found with the given empid.");
		        }

		    } catch (SQLException e) {
		        // Handle SQL exception
		        System.err.println("Error updating password: " + e.getMessage());
		        e.printStackTrace();
		    }
		}

	   public void updateGenderDoctor(int empid, String gender) { 
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		    
		    // Query to update the gender of the employee using empid
		    String query = "UPDATE EMPLOYEE SET gender = ? WHERE empid = ?";
		    
		    // Try-with-resources to ensure connection and statement are closed after execution
		    try (Connection con = DriverManager.getConnection(url);
		         PreparedStatement pst = con.prepareStatement(query)) {
		         
		        // Set the parameters for the query
		        pst.setString(1, gender);
		        pst.setInt(2, empid);
		        
		        // Execute the update query
		        int rowsUpdated = pst.executeUpdate();
		        
		        // Check if the update was successful
		        if (rowsUpdated > 0) {
		            System.out.println("Doctor's gender updated successfully.");
		        } else {
		            System.out.println("No doctor found with the given empid.");
		        }
		    } catch (SQLException e) {
		        System.err.println("Error updating doctor's gender: " + e.getMessage());
		    }
		}

	   public void updateExperienceDoctor(int empid, int experienceInt) { 
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		    
		    // Query to update the experience for the given empid
		    String query = "UPDATE EMPLOYEE SET experience = ? WHERE empid = ?";
		    
		    try (Connection conn = DriverManager.getConnection(url);
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		         
		        // Set the parameters for the query
		        stmt.setInt(1, experienceInt); // Set the experience value
		        stmt.setInt(2, empid);         // Set the employee ID
		        
		        // Execute the update query
		        int rowsUpdated = stmt.executeUpdate();
		        
		        // Check if the update was successful
		        if (rowsUpdated > 0) {
		            System.out.println("Doctor's experience updated successfully.");
		        } else {
		            System.out.println("No doctor found with the given empid.");
		        }

		    } catch (SQLException e) {
		        // Handle SQL exception
		        System.err.println("Error updating doctor's experience: " + e.getMessage());
		        e.printStackTrace();
		    }
		}

	   public void updateContactDoctor(int empid, String contact) { 
		    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		    
		    // Query to update the contact for the given empid
		    String query = "UPDATE EMPLOYEE SET contact = ? WHERE empid = ?";
		    
		    try (Connection conn = DriverManager.getConnection(url);
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		         
		        // Set the parameters for the query
		        stmt.setString(1, contact); // Set the contact value
		        stmt.setInt(2, empid);      // Set the employee ID
		        
		        // Execute the update query
		        int rowsUpdated = stmt.executeUpdate();
		        
		        // Check if the update was successful
		        if (rowsUpdated > 0) {
		            System.out.println("Doctor's contact updated successfully.");
		        } else {
		            System.out.println("No doctor found with the given empid.");
		        }

		    } catch (SQLException e) {
		        // Handle SQL exception
		        System.err.println("Error updating doctor's contact: " + e.getMessage());
		        e.printStackTrace();
		    }
		}
	   
	   public void updateAvailableDaysDoctor(int did, boolean isMonday, boolean isTuesday, boolean isWednesday,
	            boolean isThursday, boolean isFriday, boolean isSaturday) {
	    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
	    
	    // Query to update available days for the doctor based on empid
	    String query = "UPDATE DOCTOR SET monday = ?, tuesday = ?, wednesday = ?, thursday = ?, friday = ?, saturday = ? WHERE did = ?";
	    
	    try (Connection conn = DriverManager.getConnection(url);
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	         
	        // Set the availability values (1 for true, 0 for false)
	        stmt.setInt(1, isMonday ? 1 : 0);
	        stmt.setInt(2, isTuesday ? 1 : 0);
	        stmt.setInt(3, isWednesday ? 1 : 0);
	        stmt.setInt(4, isThursday ? 1 : 0);
	        stmt.setInt(5, isFriday ? 1 : 0);
	        stmt.setInt(6, isSaturday ? 1 : 0);
	        stmt.setInt(7, did); // Use empid to identify the doctor
	        
	        // Execute the update query
	        int rowsUpdated = stmt.executeUpdate();
	        
	        // Check if the update was successful
	        if (rowsUpdated > 0) {
	            System.out.println("Doctor's available days updated successfully.");
	        } else {
	            System.out.println("No doctor found with the given empid.");
	        }

	    } catch (SQLException e) {
	        // Handle SQL exception
	        System.err.println("Error updating doctor's available days: " + e.getMessage());
	        e.printStackTrace();
	    }
	   }
	   
		   public void updateWorkingHoursDoctor(int did, int empid, String selectedStartTime, String selectedEndTime, int workingHours) {
			    String insertTimeslotQuery = "INSERT INTO timeslot (starttime, endtime) OUTPUT INSERTED.tid VALUES (?, ?)";
			    String insertDoctorTimeslotQuery = "INSERT INTO DoctorTimeslot (did, tid) VALUES (?, ?)";
			    String updateWorkingHoursQuery = "UPDATE EMPLOYEE SET working_hours = ? WHERE empid = ?";

			    try (Connection conn = DriverManager.getConnection(
			            "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false");
			         PreparedStatement timeslotStmt = conn.prepareStatement(insertTimeslotQuery);
			         PreparedStatement doctorTimeslotStmt = conn.prepareStatement(insertDoctorTimeslotQuery);
			         PreparedStatement updateWorkingHoursStmt = conn.prepareStatement(updateWorkingHoursQuery)) {

			        // Step 1: Insert the start and end times into the timeslot table
			        timeslotStmt.setString(1, selectedStartTime);
			        timeslotStmt.setString(2, selectedEndTime);
			        ResultSet rs = timeslotStmt.executeQuery();

			        int tid = -1;
			        if (rs.next()) {
			            tid = rs.getInt("tid");
			        }

			        if (tid == -1) {
			            System.err.println("Failed to create a timeslot.");
			            return;
			        }

			        // Step 2: Insert the doctor ID and timeslot ID into the DoctorTimeslot table
			        doctorTimeslotStmt.setInt(1, did);
			        doctorTimeslotStmt.setInt(2, tid);
			        doctorTimeslotStmt.executeUpdate();

			        // Step 3: Update working hours in the EMPLOYEE table using empid
			        updateWorkingHoursStmt.setInt(1, workingHours);
			        updateWorkingHoursStmt.setInt(2, empid);

			        int rowsUpdated = updateWorkingHoursStmt.executeUpdate();
			        if (rowsUpdated > 0) {
			            System.out.println("Doctor's working hours and timeslot updated successfully.");
			        } else {
			            System.out.println("No employee found with the given ID.");
			        }

			    } catch (SQLException e) {
			        System.err.println("Error updating doctor's working hours and timeslot: " + e.getMessage());
			        e.printStackTrace();
			    }
			}
		   
		   public boolean AddNurse(int empid, boolean morning, boolean evening, boolean night) {
			   String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
			   try (Connection conn = DriverManager.getConnection(url)) {
				    System.out.println("Database connection established.");
				    // SQL query to insert nurse details
				    String query = "INSERT INTO NURSE (empid, morning, evening, night) VALUES (?, ?, ?, ?)";
				    
				    try (PreparedStatement stmt = conn.prepareStatement(query)) {
				        stmt.setInt(1, empid);
				        stmt.setInt(2, morning ? 1 : 0);
				        stmt.setInt(3, evening ? 1 : 0);
				        stmt.setInt(4, night ? 1 : 0);
				        stmt.executeUpdate();
				        System.out.println("Nurse added successfully.");
				        return true;
				    } catch (SQLException e) {
				        System.err.println("SQL exception: " + e.getMessage());
				        e.printStackTrace();
				        return false;
				    }
				} catch (SQLException e) {
				    System.err.println("Error connecting to the datASDFGHJKabase: " + e.getMessage());
				    
				    return false;
				}
		   }
		   
		   public ObservableList<String> getNurseIds() {
		        String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
		        ObservableList<String> nurseIds = FXCollections.observableArrayList(); // Create ObservableList

		        String query = "SELECT nid FROM NURSE where active != 0"; 

		        try (Connection conn = DriverManager.getConnection(url);
		             Statement stmt = conn.createStatement();
		             ResultSet rs = stmt.executeQuery(query)) {

		            System.out.println("Database connection established.");

		            while (rs.next()) {
		                nurseIds.add(rs.getString("nid")); // Convert `nid` to String
		            }

		            System.out.println("Nurse IDs retrieved successfully.");

		        } catch (SQLException e) {
		            System.err.println("Error while retrieving nurse IDs: " + e.getMessage());
		            e.printStackTrace();
		        }

		        return nurseIds;
		    }

		   public void DeleteNurse(int nid)
		   {
			   String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
			    String updateQuery = "UPDATE Nurse SET active = 0 WHERE nid = ?";

			    try (Connection con = DriverManager.getConnection(url);
			         PreparedStatement pst = con.prepareStatement(updateQuery)) {
			         
			        pst.setInt(1, nid);

			        int rowsAffected = pst.executeUpdate();

			        if (rowsAffected > 0) {
			            System.out.println("Doctor with ID " + nid + " marked as inactive.");
			        } else {
			            System.out.println("No doctor found with ID " + nid + ".");
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
		   }
		   public int GetnidFromDid(int nid) {
			    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
			    String query = "SELECT empid FROM NURSE WHERE nid = ?";
			    int empId = -1; // Default value if no match is found

			    try (Connection conn = DriverManager.getConnection(url);
			         PreparedStatement stmt = conn.prepareStatement(query)) {

			        System.out.println("Database connection established.");

			        stmt.setInt(1, nid);
			        try (ResultSet rs = stmt.executeQuery()) {
			            if (rs.next()) {
			                empId = rs.getInt("empid");
			                System.out.println("Employee ID for Nurse ID " + nid + " is: " + empId);
			            } else {
			                System.out.println("No Employee ID found for Nurse ID " + nid);
			            }
			        }

			    } catch (SQLException e) {
			        System.err.println("Error while retrieving Employee ID: " + e.getMessage());
			        e.printStackTrace();
			    }

			    return empId; // Return the empid or -1 if no match is found
			}

		   
		   public void updateShiftTimeNurse(int nid, boolean morning, boolean evening, boolean night) {
			    String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
			    String query = "UPDATE NURSE SET morning = ?, evening = ?, night = ? WHERE nid = ?";

			    try (Connection conn = DriverManager.getConnection(url);
			         PreparedStatement stmt = conn.prepareStatement(query)) {

			        System.out.println("Database connection established.");

			        // Set the shift values as 0 or 1
			        stmt.setInt(1, morning ? 1 : 0);
			        stmt.setInt(2, evening ? 1 : 0);
			        stmt.setInt(3, night ? 1 : 0);

			        // Set the nurse ID
			        stmt.setInt(4, nid);

			        int rowsUpdated = stmt.executeUpdate();

			        if (rowsUpdated > 0) {
			            System.out.println("Shift times updated successfully for Nurse ID " + nid);
			        } else {
			            System.out.println("No Nurse found with ID " + nid + ". Shift times not updated.");
			        }

			    } catch (SQLException e) {
			        System.err.println("Error while updating shift times: " + e.getMessage());
			        e.printStackTrace();
			    }
			}
		   
		   public void updateWorkingHoursNurse(int empid, int workingHours) {
			   String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
			    String query = "UPDATE EMPLOYEE SET working_hours = ? WHERE empid = ?";

			    try (Connection conn = DriverManager.getConnection(url);
			         PreparedStatement stmt = conn.prepareStatement(query)) {
			        stmt.setInt(1, workingHours);
			        stmt.setInt(2, empid);

			        int rowsAffected = stmt.executeUpdate();
			        if (rowsAffected > 0) {
			            System.out.println("Working hours updated successfully for empid: " + empid);
			        } else {
			            System.out.println("No employee found with empid: " + empid);
			        }
			    } catch (SQLException e) {
			        System.err.println("Error updating working hours for empid " + empid + ": " + e.getMessage());
			    }
			}

		   public ObservableList<String> getReceptionistIds() {
				  ObservableList<String> ridlist = FXCollections.observableArrayList();

			        try (Connection conn = DriverManager.getConnection(
			                "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false")) {

			        	String query = "SELECT rid FROM receptionist WHERE active != 0";

			            Statement stmt = conn.createStatement();
			            ResultSet rs = stmt.executeQuery(query);

			            while (rs.next()) {
			                String rid = rs.getString("rid");
			                ridlist.add(rid); 
			            }

			            System.out.println("Fetched DIDs: " + ridlist);

			        } catch (SQLException e) {
			            e.printStackTrace();
			        }

			        return ridlist;

		   }
		   
		   public boolean AddReceptionist(int empid, boolean morning, boolean evening, boolean night) {
			   String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
			   try (Connection conn = DriverManager.getConnection(url)) {
				    System.out.println("Database connection established.");
				    // SQL query to insert nurse details
				    String query = "INSERT INTO receptionist (empid, morning, evening, night) VALUES (?, ?, ?, ?)";
				    
				    try (PreparedStatement stmt = conn.prepareStatement(query)) {
				        stmt.setInt(1, empid);
				        stmt.setInt(2, morning ? 1 : 0);
				        stmt.setInt(3, evening ? 1 : 0);
				        stmt.setInt(4, night ? 1 : 0);
				        stmt.executeUpdate();
				        System.out.println("Receptionist added successfully.");
				        return true;
				    } catch (SQLException e) {
				        System.err.println("SQL exception: " + e.getMessage());
				        e.printStackTrace();
				        return false;
				    }
				} catch (SQLException e) {
				    System.err.println("Error connecting to the datASDFGHJKabase: " + e.getMessage());
				    return false;
				}

		   }
		   
		   public void DeleteReceptionist(int rid)

		   {
			   String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
			    String updateQuery = "UPDATE receptionist SET active = 0 WHERE rid = ?";

			    try (Connection con = DriverManager.getConnection(url);
			         PreparedStatement pst = con.prepareStatement(updateQuery)) {
			         
			        // Set the `did` parameter
			        pst.setInt(1, rid);

			        // Execute the update query
			        int rowsAffected = pst.executeUpdate();

			        if (rowsAffected > 0) {
			            System.out.println("Doctor with ID " + rid + " marked as inactive.");
			        } else {
			            System.out.println("No doctor found with ID " + rid + ".");
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
		   }
		   
		   public int GetempidFromrid(int rid)
		   {
			   String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
			    Connection conn = null;
			    PreparedStatement stmt = null;
			    ResultSet rs = null;
			    int empid = -1;  // Default value if no result found
			    
			    try {
			        // Initialize the database connection
			        conn = DriverManager.getConnection(url);

			        // Prepare the SQL query
			        String query = "SELECT empid FROM receptionist WHERE rid = ?";
			        stmt = conn.prepareStatement(query);
			        stmt.setInt(1, rid);
			        
			        // Execute the query
			        rs = stmt.executeQuery();
			        
			        // Check if a result was returned
			        if (rs.next()) {
			            empid = rs.getInt("empid");
			        } else {
			            System.out.println("No receptionist found with the given ID.");
			        }

			    } catch (SQLException e) {
			        // Handle SQL exception
			        System.err.println("Error fetching empid from did: " + e.getMessage());
			    } finally {
			        // Close the resources
			        try {
			            if (rs != null) rs.close();
			            if (stmt != null) stmt.close();
			            if (conn != null && !conn.isClosed()) conn.close();
			        } catch (SQLException e) {
			            System.err.println("Error closing resources: " + e.getMessage());
			        }
			    }

			    return empid;
		   }
		   
		   public void updateworkingHourseReceptionist(int empid,int  workingHours)
		   {
			   String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
			    String query = "UPDATE EMPLOYEE SET working_hours = ? WHERE empid = ?";

			    try (Connection conn = DriverManager.getConnection(url);
			         PreparedStatement stmt = conn.prepareStatement(query)) {
			        stmt.setInt(1, workingHours);
			        stmt.setInt(2, empid);

			        int rowsAffected = stmt.executeUpdate();
			        if (rowsAffected > 0) {
			            System.out.println("Working hours updated successfully for empid: " + empid);
			        } else {
			            System.out.println("No employee found with empid: " + empid);
			        }
			    } catch (SQLException e) {
			        System.err.println("Error updating working hours for empid " + empid + ": " + e.getMessage());
			    }
		   }

		   public void updateShiftTimeReceptionist(int rid, boolean morning, boolean evening, boolean night)
		   {
			   String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false";
			    String query = "UPDATE receptionist SET morning = ?, evening = ?, night = ? WHERE rid = ?";

			    try (Connection conn = DriverManager.getConnection(url);
			         PreparedStatement stmt = conn.prepareStatement(query)) {

			        System.out.println("Database connection established.");

			        // Set the shift values as 0 or 1
			        stmt.setInt(1, morning ? 1 : 0);
			        stmt.setInt(2, evening ? 1 : 0);
			        stmt.setInt(3, night ? 1 : 0);

			        // Set the nurse ID
			        stmt.setInt(4, rid);

			        int rowsUpdated = stmt.executeUpdate();

			        if (rowsUpdated > 0) {
			            System.out.println("Shift times updated successfully for receptionist ID " + rid);
			        } else {
			            System.out.println("No receptionist found with ID " + rid + ". Shift times not updated.");
			        }

			    } catch (SQLException e) {
			        System.err.println("Error while updating shift times: " + e.getMessage());
			        e.printStackTrace();
			    }
		   }
		   public boolean isUsernameTaken(String username) {
			   String url = "jdbc:sqlserver://DESKTOP-VH2BAA0\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;encrypt=false"; 
			   String query = "SELECT COUNT(*) FROM EMPLOYEE WHERE username = ?";
			    try (Connection conn = DriverManager.getConnection(url);
			         PreparedStatement stmt = conn.prepareStatement(query)) {

			        stmt.setString(1, username);

			        try (ResultSet rs = stmt.executeQuery()) {
			            if (rs.next()) {
			                return rs.getInt(1) > 0; // If count > 0, username is taken
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			    return false;
			}


}
