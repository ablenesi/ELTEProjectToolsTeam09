package model;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ViewConstraints {
	public static final Font TITLE_LABLE_FONT 		= new Font("Thaoma", Font.PLAIN, 28);
	public static final Font BASIC_LABLE_FONT 		= new Font("Thaoma", Font.PLAIN, 14);
	public static final Font BUTTON_LABLE_FONT 		= new Font("Thaoma", Font.BOLD, 12);
	public static final Font USER_NAME_LABLE_FONT 	= new Font("Thaoma", Font.PLAIN, 18);
	
	public static final int LEFT_PANEL_WIDTH 		= 500;
	public static final int RIGHT_PANEL_WIDTH 		= 300;
	public static final int PANEL_HEIGHT 			= 450;
	
	public static final String LOGIN_BUTTON_TEXT 	= "Sign in";
	public static final String LOGOUT_BUTTON_TEXT 	= "Logout";
	public static final String REGISTER_BUTTON_TEXT = "Register";
	public static final String SEND_BUTTON_TEXT 	= "Send";
	public static final String PUBIC_USER_NAME		= "Public Chat";
		
	public static final Color BASIC_BORDER_COLOR 	= new Color(122,138,153);
	public static final Color BASIC_BG_COLOR 		= new Color(240,240,240);
	
	public static final Color USER_ONLINE_COLOR		= new Color(125, 210, 112);
	public static final Color USER_OFFLINE_COLOR	= new Color(255, 181, 71);
	public static final Color USER_AUTH_COLOR		= new Color(0, 153, 51);
	public static final Color USER_SELECTED_COLOR	= new Color(77, 148, 255);
	
	public static final Border BASIC_BORDER			= BorderFactory.createLineBorder(BASIC_BORDER_COLOR);
	public static final Border PADDING_BORDER		= BorderFactory.createEmptyBorder(10,10,10,10);
	public static final Border USER_BORDER			= BorderFactory.createLineBorder(Color.WHITE);
	
	public static final SimpleAttributeSet NAME 	= new SimpleAttributeSet();
	public static final SimpleAttributeSet DATE 	= new SimpleAttributeSet();
		
	/**
	 * Sets up basic text formating keywords
	 */
	public static void init(){
		StyleConstants.setForeground(NAME, new Color(0, 51, 204));		
		StyleConstants.setBold(NAME, true);		

		StyleConstants.setForeground(DATE, new Color(148, 170, 233));		
		StyleConstants.setBold(DATE, true);
	}
}
