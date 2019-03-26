package stream18.aescp.view.screen.system;


import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
 
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import stream18.aescp.Browser;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.form.system.UserForm;



public class InitialScreen extends Screen {

	static InitialScreen theInitialScreen = null;
	
	public InitialScreen() {
    	super(true, true);
     
    	// This screen just contains a form: the login form
    	UserForm.getInstance(this).setBounds(0, 0, bottom.getWidth(), bottom.getHeight());      
    	bottom.add(UserForm.getInstance(this));
    }
	
	public static InitialScreen getInstance() {
        if (theInitialScreen == null) {
        	theInitialScreen = new InitialScreen();
        }
        
        return theInitialScreen;
	}
	
    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theInitialScreen = InitialScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theInitialScreen, previousScreen);
	}
}