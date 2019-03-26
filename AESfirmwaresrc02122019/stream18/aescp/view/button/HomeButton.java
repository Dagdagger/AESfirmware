package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.Browser;
import stream18.aescp.controller.Controller;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.screen.HomeScreen;

public class HomeButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static HomeButton theHomeButton = null;

	public static int DEFAULT_X = 0;
	public static int DEFAULT_Y = Browser.SCREEN_HEIGHT-BUTTON_L_HEIGHT;
	
	public HomeButton(int x, int y) {
		super(LEFT_TYPE);
		moveTo(x, y, 1);
		// Adds the image and text
		config("resources/Home.png", null, new Color(0x3C90CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
			
				HomeScreen.getInstance().setActive(null);
			 
			}
		});

	}
	
	public static HomeButton getInstance(int x, int y) {
		if (theHomeButton == null) {
			theHomeButton = new HomeButton(x, y);
		}
		return theHomeButton;
	}
}
