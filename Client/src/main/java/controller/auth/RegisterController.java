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
	private boolean isPasswordEligible(String pass, String rePass){
		return (!pass.equals(pass.toLowerCase()) &&
				!pass.equals(pass.toUpperCase()) &&  //Check if it has lower and uppercase characters too
				pass.length()>=6 && pass.length() <=  12 && //Check if it's between 6 and 12 characters long
				pass.matches(".*\\d+.*") && //Check if it contains number
				pass.matches("\\w+") && //Check if it contains only numbers, english characters or underscore
				pass.equals(rePass)); //&& //Check if it is equal to the password the user gave the second time
				//pass.matches("[a-z[0-9]]"));  //Contains only english characters OR numbers
	}
	private boolean isEmailEligible(String email){
		String tmpStr0[] = email.split("@");
		String tmpStr1[] = tmpStr0[1].split("\\.");
		System.out.println(tmpStr0.length+" "+tmpStr1.length+tmpStr0[0]);
		return (tmpStr0[0].matches("\\w+") && tmpStr1[0].matches("\\w+") && tmpStr1[1].matches("\\w+") && //Contains only english characters OR numbers
				tmpStr0.length == 2 && tmpStr1.length == 2); //Check if the format of the password is eligible (name@domain.suffix)	  
	}
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		RegisterPanel registerPanel = (RegisterPanel) source.getParent();
		switch (source.getText()) {
		case ViewConstraints.REGISTER_BUTTON_TEXT:
			if(isPasswordEligible(registerPanel.getPassword(),registerPanel.getRePassword()) && isEmailEligible(registerPanel.getEmail())){
				registerToServer(registerPanel.getUsername(), registerPanel.getEmail(), registerPanel.getPassword(), registerPanel.getRePassword());
			}else{
				JOptionPane.showMessageDialog(new JFrame("Error"),"Password can contain only english characters, numbers and _.\nIt has to contain at least: \none uppercase character,\none lowercase character,\na number,\nand has to be between 6 and 12 characters.\nMake sure you gave a valid e-mail address!");
			}
			break;
		case ViewConstraints.LOGIN_BUTTON_TEXT:
			mainController.loadRightPanel("LOGIN");
			break;		
		}
		
	}
	
}
