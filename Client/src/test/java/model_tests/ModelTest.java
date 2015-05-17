package model_tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Model;
import model.User;

import org.junit.Test;

public class ModelTest {

	@Test
	public void getUsersTest() {
		Model model = new Model();
		ArrayList<User> list = new ArrayList<>();
		User user = new User("Test");
		list.add(user);
		model.addUser(user);
		assertTrue(list.equals(model.getUsers()));	
	}
	
	@Test
	public void onlineUsersTest(){
		Model model = new Model();
		ArrayList<User> testList = new ArrayList<User>();
		User user = new User("Test");
		testList.add(user);
		model.setOnlineUsers(testList);
		assertTrue(testList.equals(model.getOnlineUsers()));		
	}
	
	@Test
	public void getCellRendererTest(){
		Model model = new Model();
		assertTrue(!model.getCellRenderer().equals(null));
	}
		
}
