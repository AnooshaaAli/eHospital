package application;

public class DoctorTimeslot {
	
	public boolean AddTimeSlot(int did,String starttime,String endtime)
	{

		DB_Handler db=new DB_Handler();
		boolean check=db.AddDoctorTimeSlot(did,starttime,endtime);
		return check;
	}
}
