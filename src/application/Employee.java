package application;

public class Employee {
	private int employeeId;
	private String name;
	private String username;
	private String passowrd;
	private String gender;
	private int experience;
	private String workingHours;
	private String contact;
	private DBHandler db;
	Employee()
	{
		db= new DBHandler();
	}
	public boolean LoginDoctor(String username,String password)
	{
		Doctor a = new Doctor();
		boolean check = a.LoginDoctor(username, password);
		return check;
	}
}