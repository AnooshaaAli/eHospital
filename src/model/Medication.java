package model;

import java.util.List;

import application.DBHandler;
import javafx.collections.ObservableList;

public class Medication {
	private int medicationId;
	private String medicineName; 
	private int dosage; 
	private DBHandler dbhandler;
	
	public Medication() {
		dbhandler = new DBHandler();
	}
	
	public Medication(int id, String name, int dosage) {
		dbhandler = new DBHandler();
		this.medicationId = id;
		this.medicineName = name;
		this.dosage = dosage;
	}
		
	public int getDosage() {
		return dosage;
	}
	public void setDosage(int dosage) {
		this.dosage = dosage;
	}
	public String getMedicineName() {
		return medicineName;
	}
	
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	
	public void addPrescribeMedication(String medName,int dosage,int pid)
	{
		dbhandler.addPrescribeMedication(medName,dosage, pid);
	}
	
	public int getMedicationId() {
		return medicationId;
	}


	public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}
	public ObservableList<String> getMedications(int rpid) {
		DBHandler db=new DBHandler();
				
		        ObservableList<String> medicationList = db.getMedications(rpid);


		        return medicationList;    
		        }
			
	public ObservableList<String> getMedications() {
				DBHandler db=new DBHandler();
				
		        ObservableList<String> medicationList = db.getMedications();;


		        return medicationList;
		    }
			
	public void EnterMedicationDetails(int pid,String medicationName,int dosage)
	{
		DBHandler db=new DBHandler();
		db.EnterMedicationDetails(pid,medicationName,dosage);
		
	}
	public void EnterMedicationDetails2(int pid,String medicationName,int dosage)
	{
		DBHandler db=new DBHandler();
		db.EnterMedicationDetails2(pid,medicationName,dosage);
		
	}
			
	public List<String> GetMedicationDetails(int pid) {
	        DBHandler db = new DBHandler();

	        // Get the list of medication details
	        List<String> medicationDetails = db.FindMedicationDetails(pid);

	        // Check if the list is empty
	        if (medicationDetails.isEmpty()) {
	            System.out.println("No medication details found for PID: " + pid);
	        } else {
	            System.out.println("Medication Details for PID " + pid + ":");
	            medicationDetails.forEach(System.out::println);
	        }
	        
			return medicationDetails;
	    }
	
	public ObservableList<Medication> showExistingMedication(int pid)
	{
		ObservableList<Medication> list=dbhandler.showExistingMedication(pid);
		return list;
	}
	
}