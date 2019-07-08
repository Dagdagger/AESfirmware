package stream18.aescp.view.screen.system;


import stream18.aescp.Browser;
import stream18.aescp.view.button.CancelButton;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.form.system.AddUserForm;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.screen.SystemScreen;

public class AddUserScreen extends Screen {

	// Singleton
	static AddUserScreen theAddUserScreen = null;
	
	public AddUserScreen() {
    	super(true, true);

    	// This screen just contains a form: the AddUser form
    	bottom.add(AddUserForm.getInstance(this));
    	AddUserForm.getInstance(this).setBounds(0, 0, bottom.getWidth(), bottom.getHeight());
    	CancelButton ourCancelButton = new CancelButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y);
    	leftSide.add(ourCancelButton);
    	ourCancelButton.setPreviousScreen(SystemScreen.getInstance());
    }
	
	public static AddUserScreen getInstance() {
        if (theAddUserScreen == null) {
        	theAddUserScreen = new AddUserScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theAddUserScreen.addStatus();
        
        return theAddUserScreen;
	}
	
    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theAddUserScreen = AddUserScreen.getInstance();
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theAddUserScreen, SystemScreen.getInstance());
	}
}