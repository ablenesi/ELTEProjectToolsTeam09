package core;


import controller.Controller;
import view.MainFrame;

public class Main {
	public static void main(String[] args) {
		System.out.println("Chat-Client Started");        
        Controller controller = new Controller();
        MainFrame mainFrame = new MainFrame(controller);        
        controller.setMainFrame(mainFrame);
        controller.loadLogin();
        mainFrame.pack();
        mainFrame.setVisible(true);        
	}
}
