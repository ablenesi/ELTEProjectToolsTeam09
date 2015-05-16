package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import controller.Controller;
import model.Message;
import model.User;

public class ServerHandler extends SwingWorker<Void, String>{

	private Controller controller;

	private Socket client;
	private PrintWriter pw;
	private BufferedReader br;
	private String messageToServer;	

	private static final String host = "localhost";
	private static final int	port = 9032;
	private static final int	REQUEST_REFRESH_RATE = 2500;

	static final String REGISTER_KEYWORD 	= "REG"; 
	private static final String LOGIN_KEYWORD 		= "LOG";
	private static final String SEND_MESS_KEYWORD 	= "MES";
	private static final String GET_DATA_KEYWORD 	= "UPD";
	private static final String GET_LOGOUT_KEYWORD 	= "OUT";
	
	private static final String	SEPARATOR			= ""+(char)37;
	private static final String	SEPARATOR2			= ""+(char)38;
	private static final String	SEPARATOR3			= ""+(char)39;

	private static final int REQUEST_OK						= 0;
	private static final int INCORRECT_REQUEST_FORMAT 		= -1;
	private static final int USER_NAME_ALREADY_EXIST 		= -2;
	private static final int INCORRECT_USER_OR_PASSWOROD 	= -3;

	public ServerHandler(Controller controller) {
		client = null;
		pw = null;
		br = null;		
		this.controller = controller;
	}

	public boolean connect(){

		try {
			client = new Socket(host, port);
		} catch (IOException e) {
			System.err.println("[EXCEPTION] connecting to server");			
			JOptionPane.showMessageDialog(null, "Connection to server could not be made. Try again later.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		System.out.println("Connection to server SUCESSFULL");

		try {
			pw = new PrintWriter(client.getOutputStream());
			br = new BufferedReader(
					new InputStreamReader(client.getInputStream()));	
		} catch (IOException e) {
			System.err.println("[EXCEPTION] creating streams");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean register(String userName, String passwd, String email){
		messageToServer = REGISTER_KEYWORD + SEPARATOR + userName + SEPARATOR + passwd
				+ SEPARATOR + email;
		pw.println(messageToServer);
		pw.flush();

		System.out.println("Message sent to server: " + messageToServer);
		try {
			return handleRegister(br.readLine());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public boolean login(String userName, String passwd){
		messageToServer = LOGIN_KEYWORD + SEPARATOR + userName + SEPARATOR + passwd;				
		pw.println(messageToServer);
		pw.flush();

		System.out.println("Message sent to server: " + messageToServer);
		try {
			return handleLogin(br.readLine());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public void sendMessage(Message message){
		messageToServer = SEND_MESS_KEYWORD + SEPARATOR + controller.getModel().getAuthUser().getToken()
				+ SEPARATOR + message.getUserName() + SEPARATOR + message.getMessage();
		pw.println(messageToServer);
		pw.flush();

		System.out.println("Message sent to server: " + messageToServer);
		try {
			handleServerMessage(br.readLine());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getData(){
		messageToServer = GET_DATA_KEYWORD + SEPARATOR + controller.getModel().getAuthUser().getToken();
		pw.println(messageToServer);
		pw.flush();

		String data = null;
		try {
			data = br.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return data;
	}

	public void logout(){
		messageToServer = GET_LOGOUT_KEYWORD;				
		if(pw!=null){
			pw.println(messageToServer);		
			pw.flush();
		}
		try {
			if(client!= null) client.close();
			if(pw!=null) pw.close();
			if(br!=null) br.close();
		} catch (IOException e) {
			System.err.println("[EXCEPTION] closing streams, socket");
			e.printStackTrace();
		}
	}

	private boolean handleRegister(String serverMessage){
		String[] messageParts = serverMessage.split(SEPARATOR);
		int error = Integer.parseInt(messageParts[0]);
		switch (error) {
		case REQUEST_OK:
			System.out.println("Register request OK");
			JOptionPane.showMessageDialog(null,"Username is crated now you can Sign in!", "Register successful",
					JOptionPane.INFORMATION_MESSAGE);
			return true;
		case INCORRECT_REQUEST_FORMAT:
			System.err.println("Oups! \nSomethin went wrong!\n Request format was invalid");
			break;		
		case USER_NAME_ALREADY_EXIST:
			JOptionPane.showMessageDialog(null,"Username alredy exists!", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
		return false;
	}

	private boolean handleLogin(String serverMessage){
		String[] messageParts = serverMessage.split(SEPARATOR);
		int error = Integer.parseInt(messageParts[0]);
		switch (error) {
		case REQUEST_OK:
			System.out.println("Login request OK");
			controller.getModel().getAuthUser().setToken(messageParts[1]);
			return true;

		case INCORRECT_REQUEST_FORMAT:
			System.err.println("Oups! \nSomethin went wrong!\n Request format was invalid");
			break;

		case INCORRECT_USER_OR_PASSWOROD:
			JOptionPane.showMessageDialog(null,"Username or password is incorrect!", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;

		default:
			break;
		}
		return false;
	}

	private boolean handleData(String serverMessage){
		String[] messageParts = serverMessage.split(SEPARATOR);
		int error = Integer.parseInt(messageParts[0]);
		switch (error) {
		case REQUEST_OK:			
			hanleOkData(messageParts);
			return true;
		case INCORRECT_REQUEST_FORMAT:
			System.err.println("Oups! \nSomethin went wrong!\n Request format was invalid");
			break;

		case INCORRECT_USER_OR_PASSWOROD:
			JOptionPane.showMessageDialog(null,"Username or password is incorrect!", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;	

		default:
			break;
		}
		return false;
	}

	private void hanleOkData(String[] messageParts) {
		switch (messageParts.length) {
		case 4:
			processPublicMessages(messageParts);
		case 3:
			processMessages(messageParts);
		case 2:
			processUsers(messageParts);			
		}				
	}

	private void processUsers(String[] messageParts) {
		boolean add;
		List<User> onlineUsers = new ArrayList<User>();
		String[] users = messageParts[1].split(SEPARATOR2);
		for (int i = 0; i < users.length; i++) {
			add = true;
			for (User user: controller.getModel().getUsers()) {					
				if(user.getUserName().equals(users[i])){ 
					add = false;
					onlineUsers.add(user);
				}
			}
			if (add){
				System.out.println("ADD USER "+ users[i]);
				User newUser = new User(users[i]);
				controller.getModel().addUser(newUser);
				onlineUsers.add(newUser);
			}		
		}
		controller.getModel().setOnlineUsers(onlineUsers);
		controller.getMainFrame().repaint();
	}

	private void processPublicMessages(String[] messageParts) {
		User fromUser = controller.getModel().getUsers().get(0);		
		String[] messages = messageParts[3].split(SEPARATOR2);
		for (int i = 0; i < messages.length; i++) {
			String[] aMessage = messages[i].split(SEPARATOR3);			
			fromUser.addMessage(new Message(aMessage[0],aMessage[1]));
			fromUser.update();
		}
	}

	private void processMessages(String[] messageParts) {
		boolean add;
		User fromUser = null;
		String[] messages = messageParts[2].split(SEPARATOR2);
		if(!messages[0].equals("")){
			for (int i = 0; i < messages.length; i++) {
				String[] aMessage = messages[i].split(SEPARATOR3);
				add = true;
				for (User user: controller.getModel().getUsers()) {					
					if(user.getUserName().equals(aMessage[0])) {
						add = false;
						break;
					}
				}					
				if (add){
					System.out.println("ADD USER "+ aMessage[0]);
					fromUser = new User(aMessage[0]);
					controller.getModel().addUser(fromUser);
				}else{
					for (User user: controller.getModel().getUsers()) {					
						if(user.getUserName().equals(aMessage[0])) {
							fromUser = user;
							break;
						}
					}
				}
				fromUser.addMessage(new Message(fromUser.getUserName(),aMessage[1]));
				fromUser.update();
			}
		}
	}

	private boolean handleServerMessage(String serverMessage){
		String[] messageParts = serverMessage.split(SEPARATOR);
		int error = Integer.parseInt(messageParts[0]);
		switch (error) {
		case REQUEST_OK:
			System.out.println("Send Request OK");			
			return true;

		case INCORRECT_REQUEST_FORMAT:
			System.err.println("Oups! \nSomethin went wrong!\n Request format was invalid");
			break;

		case INCORRECT_USER_OR_PASSWOROD:
			JOptionPane.showMessageDialog(null,"Username or password is incorrect!", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;

		case USER_NAME_ALREADY_EXIST:
			JOptionPane.showMessageDialog(null,"Username alredy exists!", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;

		default:
			break;
		}
		return false;
	}


	@Override
	protected Void doInBackground() throws Exception {
		while(true){			
			publish(getData());
			try {
				Thread.sleep(REQUEST_REFRESH_RATE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
	}

	@Override
	protected void process(List<String> chunks) {
		for (String data: chunks) {
			handleData(data);
		}
	}


}
