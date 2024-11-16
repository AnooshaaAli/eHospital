package application;

public class Medication {
	private int medicationId;
	private String medicineName; 
	private int dosage; 
	
	
	
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
	
	
}