package stream18.aescp.view.button.mode;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.button.ReadyScreenButton;
import stream18.aescp.view.screen.mode.LeakScreen;

public class LeakButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static LeakButton theLeakButton = null;
	private static String testMode;
	public static String getTestMode() {
		return testMode;
	}

	public static void setTestMode(String testMode) {
		LeakButton.testMode = testMode;
		ReadyScreenButton.setTestMode("Leak"); 
	}

	public LeakButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/mode/Leak.png", "Leak", new Color(0xE69440));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				LeakScreen.getInstance().setActive(null);
				setTestMode("Leak");
			}	
		});
	}
		
	public static LeakButton getInstance(int x, int y) {
		if (theLeakButton == null) {
			theLeakButton = new LeakButton(x, y);
		}
		return theLeakButton;
	}
}
