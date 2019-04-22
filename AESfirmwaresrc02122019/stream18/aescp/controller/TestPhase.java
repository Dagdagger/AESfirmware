package stream18.aescp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.model.PhaseCfg;
import stream18.aescp.model.Program;
import stream18.aescp.model.TestModeCfg;
import stream18.aescp.model.TestModeCfgManager;

public class TestPhase {
	
	
	boolean pass;
	// Set, to avoid duplicates
	protected Set<ActionListener> listeners = new HashSet<ActionListener>();

	public void addActionListener(ActionListener l) {
		listeners.add(l);
	}
	
	public void setPass(boolean pass) {
		this.pass = pass;
	}
	
	public boolean getPass() {
		return this.pass;
	}
	
	public static enum Phase {
		READY, H_FWD, V_FWD, FILL, SETTLE, TEST, VENT, V_BACK, H_BACK, FINISHED, EXHAUST, FAIL, PASS, RESULTS, STOPPED, STOP_BACK, STOP_HBACK
	}
 
	Phase thePhase;

	public TestPhase() {
		thePhase = Phase.READY;
	}
	
	public void setPhase(Phase newPhase) {
		this.thePhase = newPhase;
		// Notify to listeners that phase has changed
		fireActionEvent(0);
 	}
	
	public Phase getPhase() {
		return this.thePhase;
	}
		
	public String getTestPhaseAsText() {
		

		switch(thePhase) {
			case PASS:
				return "Pass";
			case FAIL:
				return "Fail";
			case READY:		
				return "Ready";
			case H_FWD:
				return "Insert";
			case V_FWD:
				return "Lid Close";
			case EXHAUST:
				return "Exhaust";
			case FILL:			 
				return "Fill";
			case SETTLE:
				return "Settle";
			case TEST:
				return "Test";
			case VENT:
				return "Vent";
			case V_BACK:
				return "Lid Open";
			case H_BACK:
				return "Eject";
			case FINISHED:
				return "Finished";
			case RESULTS:
				return "Results";
			case STOPPED:
				return "Stopped";
			case STOP_BACK:
				return "Return";
			case STOP_HBACK:
				return "Return";
				 
		}
		return "?";
	}	
	
	protected void fireActionEvent(int id) {
	    ActionEvent event = new ActionEvent(this, id, null);
	    for (ActionListener l : listeners) {
	      l.actionPerformed(event);
	    }
	}
	
	
}
