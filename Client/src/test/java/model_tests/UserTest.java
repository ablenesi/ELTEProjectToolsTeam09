package model_tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.swing.JTextField;
import javax.swing.text.Document;

import model.Message;
import model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
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
	
	@Mock
	  Message messageMock;
	@Mock
		Document doc;
	
	@Test
	public void updateActivatedTest(){
		User testUser = new User("Test");
		testUser.addMessage(messageMock);
		testUser.setDocumnent(doc);
		testUser.setActivated(true);
		testUser.update();
		Mockito.verify(messageMock).printMessage(doc);		
	}

	@Test
	public void updateDeActivatedTest(){
		User testUser = new User("Test");
		testUser.addMessage(messageMock);
		testUser.setDocumnent(doc);		
		testUser.update();
		Mockito.verify(messageMock, never()).printMessage(doc);		
	}
	
	@Test
	public void updateNullDocTest(){
		User testUser = new User("Test");
		testUser.addMessage(messageMock);
		testUser.setDocumnent(null);		
		testUser.update();
		Mockito.verify(messageMock, never()).printMessage(doc);		
	}
	
}
