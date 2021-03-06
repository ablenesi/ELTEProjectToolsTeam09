package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.Document;

import controller.MessageBoardController;
import model.User;
import model.ViewConstraints;
/**
 * This is a users messageBoard.
 * Displays the messages and has an input filed for sending messages.
 */
public class MessageBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// User
	private User user;
	// users Document
	private Document doc;
	
	// UI components
	private JLabel currentChat;
	private JButton sendButton;
	private JTextArea inputText;
	private JTextPane messagePane;
	private JScrollPane scrollPane;
	
	public MessageBoard(User user, MessageBoardController controller){
		if(user == null){
			this.user = new User("All users");			
		}else{
			this.user = user;
		}
		initializeComponents(controller);
		addComponents();
	}
	
	/**
	 * Initializes all components of the MessageBoard
	 * @param controller
	 */
	private void initializeComponents(MessageBoardController controller){
		currentChat 	= new JLabel("You chat with: "+user.getUserName());
		sendButton		= new JButton(ViewConstraints.SEND_BUTTON_TEXT);		
		inputText 		= new JTextArea();
		messagePane 	= new JTextPane();

		// Panel Title
		currentChat.setFont(ViewConstraints.TITLE_LABLE_FONT);
		
		// Document display
		doc = messagePane.getDocument();
		messagePane.setEditable(false);		
		scrollPane 		= new JScrollPane(messagePane);
		
		scrollPane.setPreferredSize(new Dimension(ViewConstraints.LEFT_PANEL_WIDTH-10,ViewConstraints.PANEL_HEIGHT/3-10));
		scrollPane.setBackground(ViewConstraints.BASIC_BG_COLOR);
		scrollPane.setBorder(BorderFactory.createCompoundBorder(
				ViewConstraints.BASIC_BORDER, 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		// Input filed
		inputText.setBorder(BorderFactory.createCompoundBorder(
					ViewConstraints.BASIC_BORDER, 
		            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		inputText.addKeyListener(controller);
		
		// Send button
		sendButton.addActionListener(controller);		
		
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(ViewConstraints.LEFT_PANEL_WIDTH-10,ViewConstraints.PANEL_HEIGHT-10));
	}
		
	/**
	 * Adds all components to the MessageBoard
	 */
	private void addComponents(){
		GridBagConstraints c = new GridBagConstraints();		
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 1;		
		
		// Panel Title
		this.add(currentChat, c);		
		
		c.weighty = 20;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;	
		
		// Document display
		this.add(scrollPane,c);
		
		c.weighty = 5;
		c.gridy = 2;
		c.insets = new Insets(10, 0, 0, 0);
		
		// Input filed
		this.add(inputText, c);
		
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;
		c.gridy = 3;
		
		// Send button
		this.add(sendButton,c);
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}	
	
	public Document getDoc(){
		return doc;
	}
	
	/**
	 * 
	 * @return message from input filed
	 */
	public String getMessage(){		
		String mess = inputText.getText();
		return mess;
	}

	/**
	 * Clears input field.
	 * @param string
	 */
	public void setMessage(String string) {
		inputText.setText("");
	}
}
