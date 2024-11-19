package application;

import java.util.ArrayList;
import java.util.List;

public class PatientRecord {
	private String temperature;
	private String bloodPressure;
	private String heartRate;
	private List<Medication> medicine;
    private List<Appointment> appointments;
    private List<Bill> bills;
    private DBHandler dbhandler;
    
    PatientRecord() {
    	dbhandler = new DBHandler();
    	medicine = new ArrayList<Medication>();
    	appointments = new ArrayList<Appointment>();
    	bills = new ArrayList<Bill>();
    }
	public List<Medication> getMedicine() {
		return medicine;
	}
	public void setMedicine(List<Medication> medicine) {
		this.medicine = medicine;
	}
//	public List<Appointment> getAppointments() {
//		return appointments;
//	}
//	public void setAppointments(List<Appointment> appointments) {
//		this.appointments = appointments;
//	}
//	public Bill getBills() {
//		return bills;
//	}
//	public void setBills(Bill bills) {
//		this.bills = bills;
//	}
	public void insertDefaultRecord(int patientId) {
		temperature = "98.6 F";
		bloodPressure = "120/80 mmHg";
		heartRate = "72 bpm";
		dbhandler.insertPatientRecord(patientId, temperature, bloodPressure, heartRate);
	}
	
	public PatientRecord getRecord(int patId) {
		Medication m = new Medication();
		medicine = m.showExistingMedication(patId);
		return this;
	}
}