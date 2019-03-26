package stream18.aescp.view.screen.logs;

import stream18.aescp.Browser;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.LogsNavButton;
import stream18.aescp.view.screen.Screen;

/**
 * @author egarcia
 *
 * This Screen handles two forms: ProgramForm and OptionsForm
 */
public class ViewRecordScreen extends Screen {

	// Singleton
	static ViewRecordScreen theViewRecordScreen = null;
	
	public ViewRecordScreen() {
    	super(true, true);
    	    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new LogsNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }

	public static ViewRecordScreen getInstance() {
        if (theViewRecordScreen == null) {
        	theViewRecordScreen = new ViewRecordScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theViewRecordScreen.addStatus();
        
        return theViewRecordScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theViewRecordScreen = ViewRecordScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theViewRecordScreen, previousScreen);
	}
}
