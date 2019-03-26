package stream18.aescp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class TestStatus {
	
    // Set, to avoid duplicates
	protected Set<ActionListener> listeners = new HashSet<ActionListener>();

	public void addActionListener(ActionListener l) {
		listeners.add(l);
	}
	
	public enum Status {
		SELECT, READY, RUNNING, STOPPED, PASS, FAIL
	}
	
	Status theStatus;
	
	public TestStatus() {
		theStatus = Status.SELECT;
	}
	
	public void setStatus(Status newStatus) {
		this.theStatus = newStatus;
		// Notify to listeners that status changed
		fireActionEvent(0);
	}
	
	public boolean isPass() {
		return theStatus == Status.PASS;
	}
	
	public boolean isFail() {
		return theStatus == Status.FAIL;
	}
	
	
	public Status getStatus() {
		return this.theStatus;
		
	}
	
	public boolean isSelect() {
		return theStatus == Status.SELECT;
	}
	
	public boolean isReady() {
		return theStatus == Status.READY;
	}
	
	public boolean isRunning() {
		return theStatus == Status.RUNNING;
	}
	
	public String getTestStatusAsText() {
		switch(theStatus) {
			case SELECT:
				//return "SELECT";
			case READY:
				return "READY";
			case RUNNING:
				return "RUNNING";
			case FAIL:
				return "FAIL";
			case PASS:
				return "PASS";
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
