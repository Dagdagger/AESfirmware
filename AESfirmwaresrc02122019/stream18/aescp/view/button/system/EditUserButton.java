package stream18.aescp.view.button.system;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.Browser;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.HomeScreen;
import stream18.aescp.view.screen.SystemScreen;
import stream18.aescp.view.screen.system.EditUserScreen;
import stream18.aescp.view.screen.system.UserAdminScreen;

public class EditUserButton extends Button {
	private static final long serialVersionUID = 1L;
	private static EditUserButton theEditUserButton;
	
	public EditUserButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 0.75f, 0.75f);
		config("resources/system/useradmin.png", "Edit User", new Color(0xF07746));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				System.out.println("Editing User!");
				Browser.getInstance().setScreen(EditUserScreen.getInstance(), HomeScreen.getInstance());
//				EditUserScreen.getInstance().setActive(UserAdminScreen.getInstance());
				// TODO: notify controller, so it loads the ID of the selected user
			}
		});
	}	

	public static EditUserButton getInstance(int x, int y) {
		if (theEditUserButton == null) {
			theEditUserButton = new EditUserButton(x, y);
		}
		return theEditUserButton;
	}
}
