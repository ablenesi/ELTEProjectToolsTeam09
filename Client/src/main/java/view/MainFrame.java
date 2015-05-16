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
	private WelcomePanel welcomePanel;
	
	private UserBoard userPanel;
	private HashMap<String,MessageBoard> messageBoards;
	
	private GridBagConstraints c;	
	
	private ServerHandler serverHandler;
	
	private final static String TITLE = "Chat";
	
	public MainFrame(Controller controller){
		serverHandler = new ServerHandler(controller);
		initializeComponents(controller);
		addComponents();
		//setResizable(false);
	}
	
	private void initializeComponents(Controller controller){
		mainPanel = new JPanel(new GridBagLayout());
		leftPanel = new JPanel(new CardLayout());
		rigthPanel = new JPanel(new CardLayout());
		ViewConstraints.init();
		leftPanel.setPreferredSize(new Dimension(ViewConstraints.LEFT_PANEL_WIDTH,ViewConstraints.PANEL_HEIGHT));
		rigthPanel.setPreferredSize(new Dimension(ViewConstraints.RIGHT_PANEL_WIDTH,ViewConstraints.PANEL_HEIGHT));
		
		setContentPane(mainPanel);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
		setTitle(TITLE);
		
		loginPanel = new LoginPanel(controller.getLoginController());
		registerPanel = new RegisterPanel(controller.getRegisterController());
		welcomePanel = new WelcomePanel();

		userPanel = new UserBoard(controller.getModel(), controller.getUserBoardController());
		messageBoards = new HashMap<String, MessageBoard>();
		c = new GridBagConstraints();
	}
	
	private void addComponents(){				
		addToLeftPanel(welcomePanel, "WELCOME");		
		addToRigthPanel(loginPanel, "LOGIN");
		addToRigthPanel(registerPanel, "REGISTER");
		addToRigthPanel(userPanel, "USER_BOARD");
		c.weightx = 100;		
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20,20,20,20);
		mainPanel.add(leftPanel,c);				
		c.weightx = 1;
		c.gridx = 1;
		mainPanel.add(rigthPanel,c);
	}

	public void addMessageBoard(User user, MessageBoardController controller){
		MessageBoard m = new MessageBoard(user,controller);
		user.setDocumnent(m.getDoc());
		messageBoards.put(user.getUserName(), m);
		addToLeftPanel(m, user.getUserName());
	}
	
	public void showMessageBoard(User user){
		showLeftPanel(user.getUserName());
	}
	
	public void addToRigthPanel(JPanel comp, String constraints){
		rigthPanel.add(comp, constraints);
	}
	
	public void addToLeftPanel(JPanel comp, String constraints){
		leftPanel.add(comp, constraints);
	}
	
	public void showRightPanel(String name) {
		CardLayout cl = (CardLayout)(rigthPanel.getLayout());
	    cl.show(rigthPanel, name);
	    this.pack();
	}
	
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
