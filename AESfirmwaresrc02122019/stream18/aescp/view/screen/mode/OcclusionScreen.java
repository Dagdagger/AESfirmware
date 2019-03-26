package stream18.aescp.view.screen.mode;

import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.form.mode.OcclusionForm;
import stream18.aescp.view.form.mode.OcclusionResultsForm;
import stream18.aescp.view.form.mode.OcclusionSettingsForm;

/**
 * @author egarcia
 */
public class OcclusionScreen extends SK_ModeScreen {

	// Singleton
	static OcclusionScreen theOcclusionScreen = null;
	
	public OcclusionScreen() {
    	super("Occlusion",
    			OcclusionResultsForm.getInstance(null),
    			OcclusionForm.getInstance(null),
    			
    			OcclusionSettingsForm.getInstance(null));
    	
    	this.setTestMode(Mode.OCCLUSION);
    }

	public static OcclusionScreen getInstance() {
        if (theOcclusionScreen == null) {
        	theOcclusionScreen = new OcclusionScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theOcclusionScreen.addStatus();

        theOcclusionScreen.addTop();

        return theOcclusionScreen;
	}
}
