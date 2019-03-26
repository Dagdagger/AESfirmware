package stream18.aescp.view.screen.mode;

import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.form.mode.VerifyCalForm;
import stream18.aescp.view.form.mode.VerifyCalResultsForm;
import stream18.aescp.view.form.mode.VerifyCalSettingsForm;

/**
 * @author egarcia
 */
public class VerifyCalScreen extends SK_ModeScreen {

	// Singleton
	static VerifyCalScreen theVerifyCalScreen = null;
	
	public VerifyCalScreen() {
    	super("Verify Cal",
    			VerifyCalForm.getInstance(null),
    			VerifyCalResultsForm.getInstance(null),
    			VerifyCalSettingsForm.getInstance(null));
    	
    	this.setTestMode(Mode.CAL);
    }

	public static VerifyCalScreen getInstance() {
        if (theVerifyCalScreen == null) {
        	theVerifyCalScreen = new VerifyCalScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theVerifyCalScreen.addStatus();

        theVerifyCalScreen.addTop();

        return theVerifyCalScreen;
	}
}
