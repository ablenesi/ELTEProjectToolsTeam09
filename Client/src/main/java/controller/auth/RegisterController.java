package controller.auth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.ReverbType;
import javax.swing.JButton;

import view.auth.RegisterPanel;
import controller.Controller;
import model.ViewConstraints;

public class RegisterController implements ActionListener{
	private Controller mainController;
	
	public RegisterController(Controller mainController) {		
		this.mainController = mainController;
	}
		
	private void registerToServer(String userName, String email, String password, String rePassword){
		System.out.println("I try to register "+ userName +" " + email+ " "+ password + " "+ rePassword);
		mainController.getServerHandler().register(userName, password, email);
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		RegisterPanel registerPanel = (RegisterPanel) source.getParent();
		switch (source.getText()) {
		case ViewConstraints.REGISTER_BUTTON_TEXT:
			registerToServer(registerPanel.getUsername(), registerPanel.getEmail(), registerPanel.getPassword(), registerPanel.getRePassword());
			break;
		case ViewConstraints.LOGIN_BUTTON_TEXT:
			mainController.loadRightPanel("LOGIN");
			break;		
		}
		
	}
	
}
