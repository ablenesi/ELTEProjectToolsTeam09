package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Message;
import view.MessageBoard;

public class MessageBoardController implements ActionListener{
	private Controller controller;
	
	public MessageBoardController(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MessageBoard messageBoard =(MessageBoard)((JButton)e.getSource()).getParent();
		//System.out.println("Send this message: "+messageBoard.getMessage()+" to: "+messageBoard.getUser().getUserName());
		String mess = messageBoard.getMessage();
		System.out.println("in controller "+mess);
		Message message = new Message(messageBoard.getUser().getUserName(),mess);
		message.printMessage(messageBoard.getUser().getDoc());
		controller.getServerHandler().sendMessage(message);
	}

}
