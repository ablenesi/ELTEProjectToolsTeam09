package model_tests;

import static org.junit.Assert.*;
import model.AuthUser;

import org.junit.Assert;
import org.junit.Test;

public class AuthUserTest {
	
	@Test
	public void getAuthUserNameTest(){
		String name = "Test Name";
		AuthUser user = new AuthUser(name, null);
		assertTrue(name.equals(user.getUserName()));
	}

	@Test
	public void setAuthUserNameTest(){
		String name = "Test Name";
		AuthUser user = new AuthUser(null, null);
		user.setUserName(name);
		assertTrue(name.equals(user.getUserName()));
	}
	
	@Test
	public void getAuthUserTokenTest(){
		String token = "Test Token";
		AuthUser user = new AuthUser(null, token);
		assertTrue(token.equals(user.getToken()));
	}

	@Test
	public void setAuthUserTokenTest(){
		String token = "Test Token";
		AuthUser user = new AuthUser(null, null);
		user.setToken(token);
		assertTrue(token.equals(user.getToken()));
	}
	
}
