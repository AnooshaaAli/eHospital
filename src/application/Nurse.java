package application;

import java.util.List;

import javafx.collections.ObservableList;

public class Nurse extends Employee{
	private int nurseId;
	private String Morning;
	private String Evening;
	private String Night;
	private DBHandler db;
	private static Nurse instance; 
	
	Nurse()
	{
		db= new DBHandler();
		
	}
	
	public boolean LoginNurse(String username, String password)
	{
		
		boolean check= db.LoginNurse(username, password);
		return check;
	}
	
	public void init(int id, String sh) {
		this.nurseId=id;
		/*
		this.Morning=mor;
		this.Evening=eve;
		this.Night=night;
		*/
    }
	
	public static Nurse getInstance() 
	{
		if (instance == null) {
            instance = new Nurse();
        }
        return instance;
	}
	
	public int loadNurseId(String username)
	{
		
		int id=db.loadNurseId(username);
		return id;
		
	}

	public int getNurseId() {
		return nurseId;
	}
	
	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}
	
	public DBHandler getDb() {
		return db;
	}
	
	public void setDb(DBHandler db) {
		this.db = db;
	}
	
	// -------------------------------------------------- SARA AKBAR USE CASES ------------------------------------------------------------- //
	
	public boolean AddNurse(int empid, List<String> shitfTime)
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
     
		boolean check=db.AddNurse(empid,morning,evening,night);
		return check;
	}
	
	public ObservableList<String> getNurseIds()
	{
        ObservableList<String> nidList = db.getNurseIds();
        return nidList;
	}
	
	public int GetnidFromDid(int nid)
	{
		int nidList = db.GetnidFromDid(nid);
		return nidList;
	}
}