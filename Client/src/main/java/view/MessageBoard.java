package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.ViewConstraints;

public class MessageBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton send;
	private JTextArea inputText;
	private JScrollPane scrollPanel;
		
	public MessageBoard(){
		setBackground(Color.BLUE);
		initializeComponents();
		addComponents();
	}
	
	private void initializeComponents(){
		send = new JButton(ViewConstraints.SEND_BUTTON_TEXT);
		inputText = new JTextArea();
		scrollPanel = new JScrollPane();
		scrollPanel.setPreferredSize(new Dimension(ViewConstraints.LEFT_PANEL_WIDTH-10,(int) ((int)ViewConstraints.PANEL_HEIGHT*0.8)));
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(ViewConstraints.LEFT_PANEL_WIDTH-10,ViewConstraints.PANEL_HEIGHT-10));
	}
	private void addComponents(){
		add(scrollPanel, BorderLayout.NORTH);
		add(inputText,BorderLayout.CENTER);
		add(send, BorderLayout.SOUTH);
	}

}
