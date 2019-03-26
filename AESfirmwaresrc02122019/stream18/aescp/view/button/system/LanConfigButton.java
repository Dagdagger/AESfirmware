package stream18.aescp.view.button.system;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.system.LanConfigScreen;

public class LanConfigButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static LanConfigButton theLanConfigButton = null;
	
	public LanConfigButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/system/lanconfig.png", "Config LAN", new Color(0xaaaaaa));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				LanConfigScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static LanConfigButton getInstance(int x, int y) {
		if (theLanConfigButton == null) {
			theLanConfigButton = new LanConfigButton(x, y);
		}
		return theLanConfigButton;
	}
}
