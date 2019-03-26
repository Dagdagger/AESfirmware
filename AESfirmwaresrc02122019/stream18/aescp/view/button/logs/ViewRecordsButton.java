package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;

public class ViewRecordsButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static ViewRecordsButton theViewRecordsButton = null;
	
	public ViewRecordsButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 0.75f, 0.75f);
		config("resources/logs/ViewRecord.png", "View", new Color(0xF072AC));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
//				ViewRecordsScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static ViewRecordsButton getInstance(int x, int y) {
		if (theViewRecordsButton == null) {
			theViewRecordsButton = new ViewRecordsButton(x, y);
		}
		return theViewRecordsButton;
	}
}
