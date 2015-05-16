package model;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

public class Model {
	public static final String USER = "user";
	
	private List<User> users;
	private AuthUser authUser;
	private PropertyChangeSupport propertyChangeSupport;

	private List<User> onlineUsers;	
	private ListCellRenderer<? super User> cellRenderer;	
	
	public Model() {
		users = new ArrayList<User>();
		onlineUsers = new ArrayList<User>();
		authUser = new AuthUser(null, null);
		propertyChangeSupport = new PropertyChangeSupport(this);
		cellRenderer = createListRenderer();
	}

	private ListCellRenderer<User> createListRenderer() {
		return new ListCellRenderer<User>() {			

			@Override
			public Component getListCellRendererComponent(
					JList<? extends User> list, User value, int index,
					boolean isSelected, boolean cellHasFocus) {
				JLabel label = new JLabel();
				Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
				//JLabel will be involved for this border
				Border border = BorderFactory.createLineBorder(Color.WHITE);
				label.setText("  "+value.getUserName());
				label.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));				
				label.setFont(ViewConstraints.USER_NAME_LABLE_FONT);
				if (onlineUsers.contains(value)||value.getUserName().equals("Public Chat")) {
					label.setText("  "+value.getUserName()+ "    [online]");
					label.setBackground(new Color(125, 210, 112));
					label.setOpaque(true);
				}else{
					label.setText("  "+value.getUserName()+ "    [offline]");
					label.setBackground(new Color(255, 181, 71));
					label.setOpaque(true);
				}
				if (authUser.getUserName()!= null && authUser.getUserName().equals(value.getUserName())){
					label.setText("  "+value.getUserName()+ "  [You are online]");
					label.setBackground(new Color(0, 153, 51));
					label.setOpaque(true);
				}
				if (isSelected) {				
					label.setText("  "+value.getUserName()+ "    [selected]");
					label.setBackground(new Color(77, 148, 255));
					label.setOpaque(true);
				}
				
				return label;				
			}
		};
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

	public ListCellRenderer<? super User> getCellRenderer() {
		return cellRenderer;
	}
		
}
