package stream18.aescp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class TestUnit {
	
    // Set, to avoid duplicates
	protected Set<ActionListener> listeners = new HashSet<ActionListener>();

	public void addActionListener(ActionListener l) {
		listeners.add(l);
	}
	
	public enum Units {
		PSI, H2O, MBAR
	}
	
	Units theUnit;
	
	public TestUnit() {
		theUnit = theUnit.MBAR;
	}
	
	public void setUnit(Units newUnit) {
		this.theUnit = newUnit;
		// Notify to listeners that status changed
		fireActionEvent(0);
	}
	
	public Units getStatus() {
		return this.theUnit;
	}
	
	public boolean isPsi() {
		return theUnit == Units.MBAR;
	}
	
	public boolean isH20() {
		return theUnit == Units.H2O;
	}
	
	 
	
	public String getTestStatusAsText() {
		switch(theUnit) {
			case MBAR:
				return "mBar";
			case H2O:
				return "H2O";
		 
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
