package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.User;

public class UserBoardController implements ListSelectionListener, ActionListener {

	private Controller controller;

	public UserBoardController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		System.out.println(source.getText() + "LOging out");
		controller.loadRightPanel("LOGIN");
		controller.loadWelcome();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int index = ((JList)e.getSource()).getSelectedIndex();

		User user = controller.getModel().getUsers().get(index);
		System.out.println(user.getUserName()+" Open NEW tab");
		controller.loadMessageBoard(user);
		user.update();
	}

}
