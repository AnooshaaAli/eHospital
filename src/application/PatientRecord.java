package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public class PatientRecord {
	
	private int recordID;
	private String temperature;
	private String bloodPressure;
	private String heartRate;
	private List<Medication> medicine;
    private List<Appointment> appointments;
    private List<Bill> bills;
    private DBHandler dbhandler;
    private DischargeSummary summary;
    
    PatientRecord() {
    	dbhandler = new DBHandler();
    	summary = new DischargeSummary();
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
	
	public void addPrescribeMedication(String medName,int dosage,int pid)
	{
		dbhandler.addPrescribeMedication(medName,dosage, pid);
	}
	
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public String getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	
	public ObservableList<String> getPatientIds(int pid)
	{
		DBHandler db=new DBHandler();
		ObservableList<String> pidList=db.findPatientRecord(pid);
		return pidList;
	
	}
	
	// ---------------------------------------- UPDATE PATIENT RECORD ------------------------------------------------ //
	
	public void updatePatientRecord(int pid,String PatientRecord,String bloodPressureText,String heartRateText)
	{
		DBHandler db=new DBHandler();
		db.updatePatientRecord(pid,PatientRecord,bloodPressureText,heartRateText);
	}
	
	// ---------------------------------------- DISCHARGE PATIENT ------------------------------------------------ //
	
	public void dischargePatient(String inst,LocalDate date,int pid)
	{
		//System.out.println("this is the record id " +this.getRecordID() );
		summary.createDischargePatient(inst, date,pid);
	}
	
	// ---------------------------------------- INSERT DEFAULT RECORD ------------------------------------------------ //
	
	public void insertDefaultRecord(int patientId) {
		temperature = "98.6 F";
		bloodPressure = "120/80 mmHg";
		heartRate = "72 bpm";
		dbhandler.insertPatientRecord(patientId, temperature, bloodPressure, heartRate);
	}
	
	// ---------------------------------------- GET PATIENT RECORD ------------------------------------------------ //
	
	public PatientRecord getRecord(int patId) {
		Medication m = new Medication();
		medicine = m.showExistingMedication(patId);
		Appointment a = new Appointment();
		appointments = a.getAppointments(patId);
		Bill b = new Bill();
		bills = b.getBills(patId);
		return this;
	}
	
	// ---------------------------------------- SHOW MEDICATIONS ------------------------------------------------ //
	
	public ObservableList<Medication> showExistingMedication(int pid)
	{
		ObservableList<Medication> list=dbhandler.showExistingMedication(pid);
		medicine= list;
		return list;
	}
	
	// ----------------------------------------- GET RECORD ID ------------------------------------------------- //
	
	public int getRecordId(int patId) {
		int id = dbhandler.getRecordId(patId);
		return id;
	}
	
	// ------------------------------------------------ ADD A NEW APPOINTMENT --------------------------------------------------- //
	
	public boolean addAppointment(int recId, int docId, LocalDate date, int timeslotId) {
		return dbhandler.addAppointment(recId, docId, date, timeslotId) ;
	}
	
	// ------------------------------------------------------ ADD BILL ---------------------------------------------------------- //

	public void addBill(int recId, double payment, String type) {
		Bill b = new Bill();
		b.addBill(recId, payment, type);
	}
	
	// ----------------------------------------------------- GET PENDING APPOINTMENTS LIST -------------------------------------- //
	
	public ObservableList<Appointment> getPendingAppointments(int patId)
	{
		ObservableList<Appointment> list = dbhandler.getPendingAppointments(patId);
		return list;
	}
	
    // ----------------------------------------------------- GET PENDING APPOINTMENT IDS ----------------------------------------------- //
    
	public ObservableList<Integer> getPendingAppointmentIdsList(int patId) {
		int recordId = this.getRecordId(patId);
		return dbhandler.getPendingAppointmentIdsList(recordId);
	}
	
	// ------------------------------------------------------ MARK APPOINTMENT AS DONE ------------------------------------------------- //
	
	public void markAptCompleted(int aptId) {
		Appointment apt = new Appointment();
		apt.setAppointmentId(aptId);
		apt.markAptCompleted();
	}
}


