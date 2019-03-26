package stream18.aescp.view.button.mode;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.mode.VerifyCalScreen;

public class VerifyCalButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static VerifyCalButton theVerifyCalButton = null;
	
	public VerifyCalButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/mode/VerifyCal.png", "Verify Cal.", new Color(0x697519));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				VerifyCalScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static VerifyCalButton getInstance(int x, int y) {
		if (theVerifyCalButton == null) {
			theVerifyCalButton = new VerifyCalButton(x, y);
		}
		return theVerifyCalButton;
	}
}
