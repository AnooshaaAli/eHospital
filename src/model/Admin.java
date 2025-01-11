package model;
import java.util.List;
import application.DBHandler;
import javafx.collections.ObservableList;

public class Admin {
	
	private int adminId;
	private String adminName;
	private String username;
	private String password;
	private DBHandler db;
	private static Admin instance; 
	
	public Admin()
	{
		db = new DBHandler();
	}
	
	public void init(int id, String nm, String usr, String pass) {
		this.adminId=id;
		this.adminName=nm;
		this.username=usr;
		this.password=pass;
		
    }
	public static Admin getInstance() 
	{
		if (instance == null) 
            instance = new Admin();
        
        return instance;
	}

	public boolean LoginAdmin(String username,String password)
	{
		boolean check=db.LoginAdmin(username,password);
		return check;
	}
	public String loadAdminName(String username) {
		String name=db.loadAdminName(username);
		return name;
	}
	public int loadAdminId(String username)
	 {
		 int id=db.loadAdminId(username);
		 return id;
	 }
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DBHandler getDb() {
		return db;
	}
	public void setDb(DBHandler db) {
		this.db = db;
	}
	public static void setInstance(Admin instance) {
		Admin.instance = instance;
	}
	
   public ObservableList<String> getDoctorIds()
   {
		//System.out.println("dance: " );
		ObservableList<String> didList = db.getDoctorIds();
		//System.out.println("youu dance");
		return didList;
   }
	    
   public boolean AddDoctor(String name, String username, String password, String gender, int experience, String contact,
   		List<String> availableDays, String starttime, String endtime, int workingHours) {
		// Add the employee
		Employee e = new Employee();
		int empid = e.AddEmployee(name, username, password, gender, experience, contact, workingHours);
		if (empid == -1) {
		return false; // Employee addition failed
		}
		
		int noOfApp = 0;
		System.out.println("empid: " + empid);
		
		Doctor d = new Doctor();
		int did = d.AddDoctor(empid, availableDays, noOfApp);
		if (did == -1) {
		return false; 
		}
		System.out.println("did: " + did);
		
		// Generate time slots
		TimeSlot t = new TimeSlot();
		List<Integer> tids = t.AddTimeSlots(starttime, endtime);
		if (tids.isEmpty()) {
		return false; // No time slots generated
		}
		System.out.println("Generated tids: " + tids);
		
		for (int tid : tids) {
		    if (!db.AddTimeSlot(did, tid)) { // Correct method signature
		        return false; // Time slot assignment failed
		    }
		}

		return true; 
}

   public void deleteDctor(int did)
   {
	   db.deleteDoctor(did);
   }

	public void updateNameEmployee(int did, String name) {
		db.updateNameDoctor(did,name);
	}

	public void updatePasswordEmployee(int did, String password) {
		db.updatePasswordDoctor(did,password);
	}

	public void updateUserNameEmployee(int did, String username) {
		db.updateUserNameDoctor(did,username);
	}

	public void updateworkingHourseEmployee(int did,int empid, String selectedStartTime, String selectedEndTime, int workingHours) {
		db.updateWorkingHoursDoctor(did,empid,selectedStartTime,selectedEndTime,workingHours);

	}
	public void updateContactEmployee(int did, String contact) {
		db.updateContactDoctor(did,contact);
	}

	public void updateAvailabeDaysDoctor(int did, List<String> availableDays) {
    	System.out.println(availableDays);
    	boolean isMonday = false;
        boolean isTuesday = false;
        boolean isWednesday = false;
        boolean isThursday = false;
        boolean isFriday = false;
        boolean isSaturday = false;
     if (availableDays.contains("Monday")) {
    	 isMonday = true;
         System.out.println("Monday is selected: " + isMonday);
     }
     if (availableDays.contains("Tuesday")) {
    	 isTuesday = true;
         System.out.println("Tuesday is selected: " + isTuesday);
     }
     if (availableDays.contains("Wednesday")) {
    	 isWednesday = true;
         System.out.println("Wednesday is selected: " + isWednesday);
     }
     if (availableDays.contains("Thursday")) {
    	 isThursday = true;
         System.out.println("Thursday is selected: " + isThursday);
     }
     if (availableDays.contains("Friday")) {
    	 isFriday = true;
         System.out.println("Friday is selected: " + isFriday);
     }
     if (availableDays.contains("Saturday")) {
    	 isSaturday = true;
         System.out.println("Saturday is selected: " + isSaturday);
     }
		 System.out.println("Monday: " + isMonday);
	     System.out.println("Tuesday: " + isTuesday);
	     System.out.println("Wednesday: " + isWednesday);
	     System.out.println("Thursday: " + isThursday);
	     System.out.println("Friday: " + isFriday);
	     System.out.println("Saturday: " + isSaturday);
		db.updateAvailableDaysDoctor(did,isMonday,isTuesday,isWednesday,isThursday,isFriday,isSaturday);
	}

	public void updateGenderEmployee(int did, String gender) {
		db.updateGenderDoctor(did,gender);
	}

	public void updateExperienceEmployee(int did, int experienceInt) {
		db.updateExperienceDoctor(did,experienceInt);
	}
	
	public boolean AddNurse(String name,String username,String password,String gender,int experience,String contact,List<String> ShitfTime,int workingHours)
    {
	  Employee e=new Employee();
	  int empid = e.AddEmployee(name,username,password,gender,experience,contact,workingHours);
	  if(empid==-1)
	  {
		  return false;
	  }
	  System.out.println("empid" +empid);
      Nurse n=new Nurse();
     boolean check= n.AddNurse(empid,ShitfTime); 
      return check;
    }
	
	public void DeleteNurse(int nid)
	{
		db.DeleteNurse(nid);
	}
	
	
	public int GetnidFromDid(int nid)
	{
		Nurse n=new Nurse();
		int nidList=n.GetnidFromDid(nid);
		return nidList;
	}

	public void updateShiftTimeNurse(int nid, List<String> ShiftTime) {
    	System.out.println(ShiftTime);
    	boolean morning=false;
        boolean evening = false;
        boolean night = false;
     if (ShiftTime.contains("morning")) {
    	 morning = true;
         System.out.println("Monday is selected: " + morning);
     }
     if (ShiftTime.contains("evening")) {
    	 evening = true;
         System.out.println("Tuesday is selected: " + evening);
     }
     if (ShiftTime.contains("night")) {
    	 night = true;
         System.out.println("Wednesday is selected: " + night);
     }
     
    
		 System.out.println("morning: " + morning);
	     System.out.println("evening: " + evening);
	     System.out.println("night: " + night);
		db.updateShiftTimeNurse(nid,morning,evening,night);
	}
	
	public void updateworkingHourseNurse(int empid, int workingHours) {
		db.updateWorkingHoursNurse(empid,workingHours);

	}
	
	
	public boolean AddReceptionist(String name,String username,String password,String gender,int experience,String contact,List<String> ShitfTime,int workingHours)
	{
		 Employee e=new Employee();
		  int empid = e.AddEmployee(name,username,password,gender,experience,contact,workingHours);
		  System.out.println("empid" +empid);
		  Receptionist r =new Receptionist();
	     boolean check= r.AddReceptionist(empid,ShitfTime);    
	     return check;
	}
	
	public void deleteReceptionist(int rid)
	{
		db.DeleteReceptionist(rid);
	}
	
	public int GetempidFromrid(int rid)
	{
		Receptionist n=new Receptionist();
		int rids=n.GetempidFromrid(rid);
		return rids;
	}
	
	public void updateworkingHourseReceptionist(int empid,int  workingHours)
	{
		db.updateworkingHourseReceptionist(empid,workingHours);	
	}
	
	public void updateShiftTimeReceptionist(int rid,List<String>  ShiftTime)
	{
    	System.out.println(ShiftTime);
    	boolean morning=false;
        boolean evening = false;
        boolean night = false;
     if (ShiftTime.contains("morning")) {
    	 morning = true;
         System.out.println("Monday is selected: " + morning);
     }
     if (ShiftTime.contains("evening")) {
    	 evening = true;
         System.out.println("Tuesday is selected: " + evening);
     }
     if (ShiftTime.contains("night")) {
    	 night = true;
         System.out.println("Wednesday is selected: " + night);
     }
     
    
		 System.out.println("morning: " + morning);
	     System.out.println("evening: " + evening);
	     System.out.println("night: " + night);
		db.updateShiftTimeReceptionist(rid,morning,evening,night);
	}

	
	 public boolean isUsernameTaken(String username) 
	 {
		 boolean check=db.isUsernameTaken(username);
		 return check;
	 }
}