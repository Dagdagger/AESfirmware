package stream18.aescp.view.screen.system;


import stream18.aescp.Browser;
import stream18.aescp.view.button.CancelButton;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.form.system.EditUserForm;
import stream18.aescp.view.screen.Screen;

public class EditUserScreen extends Screen {

	// Singleton
	static EditUserScreen theEditUserScreen = null;
	
	public EditUserScreen() {
    	super(true, true);

    	// This screen just contains a form: the EditUser form
    	bottom.add(EditUserForm.getInstance(this));
    	EditUserForm.getInstance(this).setBounds(0, 0, bottom.getWidth(), bottom.getHeight());
    	
    	leftSide.add(new CancelButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }
	
	public static EditUserScreen getInstance() {
        if (theEditUserScreen == null) {
        	theEditUserScreen = new EditUserScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theEditUserScreen.addStatus();
        
        return theEditUserScreen;
	}
	
    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theEditUserScreen = EditUserScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theEditUserScreen, previousScreen);
	}
}