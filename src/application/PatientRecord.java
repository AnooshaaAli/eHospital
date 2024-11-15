package application;

import java.util.List;

public class PatientRecord {
	private String temperature;
	private String bloodPressure;
	private String heartRate;
	private List<Medication> medicine;
    private List<Appointment> appointments;
    private Bill bills;
	public List<Medication> getMedicine() {
		return medicine;
	}
	public void setMedicine(List<Medication> medicine) {
		this.medicine = medicine;
	}
//	public List<Appointment> getAppointments() {
//		return appointments;
//	}
//	public void setAppointments(List<Appointment> appointments) {
//		this.appointments = appointments;
//	}
//	public Bill getBills() {
//		return bills;
//	}
//	public void setBills(Bill bills) {
//		this.bills = bills;
//	}
}