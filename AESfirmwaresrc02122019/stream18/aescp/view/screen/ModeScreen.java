package stream18.aescp.view.screen;

import stream18.aescp.Browser;

import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.mode.VacuumChamberButton;
import stream18.aescp.view.form.TopForm;

public class ModeScreen extends Screen {

	// Singleton
	static ModeScreen theModeScreen = null;
	private static String testModeScree;
	
	public static String getTestModeScree() {
		return testModeScree;
	}

	public static void setTestModeScree(String testModeScree) {
		ModeScreen.testModeScree = testModeScree;
	}

	public ModeScreen() {
		
		
    	super(true, true);
 

    	final int BTN_MARGIN_H = 20;
    	final int BTN_MARGIN_V = 30;
    	
    	// Add 5 buttons in two rows
    	int x = BTN_MARGIN_H;
    	int y = BTN_MARGIN_V;
    	int gap = (Browser.SCREEN_WIDTH - LEFT_WIDTH - (2*BTN_MARGIN_H) + 18) / 5;
    	int vgap = (BOTTOM_HEIGHT - (2*BTN_MARGIN_V)) / 2;


    	// Coordinates are relative to its container, the bottom panel
    // 	bottom.add(FlowButton.getInstance(x, y));
     //	x += gap;
    //	bottom.add(LeakButton.getInstance(x, y));
    //	x += gap;
    //	bottom.add(OcclusionButton.getInstance(x, y));
    //    x += gap;
      
    //	bottom.add(PressureChamberButton.getInstance(x, y));
    //	x += gap;

    //	x = BTN_MARGIN_H;
    //	y += vgap;
    	bottom.add(VacuumChamberButton.getInstance(x, y));
    	x += gap;

    	//x = BTN_MARGIN_H;
    	//y += vgap;

    	//bottom.add(LinkVChamberButton.getInstance(x, y));
    	//x += gap;
    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }
	
	public static ModeScreen getInstance() {
        if (theModeScreen == null) {
        	theModeScreen = new ModeScreen();
        }

        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theModeScreen.addStatus();
       theModeScreen.addTop();
        
         TopForm.getInstance(null).ModesTopForm();
        
      //  theModeScreen.addTopFormModeScreen();
        

        return theModeScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theModeScreen = ModeScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theModeScreen, previousScreen);
	 
	}
}
