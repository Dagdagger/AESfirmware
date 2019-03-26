package stream18.aescp.view.screen;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import stream18.aescp.Browser;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.form.StatusForm;
import stream18.aescp.view.form.TopForm;


/*
 * This is the base for all the Screens in the application
 * Screens have FOUR areas (each one represented by one JPanel): left, top, bottom and status
 * Each concrete Screen class customizes the areas
 * The top and status areas contain Form: TopForm and StatusForm
 */

public abstract class Screen {
    public static final int LEFT_WIDTH = 60;
    public static final int TOP_HEIGHT = 50;
    public static final int BOTTOM_HEIGHT = 380;
    public static final int STATUS_HEIGHT = 50;
     
    // Set, to avoid duplicates
	protected Set<ActionListener> listeners = new HashSet<ActionListener>();
	
	public void addActionListener(ActionListener l) {
		listeners.add(l);
	}
    
	/** The swing canvas for displaying the rendered document */
    protected JPanel screenCanvas;
    
    // Used to navigate
    protected Screen previousScreen;

	protected boolean hasStatus;
	protected boolean hasTop;
    
	protected JPanel leftSide;
	protected JPanel top;
//	protected JPanel vk_top;
	protected JPanel bottom;
	protected JPanel status;
    
    public Screen(boolean hasStatus, boolean hasTop) {
    	this.hasStatus = hasStatus;
    	this.hasTop = hasTop;
    	
    	screenCanvas = new JPanel();
    	screenCanvas.setLayout(null);
    	
    	leftSide = new JPanel();
    	leftSide.setLayout(null);
    	leftSide.setBackground(new Color(0x0D1738));
    	leftSide.setBounds(new Rectangle(0,  0,  LEFT_WIDTH, Browser.SCREEN_HEIGHT));
    	screenCanvas.add(leftSide);
    	
    

// This is moved to VKScreens
//    	vk_top = new JPanel();
//    	vk_top.setLayout(null);
//    	vk_top.setBackground(new Color(0xaaaaaa));
//    	vk_top.setBounds(new Rectangle(LEFT_WIDTH,  0,  Browser.SCREEN_WIDTH, TOP_HEIGHT));
//    	screenCanvas.add(vk_top);
//    	vk_top.setVisible(false);
    	
    	bottom = new JPanel();
    	bottom.setLayout(null);
    	bottom.setBackground(new Color(0x29315D));
    	if (hasStatus) {
    		bottom.setBounds(new Rectangle(LEFT_WIDTH,  TOP_HEIGHT,  Browser.SCREEN_WIDTH, BOTTOM_HEIGHT));
    	} else {
    		bottom.setBounds(new Rectangle(LEFT_WIDTH,  TOP_HEIGHT,  Browser.SCREEN_WIDTH, BOTTOM_HEIGHT + STATUS_HEIGHT));    		
    	}
    	screenCanvas.add(bottom);
    }
    
	protected void addStatus() {
    	if (hasStatus) {
    		status = StatusForm.getInstance(this);
    		screenCanvas.add(status);
    	}
	}

	protected void addTop() {
    	if (hasTop) {
    		top = TopForm.getInstance(this);
    		screenCanvas.add(top);
    	}
	}
 


	
	public Screen getPreviousScreen() {
		return previousScreen;
	}

	public void setPreviousScreen(Screen previousScreen) {
		this.previousScreen = previousScreen;
	}
    
	public Component getScreenCanvas() {
		return screenCanvas;
	}
			
	// Can be overridden
	// The postCreationAction is executed just after the
	// Browser has reloaded this screen
	public void postCreationAction() {}
	
	// The previous Screen can be set in case there is a "Back" button,
	// or left null
	public abstract void setActive(Screen previousScreen);

	protected static JComponent makeFormPanel(Form form) {
	    JPanel panel = new JPanel(false);
	    panel.setLayout(new GridLayout(1, 1));
	    panel.add(form);
	    return panel;
	}

	protected static ImageIcon createImageIcon(String path) {
	    java.net.URL imgURL = SetupScreen.class.getResource("/" + path);
	    if (imgURL != null) {
	        ImageIcon icon = new ImageIcon(imgURL);
	        Image image = icon.getImage(); // transform it 
	        Image newimg = image.getScaledInstance(SetupScreen.ICON_SIZE , SetupScreen.ICON_SIZE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	        return new ImageIcon(newimg);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	
	protected void fireActionEvent(int id) {
	    ActionEvent event = new ActionEvent(this, id, null);
	    for (ActionListener l : listeners) {
	      l.actionPerformed(event);
	    }
	}
	

	

 
}
