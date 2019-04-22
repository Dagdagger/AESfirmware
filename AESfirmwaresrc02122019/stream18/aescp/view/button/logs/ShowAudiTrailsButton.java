package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.ShowAudiTrails;
import stream18.aescp.view.screen.logs.ShowAudiTrails;

public class ShowAudiTrailsButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static ShowAudiTrailsButton theShowAudiTrailsButton = null;
	
	public ShowAudiTrailsButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/logs/ShowLogs.png", "AudiTrails", new Color(0xFF1388));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				try {
					ShowAudiTrails.getInstance().setActive(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		});
	}
		
	public static ShowAudiTrailsButton getInstance(int x, int y) {
		if (theShowAudiTrailsButton == null) {
			theShowAudiTrailsButton = new ShowAudiTrailsButton(x, y);
		}
		return theShowAudiTrailsButton;
	}
}
