package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.form.system.AddUserForm;
import stream18.aescp.view.form.system.EditUserForm;
import stream18.aescp.view.form.system.UserAdminForm;

public class OkButton extends Button {
	private static final long serialVersionUID = 1L;
	private static OkButton theOkButton;
	
	public OkButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 0.75f, 0.75f);
		config("resources/Save.png", "Add", new Color(0x3C90CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				AddUserForm f = (AddUserForm) AddUserForm.getInstance(null);
				f.add();
				
			}
		});
	}	

	public static OkButton getInstance(int x, int y) {
		if (theOkButton == null) {
			theOkButton = new OkButton(x, y);
		}
		return theOkButton;
	}
}
