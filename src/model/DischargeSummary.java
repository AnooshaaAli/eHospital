package model;

import java.sql.Date;
import java.time.LocalDate;

import application.DBHandler;

public class DischargeSummary {
	//private Patient patient;
	private int dischargeId;
	private String instructions;
	private Date date;	
	private DBHandler dbhandler;
	
	DischargeSummary()
	{
		dbhandler= new DBHandler();
	}
	public void createDischargePatient(String inst,LocalDate date,int id)
	{
		dbhandler.createDischargePatient(inst, date, id);
	}
}