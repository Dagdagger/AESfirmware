package stream18.aescp.view.screen;

import stream18.aescp.Browser;
import stream18.aescp.view.button.CancelButton;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.system.AddUserButton;
import stream18.aescp.view.button.system.LanConfigButton;
import stream18.aescp.view.button.system.LogoutButton;
import stream18.aescp.view.button.system.RemoveUserButton;
import stream18.aescp.view.button.system.ShutDownButton;
import stream18.aescp.view.button.system.UserAdminButton;
import stream18.aescp.view.form.TopForm;

public class SystemScreen extends Screen {

	// Singleton
	static SystemScreen theSystemScreen = null;
	
	public SystemScreen() {
    	super(true, true);

    	final int BTN_MARGIN_H = 20;
    	final int BTN_MARGIN_V = 30;
    	
    	// Add 5 buttons in two rows
    	int x = BTN_MARGIN_H;
    	int y = BTN_MARGIN_V;
    	int gap = (Browser.SCREEN_WIDTH - LEFT_WIDTH - (2*BTN_MARGIN_H) + 25) / 4;
    	int vgap = (BOTTOM_HEIGHT - (2*BTN_MARGIN_V)) / 2;

    	// Coordinates are relative to its container, the bottom panel
    	bottom.add(ShutDownButton.getInstance(x, y));
    	x += gap;
      	bottom.add(LogoutButton.getInstance(x, y));
      	
      	x+= gap;
      	bottom.add(UserAdminButton.getInstance(x, y));


  
    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }
	
	public static SystemScreen getInstance() {
        if (theSystemScreen == null) {
        	theSystemScreen = new SystemScreen();
        }

        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theSystemScreen.addStatus();

        theSystemScreen.addTop();
        TopForm.getInstance(null).systemTopForm();

        return theSystemScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theSystemScreen = SystemScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theSystemScreen, HomeScreen.getInstance());
	}
}
