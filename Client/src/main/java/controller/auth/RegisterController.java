package controller.auth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.Controller;
import model.ViewConstraints;

public class RegisterController implements ActionListener{
	private Controller mainController;
	
	public RegisterController(Controller mainController) {		
		this.mainController = mainController;
	}
		
	private void registerToServer(){
		System.out.println("I try to register");		
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		switch (source.getText()) {
		case ViewConstraints.REGISTER_BUTTON_TEXT:
			registerToServer();
			break;
		case ViewConstraints.LOGIN_BUTTON_TEXT:
			mainController.loadLogin();
			break;		
		}
		
	}
	
}
