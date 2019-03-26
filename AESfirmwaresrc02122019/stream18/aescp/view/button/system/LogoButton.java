package stream18.aescp.view.button.system;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.system.UserAdminScreen;

public class LogoButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static LogoButton theUserAdminButton = null;
	
	public LogoButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/system/AES_logo.png", "Operator", new Color(0xaaaaaa));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				UserAdminScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static LogoButton getInstance(int x, int y) {
		if (theUserAdminButton == null) {
			theUserAdminButton = new LogoButton(x, y);
		}
		return theUserAdminButton;
	}
}
