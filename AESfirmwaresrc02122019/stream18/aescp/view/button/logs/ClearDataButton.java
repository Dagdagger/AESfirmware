package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.ClearDataScreen;

public class ClearDataButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static ClearDataButton theClearDataButton = null;
	
	public ClearDataButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/logs/ClearData.png", "Clear Data", new Color(0x9D4FF0));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				ClearDataScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static ClearDataButton getInstance(int x, int y) {
		if (theClearDataButton == null) {
			theClearDataButton = new ClearDataButton(x, y);
		}
		return theClearDataButton;
	}
}
