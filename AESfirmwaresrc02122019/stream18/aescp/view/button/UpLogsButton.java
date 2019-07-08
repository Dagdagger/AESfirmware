package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import stream18.aescp.Browser;
import stream18.aescp.controller.Controller;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.view.screen.HomeScreen;
import stream18.aescp.view.screen.logs.ShowAlarms;
import stream18.aescp.view.screen.logs.ShowAudiTrails;
import stream18.aescp.view.screen.logs.ShowCycles;
import stream18.aescp.view.screen.logs.ShowTotalLogs;

public class UpLogsButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static UpLogsButton theHomeButton = null;

	public static int DEFAULT_X = 0;
	public static int DEFAULT_Y = Browser.SCREEN_HEIGHT-BUTTON_L_HEIGHT;
	
	public UpLogsButton(int x, int y) {
		super(LEFT_TYPE);
		moveTo(x, y, 1);
		// Adds the image and text
		config("resources/arrowUp.png", null, new Color(0x3C90CB));
		 
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				try
				{
				ShowTotalLogs j = ShowTotalLogs.getInstance();
				ShowAudiTrails k = ShowAudiTrails.getInstance();
				ShowCycles l = ShowCycles.getInstance();
				ShowAlarms m = ShowAlarms.getInstance();
				
				m.connect();
				l.connect();
				k.connect();
				j.connect();
				
				
				
				
				}catch(Exception u)
				{ System.out.println(u);
				}
				
				
			}
		});

	}
	
	public static UpLogsButton getInstance(int x, int y) {
		if (theHomeButton == null) {
			theHomeButton = new UpLogsButton(x, y);
		}
		return theHomeButton;
	}
}
