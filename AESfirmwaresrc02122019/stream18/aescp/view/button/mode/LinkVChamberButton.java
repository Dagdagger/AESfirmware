package stream18.aescp.view.button.mode;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.mode.VacuumScreen;

public class LinkVChamberButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static LinkVChamberButton theLinkVChamberButton = null;
	
	public LinkVChamberButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/mode/LinkVChamber.png", "Vacuum", new Color(0xA3B627));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				VacuumScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static LinkVChamberButton getInstance(int x, int y) {
		if (theLinkVChamberButton == null) {
			theLinkVChamberButton = new LinkVChamberButton(x, y);
		}
		return theLinkVChamberButton;
	}
}
