package model_tests;

import static org.junit.Assert.*;

import javax.swing.JTextField;
import javax.swing.text.Document;
import model.User;

import org.junit.Test;

public class UserTest {

	@Test
	public void getUserNameTest(){
		String name = "testUser";
		User user = new User(name);
		assertTrue(name.equals(user.getUserName()));
	}
	
	@Test
	public void setUserNameTest(){
		String name = "testUser";
		User user = new User(null);
		user.setUserName(name);
		assertTrue(name.equals(user.getUserName()));
	}
	
	@Test
	public void setActiveTest(){
		String name = "testUser";
		User user = new User(name);
		boolean test = true;
		user.setActive(test);
		assertTrue(test == user.isActive());
	}
	
	@Test
	public void setActivatedTest(){
		String name = "testUser";
		User user = new User(name);
		boolean test = true;
		user.setActivated(test);
		assertTrue(test == user.isActivated());
	}
	
	@Test
	public void setDocumentTest(){
		JTextField testField = new JTextField("test");
		Document doc= testField.getDocument();
		String name = "testUser";
		User user = new User(name);
		user.setDocumnent(doc);
		assertTrue(doc.equals(user.getDoc()));
	}

}
