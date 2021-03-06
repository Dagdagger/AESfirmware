package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.screen.OpenScreen;

public class OpenButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static OpenButton theOpenButton = null;
	
	public OpenButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/Open.png", "Open", new Color(0x8E26CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				OpenScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static OpenButton getInstance(int x, int y) {
		if (theOpenButton == null) {
			theOpenButton = new OpenButton(x, y);
		}
		return theOpenButton;
	}
}
