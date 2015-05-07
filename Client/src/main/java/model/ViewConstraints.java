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
	
	public static final Color USER_SEL_BG_COLOR		= new Color(210,244,210);
	public static final Color USER_NOT_SEL_BG_COLOR	= new Color(224,224,224);
	
	public static final Color BASIC_BORDER_COLOR 	= new Color(122,138,153);
	public static final Color BASIC_BG_COLOR 		= new Color(240,240,240);
	
	public static final Border BASIC_BORDER			= BorderFactory.createLineBorder(BASIC_BORDER_COLOR);
	
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
