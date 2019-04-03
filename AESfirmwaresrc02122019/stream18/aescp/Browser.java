package stream18.aescp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import stream18.aescp.controller.Controller;
import stream18.aescp.model.Program;
import stream18.aescp.model.TestModeCfgManager;
import stream18.aescp.view.button.ReadyScreenButton;
import stream18.aescp.view.button.mode.FlowButton;
import stream18.aescp.view.screen.HomeScreen;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.screen.system.InitialScreen;

public class Browser extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public final static int SCREEN_WIDTH = 800;
	public final static int SCREEN_HEIGHT = 480;
	public final static Font FONT = new Font(Font.DIALOG, Font.PLAIN, 20);
	public final static Font FONT_2 = new Font(Font.DIALOG, Font.PLAIN, 20);
	public final static Font FONT_BIG = new Font(Font.DIALOG, Font.BOLD, 24);
	private static Browser theBrowser = null;
	
	public Browser() {		
		// Initialize the Controller
		Controller.getInstance();			
		///line muted and we are taking chatts
		// Other options: http://www.java2s.com/Tutorial/Java/0240__Swing/CustomizingaJOptionPaneLookandFeel.htm
		UIManager.put("OptionPane.buttonFont", new FontUIResource(FONT));
		UIManager.put("OptionPane.messageFont", new FontUIResource(FONT));
		UIManager.put("OptionPane.minimumSize", new Dimension(SCREEN_WIDTH * 1 / 2, SCREEN_HEIGHT * 1 / 2));
		
        setTitle("CP Browser");
        setResizable(false);
        setBackground(new Color(0xFFFFFF));
        setUndecorated(true);
        
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
                 
            }
        });
	}
	
	public void setScreen(Screen screen, Screen previousScreen) {
		screen.setPreviousScreen(previousScreen);
		getContentPane().removeAll();
        getContentPane().add(screen.getScreenCanvas());
        pack();
        setVisible(true);

        setSize(new Dimension(SCREEN_WIDTH,  SCREEN_HEIGHT));
        screen.postCreationAction();
	}

	public static Browser getInstance() {
        if (theBrowser == null) {
        	theBrowser = new Browser();
        }
   
        return theBrowser;
	}
	
    public static void main(String args[]) {
    	Locale.setDefault(Locale.US);
        // JFrame that will hold all the screens
    	// It is a singleton
        getInstance();
        //  A screen descriptor encapsulates the path of the
        //  template together with the clickable objects
       // HomeScreen.getInstance().setActive(null);
        InitialScreen.getInstance().setActive(null);
    
    }

	public int displayDialog(String message) {
		Object[] options = {"Accept",
			"Cancel"};
		int n = JOptionPane.showOptionDialog(Browser.getInstance(),
				message,
				null,
				JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				options[1]); //default button title
		return n;
	}
}
