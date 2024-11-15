package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
	
   String url = "jdbc:sqlserver://10N5Q8AKAMRA\\SQLEXPRESS01;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
	// String url ="jdbc:sqlserver://FATIMA\\SQLEXPRESS;databaseName=eHospital;integratedSecurity=true;trustServerCertificate=true";
	   
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
}
