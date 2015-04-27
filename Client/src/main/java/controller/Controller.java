package controller;


import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import model.User;
import view.LoginPanel;
import view.MainFrame;
import view.RegisterPanel;

public class Controller {
	private MainFrame mainFrame;	
	
	private LoginController loginController;
	private RegisterController registerController;
	private UserBoardController userBoardController;
	
	private List<User> users;
	
	public Controller() {
		loginController = new LoginController(this);
		registerController = new RegisterController(this);
		userBoardController = new UserBoardController(this);
		
		users = new ArrayList<User>();
		/* J U S T  T E S T */
		users.add(new User("Peti"));
		users.add(new User("Lai"));
		users.add(new User("JANI"));
	}
	
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void loadLogin(){		
		mainFrame.show("LOGIN");
	}
	
	public void loadRegister(){
		mainFrame.show("REGISTER");
	}
	
	public void loadUserBoard(){		
		mainFrame.show("USER_BOARD");
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public RegisterController getRegisterController() {
		return registerController;
	}
	
	public UserBoardController getUserBoardController() {
		return userBoardController;
	}
	
	public List<User> getUsers() {
		return users;
	}			
	
}
