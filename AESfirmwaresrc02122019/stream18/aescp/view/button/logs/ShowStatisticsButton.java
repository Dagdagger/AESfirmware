package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.ShowAlarms;
import stream18.aescp.view.screen.logs.ShowStatistics;

public class ShowStatisticsButton extends Button {
	
	protected static ShowStatisticsButton theShowStatisticsButton = null;

	
	

	public ShowStatisticsButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/logs/ShowLogs.png", "Statistics", new Color(0x4b0082));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				try {
					ShowStatistics.getInstance().setActive(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		});
	}
		
	public static ShowStatisticsButton getInstance(int x, int y) {
		if (theShowStatisticsButton == null) {
			theShowStatisticsButton = new ShowStatisticsButton(x, y);
		}
		return theShowStatisticsButton;
	}
}
