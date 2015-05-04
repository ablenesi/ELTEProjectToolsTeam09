package model;

public class User {
	private String userName;
	private boolean active;
	
	public User() {		
		active = false;
	}
	
	public User(String userName) {
		this.userName = userName;
		active = false;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
		
}
