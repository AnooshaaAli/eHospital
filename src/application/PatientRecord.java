package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public class PatientRecord {
	
	private static PatientRecord instance;
	private DBHandler dbhandler;
	private String temperature;
	private String bloodPressure;
	private String heartRate;
	private List<Medication> medicine;
    private List<Appointment> appointments;
    //private Bill bills;
    private List<Bill> bills;
    private int recordID;
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
    
    
    PatientRecord()
    {
    	dbhandler=new DBHandler();
    	summary= new DischargeSummary();
    	bills = new ArrayList<>();
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
		Medication med= new Medication();
		ObservableList<Medication> list=med.showExistingMedication(pid);
		//ObservableList<Medication> list=dbhandler.showExistingMedication(pid);
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
	
	public boolean payByCash()
	{
		boolean check= dbhandler.payByCash(recordID);
		return check;
	}
	public boolean payByCard()
	{
		boolean check= dbhandler.payByCard(recordID);
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
	
	
}