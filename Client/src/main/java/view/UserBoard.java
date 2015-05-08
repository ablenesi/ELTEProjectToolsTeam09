package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controller.UserBoardController;
import model.Model;
import model.User;
import model.ViewConstraints;

public class UserBoard extends JPanel implements PropertyChangeListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Model model;	

	private JLabel onlineUsersLabel;
	private JButton logoutButton;
	private JScrollPane scrollPanel;
	private JList<User> userList;
	private DefaultListModel<User> userListModel;

	private GridBagConstraints c;	
	private UserBoardController controller;

	public UserBoard(Model model,UserBoardController controller) {
		this.model = model;
		this.controller = controller;
		initializeComponents();
		addComponents();
	}

	private void initializeComponents(){
		onlineUsersLabel = new JLabel("Online users:");
		onlineUsersLabel.setFont(ViewConstraints.TITLE_LABLE_FONT);

		logoutButton = new JButton(ViewConstraints.LOGOUT_BUTTON_TEXT);
		logoutButton.setFont(ViewConstraints.BUTTON_LABLE_FONT);
		logoutButton.addActionListener(controller);

		c = new GridBagConstraints();

		userListModel = new DefaultListModel<User>();
		for (User user : model.getUsers()) {
			userListModel.addElement(user);
		}
		userList = new JList<User>(userListModel);
		userList.addListSelectionListener(controller);
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model.getPropertyChangeSupport().addPropertyChangeListener(Model.USER, this);

		scrollPanel = new JScrollPane(userList);
		scrollPanel.setBackground(ViewConstraints.BASIC_BG_COLOR);

		this.setLayout(new GridBagLayout());
	}


	private void addComponents(){
		c.weightx = 1;
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {		
		if (evt.getSource() == model) {
			if (Model.USER.equals(evt.getPropertyName())) {
				if (evt.getOldValue() != null && evt.getNewValue() == null) {
					userListModel.removeElement(evt.getOldValue());
				} else if (evt.getOldValue() == null && evt.getNewValue() != null) {
					userListModel.addElement((User) evt.getNewValue());
				}
			}
		}

	}

}
