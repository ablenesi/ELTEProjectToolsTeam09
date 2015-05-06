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
import controller.auth.RegisterController;

/**
 * 
 * @author Blénesi Attila
 *
 */
public class RegisterPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel registerLabel;
	private JLabel usernameLable;
	private JLabel emailLable;
	private JLabel passwordLable;
	private JLabel rePasswordLable;
	
	private JTextField usernameFiled;
	private JTextField emailFiled;
	
	private JPasswordField passwordFiled;
	private JPasswordField rePasswordFiled;
	
	private JButton registerButton;
	private JButton loginButton;
		
	private GridBagConstraints c;
	
	public RegisterPanel(RegisterController controller) {
		initializeComponents(controller);
		addComponents();				
	}
	
	private void initializeComponents(RegisterController controller){		
		registerLabel = new JLabel("Get an acount");
		usernameLable = new JLabel("Username:");
		emailLable = new JLabel("Email:");
		passwordLable = new JLabel("Password:");
		rePasswordLable = new JLabel("Password again:");
		
		registerLabel.setFont(ViewConstraints.TITLE_LABLE_FONT);
		usernameLable.setFont(ViewConstraints.BASIC_LABLE_FONT);
		emailLable.setFont(ViewConstraints.BASIC_LABLE_FONT);
		passwordLable.setFont(ViewConstraints.BASIC_LABLE_FONT);
		rePasswordLable.setFont(ViewConstraints.BASIC_LABLE_FONT);
		
		usernameFiled = new JTextField();
		emailFiled = new JTextField();
		passwordFiled = new JPasswordField();
		rePasswordFiled = new JPasswordField();
		
		usernameFiled.setFont(ViewConstraints.BASIC_LABLE_FONT);
		emailFiled.setFont(ViewConstraints.BASIC_LABLE_FONT);
		passwordFiled.setFont(ViewConstraints.BASIC_LABLE_FONT);
		rePasswordFiled.setFont(ViewConstraints.BASIC_LABLE_FONT);
		
		registerButton = new JButton(ViewConstraints.REGISTER_BUTTON_TEXT);
		loginButton = new JButton(ViewConstraints.LOGIN_BUTTON_TEXT);
		
		registerButton.addActionListener(controller);
		loginButton.addActionListener(controller);

		c = new GridBagConstraints();		
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(ViewConstraints.RIGHT_PANEL_WIDTH,ViewConstraints.PANEL_HEIGHT));
	}
	
	private void addComponents(){
		c.weightx = 1;
		c.weighty = 0.2;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		this.add(registerLabel, c);
		
		c.gridy = 1;
		this.add(usernameLable, c);
		
		c.gridy = 2;
		this.add(usernameFiled, c);
		
		c.gridy = 3;
		this.add(emailLable, c);
		
		c.gridy = 4;
		this.add(emailFiled, c);
		
		c.gridy = 5;
		this.add(passwordLable, c);
		
		c.gridy = 6;
		this.add(passwordFiled, c);
		
		c.gridy = 7;
		this.add(rePasswordLable, c);
		
		c.gridy = 8;
		this.add(rePasswordFiled, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(10, 0, 0, 0);
		c.weightx = 0.3;
		c.gridy = 9;
		this.add(registerButton, c);
		
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 20;
		c.gridy = 9;
		this.add(loginButton, c);
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public JButton getLoginButton() {
		return loginButton;
	}
	
	public String getUsername(){
		return usernameFiled.getText();
	}
	
	public String getEmail(){
		return emailFiled.getText();
	}
	
	public String getPassword(){
		return new String(passwordFiled.getPassword());
	}
	
	public String getRePassword(){
		return new String(rePasswordFiled.getPassword());
	}
		
}

