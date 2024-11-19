package application;

import java.util.List;

public class Doctor extends Employee{
	private DBHandler db;
	
	private int doctorId;
    private boolean[] availableDays; 
	private int num_appointments;
	
    public Doctor() {
    	db= new DBHandler();
        availableDays = new boolean[6]; 
    }
    public boolean LoginDoctor(String username,String pass)
    {
    	boolean check=db.LoginDoctor(username,pass);
		return check;   	
    }
    
}
