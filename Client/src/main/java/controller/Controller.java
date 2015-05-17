package controller;

import communication.ServerHandler;
import controller.auth.LoginController;
import controller.auth.RegisterController;
import model.Model;
import model.User;
import model.ViewConstraints;
import view.MainFrame;

/**
 * Stands between the ServerHandler and the Views.
 * Controllers the user events.
 * Refreshes the UI if necessary.
 */
public class Controller {
	
	private MainFrame mainFrame;
	private ServerHandler serverHandel;
	
	// Panel Controllers
	private LoginController loginController;
	private RegisterController registerController;
	private UserBoardController userBoardController;
	private MessageBoardController messageBoardController;
	
	// Data model
	private Model model;
	
	/**
	 * Creates the Panel controllers, the data model and adds the Public Chat.
	 */
	public Controller() {
		loginController = new LoginController(this);
		registerController = new RegisterController(this);
		userBoardController = new UserBoardController(this);
		messageBoardController = new MessageBoardController(this);
		model = new Model();
		
		// Adding Public Chat
		model.addUser(new User(ViewConstraints.PUBIC_USER_NAME));
	}	
	
	/**
	 * After setting the MainFrame connects to the server.
	 * @param mainFrame Can't be null.
	 */
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;		
		serverHandel = mainFrame.getServerHandler();		
		
		// Connecting to server
		if(serverHandel.connect()){
			loadRightPanel("LOGIN");
	        mainFrame.pack();
	        mainFrame.setVisible(true);
		}
	}
	
	/**
	 * 
	 * @param panel
	 */
	public void loadRightPanel(String panel){		
		mainFrame.showRightPanel(panel);
	}
	
	/**
	 * On the left side of the window shows the selected users messageBoard.
	 * Sets the user activated if it was not already.
	 * @param user
	 */
	public void loadMessageBoard(User user){
		if (user.isActivated()){			

			mainFrame.showMessageBoard(user);			
		}else{
			
			user.setActivated(true);
			mainFrame.addMessageBoard(user, messageBoardController);
			mainFrame.showMessageBoard(user);
		}			
	}

	/**
	 * On the Left side of the window shows the Welcoming initial panel.
	 */
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
