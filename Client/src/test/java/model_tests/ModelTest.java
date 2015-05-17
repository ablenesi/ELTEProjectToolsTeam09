package model_tests;

import static org.junit.Assert.*;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.ListCellRenderer;

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
	public void getOnlineUsersTest(){
		Model model = new Model();
		ArrayList<User> list = new ArrayList<>();
		User user = new User("Test");
		list.add(user);
		model.setOnlineUsers(list);
		assertTrue(list.equals(model.getOnlineUsers()));
	}
	
	@Test
	public void getCellRendererTest(){
		Model model = new Model();
		ListCellRenderer <? super User> cellRenderer;
		cellRenderer = model.getCellRenderer();
		assertTrue(cellRenderer.equals(model.getCellRenderer()));
	}
	
	@Test
	public void removeUserTest(){
		Model model = new Model();
		ArrayList<User> list = new ArrayList<>();
		User user = new User("Test");
		User user2 = new User("Test2");
		list.add(user2);
		model.addUser(user);
		model.addUser(user2);
		model.removeUser(user);
		assertTrue(list.equals(model.getUsers()));
	}
	
	@Test
	public void getPropertyChangeSupportTest(){
		Model model = new Model();
		PropertyChangeSupport pcs = model.getPropertyChangeSupport();
		assertTrue(pcs.equals(model.getPropertyChangeSupport()));
	}
}
