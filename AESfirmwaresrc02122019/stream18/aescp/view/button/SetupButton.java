package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.screen.SetupScreen;

public class SetupButton extends Button {
	private static final long serialVersionUID = 1L;
	private static SetupButton theSetupButton;
	
	public SetupButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/Setup.png", "Setup", new Color(0x2F9586));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				SetupScreen.getInstance().setActive(null);
			}	
		});
	}
	
	public static SetupButton getInstance(int x, int y) {
		if (theSetupButton == null) {
			theSetupButton = new SetupButton(x, y);
		}
		return theSetupButton;
	}
}
