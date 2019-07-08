package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.form.system.AddUserForm;
import stream18.aescp.view.form.system.EditUserForm;
import stream18.aescp.view.form.system.UserAdminForm;

public class UpdateButton extends Button {
	private static final long serialVersionUID = 1L;
	private static UpdateButton theUpdateButton;
	
	public UpdateButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 0.75f, 0.75f);
		config("resources/Save.png", "Update", new Color(0x3C90CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				EditUserForm f = (EditUserForm) EditUserForm.getInstance(null);
				f.change();
			}
		});
	}	

	public static UpdateButton getInstance(int x, int y) {
		if (theUpdateButton == null) {
			theUpdateButton = new UpdateButton(x, y);
		}
		return theUpdateButton;
	}
}
