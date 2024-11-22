package application;
import java.lang.Integer;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBHandler {
	
   String url = "jdbc:sqlserver://10N5Q8AKAMRA\\SQLEXPRESS01;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
	// String url ="jdbc:sqlserver://FATIMA\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
   DBHandler()
   {}
	private Connection connect() {
	    Connection conn = null;
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Load SQL Server JDBC driver
	        conn = DriverManager.getConnection(url);
	        //System.out.println("Connected to the database successfully!");
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
	
	// ---------------------------------------- View Existing Medications ------------------------------------------------ //
	
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
	
	// ---------------------------------------------- VIEW APPOINTMENTS -------------------------------------------------------- //
	
	public ObservableList<Appointment> getAppointments(int pid) {
	    ObservableList<Appointment> appointments = FXCollections.observableArrayList();
	    String recordIdQuery = "SELECT recordID FROM PATIENTRECORD WHERE pid = " + pid;

	    try (Connection conn = connect(); 
	         Statement stmt = conn.createStatement()) {

	        // First, get the recordID
	        ResultSet rsRecordId = stmt.executeQuery(recordIdQuery);

	        if (rsRecordId.next()) {
	            int recordID = rsRecordId.getInt("recordID");

	            // Query the APPOINTMENT table and join with TIMESLOT to fetch all required details
	            String appointmentQuery = 
	                "SELECT a.aid, a.appdate, a.did, a.status, t.starttime, t.endtime " +
	                "FROM APPOINTMENT a " +
	                "JOIN TIMESLOT t ON a.tid = t.tid " +
	                "WHERE a.recordID = " + recordID;

	            ResultSet rsAppointment = stmt.executeQuery(appointmentQuery);

	            // Fetch appointment details and add to ObservableList
	            while (rsAppointment.next()) {
	                int aptId = rsAppointment.getInt("aid");
	                Date appdate = rsAppointment.getDate("appdate");
	                int doctorId = rsAppointment.getInt("did");
	                boolean status = rsAppointment.getBoolean("status");
	                Time startTime = rsAppointment.getTime("starttime");
	                Time endTime = rsAppointment.getTime("endtime");

	                // Create an Appointment object (ensure it has fields for startTime and endTime)
	                Appointment appointment = new Appointment(aptId, appdate, status, startTime, endTime);
	                appointments.add(appointment);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return appointments;
	}

	// ---------------------------------------------- VIEW BILLS -------------------------------------------------------- //
	
	public ObservableList<Bill> getBills(int pid) {
	    ObservableList<Bill> bills = FXCollections.observableArrayList();
	    
	    // First, get the recordID for the given patient ID (pid)
	    String recordIdQuery = "SELECT recordID FROM PATIENTRECORD WHERE pid = " + pid;

	    try (Connection conn = connect(); 
	         Statement stmt = conn.createStatement()) {

	        // Execute the query to get the recordID
	        ResultSet rsRecordId = stmt.executeQuery(recordIdQuery);

	        if (rsRecordId.next()) {
	            int recordID = rsRecordId.getInt("recordID");

	            // Query the BILL table to fetch all the bills related to the recordID
	            String billQuery = 
	                "SELECT b.bid, b.payment, b.paymentType, b.status " +
	                "FROM BILL b " +
	                "WHERE b.recordId = " + recordID;

	            ResultSet rsBill = stmt.executeQuery(billQuery);

	            // Fetch bill details and add them to the ObservableList
	            while (rsBill.next()) {
	                int bid = rsBill.getInt("bid");
	                double payment = rsBill.getDouble("payment");
	                String paymentType = rsBill.getString("paymentType");
	                boolean status = rsBill.getBoolean("status");

	                // Create a Bill object (ensure your Bill class has appropriate fields for bid, payment, paymentType, and status)
	                Bill bill = new Bill(bid, payment, paymentType, status);
	                bills.add(bill);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return bills;
	}

	
	// -------------------------------------------------- FIND PATIENT RECORD ------------------------------------------------------------- //
	
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
	 
		// ---------------------------------------------- GET PATIENT ID ------------------------------------------------------ //
		
		public int getPatientId(String username) {
		    // SQL query to get patientId for the provided username
		    String query = "SELECT patientId FROM patients WHERE username = ?";
		    
		    try (Connection conn = this.connect();
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		        
		        // Set the username parameter in the query
		        stmt.setString(1, username);
		
		        // Execute the query
		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                // Retrieve patientId from the result set
		                return rs.getInt("patientId");
		            } else {
		                // Return -1 or any value to indicate the username was not found
		                return -1;
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return -1; // In case of error, return -1 or handle it as needed
		    }
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
		
		// --------------------------------------------- INSERT INTO PATIENT RECORD ---------------------------------------------------- //
		
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
		
		 // --------------------------------------------------- LOGIN PATIENT ------------------------------------------------------ //
		
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
	 
	 // ----------------------------------------- GET ALL IDS OF DOCTORS ------------------------------------------------- //
	 
	public ObservableList<Integer> getDoctorIdsList() {
	    ObservableList<Integer> docIdsList = FXCollections.observableArrayList();
	   
	    try (Connection conn = this.connect()) {
	        String query = "SELECT did FROM doctor";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            int did = rs.getInt("did");
	            docIdsList.add(did);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return docIdsList;
	}
	
	// --------------------------------------- IS DOCTOR AVAILABLE --------------------------------------------------------- //

	public boolean checkDoctorAvailability(int doctorId, LocalDate appointmentDate) {

	    DayOfWeek dayOfWeek = appointmentDate.getDayOfWeek();
	    String dayString = dayOfWeek.toString(); 

	    String query = "SELECT monday, tuesday, wednesday, thursday, friday, saturday FROM DOCTOR WHERE did = ?";

	    try (Connection conn = this.connect();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, doctorId);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            boolean isAvailable = false;
	            
	            switch (dayString) {
	                case "MONDAY":
	                    isAvailable = rs.getBoolean("monday");
	                    break;
	                case "TUESDAY":
	                    isAvailable = rs.getBoolean("tuesday");
	                    break;
	                case "WEDNESDAY":
	                    isAvailable = rs.getBoolean("wednesday");
	                    break;
	                case "THURSDAY":
	                    isAvailable = rs.getBoolean("thursday");
	                    break;
	                case "FRIDAY":
	                    isAvailable = rs.getBoolean("friday");
	                    break;
	                case "SATURDAY":
	                    isAvailable = rs.getBoolean("saturday");
	                    break;
	                default:
	                    isAvailable = false;
	            }

	            return isAvailable;
	        }

	        return false;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;  
	    }
	}

	// ------------------------------------------- GET FREE TIME SLOTS OF THE DOCTOR ---------------------------------------------- //
	
	public ObservableList<String> fetchAvailableTimeSlots(int doctorId, LocalDate selectedDate) {
	    ObservableList<String> timeSlots = FXCollections.observableArrayList();
	    
	    Date sqlDate = Date.valueOf(selectedDate);
	    String query = "SELECT t.starttime FROM DoctorTimeslot dt " +
	                   "JOIN timeslot t ON dt.tid = t.tid " +
	                   "WHERE dt.did = ? AND dt.date = ?";

	    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");  // Set the format to "HH:mm"
	    
	    try (Connection conn = this.connect();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setInt(1, doctorId);
	        stmt.setDate(2, sqlDate);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            // Fetch the time from the result set as a Time object
	            Time timeFromDB = rs.getTime("starttime");
	            
	            // Convert the SQL Time to LocalTime
	            LocalTime localTime = timeFromDB.toLocalTime();
	            
	            // Format the LocalTime to "HH:mm"
	            String formattedTime = localTime.format(timeFormatter);
	            
	            // Add the formatted time to the list
	            timeSlots.add(formattedTime);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return timeSlots;
	}
	
	// ---------------------------------------------- ADD APPOINTMENT ------------------------------------------------- //

	public boolean addAppointment(int recordID, int doctorId, LocalDate appDate, int timeSlotId) {
	    String insertQuery = "INSERT INTO APPOINTMENT (recordID, did, APPDATE, tid, status) " +
	                         "VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = this.connect(); 
	         PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

	        // Set the parameters for the insert query
	        stmt.setInt(1, recordID);  // Patient record ID
	        stmt.setInt(2, doctorId);   // Doctor ID
	        stmt.setDate(3, Date.valueOf(appDate)); // Appointment date
	        stmt.setInt(4, timeSlotId); // Time slot ID
	        stmt.setInt(5, 0);          // Status (default to 0 for new appointments)

	        // Execute the insert query
	        int rowsAffected = stmt.executeUpdate();

	        // If one row is inserted successfully, return true
	        if (rowsAffected > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    // Return false if something goes wrong
	    return false;
	}

	// ---------------------------------------------- GET PATIENT RECORD ID ------------------------------------------------------ //
	
	public int getRecordId(int patId) {
	    // SQL query to get patientId for the provided username
	    String query = "SELECT recordID FROM PATIENTRECORD WHERE pid = ?";
	    
	    try (Connection conn = this.connect();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        // Set the username parameter in the query
	        stmt.setInt(1, patId);
	
	        // Execute the query
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                // Retrieve patientId from the result set
	                return rs.getInt("recordID");
	            } else {
	                // Return -1 or any value to indicate the username was not found
	                return -1;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1; // In case of error, return -1 or handle it as needed
	    }
	}
	
	// --------------------------------------------- GET TIME SLOT ID ----------------------------------------------------- //
	
	public int getTimeSlotIdByStartTime(String startTime) {
	    String query = "SELECT tid FROM timeslot WHERE starttime = ?";
	    int timeSlotId = -1; // Default value to indicate that no match was found

	    try (Connection conn = this.connect(); 
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        // Set the parameter (startTime) for the query
	        stmt.setString(1, startTime);
	        
	        // Execute the query
	        ResultSet rs = stmt.executeQuery();
	        
	        // Check if the result set has a matching record
	        if (rs.next()) {
	            timeSlotId = rs.getInt("tid");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return timeSlotId;
	}
	
	// ------------------------------------------------- ADD BILL ----------------------------------------------------------------- //
	
	public void addBill(int recordId, double payment, String paymentType) {
	    String insertBillQuery = "INSERT INTO BILL (recordId, payment, paymentType, status) VALUES (?, ?, ?, ?)";

	    try (Connection conn = this.connect(); 
	         PreparedStatement stmt = conn.prepareStatement(insertBillQuery, Statement.RETURN_GENERATED_KEYS)) {

	        // Set the parameters for the query
	        stmt.setInt(1, recordId);
	        stmt.setDouble(2, payment);
	        stmt.setString(3, paymentType);
	        stmt.setInt(4, 0); // Default status is 0

	        // Execute the insert query
	        int rowsInserted = stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// -------------------------------------------- GET PENDING APPOINTMENTS LIST ----------------------------------------------- //
	
	public ObservableList<Appointment> getPendingAppointments(int pid) {
	    ObservableList<Appointment> appointments = FXCollections.observableArrayList();
	    String recordIdQuery = "SELECT recordID FROM PATIENTRECORD WHERE pid = " + pid;

	    try (Connection conn = connect(); 
	         Statement stmt = conn.createStatement()) {

	        // First, get the recordID
	        ResultSet rsRecordId = stmt.executeQuery(recordIdQuery);

	        if (rsRecordId.next()) {
	            int recordID = rsRecordId.getInt("recordID");

	            // Query the APPOINTMENT table and join with TIMESLOT to fetch all required details
	            String appointmentQuery = 
	                "SELECT a.aid, a.appdate, a.did, a.status, t.starttime, t.endtime " +
	                "FROM APPOINTMENT a " +
	                "JOIN TIMESLOT t ON a.tid = t.tid " +
	                "WHERE a.recordID = " + recordID + "and a.status = " + 0;

	            ResultSet rsAppointment = stmt.executeQuery(appointmentQuery);

	            // Fetch appointment details and add to ObservableList
	            while (rsAppointment.next()) {
	                int aptId = rsAppointment.getInt("aid");
	                Date appdate = rsAppointment.getDate("appdate");
	                int doctorId = rsAppointment.getInt("did");
	                boolean status = rsAppointment.getBoolean("status");
	                Time startTime = rsAppointment.getTime("starttime");
	                Time endTime = rsAppointment.getTime("endtime");

	                // Create an Appointment object (ensure it has fields for startTime and endTime)
	                Appointment appointment = new Appointment(aptId, appdate, status, startTime, endTime);
	                appointments.add(appointment);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return appointments;
	}

    // ----------------------------------------- GET PENDING APPOINTMENT IDS LIST ---------------------------------------------- //
	
    public ObservableList<Integer> getPendingAppointmentIdsList(int recordId) {
        ObservableList<Integer> pendingAppointments = FXCollections.observableArrayList();
        String query = "SELECT aid FROM appointment WHERE recordID = ? AND status = " + 0;

        try (PreparedStatement preparedStatement = this.connect().prepareStatement(query)) {
            // Set the patient ID parameter
            preparedStatement.setInt(1, recordId);

            // Execute the query and process the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through the result set and add each appointment ID to the list
            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("aid");
                pendingAppointments.add(appointmentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pendingAppointments;
    }
    
    // ------------------------------------------------------ MARK AS COMPLETED --------------------------------------------------------- //
    
    public void makeAptCompleted(int appointmentId) {
        String updateQuery = "UPDATE Appointment SET status = " + 1 + "WHERE aid = ?";

        try (PreparedStatement stmt = this.connect().prepareStatement(updateQuery)) {
            // Set the appointmentId parameter
            stmt.setInt(1, appointmentId);

            // Execute the update query
            int rowsUpdated = stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error while marking appointment as completed: " + e.getMessage());
        }
    }
}
