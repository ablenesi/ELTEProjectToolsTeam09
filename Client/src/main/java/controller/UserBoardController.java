package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import model.User;
import model.ViewConstraints;
import view.UserBoard;

public class UserBoardController implements MouseListener, ActionListener {

	private Controller controller;

	public UserBoardController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		UserBoard.ListItem source = (UserBoard.ListItem) arg0.getSource();
		User user = source.getUser();
		System.out.println(user.getUserName()+" Open NEW tab");
		source.setActiveUser(user);
		controller.loadMessageBoard(user);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		System.out.println(source.getText() + "LOging out");
		controller.loadLogin();
		controller.loadWelcome();
	}

}
