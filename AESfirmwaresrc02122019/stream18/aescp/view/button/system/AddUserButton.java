package stream18.aescp.view.button.system;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.Browser;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.HomeScreen;
import stream18.aescp.view.screen.system.AddUserScreen;
import stream18.aescp.view.screen.system.EditUserScreen;

public class AddUserButton extends Button {
	private static final long serialVersionUID = 1L;
	private static AddUserButton theAddUserButton;
	
	public AddUserButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 0.75f, 0.75f);
		config("resources/system/useradmin.png", "Add User", new Color(0x3C90CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				Browser.getInstance().setScreen(AddUserScreen.getInstance(), HomeScreen.getInstance());			}
		});
	}	

	public static AddUserButton getInstance(int x, int y) {
		if (theAddUserButton == null) {
			theAddUserButton = new AddUserButton(x, y);
		}
		return theAddUserButton;
	}
}
