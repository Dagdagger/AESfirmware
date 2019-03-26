package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StopButton extends Button {
	private static final long serialVersionUID = 1L;

	protected static StopButton theStopButton = null;
	
	public StopButton() {
		super(BOTTOM_TYPE);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
    			if (isDisabled) return;
				super.mouseReleased(e);
				
				// Stop test: just notify the Controller
				// The controller should disable Stop and enable Start buttons
				fireActionEvent(STOP_TEST_REQ);
			}
		});
	}
	
	public void setDisabled(boolean disabled) {
		this.isDisabled = disabled;
		if (disabled) {
			config("resources/StopD.png", "Stop");
		} else {
			config("resources/StopR.png", "Stop", new Color(0xC92E1C));
		}
	}

	public static StopButton getInstance(int x, int y) {
		if (theStopButton == null) {
			theStopButton = new StopButton();
		}
		theStopButton.moveTo(x, y, 2);
		return theStopButton;
	}
	
	public static StopButton getInstance() {
		if (theStopButton == null) {
			theStopButton = new StopButton();
		}
		return theStopButton;
	}
}
