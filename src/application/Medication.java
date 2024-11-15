package application;

public class Medication {
	private int medicationId;
	private int dosage; 
	private String medicineName; 
	
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
	
	public void enterMedicationDetails(String name)
	{}
	public void updateMedicationDetails()
	{
		
	}
	public Medication showExistingMedicationDetails()
	{
		return null;
	}
	
	
}