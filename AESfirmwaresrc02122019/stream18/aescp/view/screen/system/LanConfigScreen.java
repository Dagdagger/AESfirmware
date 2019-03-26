package stream18.aescp.view.screen.system;


import stream18.aescp.Browser;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.SystemNavButton;
import stream18.aescp.view.form.system.LanConfigForm;
import stream18.aescp.view.screen.Screen;

public class LanConfigScreen extends Screen {

	// Singleton
	static LanConfigScreen theLanConfigScreen = null;
	
	public LanConfigScreen() {
    	super(true, true);

    	// This screen just contains a form: the LanConfig form
    	bottom.add(LanConfigForm.getInstance(this));
    	LanConfigForm.getInstance(this).setBounds(0, 0, bottom.getWidth(), bottom.getHeight());
    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new SystemNavButton(SystemNavButton.DEFAULT_X, SystemNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }
	
	public static LanConfigScreen getInstance() {
        if (theLanConfigScreen == null) {
        	theLanConfigScreen = new LanConfigScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theLanConfigScreen.addStatus();
        
        return theLanConfigScreen;
	}
	
    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theLanConfigScreen = LanConfigScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theLanConfigScreen, previousScreen);
	}
}