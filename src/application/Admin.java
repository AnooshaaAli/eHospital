package application;

public class Admin {
	private int adminId;
	private String adminName;
	private String username;
	private String password;
	private DBHandler db;
	
	Admin()
	{
		db=new DBHandler();
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
}