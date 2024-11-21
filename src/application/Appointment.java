package application;

import java.sql.Date;
import java.sql.Time;

import javafx.collections.ObservableList;

public class Appointment {
	private int appointmentId;
	private Date appointmentDate;
	private TimeSlot time;
	private boolean status;
	private DBHandler dbhandler;
	
	Appointment() {
		time = new TimeSlot();
		dbhandler = new DBHandler();
	}

	Appointment(int id, Date date, boolean status, Time startTime, Time endTime) {
		this.appointmentId = id;
		this.appointmentDate = date;
		this.status = status;
		this.time = new TimeSlot();
		this.time.setStart_time(startTime);
		this.time.setEnd_time(endTime);
		dbhandler = new DBHandler();
	}
	
	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public TimeSlot getTime() {
		return time;
	}

	public void setTime(TimeSlot time) {
		this.time = time;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public DBHandler getDbhandler() {
		return dbhandler;
	}

	public void setDbhandler(DBHandler dbhandler) {
		this.dbhandler = dbhandler;
	}
	
	
	
	// ---------------------------------------------- GET APPOINTMENTS LIST OF A PATIENT ------------------------------------------------ //
	
	public ObservableList<Appointment> getAppointments(int pid)
	{
		ObservableList<Appointment> list = dbhandler.getAppointments(pid);
		return list;
	}

}
