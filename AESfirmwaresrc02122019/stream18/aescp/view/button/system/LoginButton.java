package stream18.aescp.view.button.system;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;

public class LoginButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static LoginButton theLoginButton = null;
	
	public LoginButton() {
		super(BOTTOM_TYPE);
		config("resources/system/login.png", "Login", new Color(0xaaaaff));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// authenticate: notify
				fireActionEvent(AUTHENTICATE_REQ);
			}	
		});
	}
		
	public static LoginButton getInstance(int x, int y) {
		if (theLoginButton == null) {
			theLoginButton = new LoginButton();
		}
		theLoginButton.moveTo(x, y, 2);
		return theLoginButton;
	}
	
	public static LoginButton getInstance() {
		if (theLoginButton == null) {
			theLoginButton = new LoginButton();
		}
		return theLoginButton;
	}
}
