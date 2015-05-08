package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.Document;

import controller.MessageBoardController;
import model.User;
import model.ViewConstraints;

public class MessageBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private JLabel currentChat;
	private JButton sendButton;
	private JTextArea inputText;
	private JTextPane messagePane;
	private Document doc;
	
	private GridBagConstraints c;
	
	public MessageBoard(User user, MessageBoardController controller){
		if(user == null){
			this.user = new User("All users");			
		}else{
			this.user = user;
		}
		initializeComponents(controller);
		addComponents();
	}
	
	private void initializeComponents(MessageBoardController controller){
		currentChat = new JLabel("You chat with: "+user.getUserName());
		currentChat.setFont(ViewConstraints.TITLE_LABLE_FONT);
		sendButton = new JButton(ViewConstraints.SEND_BUTTON_TEXT);
		inputText = new JTextArea();

		inputText.setBorder(BorderFactory.createCompoundBorder(
					ViewConstraints.BASIC_BORDER, 
		            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		sendButton.addActionListener(controller);
		
		messagePane = new JTextPane();
		doc = messagePane.getDocument();
		
		messagePane.setEditable(false);
		messagePane.setBackground(ViewConstraints.BASIC_BG_COLOR);
		messagePane.setBorder(BorderFactory.createCompoundBorder(
				ViewConstraints.BASIC_BORDER, 
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(ViewConstraints.LEFT_PANEL_WIDTH-10,ViewConstraints.PANEL_HEIGHT-10));
	}
	private void addComponents(){
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 1;
		this.add(currentChat, c);		
		c.weighty = 20;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;	
		this.add(messagePane,c);
		c.weighty = 5;
		c.gridy = 2;
		c.insets = new Insets(10, 0, 0, 0);
		this.add(inputText, c);
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;
		c.gridy = 3;
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
	
	public String getMessage(){		
		String mess = inputText.getText();
		System.out.println("in get" + mess);
		inputText.setText("");
		return mess;
	}
}
