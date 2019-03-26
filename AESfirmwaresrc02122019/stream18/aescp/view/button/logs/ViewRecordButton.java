package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;

public class ViewRecordButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static ViewRecordButton theViewRecordButton = null;
	
	public ViewRecordButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 0.75f, 0.75f);
		config("resources/logs/ViewRecord.png", "Details", new Color(0xF072AC));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				// TODO
//				ViewRecordScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static ViewRecordButton getInstance(int x, int y) {
		if (theViewRecordButton == null) {
			theViewRecordButton = new ViewRecordButton(x, y);
		}
		return theViewRecordButton;
	}
}
