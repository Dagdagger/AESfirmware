package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.Browser;
import stream18.aescp.view.screen.Screen;

public class CancelButton extends Button {
	private static final long serialVersionUID = 1L;

	public static int DEFAULT_X = 0;
	public static int DEFAULT_Y = Browser.SCREEN_HEIGHT-BUTTON_L_HEIGHT;

	private Screen previousScreen;
	
	public CancelButton(int x, int y) {
		super(LEFT_TYPE);
		moveTo(x, y, 1);
		// Adds the image and text
		config("resources/Cancel.png", null, new Color(0xC92E1C));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				navigatePrevious();
			}
		});

	}
	
	public void navigatePrevious() {
		previousScreen.setActive(previousScreen != null ? previousScreen.getPreviousScreen() : null);
	}

	public void setPreviousScreen(Screen previousScreen) {
		this.previousScreen = previousScreen;
	}
}
