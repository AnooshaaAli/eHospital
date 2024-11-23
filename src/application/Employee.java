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
	
	public int AddEmployee(String name,String username,String password,String gender,int experience,String contact,int workingHours)
	{
		DB_Handler db= new DB_Handler();
		int empid=db.AddEmployee(name,username,password,gender,experience,contact,workingHours);
		return empid;
	}
}