package stream18.aescp.view.button.mode;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.mode.OcclusionScreen;

public class OcclusionButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static OcclusionButton theOcclusionButton = null;
	
	public OcclusionButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/mode/Occlusion.png", "Occlusion", new Color(0xFFB510));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				OcclusionScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static OcclusionButton getInstance(int x, int y) {
		if (theOcclusionButton == null) {
			theOcclusionButton = new OcclusionButton(x, y);
		}
		return theOcclusionButton;
	}
}
