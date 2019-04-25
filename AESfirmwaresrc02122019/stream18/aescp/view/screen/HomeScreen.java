package stream18.aescp.view.screen;

import javafx.scene.control.SelectionMode;
import stream18.aescp.Browser;
import stream18.aescp.controller.TestMode;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.button.LogsButton;
import stream18.aescp.view.button.ModeButton;
import stream18.aescp.view.button.OpenButton;
import stream18.aescp.view.button.ReadyScreenButton;
import stream18.aescp.view.button.SaveButton;
import stream18.aescp.view.button.SetupButton;
import stream18.aescp.view.button.StartButton;
import stream18.aescp.view.button.StopButton;
import stream18.aescp.view.button.SystemButton;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.screen.mode.FlowScreen;
import stream18.aescp.view.screen.mode.SK_ModeScreen;
import stream18.aescp.view.button.HomeButton;;

public class HomeScreen extends Screen {

	// Singleton
	static HomeScreen theHomeScreen = null;
	private StartButton theStartButton;
	private StopButton theStopButton;

	
	public HomeScreen() {
    	super(true, true);

    	
 
    	final int BTN_MARGIN_H = 20;
    	final int BTN_MARGIN_V = 30;
    	
    	// Add 8 buttons in two rows
    	int x = BTN_MARGIN_H;
    	int y = BTN_MARGIN_V;
    	int gap = (Browser.SCREEN_WIDTH - LEFT_WIDTH - (2*BTN_MARGIN_H) + 18) / 5;
    	int vgap = (BOTTOM_HEIGHT - (2*BTN_MARGIN_V)) / 2;

    	
    	
    	
    	// Coordinates are relative to its container, the bottom panel
    	bottom.add(ReadyScreenButton.getInstance(x, y));
    	x += gap; 
    	bottom.add(new ModeButton(x, y, this));
    	x += gap;  
    	bottom.add(SetupButton.getInstance(x, y));
    
    	x += gap;
    	bottom.add(LogsButton.getInstance(x, y));
    	x += gap;

    	bottom.add(OpenButton.getInstance(x, y));
         x += gap;
    	x = BTN_MARGIN_H;//Simo
    	y += vgap;///Simo
    	
    	bottom.add(SystemButton.getInstance(x, y));
    	
    	x += gap;
    	x = BTN_MARGIN_H;//Simo
    	
  

    }
	
	public static HomeScreen getInstance() {
        if (theHomeScreen == null) {
        	theHomeScreen = new HomeScreen();
        }
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theHomeScreen.addStatus();
        theHomeScreen.addTop();
        TopForm.getInstance(null).menuTopForm();
        return theHomeScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theHomeScreen = HomeScreen.getInstance();
		
		// Now ask the browser to make this screen active
Browser.getInstance().setScreen(theHomeScreen, previousScreen);
	}
}
