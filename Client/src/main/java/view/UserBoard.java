package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import controller.UserBoardController;
import model.User;
import model.ViewConstraints;

public class UserBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<User> users;
	private List<ListItem> listItems;
	
	private JLabel onlineUsersLabel;
	private JButton logoutButton;
	private JScrollPane scrollPanel;
	private JPanel board;
	
	private GridBagConstraints c;
	
	private UserBoardController controller;
	
	public UserBoard(List<User> users,UserBoardController controller) {
		this.users = users;
		this.controller = controller;
		initializeComponents();
		addComponents();
	}
	
	private void initializeComponents(){
		listItems = new ArrayList<UserBoard.ListItem>();
		
		for (User user : users) {
			listItems.add(new ListItem(user, controller, this));
		}		
		onlineUsersLabel = new JLabel("Online users:");
		onlineUsersLabel.setFont(ViewConstraints.TITLE_LABLE_FONT);
		logoutButton = new JButton(ViewConstraints.LOGOUT_BUTTON_TEXT);
		logoutButton.setFont(ViewConstraints.BUTTON_LABLE_FONT);
		logoutButton.addActionListener(controller);
		c = new GridBagConstraints();
		this.setLayout(new FlowLayout());		
		scrollPanel = new JScrollPane();
		scrollPanel.setBackground(ViewConstraints.BASIC_BG_COLOR);
		board = new JPanel(new GridBagLayout());
		board.setBackground(ViewConstraints.BASIC_BG_COLOR);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(ViewConstraints.RIGHT_PANEL_WIDTH,ViewConstraints.PANEL_HEIGHT));
	}
	
	private void addComponents(){
		int row = 0;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 0;		
		c.fill = GridBagConstraints.HORIZONTAL;		
		c.anchor = GridBagConstraints.PAGE_START;

		for (ListItem listItem: listItems){
			c.gridy = row;
			board.add(listItem, c);
			row++;
			c.gridy = row;
			board.add(new JSeparator(), c);
			row++;			
		}
		c.weighty = 1;
		board.add(new JSeparator(), c);
		scrollPanel.setViewportView(board);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		this.add(onlineUsersLabel, c);
		c.weighty = 20;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;		
		this.add(scrollPanel,c);
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;
		c.gridy = 2;
		this.add(logoutButton,c);
	}
	
	private void update(){
		for (ListItem listItem : listItems) {
			listItem.update();
		}
	}
	
	public void changeActiveUser(User user){
		for (User u: users){
			u.setActive(u.getUserName().equals(user.getUserName()));
		}
		update();
	}	
	
	/**
	 * ListItem represents one element in the UserBoard.
	 * It's content is described by a User model.
	 * 
	 * @author Blénesi Attila
	 *
	 */
	public class ListItem extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private UserBoard parent;
		private User user;
		private JLabel userNameLabel;
		private GridBagConstraints c;
		
		public ListItem(User user, UserBoardController controller, UserBoard parent) {
			super();
			this.user = user;
			this.parent = parent;
			initializeComponents(controller);
			addComponents();
		}
		
		private void initializeComponents(UserBoardController controller){
			setLayout(new GridBagLayout());			
			setBackground(ViewConstraints.USER_NOT_SEL_BG_COLOR);
			userNameLabel = new JLabel(user.getUserName());
			userNameLabel.setFont(ViewConstraints.USER_NAME_LABLE_FONT);
			c = new GridBagConstraints();
			addMouseListener((UserBoardController)controller);
		}					
		
		private void addComponents(){
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.anchor = GridBagConstraints.LAST_LINE_START;
			add(userNameLabel,c);
		}		
		
		public void setUser(User user){
			this.user = user;
		}
		
		public User getUser(){
			return user;
		}
		
		public void setActiveUser(User user){
			parent.changeActiveUser(user);
		}
		
		public void update(){
			setBackground(ViewConstraints.USER_NOT_SEL_BG_COLOR);
			if(user.isActive()){
				setBackground(ViewConstraints.USER_SEL_BG_COLOR);
			}
		}
	}
}
