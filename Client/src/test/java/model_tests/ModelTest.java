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
}
