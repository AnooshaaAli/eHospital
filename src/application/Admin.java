package application;

public class Admin {
	private int adminId;
	private String adminName;
	private String username;
	private String password;
	private DBHandler db;
	private static Admin instance; 
	
	Admin()
	{
		db=new DBHandler();
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
}