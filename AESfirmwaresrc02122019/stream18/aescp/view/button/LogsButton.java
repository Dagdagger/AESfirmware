package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.screen.LogsScreen;

public class LogsButton extends Button {
	private static final long serialVersionUID = 1L;

	protected static LogsButton theLogsButton = null;
	
	public LogsButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/Logs.png", "Logs", new Color(0xE01C66));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				LogsScreen.getInstance().setActive(null);
			}	
		});
	}
	
	public static LogsButton getInstance(int x, int y) {
		if (theLogsButton == null) {
			theLogsButton = new LogsButton(x, y);
		}
		return theLogsButton;
	}
}