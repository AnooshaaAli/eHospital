package application;

public class Nurse extends Employee{
	private int nurseId;
	private String shift;
	private DBHandler db;
	Nurse()
	{
		db= new DBHandler();
		
	}
	public boolean LoginNurse(String username, String password)
	{
		
		boolean check= db.LoginNurse(username, password);
		return check;
	}
	
	
}
