package application;

import java.sql.Date;

public class Receptionist extends Employee{
	private int receptionistId;
	private String shift;
	private static Receptionist instance;
	private DBHandler db;
	Receptionist()
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
	
	


}
