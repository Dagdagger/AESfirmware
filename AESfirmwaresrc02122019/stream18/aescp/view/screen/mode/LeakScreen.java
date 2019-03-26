package stream18.aescp.view.screen.mode;

import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.form.mode.LeakForm;
import stream18.aescp.view.form.mode.LeakResultsForm;
import stream18.aescp.view.form.mode.LeakSettingsForm;

/**
 * @author egarcia
 */
public class LeakScreen extends SK_ModeScreen {

	// Singleton
	static LeakScreen theLeakScreen = null;
	
	public LeakScreen() {
    	super("Leak",
    			LeakResultsForm.getInstance(null),
    			LeakForm.getInstance(null),
    			LeakSettingsForm.getInstance(null));
    	
    	this.setTestMode(Mode.LEAK);
    }

	public static LeakScreen getInstance() {
        if (theLeakScreen == null) {
        	theLeakScreen = new LeakScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theLeakScreen.addStatus();

        theLeakScreen.addTop();

        return theLeakScreen;
	}
}
