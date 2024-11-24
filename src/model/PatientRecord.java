package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.DBHandler;
import javafx.collections.ObservableList;

public class PatientRecord {
	
	private int recordID;
	private String temperature;
	private String bloodPressure;
	private String heartRate;
	private List<Medication> medicine;
    private List<Appointment> appointments;
    private List<Bill> bills;
	private static PatientRecord instance;
	private DBHandler dbhandler;
    private DischargeSummary summary;
   
    public static PatientRecord getInstance() 
	{
		if (instance == null) {
            instance = new PatientRecord();
        }
        return instance;
	}
    
    public void initPatientRecord(int id)
	{
    	this.bloodPressure= dbhandler.loadBloopPressure(id);
    	this.temperature=dbhandler.loadTemperature(id);
    	this.heartRate=dbhandler.loadHeartRate(id);
    	this.recordID= dbhandler.loadRecordID(id);
	}
    
    public PatientRecord() {
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
		Medication med= new Medication();
		med.addPrescribeMedication(medName,dosage,pid);
		//dbhandler.addPrescribeMedication(medName,dosage, pid);
	}
	
	public ObservableList<Medication> showExistingMedication(int pid)
	{
		Medication med = new Medication();
		ObservableList<Medication> list = med.showExistingMedication(pid);
		medicine= list;
		return list;
	}
	
	public ObservableList<String> getPatientIds(int pid)
	{
		DBHandler db=new DBHandler();
		ObservableList<String> pidList=db.findPatientRecord(pid);
		return pidList;
	
	}
	
	public void updatePatientRecord(int pid,String PatientRecord,String bloodPressureText,String heartRateText)
	{
		DBHandler db=new DBHandler();
		db.updatePatientRecord(pid,PatientRecord,bloodPressureText,heartRateText);
	}
	
	public void dischargePatient(String inst,LocalDate date,int pid)
	{
		//System.out.println("this is the record id " +this.getRecordID() );
		summary.createDischargePatient(inst, date,pid);
	}

	public ObservableList<Bill> loadBills()
	{
		Bill a = new Bill();
		ObservableList<Bill> bill= a.loadBills(recordID);
		return bill;
	}
	
	public ObservableList<Integer> loadBillID()
	{
		ObservableList<Integer> list= dbhandler.loadBillID(recordID);
		return list;
	}
	
	public boolean payByCash(int billId)
	{
		Bill b = new Bill();
		boolean check= b.payByCash(billId);
		return check;
	}
	public boolean payByCard(int billId)
	{
		Bill b = new Bill();
		boolean check= b.payByCard(billId);
		return check;
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

	public DischargeSummary getSummary() {
		return summary;
	}

	public void setSummary(DischargeSummary summary) {
		this.summary = summary;
	}
	
	// -------------------------------------------- ANOOSHA'S FUNCS --------------------------------------------------- //
	
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
		Appointment a = new Appointment();
		ObservableList<Appointment> list = a.getPendingAppointments(patId);
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