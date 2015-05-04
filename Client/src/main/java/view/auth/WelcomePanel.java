package view.auth;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ViewConstraints;

public class WelcomePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel welcomeLabel;	
	private ImagePanel imagePanel;
	
	public WelcomePanel() {
		initializeComponents();
		addComponents();
	}
	
	private void initializeComponents(){
		welcomeLabel = new JLabel("Hello there! Log in Or Register");
		welcomeLabel.setFont(ViewConstraints.TITLE_LABLE_FONT);
		imagePanel = new ImagePanel();

		setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(ViewConstraints.LEFT_PANEL_WIDTH,ViewConstraints.PANEL_HEIGHT));
	} 
	
	private void addComponents(){
		add(welcomeLabel,BorderLayout.NORTH);
		add(imagePanel,BorderLayout.CENTER);
		imagePanel.repaint();
	}
		
	private class ImagePanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;		
		
		private BufferedImage image;
		
		public ImagePanel() {			
			image = null;		
			try {
				image = ImageIO.read(new File("src\\main\\resources\\blue-talk-chat.png"));
			} catch (IOException e) {
				System.err.println("Welcome picture could not be loaded");
			}
			
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, null);       
			System.out.println("pasd");
		}
	}
}
