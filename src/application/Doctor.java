package application;

import java.util.List;

public class Doctor extends Employee{
	private DBHandler db;
	private static Doctor instance; 
	private int doctorId;
    private boolean[] availableDays; 
	private int num_appointments;
	
	public void init(int id, boolean[] available, int app) {
		this.doctorId=id;
		this.availableDays= available;
		this.num_appointments=app;
    }
	public static Doctor getInstance() 
	{
		if (instance == null) {
            instance = new Doctor();
        }
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
    	db= new DBHandler();
        availableDays = new boolean[6]; 
    }
    public boolean LoginDoctor(String username,String pass)
    {
    	boolean check=db.LoginDoctor(username,pass);
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
		int data=db.loadDoctorId(username);
		
		return data;
	}
    public String loadDoctorWorkingDays(String username)
    {
    	
    	String days= db.loadWorkingDays(username);
    	return days;
    }
	public DBHandler getDb() {
		return db;
	}
	public void setDb(DBHandler db) {
		this.db = db;
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
    
}
