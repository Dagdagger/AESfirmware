package stream18.aescp.view.screen.system;


import stream18.aescp.Browser;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.SystemNavButton;
import stream18.aescp.view.form.system.UserAdminForm;
import stream18.aescp.view.screen.Screen;

public class UserAdminScreen extends Screen {

	// Singleton
	static UserAdminScreen theUserAdminScreen = null;
	
	public UserAdminScreen() {
    	super(true, true);

    	// This screen just contains a form: the UserAdmin form
    	bottom.add(UserAdminForm.getInstance(this));
    	UserAdminForm.getInstance(this).setBounds(0, 0, bottom.getWidth(), bottom.getHeight());
    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new SystemNavButton(SystemNavButton.DEFAULT_X, SystemNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }
	
	public static UserAdminScreen getInstance() {
        if (theUserAdminScreen == null) {
        	theUserAdminScreen = new UserAdminScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theUserAdminScreen.addStatus();
        
        return theUserAdminScreen;
	}
	
    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theUserAdminScreen = UserAdminScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theUserAdminScreen, previousScreen);
	}
}