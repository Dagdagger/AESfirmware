package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import stream18.aescp.Browser;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.ShowLogsScreen;
import stream18.aescp.view.screen.logs.ShowLogsScreen2;
import stream18.aescp.view.screen.HomeScreen;

public class TurntoPDFButton extends Button {
	private static final long serialVersionUID = 1L;

	public static int DEFAULT_X = 0;
	public static int DEFAULT_Y = Browser.SCREEN_HEIGHT-60;
	
	
	protected static TurntoPDFButton theTurntoPDFButton = null;
	
	public TurntoPDFButton(int x, int y) {
		super(LEFT_TYPE);
		moveTo(x, y, 1);
		config("resources/mode/VacuumChamber.png", "print", new Color(0x3CF0CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				
				int shouldLogout = Browser.getInstance().displayDialog("Are you sure you want to Export to PDF?");
				if (shouldLogout == 0) {
					ShowLogsScreen2.print();
				}
			}	
		});
	}
		
	public static TurntoPDFButton getInstance(int x, int y) {
		if (theTurntoPDFButton == null) {
			theTurntoPDFButton = new TurntoPDFButton(x, y);
		}
		return theTurntoPDFButton;
	}
}
