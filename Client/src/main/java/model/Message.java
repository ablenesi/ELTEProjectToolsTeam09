package model;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Message {
	private String userName;
	private String message;
	
	public Message(String userName, String message) {
		super();
		this.userName = userName;
	}
	
	public void printMessage(Document doc){
		try {
			doc.insertString(doc.getLength(), userName, ViewConstraints.NAME );
			doc.insertString(doc.getLength(), message, null );
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
		
}
