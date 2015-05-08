package model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Model {
	public static final String USER = "user";
	
	private List<User> users;
	private AuthUser authUser;
	private PropertyChangeSupport propertyChangeSupport;
	
	public Model() {
		users = new ArrayList<User>();
		authUser = new AuthUser(null, null);
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;		
	}

	public List<User> getUsers() {
		return users;
	}

	public AuthUser getAuthUser() {
		return authUser;
	}
	
	public void addUser(User user){
		users.add(user);
		getPropertyChangeSupport().firePropertyChange(USER, null, user);
	}
	
	public void removeUser(User user){
		users.remove(user);
		getPropertyChangeSupport().firePropertyChange(USER, user, null);
	}
	
	
}
