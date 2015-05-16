package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import model.Message;
import view.MessageBoard;

public class MessageBoardController implements ActionListener, KeyListener{
	private Controller controller;

	public MessageBoardController(Controller controller) {
		this.controller = controller;
	}
	private void sendMessage(MessageBoard messageBoard) {
		String mess = messageBoard.getMessage();
		if(!mess.equals("")){
			System.out.println("in controller "+mess);
			Message messageToServet = new Message(messageBoard.getUser().getUserName(),mess);
			Message messageToDoc = new Message(controller.getModel().getAuthUser().getUserName(),mess);
			messageToDoc.printMessage(messageBoard.getUser().getDoc());
			controller.getServerHandler().sendMessage(messageToServet);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MessageBoard messageBoard =(MessageBoard)((JButton)e.getSource()).getParent();		
		sendMessage(messageBoard);
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			MessageBoard messageBoard = (MessageBoard)((JTextArea)e.getSource()).getParent();		
			sendMessage(messageBoard);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }

}
