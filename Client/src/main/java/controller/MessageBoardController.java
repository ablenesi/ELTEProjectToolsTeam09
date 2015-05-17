package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import model.Message;
import model.ViewConstraints;
import view.MessageBoard;

/**
 * Controls all message boards, sends the message via ServerHandler.sendMessage(Message).
 * Clears input field after sending message.
 */
public class MessageBoardController implements ActionListener, KeyListener{
	private Controller controller;

	public MessageBoardController(Controller controller) {
		this.controller = controller;
	}
	
	/**
	 * Send a message from a specified message board, if the message is not empty.
	 * @param messageBoard
	 */
	private void sendMessage(MessageBoard messageBoard) {
		String mess = messageBoard.getMessage();
		if(!mess.equals("")){

			// cleaning input field
			messageBoard.setMessage("");
			
			Message messageToServet = null;
			Message messageToDoc = null;
			
			// Check if the message is public or private
			if(messageBoard.getUser().getUserName().equals(ViewConstraints.PUBIC_USER_NAME)){

				// Send Public message
				messageToServet = new Message(controller.getServerHandler().PUBLIC_USER_NAME,mess);
				
			}else{
				
				// Send Private message and write to the message boards document
				messageToServet = new Message(messageBoard.getUser().getUserName(),mess);
				messageToDoc = new Message(controller.getModel().getAuthUser().getUserName(),mess);
				messageToDoc.printMessage(messageBoard.getUser().getDoc());
				
			}
			
			controller.getServerHandler().sendMessage(messageToServet);
		}
	}

	/**
	 * Send message if SEND button was pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MessageBoard messageBoard =(MessageBoard)((JButton)e.getSource()).getParent();		
		sendMessage(messageBoard);
	}

	/**
	 * Send message if ENTER KEY was pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			MessageBoard messageBoard = (MessageBoard)((JTextArea)e.getSource()).getParent();		
			sendMessage(messageBoard);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {  }
	@Override
	public void keyTyped(KeyEvent e) {	}

}
