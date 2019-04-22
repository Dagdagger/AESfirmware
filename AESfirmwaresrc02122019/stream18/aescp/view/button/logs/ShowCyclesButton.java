package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.ShowCycles;
import stream18.aescp.view.screen.logs.ShowCycles;

public class ShowCyclesButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static ShowCyclesButton theShowCyclesButton = null;
	
	public ShowCyclesButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/logs/ShowLogs.png", "Cycles", new Color(0xFF1F88));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				try {
					ShowCycles.getInstance().setActive(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		});
	}
		
	public static ShowCyclesButton getInstance(int x, int y) {
		if (theShowCyclesButton == null) {
			theShowCyclesButton = new ShowCyclesButton(x, y);
		}
		return theShowCyclesButton;
	}
}
