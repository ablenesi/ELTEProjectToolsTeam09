package view.auth;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.ViewConstraints;

/**
 * The sees this panel first contains welcome text.
 * May contain help for the usage in advance.
 * @author Bl�nesi Attila
 *
 */
public class WelcomePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImagePanel imagePanel;
	
	
	public WelcomePanel() {
		initializeComponents();
		addComponents();
	}

	private void initializeComponents(){		
		imagePanel = new ImagePanel(null);
		imagePanel.setPreferredSize(new Dimension(256,256));
		setLayout(new GridBagLayout());		
		this.setPreferredSize(new Dimension(ViewConstraints.LEFT_PANEL_WIDTH,ViewConstraints.PANEL_HEIGHT));
	} 

	private void addComponents(){
		
		add(imagePanel);
		imagePanel.repaint();
	}

	/**
	 * Represents a panel whit a specified image.
	 * @author Bl�nesi Attila
	 *
	 */
	private class ImagePanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;		

		private BufferedImage image;
		/**
		 * If the image parameter is null draws a default image on this panel.
		 * Else the given image will be displayed.
		 * @param image The image which will be drawn on this panel
		 */
		public ImagePanel(BufferedImage image) {			
			if(image == null){
				try {
					this.image = ImageIO.read(new File("src/main/resources/blue-talk-chat.png"));
				} catch (IOException e) {
					System.err.println("Welcome picture could not be loaded");
				}
			}else{
				this.image = image;
			}
		}

		/**
		 * Draws the loaded image on the panel.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, null);       
		}
	}
}
