package application;

import java.util.List;

public class Doctor extends Employee{
	private DB_Handler dbhandler;
	
	private int doctorId;
    private boolean[] availableDays; 
	private int num_appointments;
	
    public Doctor() {
        availableDays = new boolean[6]; 
    }
    
    public int AddDoctor(int empid,List<String> availableDays,int noOfApp)
    {
    	DB_Handler db=new DB_Handler();
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
		int did=db.AddDoctor(empid,isMonday,isTuesday,isWednesday,isThursday,isFriday,isSaturday,noOfApp);
    	
		
	return did;
		
    }
}