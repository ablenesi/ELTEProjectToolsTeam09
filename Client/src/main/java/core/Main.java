package core;


import controller.Controller;
import view.MainFrame;

/**
 * Starts the Client Application
 */
public class Main {
	public static void main(String[] args) {     
        Controller controller = new Controller();
        MainFrame mainFrame = new MainFrame(controller);        
        controller.setMainFrame(mainFrame);
	}
}
