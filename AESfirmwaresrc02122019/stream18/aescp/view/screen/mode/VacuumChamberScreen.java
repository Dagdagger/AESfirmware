package stream18.aescp.view.screen.mode;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.form.mode.VacuumChamberForm;
import stream18.aescp.view.form.mode.VacuumChamberResultsForm;
import stream18.aescp.view.form.mode.VacuumChamberSettingsForm;
/**
 * @author Simo
 */
public class VacuumChamberScreen extends SK_ModeScreen {

	// Singleton
	static VacuumChamberScreen theVacuumChamberScreen = null;
	
	public VacuumChamberScreen() {
    	super("Vacuum Chamber",
    			VacuumChamberResultsForm.getInstance(null),
    			VacuumChamberForm.getInstance(null),
    			VacuumChamberSettingsForm.getInstance(null));

    	this.setTestMode(Mode.VACUUMCHAMBER);
    	System.out.println("Mode set: " +this.getTestMode());
    }

	public static VacuumChamberScreen getInstance() {
       if (theVacuumChamberScreen == null) {
    	   theVacuumChamberScreen = new VacuumChamberScreen();
        }
       		theVacuumChamberScreen.addStatus();
       		theVacuumChamberScreen.addTop();
       	
       	 TopForm.getInstance(null).readyModeTopForm();
        
		return 
			theVacuumChamberScreen;
	}

}
