package controller.auth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.auth.LoginPanel;
import controller.Controller;
import model.ViewConstraints;

/**
 * Controls the Login panel, starts login or changes to register panel.
 */
public class LoginController implements ActionListener{
	
	private Controller mainController;	
	
	public LoginController(Controller mainController) {		
		this.mainController = mainController;
	}		
	
	/**
	 * Starts the login process via ServerHandler.login().
	 * if login is successful: Loads the User board and
	 * Starts the ServerHandler SwingWorker for getting data from the server. 
	 * @param userName
	 * @param password
	 */
	private void loginToServer(String userName, String password){
		System.out.println("I try to log in "+ userName + " " + password);
		if(mainController.getServerHandler().login(userName, password)){
			mainController.getModel().getAuthUser().setUserName(userName);
			mainController.loadRightPanel("USER_BOARD");
			mainController.getServerHandler().execute();
		}
	}

	/**
	 * Handles Button presses on Login panel
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		LoginPanel loginPanel = (LoginPanel)source.getParent();
		
		switch (source.getText()) {
		case ViewConstraints.LOGIN_BUTTON_TEXT:
			
			// Login
			loginToServer(loginPanel.getUserName(), loginPanel.getPassword());
			break;	
			
		case ViewConstraints.REGISTER_BUTTON_TEXT:
			
			// Switch to RegiterPanel
			mainController.loadRightPanel("REGISTER");
			break;
		}
		
	}
	
}
