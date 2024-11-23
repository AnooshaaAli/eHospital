package application;
import java.sql.Time;

public class TimeSlot {
	private int slotId;
	private String start_time;
	private String end_time;
	
	public int AddTimeSlot(String starttime,String endtime)
	{
		DB_Handler db=new DB_Handler();
		int tid=db.AddTimeSlot(starttime,endtime);
		return tid;
	}
}