package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.ViewConstraints;

public class LoginController implements ActionListener{
	
	private Controller mainController;	
	
	public LoginController(Controller mainController) {		
		this.mainController = mainController;
	}		
	
	private void loginToServer(){
		System.out.println("I try to log in");
		mainController.loadUserBoard();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		System.out.println(source.getText());
		switch (source.getText()) {
		case ViewConstraints.LOGIN_BUTTON:
			loginToServer();
			break;		
		case ViewConstraints.REGISTER_BUTTON:
			mainController.loadRegister();
			break;
		}
		
	}
	
}
