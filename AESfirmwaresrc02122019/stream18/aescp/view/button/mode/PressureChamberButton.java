package stream18.aescp.view.button.mode;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.mode.PressureChamberScreen;

public class PressureChamberButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static PressureChamberButton thePressureChamberButton = null;
	
	public PressureChamberButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/mode/PressureChamber.png", "P-Chamber", new Color(0xE01C66));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				PressureChamberScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static PressureChamberButton getInstance(int x, int y) {
		if (thePressureChamberButton == null) {
			thePressureChamberButton = new PressureChamberButton(x, y);
		}
		return thePressureChamberButton;
	}
}
