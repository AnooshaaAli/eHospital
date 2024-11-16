package application;

import java.util.List;

public class Doctor extends Employee{
	private DBHandler dbhandler;
	
	private int doctorId;
    private boolean[] availableDays; 
	private int num_appointments;
	
    public Doctor() {
        availableDays = new boolean[6]; 
    }
    
}
