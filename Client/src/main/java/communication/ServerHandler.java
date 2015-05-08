package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import model.Message;

public class ServerHandler extends Thread{

	private Socket client;
	private PrintWriter pw;
	private BufferedReader br;
	private String messageToServer;
	private static final String host = "localhost";
	private static final int	port = 9032;
	
	private static final String REGISTER_KEYWORD 	= "REG"; 
	private static final String LOGIN_KEYWORD 		= "LOG";
	private static final String SEND_MESS_KEYWORD 	= "MES";
	private static final String GET_DATA_KEYWORD 	= "UPD";
	private static final String GET_LOGOUT_KEYWORD 	= "OUT";
	private static final String	SEPARATOR			= ""+(char)37;
	
	private static final int REQUEST_OK						= 0;
	private static final int INCORRECT_REQUEST_FORMAT 		= -1;
	private static final int USER_NAME_ALREADY_EXIST 		= -2;
	private static final int INCORRECT_USER_OR_PASSWOROD 	= -3;
	
	public ServerHandler() {
		client = null;
		pw = null;
		br = null;		
	}

	public void connect(){
		
		try {
			client = new Socket(host, port);
		} catch (IOException e) {
			System.err.println("[EXCEPTION] connecting to server");
			e.printStackTrace();
		}
		System.out.println("Connection to server SUCESSFULL");
		
		try {
			pw = new PrintWriter(client.getOutputStream());
			br = new BufferedReader(
					new InputStreamReader(client.getInputStream()));	
		} catch (IOException e) {
			System.err.println("[EXCEPTION] creating streams");
			e.printStackTrace();
		}
	}

	public boolean register(String userName, String passwd, String email){
		messageToServer = REGISTER_KEYWORD + SEPARATOR + userName + SEPARATOR + passwd
				+ SEPARATOR + email;
		pw.println(messageToServer);
		pw.flush();
				
		System.out.println("Message sent to server: " + messageToServer);
		try {
			return handleRegisterServerMessage(br.readLine());
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
			return handleLoginServerMessage(br.readLine());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public void sendMessage(Message message){
		messageToServer = SEND_MESS_KEYWORD + SEPARATOR + "TOKEN GOES HERE" 
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
	
	public void getData(){
		messageToServer = GET_DATA_KEYWORD + SEPARATOR + "TOKEN GOES HERE";
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
	
	private boolean handleRegisterServerMessage(String serverMessage){
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
		
	private boolean handleLoginServerMessage(String serverMessage){
		String[] messageParts = serverMessage.split(SEPARATOR);
		int error = Integer.parseInt(messageParts[0]);
		switch (error) {
		case REQUEST_OK:
			System.out.println("Login request OK");			
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
	
	private boolean handleServerMessage(String serverMessage){
		String[] messageParts = serverMessage.split(SEPARATOR);
		int error = Integer.parseInt(messageParts[0]);
		switch (error) {
		case REQUEST_OK:
			System.out.println("Request OK");
			JOptionPane.showMessageDialog(null,"Username is crated now you can Sign in!", "Register successful",
	                JOptionPane.INFORMATION_MESSAGE);
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
}
