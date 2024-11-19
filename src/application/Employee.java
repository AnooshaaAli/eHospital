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
	public boolean LoginNurse(String username, String password)
	{
		Nurse a= new Nurse();
		boolean check= a.LoginNurse(username, password);
		return check;
	}
	public String loadDoctorName(String username)
	{
		Doctor a= new Doctor();
		String data=a.loadDoctorName(username);
		
		return data;
	}
	public int loadDoctorId(String username)
	{
		Doctor a= new Doctor();
		int data=a.loadDoctorId(username);
		
		return data;
	}
	public int loadEmployeeId(String username)
	{
		int empid= db.loadEmployeeId(username);
		return empid;
	}
	public int loadExperience(String username)
	{
		int exp= db.loadExperience(username);
		return exp;
	}
	public String loadDoctorWorkingDays(String username)
	{
		Doctor a= new Doctor();
		String data=a.loadDoctorWorkingDays(username);
		return data;
	}

}
