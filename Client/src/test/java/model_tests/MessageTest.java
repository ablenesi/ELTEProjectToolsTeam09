package model_tests;

import static org.junit.Assert.*;
import model.Message;

import org.junit.Test;

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

}
