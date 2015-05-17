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
/**
 * All communication is controlled by this class
 *
 */
public class ServerHandler extends SwingWorker<Void, String>{

	// Main controller
	private Controller controller;

	// Network variables
	private Socket client;
	private PrintWriter pw;
	private BufferedReader br;

	private String messageToServer;	

	// Connection Constants
	private static final String host = "localhost";
	private static final int	port = 9032;
	// Data from server will be required REQUEST_REFRESH_RATE milliseconds
	private static final int	REQUEST_REFRESH_RATE = 2500;

	// Message Constants
	static final String REGISTER_KEYWORD 	= "REG"; 
	private static final String LOGIN_KEYWORD 		= "LOG";
	private static final String SEND_MESS_KEYWORD 	= "MES";
	private static final String GET_DATA_KEYWORD 	= "UPD";
	private static final String GET_LOGOUT_KEYWORD 	= "OUT";
	public static final String PUBLIC_USER_NAME 	= "COMMON";

	private static final String	SEPARATOR			= ""+(char)37;
	private static final String	SEPARATOR2			= ""+(char)38;
	private static final String	SEPARATOR3			= ""+(char)39;

	// Response Statuses
	private static final int REQUEST_OK						= 0;
	private static final int INCORRECT_REQUEST_FORMAT 		= -1;
	private static final int USER_NAME_ALREADY_EXIST 		= -2;
	private static final int INCORRECT_USER_OR_PASSWOROD 	= -3;

	/** 
	 * @param controller All change from server is pushed thought this controller can't be null.
	 */
	public ServerHandler(Controller controller) {
		client = null;
		pw = null;
		br = null;		
		this.controller = controller;
	}

	/**
	 * Creates the connection to the server, creates streams.
	 * @return True if connection was successful, false otherwise.
	 */
	public boolean connect(){

		// Connect to server via TCP socket
		try {

			client = new Socket(host, port);

		} catch (IOException e) {
			System.err.println("[EXCEPTION] connecting to server");			
			JOptionPane.showMessageDialog(null, "Connection to server could not be made. Try again later.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}		

		// Get input and output Streams
		try {

			pw = new PrintWriter(client.getOutputStream());
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));

		} 
		catch (IOException e) 
		{
			System.err.println("[EXCEPTION] creating streams");
			e.printStackTrace();
			return false;
		}

		System.out.println("Connection to server SUCESSFULL");
		return true;
	}

	/**
	 * Send to server register message, read the answer from server, process it.
	 * All parameters must be set
	 * @param userName	
	 * @param passwd	
	 * @param email
	 * @return True if answer was received and processed correctly, else otherwise.
	 */
	public boolean register(String userName, String passwd, String email){
		// Creating register message
		messageToServer = REGISTER_KEYWORD 
				+ SEPARATOR + userName 
				+ SEPARATOR + passwd
				+ SEPARATOR + email;

		// Sending message
		pw.println(messageToServer);
		pw.flush();		
		System.out.println("Message sent to server: " + messageToServer);

		// Reading the answer from server, processing the answer
		try {

			return handleRegister(br.readLine());

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	/**
	 * Send to server login message, read the answer from server, process it.
	 * All parameters must be set
	 * @param userName
	 * @param passwd
	 * @return  True if answer was received and processed correctly, else otherwise.
	 */
	public boolean login(String userName, String passwd){
		// Creating register message
		messageToServer = LOGIN_KEYWORD 
				+ SEPARATOR + userName 
				+ SEPARATOR + passwd;

		// Sending message		
		pw.println(messageToServer);
		pw.flush();
		System.out.println("Message sent to server: " + messageToServer);

		// Reading the answer from server, processing the answer
		try {

			return handleLogin(br.readLine());

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	/**
	 * Send to server a users message, read the answer from server, process it.
	 * message parameters must be set
	 * @param message
	 */
	public boolean sendMessage(Message message){
		// Creating register message
		messageToServer = SEND_MESS_KEYWORD 
				+ SEPARATOR + controller.getModel().getAuthUser().getToken()
				+ SEPARATOR + message.getUserName() 
				+ SEPARATOR + message.getMessage();

		// Sending message				
		pw.println(messageToServer);
		pw.flush();
		System.out.println("Message sent to server: " + messageToServer);

		// Reading the answer from server, processing the answer
		try {

			return handleServerMessage(br.readLine());

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	/**
	 * Send to server get data message, read the answer from server, return the answer.
	 * @return The servers answer.
	 */
	public String getData(){
		// Creating register message
		messageToServer = GET_DATA_KEYWORD 
				+ SEPARATOR + controller.getModel().getAuthUser().getToken();

		// Sending message				
		pw.println(messageToServer);
		pw.flush();
		System.out.println("Message sent to server: " + messageToServer);

		// Reading the answer from server, processing the answer
		String data = null;
		try {

			data = br.readLine();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		return data;
	}

	/**
	 * Send to server logout message, close connection.
	 */
	public void logout(){
		// Creating register message
		messageToServer = GET_LOGOUT_KEYWORD;				

		// Sending message
		pw.println(messageToServer);		
		pw.flush();

		// Closing connection
		try {

			if(client != null) 
				client.close();

			if(pw != null) 
				pw.close();

			if(br != null) 
				br.close();

		} catch (IOException e) {
			System.err.println("[EXCEPTION] closing streams, socket");
			e.printStackTrace();
		}
	}

	/**
	 * Process default server message
	 * @param serverMessage	The servers answer which will be processed. Can't be null.
	 * @return True if processing was successful, else false.
	 */
	private boolean handleServerMessage(String serverMessage){
		// Separating the message STATUS + OTHER
		String[] messageParts = serverMessage.split(SEPARATOR);

		// Get status code from message
		int messageStatus = Integer.parseInt(messageParts[0]);

		// Process status
		switch (messageStatus) {
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

	/**
	 * Process the servers answer for register
	 * @param serverMessage	The servers answer which will be processed. Can't be null.
	 * @return	True if processing was successful, else false.
	 */
	private boolean handleRegister(String serverMessage){
		// Separating the message STATUS + OTHER
		String[] messageParts = serverMessage.split(SEPARATOR);

		// Get status code from message
		int messageStatus = Integer.parseInt(messageParts[0]);

		// Process status
		switch (messageStatus) {
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

	/**
	 * Process the servers answer for login
	 * @param serverMessage	The servers answer which will be processed. Can't be null.
	 * @return	True if processing was successful, else false.
	 */
	private boolean handleLogin(String serverMessage){
		// Separating the message STATUS + TOKEN
		String[] messageParts = serverMessage.split(SEPARATOR);

		// Get status code from message
		int messageStatus = Integer.parseInt(messageParts[0]);

		//Process status
		switch (messageStatus) {
		case REQUEST_OK:

			// Status OK set TOKEN to Authenticated User
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

	/**
	 * Process the servers answer for get data
	 * @param serverMessage	The servers answer which will be processed. Can't be null.
	 * @return	True if processing was successful, else false.
	 */
	private boolean handleData(String serverMessage){
		// Separating the message STATUS + TOKEN
		String[] messageParts = serverMessage.split(SEPARATOR);

		// Get status code from message
		int error = Integer.parseInt(messageParts[0]);

		//Process status
		switch (error) {
		case REQUEST_OK:

			// Status OK process the data
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

	/**
	 * Processes the requested data from server. 
	 * Calls the corresponding functions.
	 * Based on the sent data.
	 * @param messageParts List of the data parts. Can't be null.
	 */
	private void hanleOkData(String[] messageParts) {
		switch (messageParts.length) {
		case 4:

			// Processing Public messages
			processPublicMessages(messageParts);

		case 3:

			// Processing Private messages
			processMessages(messageParts);

		case 2:

			// Processing online users
			processUsers(messageParts);

		}				
	}

	/**
	 * Processes Online Users from data sent by server
	 * @param messageParts The data about the users. Can't be null.
	 */
	private void processUsers(String[] messageParts) {

		boolean addNewUser = true;
		List<User> onlineUsers = new ArrayList<User>();

		// Separates the Users by constant separator USER+USER => users[USER, USER]
		String[] users = messageParts[1].split(SEPARATOR2);

		for (int i = 0; i < users.length; i++) {
			// Check if user was already added to user list
			addNewUser = true;
			for (User user: controller.getModel().getUsers()) {					
				if(user.getUserName().equals(users[i])){ 
					addNewUser = false;
					onlineUsers.add(user);
				}
			}

			if (addNewUser){
				// Create and add new user to users list and olline list
				User newUser = new User(users[i]);
				controller.getModel().addUser(newUser);
				onlineUsers.add(newUser);
				System.out.println("ADDED USER "+ users[i]);
			}		
		}

		// Set online users, reset UserPanel
		controller.getModel().setOnlineUsers(onlineUsers);
		controller.getMainFrame().repaint();
	}

	/**
	 * Processes Public messages from data sent by server
	 * @param messageParts The data about the messages. Can't be null.
	 */
	private void processPublicMessages(String[] messageParts) {

		// Set user to send the Public Chat
		User fromUser = controller.getModel().getUsers().get(0);		

		String[] messages = messageParts[3].split(SEPARATOR2);

		for (int i = 0; i < messages.length; i++) {

			// Crate and add message to Public Chat
			String[] aMessage = messages[i].split(SEPARATOR3);			
			fromUser.addMessage(new Message(aMessage[0],aMessage[1]));
			fromUser.update();		
		}
	}

	/**
	 * Processes Private messages from data sent by server
	 * @param messageParts The data about the private messages. Can't be null.
	 */
	private void processMessages(String[] messageParts) {

		boolean add = true;
		User fromUser = null;

		String[] messages = messageParts[2].split(SEPARATOR2);

		// Check if there is any message
		if(!messages[0].equals("")){

			for (int i = 0; i < messages.length; i++) {
				// Separate message USERNAME+MESSAGE
				String[] aMessage = messages[i].split(SEPARATOR3);
				add = true;
				for (User user: controller.getModel().getUsers()) {					
					if(user.getUserName().equals(aMessage[0])) {
						add = false;
						break;
					}
				}					
				if (add){

					// if user doesn't exist already create it
					System.out.println("ADD USER "+ aMessage[0]);
					fromUser = new User(aMessage[0]);
					controller.getModel().addUser(fromUser);
				}else{

					// If user exist store it in the fromUser variable
					for (User user: controller.getModel().getUsers()) {					
						if(user.getUserName().equals(aMessage[0])) {
							fromUser = user;
							break;
						}
					}
				}

				// Add message to user 
				fromUser.addMessage(new Message(fromUser.getUserName(),aMessage[1]));

				// Print message to users Document
				fromUser.update();
			}
		}
	}



	/**
	 * After every REQUEST_REFRESH_RATE milliseconds request the data form the server and publishes it, being a SwingWorker.
	 */
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

	/**
	 * Processes the published data list.
	 */
	@Override
	protected void process(List<String> chunks) {
		for (String data: chunks) {
			handleData(data);
		}
	}

}
