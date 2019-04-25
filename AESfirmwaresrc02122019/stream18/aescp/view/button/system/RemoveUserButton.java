package stream18.aescp.view.button.system;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JList;
import javax.swing.ListModel;

import stream18.aescp.model.DBConnection;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.form.system.UserAdminForm;
import stream18.aescp.view.form.system.UserRoleBean;
import stream18.aescp.view.screen.system.UserAdminScreen;

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
				UserAdminForm f = (UserAdminForm) UserAdminForm.getInstance(null);
				f.remove();
				

			    
				
			
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
