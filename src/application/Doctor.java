package application;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Doctor extends Employee{
	private int doctorId;
    private boolean[] availableDays; 
	private int num_appointments;
	private DBHandler dbhandler;
	private List<TimeSlot> timeslots;
	
    Doctor() {
        availableDays = new boolean[6]; 
        dbhandler = new DBHandler();
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
	
}
