package stream18.aescp.view.button.system;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;

public class RemoveUserButton extends Button {
	private static final long serialVersionUID = 1L;
	private static RemoveUserButton theRemoveUserButton;
	
	public RemoveUserButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 0.75f, 0.75f);
		config("resources/system/useradmin.png", "Remove", new Color(0xE01C66));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// TODO: Notify
			}
		});
	}	

	public static RemoveUserButton getInstance(int x, int y) {
		if (theRemoveUserButton == null) {
			theRemoveUserButton = new RemoveUserButton(x, y);
		}
		return theRemoveUserButton;
	}
}
