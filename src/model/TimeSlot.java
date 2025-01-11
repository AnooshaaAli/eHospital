package model;

import java.sql.Time;
import java.util.List;

import application.DBHandler;

public class TimeSlot {
	
	private int slotId;
	private Time start_time;
	private Time end_time;
	DBHandler dbhandler;
	
	// ---------------------------------- GETTERS AND SETTERS --------------------------------------------- //
	
	public TimeSlot() {
		dbhandler = new DBHandler();
	}
	
	public int getSlotId() {
		return slotId;
	}
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	public Time getStart_time() {
		return start_time;
	}
	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}
	public Time getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}
	
	// ------------------------------ GET TIME SLOT ID -------------------------------------------------- //
	
	public int findTimeSlotId(String startTime) {
		return dbhandler.getTimeSlotIdByStartTime(startTime);
	}
	
	// ---------------------------- SARA AKBAR USE CASES ----------------------------------------------- //
	
	public List<Integer> AddTimeSlots(String starttime, String endtime) {
	    return dbhandler.getTimeSlotIds(starttime, endtime); // Delegate to DB_Handler
	}
}