package stream18.aescp.view.button.mode;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.mode.PressureChamberScreen;

public class LinkPChamberButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static LinkPChamberButton theLinkPChamberButton = null;
	
	public LinkPChamberButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/mode/LinkPChamber.png", "P-Chamber", new Color(0x4000ff));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				PressureChamberScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static LinkPChamberButton getInstance(int x, int y) {
		if (theLinkPChamberButton == null) {
			theLinkPChamberButton = new LinkPChamberButton(x, y);
		}
		return theLinkPChamberButton;
	}
}
