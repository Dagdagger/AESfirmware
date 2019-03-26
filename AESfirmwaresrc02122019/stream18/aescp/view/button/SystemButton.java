package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.screen.SystemScreen;

public class SystemButton extends Button {
	private static final long serialVersionUID = 1L;
	private static SystemButton theSystemButton;

	public SystemButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/System.png", "System", new Color(0x3D3C38));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				SystemScreen.getInstance().setActive(null);
			}	
		});
	}
	
	public static SystemButton getInstance(int x, int y) {
		if (theSystemButton == null) {
			theSystemButton = new SystemButton(x, y);
		}
		return theSystemButton;
	}
}
