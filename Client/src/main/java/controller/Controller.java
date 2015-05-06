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
	private MessageBoardController messageBoardController;
	
	private List<User> users;
	
	public Controller() {
		loginController = new LoginController(this);
		registerController = new RegisterController(this);
		userBoardController = new UserBoardController(this);
		messageBoardController = new MessageBoardController(this);
		users = new ArrayList<User>();
		users.add(new User("Public Chat"));
		users.add(new User("Pasd"));
	}	
	
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void loadRightPanel(String panel){		
		mainFrame.showRightPanel(panel);
	}
	
	public void loadMessageBoard(User user){
		if (user.isActivated()){
			mainFrame.showMessageBoard(user);
		}else{
			user.setActivated(true);
			mainFrame.addMessageBoard(user, messageBoardController);
			mainFrame.showMessageBoard(user);
		}			
	}

	public void loadWelcome(){
		mainFrame.showLeftPanel("WELCOME");
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
