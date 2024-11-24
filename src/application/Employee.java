package application;

import javafx.collections.ObservableList;

public class Employee {
	private int employeeId;
	private String name;
	private static Employee instance; 
	private static Employee instanceNurse; 
	private static Employee instanceReceptionist; 
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
	public void init(int id, String name, String username, String pass, String gender,int exp,
			String workinghours, String con)
	{
		this.employeeId = id;
        this.name = name;
        this.username = username;
        this.passowrd = pass;
        this.gender = gender;
        this.experience = exp;
        this.workingHours = workingHours;
        this.contact = contact;
	}
	public void initNurse(int id, String name, String username, String pass, String gender,int exp,
			String workinghours, String con)
	{
		this.employeeId = id;
        this.name = name;
        this.username = username;
        this.passowrd = pass;
        this.gender = gender;
        this.experience = exp;
        this.workingHours = workinghours;
        this.contact = con;
	}
	public void initReceptionist(int id, String name, String username, String pass, String gender,int exp,
			String workinghours, String con)
	{
		this.employeeId = id;
        this.name = name;
        this.username = username;
        this.passowrd = pass;
        this.gender = gender;
        this.experience = exp;
        this.workingHours = workinghours;
        this.contact = con;
	}
	
	public static Employee getInstance() 
	{
		if (instance == null) {
            instance = new Employee();
        }
        return instance;
	}
	public static Employee getInstanceNurse() 
	{
		if (instanceNurse == null) {
			instanceNurse = new Employee();
        }
        return instanceNurse;
	}
	public static Employee getInstanceReceptionist() 
	{
		if (instanceReceptionist == null) {
			instanceReceptionist = new Employee();
        }
        return instanceReceptionist;
	}
	
	public boolean LoginDoctor(String username,String password)
	{
		Doctor a = new Doctor();
		boolean check = a.LoginDoctor(username, password);
		return check;
	}
	
	public boolean LoginReceptionist(String username, String password)
	{
		Receptionist a= new Receptionist();
		boolean check= a.LoginReceptionist(username, password);
		return check;
	}

	public boolean LoginNurse(String username, String password)
	{
		Nurse a= new Nurse();
		boolean check= a.LoginNurse(username, password);
		return check;
	}
	public String loadName(String username)
	{
		
		String data=db.loadName(username);
		
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
	public String loadGender(String username)
	{
		String data= db.loadGender(username);
		return data;
	}
	public String loadContact(String username)
	{
		String data= db.loadContact(username);
		return data;
	}
	public String loadWorkingHours(String username)
	{
		String data= db.loadWorkingHours(username);
		return data;
	}
	
 	public int loadNurseId(String username)
	{
		Nurse n= new Nurse();
		int id=n.loadNurseId(username);
		return id;
	}
	public String loadNurseName(String username)
	{
		String data="";
		return data;
	}
	
	public int loadReceptionistId(String username)
	{
		Receptionist n= new Receptionist();
		int id=n.loadReceptionistId(username);
		return id;
	}
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassowrd() {
		return passowrd;
	}
	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public DBHandler getDb() {
		return db;
	}
	public void setDb(DBHandler db) {
		this.db = db;
	}
	public static void setInstance(Employee instance) {
		Employee.instance = instance;
	}

	// ------------------------------------------------- SARA AKBAR USE CASE ----------------------------------------------------------- //
	
	public int AddEmployee(String name,String username,String password,String gender,int experience,String contact,int workingHours)
	{
		int empid = db.AddEmployee(name,username,password,gender,experience,contact,workingHours);
		return empid;
	}
	public static void setInstanceNurse(Employee instanceNurse) {
		Employee.instanceNurse = instanceNurse;
	}
	public static void setInstanceReceptionist(Employee instanceReceptionist) {
		Employee.instanceReceptionist = instanceReceptionist;
	}
	//-------------------------------------------------------------LIST OF ALL DOCTORS-------------------------------------------------------------//
	public ObservableList<Doctor> listDoctors() {
		 ObservableList<Doctor> list= db.listDoctors();
		 return list;
	}
	//-------------------------------------------------------------LIST OF ALL NURSE-------------------------------------------------------------//
		public ObservableList<Nurse> listNurse() {
			Employee emp= new Employee();
			ObservableList<Nurse> list= db.listNurse();
			return list;
	}
	//-------------------------------------------------------------LIST OF ALL RECEPTIONIST-------------------------------------------------------------//
	public ObservableList<Receptionist> listReceptionist() {
		Employee emp= new Employee();
		ObservableList<Receptionist> list= db.listReceptionist();
		return list;
	}
}