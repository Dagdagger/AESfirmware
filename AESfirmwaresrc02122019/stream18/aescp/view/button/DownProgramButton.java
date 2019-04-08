package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import stream18.aescp.Browser;
import stream18.aescp.controller.Controller;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.model.tempTestVars;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.form.mode.VacuumChamberSettingsForm;
import stream18.aescp.view.screen.HomeScreen;

public class DownProgramButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static DownLogsButton theHomeButton = null;

	public static int DEFAULT_X = 0;
	public static int DEFAULT_Y = Browser.SCREEN_HEIGHT-BUTTON_L_HEIGHT;
	
	public DownProgramButton(int x, int y) {
		super(LEFT_TYPE);
		moveTo(x, y, 1);
		// Adds the image and text
		config("resources/arrowDown.png", null, new Color(0x3C90CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
					String sNumber = TopForm.progNumber.getText();
					int number = Integer.parseInt(sNumber) - 1;
					sNumber = Integer.toString(number);
					if (number > 0) {
					TopForm.progNumber.setText(sNumber);
					try {
						number = number+1;
					    FileInputStream fileIn = new FileInputStream("/tmp/program"+TopForm.progNumber.getText());
					    ObjectInputStream in = new ObjectInputStream(fileIn);
					    tempTestVars lastVars = (tempTestVars) in.readObject();
					    in.close();
					    fileIn.close();
					    VacuumChamberSettingsForm.reLoad(lastVars);
					   } catch (Exception e1) {
					    System.err.println("\nError creating chamber settings Object. None exists?\n"
					      + e1.getMessage() + e1.getClass());
					   }
					
				}
			}
			});

		}
	
	public static DownLogsButton getInstance(int x, int y) {
		if (theHomeButton == null) {
			theHomeButton = new DownLogsButton(x, y);
		}
		return theHomeButton;
	}
}
