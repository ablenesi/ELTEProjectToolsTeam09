package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

public class User {
	private String userName;
	private boolean active; 	// is the users panel currently open 
	private boolean activated; // if a panel is already open for this user
	private Document doc;		// the panels document 
	private List<Message> messages; 	
	
	public User(String userName) {
		this.userName = userName;
		messages = new ArrayList<Message>();
		active = false;
		activated = false;
	}
	
	public synchronized void addMessage(Message mess){
		messages.add(mess);		
	}
	
	/**
	 * Writes all messages to the users document if the user was activated
	 */
	public void update(){
		if(activated && doc != null){			
			for (Message message : messages) {
				message.printMessage(doc);
			}
			messages.clear();
		}
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setDocumnent(Document doc){
		this.doc = doc;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public boolean isActivated() {
		return activated;
	}

	public void setActive(boolean active) {	
		this.active = active;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public Document getDoc() {
		return doc;
	}

	@Override
    public String toString() {
        return this.userName;
    }
	
}
