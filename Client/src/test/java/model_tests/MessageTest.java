package model_tests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import model.Message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MessageTest {

	@Test
	public void getUserNameTest() {
		String name = "testName";
		Message msg = new Message(name,null);
		assertTrue(name.equals(msg.getUserName()));
	}
	
	@Test
	public void setUserNameTest() {
		String name = "testName";
		Message msg = new Message(null,null);
		msg.setUserName(name);
		assertTrue(name.equals(msg.getUserName()));
	}
	
	@Test
	public void getMessageTest() {
		String test = "testMsg";
		Message msg = new Message(null,test);
		assertTrue(test.equals(msg.getMessage()));
	}
	
	@Test
	public void setMessageTest() {
		String test = "testMsg";
		Message msg = new Message(null,null);
		msg.setMessage(test);
		assertTrue(test.equals(msg.getMessage()));
	}
	
	@Mock Document doc;
	
	@Test
	public void printMessageTest() throws BadLocationException{
		Message msg = new Message("Test","Test");
		msg.printMessage(doc);
		Mockito.verify(doc, times(2)).insertString(any(Integer.class),any(String.class), any(AttributeSet.class));
	}
	
}
