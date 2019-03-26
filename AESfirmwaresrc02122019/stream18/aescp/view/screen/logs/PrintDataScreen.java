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
public class PrintDataScreen extends Screen {

	// Singleton
	static PrintDataScreen thePrintDataScreen = null;
	
	public PrintDataScreen() {
    	super(true, true);
    	    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new LogsNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }

	public static PrintDataScreen getInstance() {
        if (thePrintDataScreen == null) {
        	thePrintDataScreen = new PrintDataScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        thePrintDataScreen.addStatus();
        
        return thePrintDataScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		thePrintDataScreen = PrintDataScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(thePrintDataScreen, previousScreen);
	}
}
