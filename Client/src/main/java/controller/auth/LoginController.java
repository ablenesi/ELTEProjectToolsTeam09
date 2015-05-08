package controller.auth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.auth.LoginPanel;
import controller.Controller;
import model.ViewConstraints;

public class LoginController implements ActionListener{
	
	private Controller mainController;	
	
	public LoginController(Controller mainController) {		
		this.mainController = mainController;
	}		
	
	private void loginToServer(String userName, String password){
		System.out.println("I try to log in "+ userName + " " + password);
		if(mainController.getServerHandler().login(userName, password)){
			mainController.getModel().getAuthUser().setUserName(userName);
			mainController.loadRightPanel("USER_BOARD");
			mainController.getServerHandler().start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		LoginPanel loginPanel = (LoginPanel)source.getParent();
		System.out.println(source.getText());
		switch (source.getText()) {
		case ViewConstraints.LOGIN_BUTTON_TEXT:
			loginToServer(loginPanel.getUserName(), loginPanel.getPassword());
			break;		
		case ViewConstraints.REGISTER_BUTTON_TEXT:
			mainController.loadRightPanel("REGISTER");
			break;
		}
		
	}
	
}
