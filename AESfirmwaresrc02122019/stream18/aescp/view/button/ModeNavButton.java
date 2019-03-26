package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.Browser;
import stream18.aescp.view.screen.ModeScreen;

public class ModeNavButton extends Button {
	private static final long serialVersionUID = 1L;

	private static final int VGAP = 10;

	public static int DEFAULT_X = 0;
	public static int DEFAULT_Y = Browser.SCREEN_HEIGHT-(BUTTON_L_HEIGHT * 2) - VGAP;
	
	public ModeNavButton(int x, int y) {
		super(LEFT_TYPE);
		moveTo(x, y, 1);
		// Adds the image and text
		config("resources/Mode.png", null, new Color(0xF07746));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				ModeScreen.getInstance().setActive(null);
			}
		});

	}
}
