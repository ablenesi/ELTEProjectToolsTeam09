package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.jws.Oneway;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ViewConstraints;
import controller.Controller;
/**
 * 
 * @author Blénesi Attila
 *
 */

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;		
	private JPanel rigthPanel;
	private JPanel leftPanel;
	
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private UserBoard userPanel;
	
	private GridBagConstraints c;
	
	private final static String TITLE = "Chat";
	private final static int WIDTH = 1000;
	private final static int HEIGHT = 1000;
	
	public MainFrame(Controller controller){				
		initializeComponents(controller);
		addComponents();
	}
	
	private void initializeComponents(Controller controller){
		mainPanel = new JPanel(new GridBagLayout());
		leftPanel = new JPanel(new FlowLayout());
		rigthPanel = new JPanel(new CardLayout());
		
		rigthPanel.setPreferredSize(new Dimension(ViewConstraints.RIGHT_PANEL_WIDTH,ViewConstraints.RIGHT_PANEL_HEIGHT));
		
		setContentPane(mainPanel);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
		setTitle(TITLE);
		
		loginPanel = new LoginPanel(controller.getLoginController());
		registerPanel = new RegisterPanel(controller.getRegisterController());
		userPanel = new UserBoard(controller.getUsers(), controller.getUserBoardController());
		
		c = new GridBagConstraints();
	}
	
	private void addComponents(){
		leftPanel.setBackground(Color.red);
		addToRigthPanel(loginPanel, "LOGIN");
		addToRigthPanel(registerPanel, "REGISTER");
		addToRigthPanel(userPanel, "USER_BOARD");
		c.weightx = 100;		
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(leftPanel,c);
		
		c.insets = new Insets(60,20,20,20);
		c.weightx = 1;
		c.gridx = 1;
		mainPanel.add(rigthPanel,c);
	}

	public void addToRigthPanel(JPanel comp, String constraints){
		rigthPanel.add(comp, constraints);
	}
	
	public void addToLeftPanel(JPanel comp, String constraints){
		leftPanel.add(comp, constraints);
	}
	
	public void show(String name) {
		CardLayout cl = (CardLayout)(rigthPanel.getLayout());
	    cl.show(rigthPanel, name);
	    this.pack();
	}
	
	public Container getContent(){
		return getContentPane();
	}
	
}
