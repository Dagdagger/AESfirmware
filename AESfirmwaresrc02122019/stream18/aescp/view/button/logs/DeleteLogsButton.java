package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.model.DBConnection;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.ShowLogsScreen;
import stream18.aescp.view.screen.logs.ShowLogsScreen2;

public class DeleteLogsButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static DeleteLogsButton theShowLogsButton = null;
	
	public DeleteLogsButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/logs/ShowLogs.png", "Delete Logs", new Color(0x3C90CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				DBConnection.deleteAll();
				ShowLogsScreen2.removeDataLines();
			}	
		});
	}
		
	public static DeleteLogsButton getInstance(int x, int y) {
		if (theShowLogsButton == null) {
			theShowLogsButton = new DeleteLogsButton(x, y);
		}
		return theShowLogsButton;
	}
}
