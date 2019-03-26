package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.ExportDataScreen;

public class ExportDataButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static ExportDataButton theExportDataButton = null;
	
	public ExportDataButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/logs/ExportData.png", "Export Data", new Color(0x9D4FF0));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				ExportDataScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static ExportDataButton getInstance(int x, int y) {
		if (theExportDataButton == null) {
			theExportDataButton = new ExportDataButton(x, y);
		}
		return theExportDataButton;
	}
}
