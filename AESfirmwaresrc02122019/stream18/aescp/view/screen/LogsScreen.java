package stream18.aescp.view.screen;

import stream18.aescp.Browser;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.logs.ClearDataButton;
import stream18.aescp.view.button.logs.DeleteLogsButton;
import stream18.aescp.view.button.logs.ExportDataButton;
import stream18.aescp.view.button.logs.PrintDataButton;
import stream18.aescp.view.button.logs.ShowLogsButton;
import stream18.aescp.view.form.TopForm;


public class LogsScreen extends Screen {

	// Singleton
	static LogsScreen theLogsScreen = null;
	
	public LogsScreen() {
    	super(true, true);

    	final int BTN_MARGIN_H = 20;
    	final int BTN_MARGIN_V = 30;
    	
    	// Add 5 buttons in two rows
    	int x = BTN_MARGIN_H;
    	int y = BTN_MARGIN_V;
    	int gap = (Browser.SCREEN_WIDTH - LEFT_WIDTH - (2*BTN_MARGIN_H) + 25) / 4;
    	int vgap = (BOTTOM_HEIGHT - (2*BTN_MARGIN_V)) / 2;

    	// Coordinates are relative to its container, the bottom panel
    	bottom.add(ShowLogsButton.getInstance(x, y));
    	x += gap;
    	bottom.add(DeleteLogsButton.getInstance(x, y));
    	
    	//x = BTN_MARGIN_H;
    	//y += vgap;

    //	bottom.add(ExportDataButton.getInstance(x, y));
    //	x += gap;
    	//bottom.add(PrintDataButton.getInstance(x, y));
    	//x += gap;
    	//bottom.add(ClearDataButton.getInstance(x, y));
    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }
	
	public static LogsScreen getInstance() {
        if (theLogsScreen == null) {
        	theLogsScreen = new LogsScreen();
        }

        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theLogsScreen.addStatus();
        theLogsScreen.addTop();
    	
    	TopForm.getInstance(null).logsTopForm();
        return theLogsScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theLogsScreen = LogsScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theLogsScreen, previousScreen);
	}
}
