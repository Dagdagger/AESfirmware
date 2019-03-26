package stream18.aescp.view.screen.mode;

import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.form.mode.PressureChamberForm;
import stream18.aescp.view.form.mode.PressureChamberResultsForm;
import stream18.aescp.view.form.mode.PressureChamberSettingsForm;

/**
 * @author Simo
 */
public class PressureChamberScreen extends SK_ModeScreen {

	// Singleton
	static PressureChamberScreen thePressureChamberScreen = null;
	
	public PressureChamberScreen() {
    	super("PressureChamber",
    			
    			PressureChamberResultsForm.getInstance(null),
    			PressureChamberForm.getInstance(null),
    			PressureChamberSettingsForm.getInstance(null));
    	
    	this.setTestMode(Mode.PRESSURECHAMBER);
    }

	public static PressureChamberScreen getInstance() {
       if (thePressureChamberScreen == null) {
        	thePressureChamberScreen = new PressureChamberScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
       thePressureChamberScreen.addStatus();
       thePressureChamberScreen.addTop();

    //   return thePressureChamberScreen;
		return thePressureChamberScreen;
	}
}
