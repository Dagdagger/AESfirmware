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
public class ExportDataScreen extends Screen {

	// Singleton
	static ExportDataScreen theExportDataScreen = null;
	
	public ExportDataScreen() {
    	super(true, true);
    	    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new LogsNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }

	public static ExportDataScreen getInstance() {
        if (theExportDataScreen == null) {
        	theExportDataScreen = new ExportDataScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theExportDataScreen.addStatus();
        
        return theExportDataScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theExportDataScreen = ExportDataScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theExportDataScreen, previousScreen);
	}
}
