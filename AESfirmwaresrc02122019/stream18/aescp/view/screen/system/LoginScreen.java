package stream18.aescp.view.screen.system;


import stream18.aescp.Browser;
import stream18.aescp.view.form.system.LoginForm;
import stream18.aescp.view.screen.Screen;

public class LoginScreen extends Screen {

	// Singleton
	static LoginScreen theLogsScreen = null;
	
	public LoginScreen() {
    	super(true, true);

    	// This screen just contains a form: the login form
    	bottom.add(LoginForm.getInstance(this));
    	LoginForm.getInstance(this).setBounds(0, 0, bottom.getWidth(), bottom.getHeight());
    }
	
	public static LoginScreen getInstance() {
        if (theLogsScreen == null) {
        	theLogsScreen = new LoginScreen();
        }
        
        return theLogsScreen;
	}
	
    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theLogsScreen = LoginScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theLogsScreen, previousScreen);
	}
}