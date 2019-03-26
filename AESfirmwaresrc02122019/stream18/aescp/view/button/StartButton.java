package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.model.TestModeCfg;



public class StartButton extends Button {
	private static final long serialVersionUID = 1L;
	protected static StartButton theStartButton = null;
	
	public StartButton() {
		super(BOTTOM_TYPE);
	 
	 
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
    			if (isDisabled) return;
				super.mouseReleased(e);			
				// Start test: just notify the Controller
				// The controller should disable Start and enable Stop buttons
				
				fireActionEvent(START_TEST_REQ);
				
				
			}
		});
 
	}
	
	public void setDisabled(boolean disabled) {
		this.isDisabled = disabled;
		if (disabled) {
			config("resources/StartD.png", "Start");

		} else {
			config("resources/StartG.png", "Start", new Color(0x00aa00));

		}
	}
	
	public static StartButton getInstance(int x, int y) {
		if (theStartButton == null) {
			theStartButton = new StartButton();
		}
		theStartButton.moveTo(x, y, 2);
		return theStartButton;
	}
	
	public static StartButton getInstance() {
		if (theStartButton == null) {
			theStartButton = new StartButton();
		}
		return theStartButton;
	}
	
	public void  setwithheight() {
		if (theStartButton == null) {
			theStartButton = new StartButton();	 
		}	 
	}

}
