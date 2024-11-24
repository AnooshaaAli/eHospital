package model;

import java.sql.Date;
import java.util.List;

import application.DBHandler;
import javafx.collections.ObservableList;

public class Receptionist extends Employee{
	private int receptionistId;
	private String shift;
	private static Receptionist instance;
	private DBHandler db;
	public Receptionist()
	{
		db = new DBHandler();
	}
	public void init(int id, String sh) {
		this.receptionistId=id;
		this.shift=sh;
		
    }
	public static Receptionist getInstance() 
	{
		if (instance == null) {
            instance = new Receptionist();
        }
        return instance;
	}
	
	public int loadReceptionistId(String username) {
		int id= db.loadReceptionistId(username);
		return id;
	}
	public boolean LoginReceptionist(String username,String password)
	{
		boolean check= db.LoginReceptionist(username, password);
		return check;
	}

 	public int getReceptionistId() {
		return receptionistId;
	}
	public void setReceptionistId(int receptionistId) {
		this.receptionistId = receptionistId;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public static void setInstance(Receptionist instance) {
		Receptionist.instance = instance;
	}
	public DBHandler getDb() {
		return db;
	}
	public void setDb(DBHandler db) {
		this.db = db;
	}

	// --------------------------------------------------- SARA AKBAR USE CASES ---------------------------------------------------------------- //
	
	public ObservableList<String> getReceptionistIds()
	{
		 ObservableList<String> riplist=db.getReceptionistIds();
		 return riplist;
	}
	
	public boolean AddReceptionist(int empid, List<String> shitfTime)
	{
		System.out.println(shitfTime);
    	boolean morning = false;
        boolean evening = false;
        boolean night = false;


     // Check if each day is present in the availableDays list
     if (shitfTime.contains("Morning")) {
    	 morning = true;
         System.out.println("morning is selected: " + morning);
     }
     if (shitfTime.contains("Evening")) {
    	 evening = true;
         System.out.println("evening is selected: " + evening);
     }
     if (shitfTime.contains("Night")) {
    	 night = true;
         System.out.println("night is selected: " + night);
     }
     
		boolean check=db.AddReceptionist(empid,morning,evening,night);
		return check;
	}
	
	public int GetempidFromrid(int rid)
	{
		int rids = db.GetempidFromrid(rid);
		 return rids;
	}
	//-------------------------------------------------------------LIST OF ALL RECEPTIONIST-------------------------------------------------------------//
	public ObservableList<Receptionist> listReceptionist() {
		Employee emp= new Employee();
		ObservableList<Receptionist> list= emp.listReceptionist();
		return list;
	}
}