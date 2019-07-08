package stream18.aescp.view.button.system;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import stream18.aescp.controller.TestVars;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.LogsScreen;
import stream18.aescp.view.screen.system.UserAdminScreen;

public class UserAdminButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static UserAdminButton theUserAdminButton = null;
	
	public UserAdminButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/system/useradmin.png", "User Admin", new Color(0xadd8e6));
		
		addMouseListener(new MouseAdapter() {
			@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e);
					// Select a new Screen for the browser
				if(TestVars.getTestUservar() == "Operator") {	
					  Object[] options = {"OK"};
					    JOptionPane.showOptionDialog(null,
					                   "No access allowed as an operator","Invalid Access",
					                   JOptionPane.PLAIN_MESSAGE,
					                   JOptionPane.PLAIN_MESSAGE,
					                   null,
					                   options,
					                   options[0]);
				} else {
					UserAdminScreen.getInstance().setActive(null);
				}
				}	
			});
		}
		
	public static UserAdminButton getInstance(int x, int y) {
		if (theUserAdminButton == null) {
			theUserAdminButton = new UserAdminButton(x, y);
		}
		return theUserAdminButton;
	}
}
