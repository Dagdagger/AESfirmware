package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.PrintDataScreen;

public class PrintDataButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static PrintDataButton thePrintDataButton = null;
	
	public PrintDataButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/logs/PrintData.png", "Print Data", new Color(0xA689F0));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				PrintDataScreen.getInstance().setActive(null);
			}	
		});
	}
		
	public static PrintDataButton getInstance(int x, int y) {
		if (thePrintDataButton == null) {
			thePrintDataButton = new PrintDataButton(x, y);
		}
		return thePrintDataButton;
	}
}
