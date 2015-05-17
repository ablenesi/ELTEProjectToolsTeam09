package controller.auth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.auth.RegisterPanel;
import controller.Controller;
import model.ViewConstraints;

/**
 * Controls the Register panel. 
 * Starts registration if input data is valid.
 * Or changes to register panel.
 */
public class RegisterController implements ActionListener{
	private Controller mainController;
	
	public RegisterController(Controller mainController) {
		this.mainController = mainController;
	}
	
	/**
	 * Starts the registration process via ServerHandler.register(). 
	 * @param userName
	 * @param email
	 * @param password
	 * @param rePassword
	 */
	private void registerToServer(String userName, String email, String password, String rePassword){
		
		System.out.println("I try to register "+ userName +" " + email+ " "+ password + " "+ rePassword);
		mainController.getServerHandler().register(userName, password, email);
		
	}
	
	
	/**
	 * Handles Button presses on Register panel
	 */
	public void actionPerformed(ActionEvent e) {
		
		JButton source = (JButton) e.getSource();
		RegisterPanel registerPanel = (RegisterPanel) source.getParent();
		
		switch (source.getText()) {
		case ViewConstraints.REGISTER_BUTTON_TEXT:
			// Validate and register
			validateInputAdnRegister(registerPanel);
			break;
			
		case ViewConstraints.LOGIN_BUTTON_TEXT:
			
			// Switch to LoginPanel
			mainController.loadRightPanel("LOGIN");
			break;
			
		}
	}

	/**
	 * Validates the input and registers the user if input is vaild.
	 * @param registerPanel
	 */
	private void validateInputAdnRegister(RegisterPanel registerPanel) {

		// Validation
		if(isPasswordEligible(registerPanel.getPassword(),registerPanel.getRePassword()) && isEmailEligible(registerPanel.getEmail())){
			// Everything was OK
			registerToServer(registerPanel.getUsername(), registerPanel.getEmail(), registerPanel.getPassword(), registerPanel.getRePassword());
			
		}else if(!isEmailEligible(registerPanel.getEmail())){
			
			// Email is not valid
			JOptionPane.showMessageDialog(new JFrame("Error"),"Make sure you gave a valid E-mail address!");
			
		}else{

			// Password is not valid
			if(registerPanel.getPassword().equals(registerPanel.getRePassword())){
				
				// Password format invalid 
				JOptionPane.showMessageDialog(new JFrame("Error"),"Password can contain only english characters, numbers and _.\nIt has to contain at least: \none uppercase character,\none lowercase character,\na number,\nand has to be between 6 and 12 characters.");
				
			}else{

				// Passwords doesn't match
				JOptionPane.showMessageDialog(new JFrame("Error"),"Passwords doesn't match!");
				
			}
		}
	}
	

	/**
	 * Validates and compares the passwords from the input.
	 * @param pass
	 * @param rePass
	 * @return True if password is valid.
	 */
	private boolean isPasswordEligible(String pass, String rePass){
		
		return (!pass.equals(pass.toLowerCase()) &&
				!pass.equals(pass.toUpperCase()) &&  		//Check if it has lower and upper case characters too
				pass.length()>=6 && pass.length() <=  12 && //Check if it's between 6 and 12 characters long
				pass.matches(".*\\d+.*") && 				//Check if it contains number
				pass.matches("\\w+") && 					//Check if it contains only numbers, English characters or underscore
				pass.equals(rePass)); 						//Check if it is equal to the password the user gave the second time
	
	}
	
	/**
	 * Validates the email from the input
	 * @param email
	 * @return True if email is valid.
	 */
	private boolean isEmailEligible(String email){
		
		String tmpStr0[] = email.split("@");
		
		if(tmpStr0.length == 2){
			String tmpStr1[] = tmpStr0[1].split("\\.");
			return (tmpStr0[0].matches("\\w+") && tmpStr1[0].matches("\\w+") && 
					tmpStr1[1].matches("\\w+") && 					//Contains only English characters OR numbers
					tmpStr0.length == 2 && tmpStr1.length == 2);	//Check if the format of the password is eligible (name@domain.suffix)
		}else{
		
			return false;
		}
	}
	
}
