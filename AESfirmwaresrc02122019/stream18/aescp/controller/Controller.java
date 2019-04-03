package stream18.aescp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import stream18.aescp.Browser;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.controller.TestPhase.Phase;
import stream18.aescp.controller.TestStatus.Status;
import stream18.aescp.controller.phaser.TestPhaser;
import stream18.aescp.drivers.ADCDriver;
import stream18.aescp.model.Persistent;
import stream18.aescp.model.PhaseCfg;
import stream18.aescp.model.Program;
import stream18.aescp.model.Test;
import stream18.aescp.model.TestModeCfgManager;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.button.StartButton;
import stream18.aescp.view.button.StopButton;
import stream18.aescp.view.button.system.LoginButton;
import stream18.aescp.view.form.ProgramForm;
import stream18.aescp.view.form.StatusForm;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.form.mode.ResultsForm;
import stream18.aescp.view.form.mode.SettingsForm;
import stream18.aescp.view.form.mode.VacuumChamberResultsForm;
import stream18.aescp.view.form.mode.VacuumChamberSettingsForm;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.screen.mode.SK_ModeScreen;

/**
 * 
 * @author egarcia
 *
 * The Controller registers itself (on Screens or Buttons) as Listener of Events
 * Both Screens and Buttons include:
 * 	- an internal list of listeners
 *  - a public method for Controller to register as listener
 *  - a fireActionEvent() called internally when the Button or Screen needs to notify the Controller
 */

public class Controller {
	
	private static Controller theController;
	private static TestStatus theTestStatus;
	private static TestMode theTestMode;
	private static TestLogger theLogger;
	private static TestPhase theTestPhaseVar;
	private static TestPhaser theTestPhaser; 
	private static TestModeCfgManager theTestModeCfgManager;
	private static TestVars theTestVars;
	

	public Controller() {
		 
		theTestStatus = new TestStatus();
		theTestMode = new TestMode();
		theLogger = TestLogger.getInstance();
		// We need to have a reference to this variable to assign it a listener,
		// for us to be able to change the phase label on the UI
		theTestPhaseVar = new TestPhase();
		theTestVars= new TestVars();	
		
	 
		// Register as observer for some events
		StartButton.getInstance().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
			 
				switch(event.getID()) {
				
					case Button.START_TEST_REQ:
						if (!theTestStatus.isReady()) {
							theLogger.logSys("Trying to start test while not system not ready");
							return;
						}	
						// We can change status to Running now. It will trigger and event to change
						// the status label in the status form
						theTestStatus.setStatus(Status.RUNNING);	
						// We create a new testPhaser instance for each new test execution
						// Check if a previous thread was running
						if (theTestPhaser != null && theTestPhaser.getState() != Thread.State.TERMINATED) {							
						System.out.println("Previous thread still on execution. But if will continue in a new Thread.");
						 
						}
						if(theTestPhaser == null) {	
					 	  theTestPhaser = new TestPhaser(theTestPhaseVar);	
					 	  theTestPhaser.startNewTest(theTestMode.getTestMode());
						}
				}
			}
			
		});
		
		// Register as observer for some events
		StopButton.getInstance().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				switch(event.getID()) {
					case Button.STOP_TEST_REQ:	
						System.out.println("Test stop requested!");
						boolean bleedValves[] = {true, true, false, false, false,false, false, false, false, true};
						boolean cleanValves[] = {false, false, false, false, false,false, false, false, false, false};
						// We can change status to READY now. It will trigger and event to change
						// the status label in the status form
						theTestStatus.setStatus(Status.SELECT);	
						
if (theTestPhaseVar.getPhase() != Phase.RESULTS && theTestPhaseVar.getPhase() != Phase.H_BACK && theTestPhaseVar.getPhase() != Phase.FAIL && theTestPhaseVar.getPhase() != Phase.V_BACK) {
	theTestPhaser.interrupt();

						theTestPhaser.setStopCase(true);
						System.out.println("Test stop requested!");
						//theTestPhaser.interrupt();

						}
if (theTestPhaseVar.getPhase() == Phase.RESULTS) {
	
	theTestStatus.setStatus(Status.READY);	
	theTestPhaseVar.setPhase(Phase.FINISHED);
	
}else {

						// TODO: Stop the test
						//ADCDriver.sendData(bleedValves);
						//theTestPhaser.setPhaseVar(Phase.FAIL);
						ResultsForm results = theTestPhaser.getResultsForm();
						results.setResultFieldWait(true);
						theTestPhaser.interrupt();

						
						System.out.println("Test stop requested!");
						ADCDriver.sendData(bleedValves);
}
				}
			}
		});

		LoginButton.getInstance().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				switch(event.getID()) {
					case Button.AUTHENTICATE_REQ:
						// TODO: Authenticate
						// We will get login and password, and will check against the User's DB
						System.out.println("Authentication requested!");
				}
			}
		});
		
		// All the SK_ModeScreens share a common listener
		SK_ModeScreen.addCommonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				switch(event.getID()) {
					// For the time being just one event comes from SK_ModeScreens
					// It is triggered each time a new Test mode is selected
					case 0:
						System.out.println("SK_ModeScreen action event notified. Source: " + event.getSource());

						SK_ModeScreen theConcreteModeScreen = (SK_ModeScreen) event.getSource();
						Screen previousScreen = theConcreteModeScreen.getPreviousScreen();
						Browser.getInstance().setScreen(theConcreteModeScreen, previousScreen);
						
						theTestMode.setTestMode(theConcreteModeScreen.getTestMode());

						// As a new TestMode has been selected, we are Ready to Test,
						// unless we are already testing!
						if (theTestStatus.getStatus() != Status.RUNNING) {
							theTestStatus.setStatus(Status.READY);
						}
						// Now update the Test Mode name
						TopForm.getInstance(null).setTestMode(theTestMode.getTestModeAsText());	
					
						 
					break;
				}
			}
		});
		
		theTestStatus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// It does not mind the event ID, only the new Status is checked
				// I could use also theTestStatus instead of status.
				TestStatus status = (TestStatus) event.getSource();
				switch (status.getStatus()) {
					case SELECT:			
						// Disable both Start and Stop buttons
						StartButton.getInstance().setDisabled(true);						
						StopButton.getInstance().setDisabled(true);
					
						break;
					case READY:
						// Enable Start, disable Stop
						StartButton.getInstance().setDisabled(false);
						StopButton.getInstance().setDisabled(true);
					 
						break;
					case RUNNING:
						// Disable Start, enable Stop
						StartButton.getInstance().setDisabled(true);
						StopButton.getInstance().setDisabled(false);						
						break;
					default:
						break;
				}
				((StatusForm)StatusForm.getInstance(null)).setStatusText(status.getTestStatusAsText());
			}
		});

		// Get notified when a new program has been loaded
		((ProgramForm)ProgramForm.getInstance(null)).getTheProgram().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				if (action.getActionCommand().equals(Persistent.WAS_LOADED)) {
					// Change the name in the TopForm
					// Now update the Test Mode name
					Program program = (Program) action.getSource();
					TopForm.getInstance(null).setProgName(program.getProgram());
					 
					 
				}
			}
		});
		
		theTestPhaseVar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				// When a new phase starts I am notified here by the TestPhaser thread
				TopForm.getInstance(null).setTestPhase(theTestPhaseVar.getTestPhaseAsText());
							 
				// If the new Phase is FINISHED, then change theTestStatus back to READY
				// This will re-enable the Start button
				if (theTestPhaseVar.getPhase() == Phase.FINISHED) {
					theTestStatus.setStatus(Status.READY);
				 
				}
			}
		});
	}
	
	public static Controller getInstance() {
		if (theController == null) {
			theController = new Controller();
		}
		return theController;
	}
}
