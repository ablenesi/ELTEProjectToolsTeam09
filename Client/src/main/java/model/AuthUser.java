package model;

/**
 * Stores data of the authenticated user.
 */
public class AuthUser {
	private String name;
	private String token;
	
	public AuthUser(String userName, String token) {
		super();
		this.name = userName;
		this.token = token;
	}
	
	public String getUserName() {
		return name;
	}
	public void setUserName(String userName) {
		this.name = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
