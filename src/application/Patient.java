package application;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Patient {
	
	//attributes
	private DBHandler dbhandler;
	private int patientId;
	private String patientName;
	private String username;
	private String gender;
	private Date dob;
	private String contact;
	private boolean dischargeStatus;
	private PatientRecord record;
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
	
	
	//constructor
	Patient()
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
	public boolean payByCash()
	{
		boolean check= record.payByCash();
		return check;
	}
	public boolean payByCard()
	{
		boolean check= record.payByCard();
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
	
	
	
	
	
	
	


}