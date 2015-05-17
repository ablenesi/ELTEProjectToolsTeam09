package model;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * Stores data of a single message that can printed on to a Document.
 */
public class Message {
	private String userName;
	private String message;
	
	public Message(String userName, String message) {
		super();
		this.userName = userName;
		this.message = message;
	}
	
	/**
	 * Prints the this message to the given Document.
	 * @param doc
	 */
	public void printMessage(Document doc){
		try {
			doc.insertString(doc.getLength(), "["+userName+"]: ", ViewConstraints.NAME );
			doc.insertString(doc.getLength(), message.trim()+"\n", null );
		} catch (BadLocationException e) {
			System.err.println("Printing message to Doc:");
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
