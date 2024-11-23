package application;

import java.util.List;

import javafx.collections.ObservableList;

public class Admin {
	Admin(){
		
	}
  
	public boolean LoginAdmin(String username,String password)
	{
		DB_Handler db=new DB_Handler();
		boolean check=db.loginAdmin(username,password);
		return check;
	}
	
	   public ObservableList<String> getDoctorIds()
	   {
			DB_Handler db=new DB_Handler();
			//System.out.println("dance: " );
	        ObservableList<String> didList = db.getDoctorIds();
	        //System.out.println("youu dance");
	        return didList;

	    	 
	   }
	    
	   public boolean AddDoctor(String name,String username,String password,String gender,int experience,String contact,List<String> availableDays,String starttime,String endtime,int workingHours)
	    {
		   Employee e=new Employee();
		  int empid = e.AddEmployee(name,username,password,gender,experience,contact,workingHours);
		  if(empid==-1)
		  {
			  return false;
		  }
		  int noOfApp=0;
		  System.out.println("empid" +empid);
	        Doctor d=new Doctor();
	       int did= d.AddDoctor(empid,availableDays,noOfApp);
	       if(did==-1)
			  {
				  return false;
			  }
	       System.out.println("did" +did);
	        TimeSlot t=new TimeSlot();
	        int tid=t.AddTimeSlot(starttime,endtime);
	        if(tid==-1)
			  {
				  return false;
			  }
	        System.out.println("tid" +tid);
	        DoctorTimeslot dt=new DoctorTimeslot();
	       boolean check= dt.AddTimeSlot(did,starttime,endtime);
		return check;
	        
	    }

	   public void deleteDctor(int did)
	   {
		   DB_Handler db=new DB_Handler();
		   db.deleteDoctor(did);
	   }

	public void updateNameEmployee(int did, String name) {
		DB_Handler db=new DB_Handler();
		db.updateNameDoctor(did,name);
	}

	public void updatePasswordEmployee(int did, String password) {
		DB_Handler db=new DB_Handler();
		db.updatePasswordDoctor(did,password);
	}

	public void updateUserNameEmployee(int did, String username) {
		DB_Handler db=new DB_Handler();
		db.updateUserNameDoctor(did,username);
	}

	public void updateworkingHourseEmployee(int did,int empid, String selectedStartTime, String selectedEndTime, int workingHours) {
		DB_Handler db=new DB_Handler();
		db.updateWorkingHoursDoctor(did,empid,selectedStartTime,selectedEndTime,workingHours);

	}
	public void updateContactEmployee(int did, String contact) {
		DB_Handler db=new DB_Handler();
		db.updateContactDoctor(did,contact);
	}

	public void updateAvailabeDaysDoctor(int did, List<String> availableDays) {
		DB_Handler db=new DB_Handler();
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
		DB_Handler db=new DB_Handler();
		db.updateGenderDoctor(did,gender);
	}

	public void updateExperienceEmployee(int did, int experienceInt) {
		DB_Handler db=new DB_Handler();
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
		DB_Handler db=new DB_Handler();
		db.DeleteNurse(nid);
	}
	
	
	public int GetnidFromDid(int nid)
	{
		Nurse n=new Nurse();
		int nidList=n.GetnidFromDid(nid);
		return nidList;
	}

	public void updateShiftTimeNurse(int nid, List<String> ShiftTime) {
		DB_Handler db=new DB_Handler();
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
		DB_Handler db=new DB_Handler();
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
		DB_Handler db=new DB_Handler();
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
		DB_Handler db=new DB_Handler();
		db.updateworkingHourseReceptionist(empid,workingHours);	
	}
	
	public void updateShiftTimeReceptionist(int rid,List<String>  ShiftTime)
	{
		DB_Handler db=new DB_Handler();
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
		 DB_Handler db=new DB_Handler();
		 boolean check=db.isUsernameTaken(username);
		 return check;
	 }
}
