package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.ShowLogsScreen;
import stream18.aescp.view.screen.logs.ShowLogsScreen2;

public class ShowLogsButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static ShowLogsButton theShowLogsButton = null;
	
	public ShowLogsButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/logs/ShowLogs.png", "Show Logs", new Color(0xF01388));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				ShowLogsScreen2.getInstance().setActive(null);
			}	
		});
	}
		
	public static ShowLogsButton getInstance(int x, int y) {
		if (theShowLogsButton == null) {
			theShowLogsButton = new ShowLogsButton(x, y);
		}
		return theShowLogsButton;
	}
}
