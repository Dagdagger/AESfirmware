package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.lowagie.text.DocumentException;

import stream18.aescp.Browser;
import stream18.aescp.controller.TestVars;
import stream18.aescp.model.DBConnection;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.screen.logs.ShowAlarms;
import stream18.aescp.view.screen.logs.ShowAudiTrails;
import stream18.aescp.view.screen.logs.ShowCycles;
import stream18.aescp.view.screen.logs.ShowLogsScreen;
import stream18.aescp.view.screen.logs.ShowLogsScreen2;
import stream18.aescp.view.screen.logs.ShowTotalLogs;
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
					try {
						ShowTotalLogs j = ShowTotalLogs.getInstance();
						ShowAudiTrails k = ShowAudiTrails.getInstance();
						ShowCycles l = ShowCycles.getInstance();
						ShowAlarms m = ShowAlarms.getInstance();
						k.print();
						j.print();
						l.print();
						m.print();
						l.print();
						DBConnection.insertAudiTrail("Exported logs to pdf", "User: "+ TestVars.getTestUservar());
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
