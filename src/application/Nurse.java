package application;

public class Nurse extends Employee{
	private int nurseId;
	private String shift;
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
		this.shift=sh;
		
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
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public DBHandler getDb() {
		return db;
	}
	public void setDb(DBHandler db) {
		this.db = db;
	}
	
	
}