package stream18.aescp.view.button.system;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import stream18.aescp.Browser;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.system.InitialScreen;
import stream18.aescp.view.screen.system.LoginScreen;

public class LogoutButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static LogoutButton theLogOutButton = null;
	
	public LogoutButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/system/logout.png", "Logout", new Color(0xaaaaaa));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				System.exit(0);
				// Select a new Screen for the browser
				int shouldLogout = Browser.getInstance().displayDialog("Are you sure you want to logout?");
				if (shouldLogout == 0) {
					InitialScreen.getInstance().setActive(null);		
					try {
						Process p = Runtime.getRuntime().exec("sudo shutdown -h now");
						p.waitFor();
					} catch (IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(0);
				}				
			}	
		});
	}
		
	public static LogoutButton getInstance(int x, int y) {
		if (theLogOutButton == null) {
			theLogOutButton = new LogoutButton(x, y);
		}
		return theLogOutButton;
	}
}
