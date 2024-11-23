package application;

import java.util.List;

import javafx.collections.ObservableList;

public class Nurse extends Employee{
	private int nurseId;
	private String Morning;
	private String Evening;
	private String Night;
	
	
	public boolean AddNurse(int empid, List<String> shitfTime)
	{
		DB_Handler db= new DB_Handler();
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
		DB_Handler db=new DB_Handler();
		
        ObservableList<String> nidList = db.getNurseIds();

        return nidList;
	}
	
	public int GetnidFromDid(int nid)
	{
		DB_Handler db=new DB_Handler();
		int nidList=db.GetnidFromDid(nid);
		return nidList;
	}

}
