package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.User;

/**
 *  Controls the User board, user selection and logout
 */
public class UserBoardController implements ListSelectionListener, ActionListener {

	private Controller controller;

	public UserBoardController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Logs out the user if logout button was pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		System.out.println(source.getText() + "LOging out");
		controller.loadRightPanel("LOGIN");
		controller.loadWelcome();
	}

	/**
	 * Loads the Message board if a new User was selected
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// get index of selected user
		int index = ((JList<User>)e.getSource()).getSelectedIndex();

		// Load Message board for selected User
		User user = controller.getModel().getUsers().get(index);
		System.out.println(user.getUserName()+" Open NEW tab");
		controller.loadMessageBoard(user);
		user.update();
	}

}
