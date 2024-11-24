package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Doctor extends Employee{
	private static Doctor instance; 
	private int doctorId;
    private boolean[] availableDays; 
	private int num_appointments;
	private static DBHandler dbhandler;
	private List<TimeSlot> timeslots;
    
	public void init(int id, boolean[] available, int app) {
		this.doctorId=id;
		this.availableDays= available;
		this.num_appointments=app;
    }
	
	
    // ------------------------------------ POPULATE WITH DOCTOR ID'S ----------------------------------------------- //
    
	public ObservableList<Integer> getDoctorIdsList() {
		return dbhandler.getDoctorIdsList();
	}
	
	// ------------------------------------ CHECK DOCTOR AVAILABILITY ----------------------------------------------- //
	
	public boolean checkDoctorAvailability(int doctorId, LocalDate date) {
		return dbhandler.checkDoctorAvailability(doctorId, date);
	}
	
	// ------------------------------------- GET FREE TIME SLOTS OF DOCTOR ------------------------------------------ //
    
	public ObservableList<String> getDoctorTimeSlotsList(int docId, LocalDate date) {
		return dbhandler.fetchAvailableTimeSlots(docId, date);
	}
	
	// ------------------------------------------------- ADD INTO DOCTOR'S TIMESLOT ----------------------------------------------------------- //
	
	public boolean addDoctorTimeslot(int doctorId, int timeslotId, LocalDate date) {
		return dbhandler.addDoctorTimeslot(doctorId, timeslotId, date);
	}
	
	public static Doctor getInstance() 
	{
		if (instance == null) {
            instance = new Doctor();
        }
		
        dbhandler = new DBHandler();
        return instance;
	}
	
	public boolean[] convertDaysToBooleanArray(String days) {
	    // Create a boolean array for Monday to Friday
	    boolean[] workingDays = new boolean[5];
	    
	    // Days of the week (Monday to Friday)
	    String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	    
	    // Split the input days string into an array of individual days
	    String[] doctorDays = days.split(",\\s*"); // Assuming days are comma-separated, e.g., "Monday, Wednesday"
	    
	    // Iterate over the weekDays array and mark true if the day is in the doctorDays array
	    for (int i = 0; i < weekDays.length; i++) {
	        for (String doctorDay : doctorDays) {
	            if (weekDays[i].equalsIgnoreCase(doctorDay)) {
	                workingDays[i] = true;
	                break;
	            }
	        }
	    }
	    
	    return workingDays;
	}
	public String convertBooleanArrayToDays(boolean[] workingDays) {
	    // Days of the week (Monday to Friday)
	    String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	    
	    // Use a StringBuilder to build the resulting string
	    StringBuilder workingDaysString = new StringBuilder();
	    
	    // Iterate over the boolean array
	    for (int i = 0; i < workingDays.length; i++) {
	        if (workingDays[i]) {
	            if (workingDaysString.length() > 0) {
	                workingDaysString.append(", ");
	            }
	            workingDaysString.append(weekDays[i]);
	        }
	    }
	    
	    return workingDaysString.toString();
	}

    public Doctor() {
    	dbhandler = new DBHandler();
        availableDays = new boolean[6]; 
    }
    
    public boolean LoginDoctor(String username,String pass)
    {
    	boolean check = dbhandler .LoginDoctor(username,pass);
		return check;   	
    }
//    public String loadDoctorName(String username)
//	{
//		String data=db.loadDoctorName(username);
//		
//		return data;
//	}
	public int loadDoctorId(String username)
	{
		int data = dbhandler.loadDoctorId(username);
		
		return data;
	}
    public String loadDoctorWorkingDays(String username)
    {
    	
    	String days= dbhandler.loadWorkingDays(username);
    	return days;
    }
	public DBHandler getDb() {
		return dbhandler;
	}
	public void setDb(DBHandler db) {
		this.dbhandler = db;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public boolean[] getAvailableDays() {
		return availableDays;
	}
	public void setAvailableDays(boolean[] availableDays) {
		this.availableDays = availableDays;
	}
	public int getNum_appointments() {
		return num_appointments;
	}
	public void setNum_appointments(int num_appointments) {
		this.num_appointments = num_appointments;
	}
	public static void setInstance(Doctor instance) {
		Doctor.instance = instance;
	}
    
	// ----------------------------------------------------- SARA AKBAR USE CASES ------------------------------------------------------------------ //
    
	public int AddDoctor(int empid,List<String> availableDays,int noOfApp)
    {
    	System.out.println(availableDays);
    	boolean isMonday = false;
        boolean isTuesday = false;
        boolean isWednesday = false;
        boolean isThursday = false;
        boolean isFriday = false;
        boolean isSaturday = false;


     // Check if each day is present in the availableDays list
     if (availableDays.contains("Monday")) {
    	 isMonday = true;
         System.out.println("Monday is selected: " + isMonday);
     }
     if (availableDays.contains("Tuesday")) {
    	 isTuesday = true;
         System.out.println("Tuesday is selected: " + isTuesday);
     }
     if (availableDays.contains("Wednesday")) {
    	 isWednesday = true;
         System.out.println("Wednesday is selected: " + isWednesday);
     }
     if (availableDays.contains("Thursday")) {
    	 isThursday = true;
         System.out.println("Thursday is selected: " + isThursday);
     }
     if (availableDays.contains("Friday")) {
    	 isFriday = true;
         System.out.println("Friday is selected: " + isFriday);
     }
     if (availableDays.contains("Saturday")) {
    	 isSaturday = true;
         System.out.println("Saturday is selected: " + isSaturday);
     }

     // Print final boolean values for all days
     System.out.println("Monday: " + isMonday);
     System.out.println("Tuesday: " + isTuesday);
     System.out.println("Wednesday: " + isWednesday);
     System.out.println("Thursday: " + isThursday);
     System.out.println("Friday: " + isFriday);
     System.out.println("Saturday: " + isSaturday);
		int did = dbhandler.AddDoctor(empid,isMonday,isTuesday,isWednesday,isThursday,isFriday,isSaturday,noOfApp);
    	
		
	return did;
		
    }


	public static DBHandler getDbhandler() {
		return dbhandler;
	}


	public static void setDbhandler(DBHandler dbhandler) {
		Doctor.dbhandler = dbhandler;
	}


	public List<TimeSlot> getTimeslots() {
		return timeslots;
	}


	public void setTimeslots(List<TimeSlot> timeslots) {
		this.timeslots = timeslots;
	}
	
	//-------------------------------------------------------------LIST OF ALL DOCTORS-------------------------------------------------------------//
	public ObservableList<Doctor> listDoctors() {
		Employee emp= new Employee();
		ObservableList<Doctor> list= emp.listDoctors();
		return list;
	}
}