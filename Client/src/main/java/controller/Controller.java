package controller;

import communication.ServerHandler;
import controller.auth.LoginController;
import controller.auth.RegisterController;
import model.Model;
import model.User;
import view.MainFrame;

public class Controller {
	private MainFrame mainFrame;
	private ServerHandler serverHandel;
	
	private LoginController loginController;
	private RegisterController registerController;
	private UserBoardController userBoardController;
	private MessageBoardController messageBoardController;
	
	private Model model;
	
	public Controller() {
		loginController = new LoginController(this);
		registerController = new RegisterController(this);
		userBoardController = new UserBoardController(this);
		messageBoardController = new MessageBoardController(this);
		model = new Model();
		model.addUser(new User("Public Chat"));
	}	
	
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;		
		serverHandel = mainFrame.getServerHandler();		
		if(serverHandel.connect()){
			loadRightPanel("LOGIN");
	        mainFrame.pack();
	        mainFrame.setVisible(true);
		}
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
	
	public MainFrame getMainFrame(){
		return mainFrame;
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
	
	public ServerHandler getServerHandler() {
		return serverHandel;
	}
	
	public Model getModel() {
		return model;
	}	
}
