package model;


public class UserData {

	private String ID;
	private String Full_Name;
	private String Email;
	private String password;
	private String privilege;
	
	UserData(String id, String fullName, String email, String password,String privilege){
		
		this.ID = new String(id);
		this.Full_Name = new String(fullName);
		this.Email = new String(email);
		this.password = new String(password);
		this.privilege = new String(privilege);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFull_Name() {
		return Full_Name;
	}

	public void setFull_Name(String full_Name) {
		Full_Name = full_Name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
}
