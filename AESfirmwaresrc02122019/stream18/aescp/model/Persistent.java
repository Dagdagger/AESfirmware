package stream18.aescp.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Persistent {
	public static final String WAS_LOADED = "wasLoaded";
	public static final String WILL_BE_SAVED = "willBeSaved";
	
	private List<ActionListener> listeners = new ArrayList<ActionListener>();
	
	public void addActionListener(ActionListener l) {
		listeners.add(l);
	}
	
	public void load(InputStream is) {
		loadObject(is);
		
		// fire "wasLoaded" ActionEvent
	    ActionEvent event = new ActionEvent(this, 0, WAS_LOADED);
	    for (ActionListener l : listeners) {
	      l.actionPerformed(event);
	    }
	}
	
	/**
	 * This method must be implemented by the concrete
	 * persistent class, to unmarshall itself from the IS
	 * 
	 * @param is
	 */
	protected abstract void loadObject(InputStream is);
	
	public void save(OutputStream os) {
		// fire "willBeSaved" ActionEvent
	    ActionEvent event = new ActionEvent(this, 0, WILL_BE_SAVED);
	    for (ActionListener l : listeners) {
	      l.actionPerformed(event);
	    }
	    
	    saveObject(os);
	}
	
	/**
	 * This method must be implemented by the concrete
	 * persistent class, to marshall itself to the OS
	 * 
	 * @param os
	 */
	protected abstract void saveObject(OutputStream os);
}