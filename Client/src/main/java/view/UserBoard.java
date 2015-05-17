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

/**
 * This represents all users who can be contacted
 */
public class UserBoard extends JPanel implements PropertyChangeListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Application data
	private Model model;	

	// UI components
	private JLabel onlineUsersLabel;
	private JButton logoutButton;
	private JScrollPane scrollPanel;
	private JList<User> userList;
	private DefaultListModel<User> userListModel;

	public UserBoard(Model model,UserBoardController controller) {
		this.model = model;
		initializeComponents(controller);
		addComponents();
	}

	/**
	 * Initializes all components of the UserBoard
	 * @param controller
	 */
	private void initializeComponents(UserBoardController controller){
		onlineUsersLabel = new JLabel("Online users:");
		logoutButton = new JButton(ViewConstraints.LOGOUT_BUTTON_TEXT);
		userListModel = new DefaultListModel<User>();

		// Panel Title
		onlineUsersLabel.setFont(ViewConstraints.TITLE_LABLE_FONT);		

		// User list display
		for (User user : model.getUsers()) {
			userListModel.addElement(user);
		}
		userList = new JList<User>(userListModel);
		userList.addListSelectionListener(controller);
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userList.setCellRenderer(model.getCellRenderer());
		model.getPropertyChangeSupport().addPropertyChangeListener(Model.USER, this);

		scrollPanel = new JScrollPane(userList);
		scrollPanel.setBackground(ViewConstraints.BASIC_BG_COLOR);

		// Logout button
		logoutButton.setFont(ViewConstraints.BUTTON_LABLE_FONT);
		logoutButton.addActionListener(controller);

		this.setLayout(new GridBagLayout());
	}

	/**
	 * Adds all components to the UserBoard
	 */
	private void addComponents(){
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;

		// Panel Title
		this.add(onlineUsersLabel, c);

		c.weighty = 20;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;		

		// User list display
		this.add(scrollPanel,c);

		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;
		c.gridy = 2;

		// Send button
		//this.add(logoutButton,c);
	}

	/**
	 * Updates User list display if Application data changed
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {				

		if (Model.USER.equals(evt.getPropertyName())) {
			if (evt.getOldValue() != null && evt.getNewValue() == null) {

				// REMOVE
				userListModel.removeElement(evt.getOldValue());

			} else if (evt.getOldValue() == null && evt.getNewValue() != null) {

				// ADD
				userListModel.addElement((User) evt.getNewValue());

			}
		}
	}

}
