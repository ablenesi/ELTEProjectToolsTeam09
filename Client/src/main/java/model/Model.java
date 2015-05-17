package model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ListCellRenderer;

import view.ListRenderer;

public class Model {
	public static final String USER = "user";
	
	private List<User> users;
	private List<User> onlineUsers;	
	private AuthUser authUser;
	private PropertyChangeSupport propertyChangeSupport;

	private ListCellRenderer<? super User> cellRenderer;	
	
	public Model() {
		users = new ArrayList<User>();
		onlineUsers = new ArrayList<User>();
		authUser = new AuthUser(null, null);
		propertyChangeSupport = new PropertyChangeSupport(this);
		cellRenderer = ListRenderer.createListRenderer(this);
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

	public void setOnlineUsers(List<User> onlineUsers) {
		this.onlineUsers = onlineUsers;
	}
	
	public List<User> getOnlineUsers() {
		return onlineUsers;
	}

	public ListCellRenderer<? super User> getCellRenderer() {
		return cellRenderer;
	}
		
}
