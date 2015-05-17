package view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import communication.ServerHandler;

import view.auth.LoginPanel;
import view.auth.RegisterPanel;
import view.auth.WelcomePanel;
import model.User;
import model.ViewConstraints;
import controller.Controller;
import controller.MessageBoardController;

/**
 * This represents the window for the Client Chat Application
 */
public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// PANELS
	private JPanel mainPanel;		
	private JPanel rigthPanel;
	private JPanel leftPanel;
	
	// Auth panels
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private WelcomePanel welcomePanel;
	 
	private UserBoard userPanel;
	private HashMap<String,MessageBoard> messageBoards;		
	
	// ServerHanler
	private ServerHandler serverHandler;

	
	public MainFrame(Controller controller){
		serverHandler = new ServerHandler(controller);
		initializeComponents(controller);
		addComponents();
		//setResizable(false);
	}
	
	/**
	 * Initializes all components of the MainFrame
	 * @param controller
	 */
	private void initializeComponents(Controller controller){
		// layout panels
		mainPanel = new JPanel(new GridBagLayout());
		leftPanel = new JPanel(new CardLayout());
		rigthPanel = new JPanel(new CardLayout());
		
		ViewConstraints.init();	// Creates text styles for printing in Documents
		
		// set size
		leftPanel.setPreferredSize(new Dimension(ViewConstraints.LEFT_PANEL_WIDTH,ViewConstraints.PANEL_HEIGHT));
		rigthPanel.setPreferredSize(new Dimension(ViewConstraints.RIGHT_PANEL_WIDTH,ViewConstraints.PANEL_HEIGHT));
		
		setContentPane(mainPanel);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(ViewConstraints.TITLE);
		
		// Create panels, set controllers
		loginPanel = new LoginPanel(controller.getLoginController());
		registerPanel = new RegisterPanel(controller.getRegisterController());
		welcomePanel = new WelcomePanel();
		userPanel = new UserBoard(controller.getModel(), controller.getUserBoardController());
		messageBoards = new HashMap<String, MessageBoard>();		
	}
	
	/**
	 * Adds all components to the MainFrame
	 */
	private void addComponents(){
		GridBagConstraints c = new GridBagConstraints();

		// populate card layouts
		addToLeftPanel(welcomePanel, "WELCOME");				
		addToRigthPanel(loginPanel, "LOGIN");
		addToRigthPanel(registerPanel, "REGISTER");
		addToRigthPanel(userPanel, "USER_BOARD");
		
		// add Components
		c.weightx = 100;		
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20,20,20,20);
		
		// left panel
		mainPanel.add(leftPanel,c);
		
		c.weightx = 1;
		c.gridx = 1;

		//right panel
		mainPanel.add(rigthPanel,c);
	}

	/**
	 * Creates and adds a new MessageBoard to messageBorads map and leftPanel
	 * @param user
	 * @param controller
	 */
	public void addMessageBoard(User user, MessageBoardController controller){
		MessageBoard m = new MessageBoard(user,controller);
		user.setDocumnent(m.getDoc());
		
		messageBoards.put(user.getUserName(), m);
		addToLeftPanel(m, user.getUserName());
	}
	
	/**
	 * Shows the specified users MessageBoard
	 * @param user
	 */
	public void showMessageBoard(User user){
		showLeftPanel(user.getUserName());
	}
	
	/**
	 * Adds the Component to the rightPanels card layout.
	 * @param comp
	 * @param constraints
	 */
	public void addToRigthPanel(JPanel comp, String constraints){
		rigthPanel.add(comp, constraints);
	}

	/**
	 * Adds the Component to the leftPanels card layout.
	 * @param comp
	 * @param constraints
	 */
	public void addToLeftPanel(JPanel comp, String constraints){
		leftPanel.add(comp, constraints);
	}
	
	/**
	 * Shows the given panel on the right side
	 * @param name
	 */
	public void showRightPanel(String name) {
		CardLayout cl = (CardLayout)(rigthPanel.getLayout());
	    cl.show(rigthPanel, name);
	    this.pack();
	}
	
	/**
	 * Shows the given panel on the left side
	 * @param name
	 */
	public void showLeftPanel(String name) {
		CardLayout cl = (CardLayout)(leftPanel.getLayout());
	    cl.show(leftPanel, name);
	    this.pack();
	}
		
	public Container getContent(){
		return getContentPane();
	}

	public ServerHandler getServerHandler() {
		return serverHandler;
	}	
	
	public UserBoard getUserBoard(){
		return userPanel;
	}
}
