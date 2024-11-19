package application;

import javafx.collections.ObservableList;

public class Medication {
	private int medicationId;
	private int dosage; 
	private String medicineName; 
	DBHandler dbhandler;
	
	Medication() {
		dbhandler = new DBHandler()
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
	
	public ObservableList<Medication> showExistingMedication(int pid)
	{
		ObservableList<Medication> list=dbhandler.showExistingMedication(pid);
		return list;
	}
	
	
}