package controller;

import java.util.ArrayList;
import java.util.List;

import controller.auth.LoginController;
import controller.auth.RegisterController;
import model.User;
import view.MainFrame;

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
		mainFrame.showRightPanel("LOGIN");
	}
	
	public void loadRegister(){
		mainFrame.showRightPanel("REGISTER");
	}
	
	public void loadWelcome(){
		mainFrame.showLeftPanel("WELCOME");
	}
	
	public void loadUserBoard(){		
		mainFrame.showRightPanel("USER_BOARD");
	}
	
	public void loadMessageBoard(User user){
		mainFrame.showLeftPanel("MESSAGE_BOARD");		
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
