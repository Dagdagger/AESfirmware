package stream18.aescp.view.button.mode;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.button.ReadyScreenButton;
import stream18.aescp.view.screen.mode.FlowScreen;
import stream18.aescp.view.screen.mode.VacuumChamberScreen;

public class VacuumChamberButton extends Button {
	private static final long serialVersionUID = 1L;
	private static String testMode;
	
public static String getTestMode() {
		return testMode;
	}

	public static void setTestMode(String testMode) {
		VacuumChamberButton.testMode = testMode;
		ReadyScreenButton.setTestMode("VacuumChamber"); 
	}
	protected static VacuumChamberButton theVacuumChamberButton = null;
	
	public VacuumChamberButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/mode/VacuumChamber.png", "V-Chamber", new Color(0x4000ff));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				VacuumChamberScreen.getInstance().setActive(null);
				setTestMode("VacuumChamber");
			 
			}	
		});
	}
		
	public static VacuumChamberButton getInstance(int x, int y) {
		if (theVacuumChamberButton == null) {
			theVacuumChamberButton = new VacuumChamberButton(x, y);
		}
		return theVacuumChamberButton;
	}
}
