package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import application.DBHandler;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Patient {
	
	private int patientId;
	private String patientName;
	private String username;
	private String password;
	private String gender;
	private Date dob;
	private String contact;
	private boolean dischargeStatus;
	private PatientRecord record;
	private DBHandler dbhandler;
	private static Patient instance; 

	//functions
	public void init(int id, String name, String uname,String gen,Date DOB,String con, boolean st) {
		this.patientId=id;
		this.patientName=name;
		this.username=uname;
		this.gender= gen;
		this.dob = DOB;
		this.contact=con;
		this.dischargeStatus=st;
		initPatientRecord(patientId);
    }
	
	public void initPatientRecord(int id)
	{
		record= new PatientRecord();
		record.initPatientRecord(id);
	}
	
	public static Patient getInstance() 
	{
		if (instance == null) {
            instance = new Patient();
        }
        return instance;
	}
	
	public Patient()
	{
		dbhandler=new DBHandler();
		record= new PatientRecord();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void addMedications(String medName,int dosage,int pid)
	{
		
		record.addPrescribeMedication(medName,dosage,pid);
	}
	public ObservableList<Medication> showExistingMedication(int pid)
	{
		ObservableList<Medication> list= record.showExistingMedication(pid);
		return list;
	}
	@Override
    public String toString() {
        return patientName + " (" + patientId + ")";
    }
    public ObservableList<String> getPatientIds() {
    	DBHandler db=new DBHandler();
		
        ObservableList<String> pidList = db.getPatientIds();

        return pidList;
    } 
    public ObservableList<String> findPatientRecord(int pid) {
    	PatientRecord p=new PatientRecord();
    	ObservableList<String> pidList = p.getPatientIds(pid);
		return pidList;

    }
	public void dischargePatient(String inst,LocalDate date,int pid)
	{
		record.dischargePatient(inst,date,pid);
	}
	public boolean LoginPatient(String username, String password)
	{
		boolean check =dbhandler.LoginPatient(username,password);
		return check;
	}
	public String loadPatientName(String username)
	{
		String name= dbhandler.loadPatientName(username);
		return name;
	}
	public int loadPatientId(String username)
	{
		int id=dbhandler.loadPatientId(username);
		return id;
	}
	public String loadPatientGender(String username) {
		String data= dbhandler.loadPatientGender(username);
		return data;
	}
	public String loadPatientDOB(String username) {
		String data= dbhandler.loadPatientDOB(username);
		return data;
	}
	public String loadPatientDischargeStatus(String username) {
		String data = dbhandler.loadPatientDischargeStatus(username) ? "Discharged" : "Not Discharged";
		return data;
	}
	
	public String loadPatientContact(String username) {
		String data= dbhandler.loadPatientContact(username);
		return data;
	}
	
	public ObservableList<Bill> loadBills()
	{
		ObservableList<Bill> bill= record.loadBills();
		return bill;
	}
	
	public ObservableList<Integer> loadBillID()
	{
		ObservableList<Integer> list= record.loadBillID();
		return list;
	}
	public boolean payByCash(int billId)
	{
		boolean check= record.payByCash(billId);
		return check;
	}
	public boolean payByCard(int billId)
	{
		boolean check= record.payByCard(billId);
		return check;
	}

	public DBHandler getDbhandler() {
		return dbhandler;
	}
	public void setDbhandler(DBHandler dbhandler) {
		this.dbhandler = dbhandler;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public static void setInstance(Patient instance) {
		Patient.instance = instance;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return patientId;
	}
	public void setId(int id) {
		this.patientId = id;
	}
	public boolean isDischargeStatus() {
		return dischargeStatus;
	}
	public void setDischargeStatus(boolean dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}
	public PatientRecord getRecord() {
		return record;
	}
	public void setRecord(PatientRecord record) {
		this.record = record;
	}
	
	// ---------------------------------------------------- ANOOSHA'S FUNCTIONS ----------------------------------------------------------------------------- //
	
	// ------------------------------------------ Get patient Id on the basis of username ---------------------------------------------------//
	
	public int getPatientId(String username) {
		int id = dbhandler.getPatientId(username);
		return id;
	}
	
	// ---------------------------------------------------------- Use Cases -------------------------------------------------------------------//
	
	// -------------------------------------------------- Use Case: Register Patient --------------------------------------------------------//
	
	public void registerPatient(String name, String username, String password, String gender, String dob, String contact) {
		int id = dbhandler.registerPatient(name, username, password, gender, dob, contact);
		record.insertDefaultRecord(id);
	}
	
	// -------------------------------------------------- Use Case: View Patient Record --------------------------------------------------------//
	
	public PatientRecord viewRecord(int patId){
		record = record.getRecord(patId);
		return record;
	}
	
	// ----------------------------------------- Get Record Id on the basis of Patient ID ----------------------------------------------------- //
	
	public int getRecordId(int patId) {
		int id = record.getRecordId(patId);
		System.out.println(id);
		return id;
	}
	
	// ------------------------------------------------ Add a new Appointment --------------------------------------------------- //
	
	public boolean addAppointment(int recId, int docId, LocalDate date, int timeslotId) {
		return record.addAppointment(recId, docId, date, timeslotId) ;
	}
	
	// ------------------------------------------------------ Add bill ---------------------------------------------------------- //

	public void addBill(int recId, double payment, String type) {
		record.addBill(recId, payment, type);
	}
	
	
	// ----------------------------------------------------- GET PENDING APPOINTMENTS LIST -------------------------------------- //
	
	public ObservableList<Appointment> getPendingAppointments(int patId)
	{
		ObservableList<Appointment> list = record.getPendingAppointments(patId);
		return list;
	}
	
    // ----------------------------------------------------- GET PENDING APPOINTMENT IDS ----------------------------------------------- //
    
	public ObservableList<Integer> getPendingAppointmentIdsList(int patId) {
		return record.getPendingAppointmentIdsList(patId);
	}
	
	// ------------------------------------------------------ MARK APPOINTMENT AS DONE ------------------------------------------------- //
	
	public void markAptCompleted(int aptId) {
		record.markAptCompleted(aptId);
	}
	
	
	//----------------------------------------------------PATIENT RECORD NURSE -----------------------------------------------------------///
	public ObservableList<PatientRecord> loadPatientRecord(int pid)
	{
		PatientRecord p= new PatientRecord();
		ObservableList<PatientRecord> list= p.loadPatientRecord(pid);
		return list;
	}
}