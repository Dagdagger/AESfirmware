package stream18.aescp.view.screen.mode;

import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.form.mode.LeakForm;
import stream18.aescp.view.form.mode.LeakResultsForm;
import stream18.aescp.view.form.mode.LeakSettingsForm;

/**
 * @author egarcia
 */
public class LeakScreen_copy extends SK_ModeScreen {

	// Singleton
	static LeakScreen_copy theLeakScreen = null;
	
	public LeakScreen_copy() {
    	super("Leak",
    			LeakForm.getInstance(null),
    			LeakResultsForm.getInstance(null),
    			LeakSettingsForm.getInstance(null));
    	
    	this.setTestMode(Mode.LEAK);
    }

	public static LeakScreen_copy getInstance() {
        if (theLeakScreen == null) {
        	theLeakScreen = new LeakScreen_copy();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theLeakScreen.addStatus();

        theLeakScreen.addTop();

        return theLeakScreen;
	}
}
