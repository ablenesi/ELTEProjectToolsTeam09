package controller.auth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	private boolean isPasswordEligible(String pass){
		return (!pass.equals(pass.toLowerCase()) && !pass.equals(pass.toUpperCase()) && pass.length()>=6 && pass.length() <=  12 && pass.matches(".*\\d+.*"));
	}
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		RegisterPanel registerPanel = (RegisterPanel) source.getParent();
		switch (source.getText()) {
		case ViewConstraints.REGISTER_BUTTON_TEXT:
			if(isPasswordEligible(registerPanel.getPassword())){
				registerToServer(registerPanel.getUsername(), registerPanel.getEmail(), registerPanel.getPassword(), registerPanel.getRePassword());
			}else{
				JOptionPane.showMessageDialog(new JFrame("Error"),"Password has to contain at least one uppercase character, one lowercase character, a number, and has to be between 6 and 12 characters.");
			}
			break;
		case ViewConstraints.LOGIN_BUTTON_TEXT:
			mainController.loadRightPanel("LOGIN");
			break;		
		}
		
	}
	
}
