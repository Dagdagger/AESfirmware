package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.screen.ModeScreen;
import stream18.aescp.view.screen.Screen;

public class ModeButton extends Button {
	private static final long serialVersionUID = 1L;
	
	public ModeButton(int x, int y, Screen ps) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/Mode.png", "Mode", new Color(0xF07746));
		
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
