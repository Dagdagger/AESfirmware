package stream18.aescp.view.screen.mode;

import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.form.mode.VacuumForm;
import stream18.aescp.view.form.mode.VacuumResultsForm;
import stream18.aescp.view.form.mode.VacuumSettingsForm;

/**
 * @author egarcia
 */
public class VacuumScreen extends SK_ModeScreen {

	// Singleton
	static VacuumScreen theVacuumScreen = null;
	
	public VacuumScreen() {
    	super("Vacuum",
    			VacuumForm.getInstance(null),
    			VacuumResultsForm.getInstance(null),
    			VacuumSettingsForm.getInstance(null));
    	
    	this.setTestMode(Mode.VACUUM);
    }

	public static VacuumScreen getInstance() {
        if (theVacuumScreen == null) {
        	theVacuumScreen = new VacuumScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theVacuumScreen.addStatus();

        theVacuumScreen.addTop();

        return theVacuumScreen;
	}
}
