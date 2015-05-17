package view.auth;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



import model.ViewConstraints;
import controller.auth.LoginController;
/**
 * This represents the interface where users can log in
 */
public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// UI components
	private JLabel singInLabel;
	private JLabel usernameLable;
	private JLabel passwordLable;
	
	private JTextField usernameFiled;
	private JPasswordField passwordFiled;
	
	private JButton loginButton;
	private JButton registerButton;
	
	public LoginPanel(LoginController controller){				
		initializeComponents(controller);
		addComponents();
	}
	
	/**
	 * Initializes all components of the LoginPanel
	 * @param controller
	 */
	private void initializeComponents(LoginController loginController){				
		singInLabel = new JLabel("Sing in");
		usernameLable = new JLabel("Email or username:");
		passwordLable = new JLabel("Password:");
		
		singInLabel.setFont(ViewConstraints.TITLE_LABLE_FONT);
		usernameLable.setFont(ViewConstraints.BASIC_LABLE_FONT);
		passwordLable.setFont(ViewConstraints.BASIC_LABLE_FONT);
		
		usernameFiled = new JTextField();
		passwordFiled = new JPasswordField();
		
		usernameFiled.setFont(ViewConstraints.BASIC_LABLE_FONT);
		passwordFiled.setFont(ViewConstraints.BASIC_LABLE_FONT);
		
		loginButton = new JButton(ViewConstraints.LOGIN_BUTTON_TEXT);
		registerButton = new JButton(ViewConstraints.REGISTER_BUTTON_TEXT);
		
		loginButton.addActionListener(loginController);
		registerButton.addActionListener(loginController);
				
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(ViewConstraints.RIGHT_PANEL_WIDTH,ViewConstraints.PANEL_HEIGHT));
	}
	
	/**
	 * Adds all components to the UserBoard
	 */
	private void addComponents(){
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 0.25;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		
		// Panel Title
		this.add(singInLabel, c);
		
		c.gridy = 1;
		
		this.add(usernameLable, c);
		
		c.gridy = 2;
		
		this.add(usernameFiled, c);
		
		c.gridy = 3;
		
		this.add(passwordLable, c);
		
		c.gridy = 4;
		
		this.add(passwordFiled, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.insets = new Insets(10, 0, 0, 0);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.3;
		c.gridy = 5;
		
		this.add(loginButton, c);
		
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 10;		
		c.gridy = 6;
		
		this.add(registerButton, c);		
	}
	
	public String getUserName(){
		return usernameFiled.getText();
	}
	
	public String getPassword(){
		return new String(passwordFiled.getPassword());
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}
}
