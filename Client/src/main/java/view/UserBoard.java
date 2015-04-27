package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.UserBoardController;
import model.User;
import model.ViewConstraints;

public class UserBoard extends JPanel {
	private List<User> users;
	private List<ListItem> listItems;
	private GridBagConstraints c;
	
	public UserBoard(List<User> users,UserBoardController controller) {
		this.users = users;
		initializeComponents(controller);
		addComponents();
	}
	
	private void initializeComponents(UserBoardController controller){
		listItems = new ArrayList<UserBoard.ListItem>();
		for (User user : users) {
			listItems.add(new ListItem(user, controller));
		}
		c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
	}
	
	private void addComponents(){
		int row = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		for (ListItem listItem: listItems){
			c.gridy = row;
			this.add(listItem, c);
			row++;
		}
	}
	
	private class ListItem extends JPanel{
		private User user;
		private JLabel userNameLabel;
		private GridBagConstraints c;
		
		public ListItem(User user, UserBoardController controller) {
			super();
			this.user = user;
			initializeComponents(controller);
			addComponents();
		}
		
		private void initializeComponents(UserBoardController controller){
			setLayout(new GridBagLayout());
			userNameLabel = new JLabel(user.getUserName());
			userNameLabel.setFont(ViewConstraints.TITLE_LABLE);
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
	}
}
