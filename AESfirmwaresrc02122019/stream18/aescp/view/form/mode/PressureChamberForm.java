package stream18.aescp.view.form.mode;


import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class PressureChamberForm extends Form {
	private static final long serialVersionUID = 1L;

	private JTextField chargeTime;
	private JTextField settleTime;
	private JTextField testTime;

	
	private static PressureChamberForm thePressureChamberForm;

	public PressureChamberForm(Screen parentScreen) {
		super(parentScreen);

		addPlot();
		
		chargeTime = createVTextField("Charge time:", GRAPH_WIDTH + 10, 10, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), false);
		chargeTime.setText("10.0");
		settleTime = createVTextField("Settle time:", GRAPH_WIDTH + 10, 10 + 80, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), false);
		settleTime.setText("10.0");
		testTime = createVTextField("Test time:", GRAPH_WIDTH +  10, 10 + 160, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), false);
		testTime.setText("10.0");
	 
	}	

	public static Form getInstance(Screen parentScreen) {
		if (thePressureChamberForm == null) {
			thePressureChamberForm = new PressureChamberForm(parentScreen);
		}
		
		return thePressureChamberForm;
	}
}
