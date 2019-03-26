package stream18.aescp.view.screen.mode;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.form.mode.FlowForm;
import stream18.aescp.view.form.mode.FlowResultsForm;
import stream18.aescp.view.form.mode.FlowSettingsForm;

/**
 * @author egarcia
 */
public class FlowScreen extends SK_ModeScreen {

	// Singleton
	static FlowScreen theFlowScreen = null;
 
	
	public FlowScreen() {
    	super("Flow",
    			
    			FlowResultsForm.getInstance(null),
    			FlowForm.getInstance(null),
    			FlowSettingsForm.getInstance(null));
    	
    	this.setTestMode(Mode.FLOW);
 
     }

	public static FlowScreen getInstance() {
        if (theFlowScreen == null) {
        	theFlowScreen = new FlowScreen();
        }
        
        theFlowScreen.addStatus();
        theFlowScreen.addTop();
        return theFlowScreen;
	}
}
