package application;

import java.util.List;

import javafx.collections.ObservableList;

public class Medication {
	private int medicationId;
	private String medicineName; 
	private int dosage; 
	private DBHandler db;
 	Medication()
	{
		db= new DBHandler();
	}
	
	
	Medication(int medicationId, String medicineName, int dosage)
	{
		this.medicationId=medicationId;
		this.dosage=dosage;
		this.medicineName=medicineName;
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
		//Medication med= new Medication();
		//med.addPrescribeMedication(medName,dosage,pid);
		db.addPrescribeMedication(medName,dosage, pid);
	}
	public ObservableList<Medication> showExistingMedication(int pid)
	{

		ObservableList<Medication> list=db.showExistingMedication(pid);
		
		return list;
	}
//	public void enterMedicationDetails(String name)
//	{}
//	public void updateMedicationDetails()
//	{
//		
//	}
//	public Medication showExistingMedicationDetails()
//	{
//		return null;
//	}


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
	
}