package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.Browser;
import stream18.aescp.view.screen.SystemScreen;

public class SystemNavButton extends Button {
	private static final long serialVersionUID = 1L;
	private static SystemNavButton theSystemNavButton;

	private static final int VGAP = 10;

	public static int DEFAULT_X = 0;
	public static int DEFAULT_Y = Browser.SCREEN_HEIGHT-(BUTTON_L_HEIGHT * 2) - VGAP;
	
	public SystemNavButton(int x, int y) {
		super(LEFT_TYPE);
		moveTo(x, y, 1);
		config("resources/System.png", "", new Color(0x3D3C38));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				SystemScreen.getInstance().setActive(null);
			}
		});
	}
	
	public static SystemNavButton getInstance(int x, int y) {
		if (theSystemNavButton == null) {
			theSystemNavButton = new SystemNavButton(x, y);
		}
		return theSystemNavButton;
	}
}
