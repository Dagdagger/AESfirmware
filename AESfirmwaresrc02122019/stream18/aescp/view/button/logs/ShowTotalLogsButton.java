package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.ShowTotalLogs;
import stream18.aescp.view.screen.logs.ShowTotalLogs;

public class ShowTotalLogsButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static ShowTotalLogsButton theShowTotalLogsButton = null;
	
	public ShowTotalLogsButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/logs/ShowLogs.png", "All Logs", new Color(0xFF1F88));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				try {
					ShowTotalLogs.getInstance().setActive(null);
					ShowTotalLogs hello = ShowTotalLogs.getInstance();
					hello.connect();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		});
	}
		
	public static ShowTotalLogsButton getInstance(int x, int y) {
		if (theShowTotalLogsButton == null) {
			theShowTotalLogsButton = new ShowTotalLogsButton(x, y);
		}
		return theShowTotalLogsButton;
	}
}
