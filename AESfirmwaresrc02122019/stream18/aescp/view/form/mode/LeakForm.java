package stream18.aescp.view.form.mode;


import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.button.StartButton;
import stream18.aescp.view.button.StopButton;

public class LeakForm extends Form {
	private static final long serialVersionUID = 1L;

	private JTextField cycleTime;
	private JTextField decay;
	
	private static LeakForm theLeakForm;

	public LeakForm(Screen parentScreen) {
		super(parentScreen);

		addPlot();
		
		cycleTime = createVTextField("Cycle time:", GRAPH_WIDTH + 10, 10, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), false);
		cycleTime.setText("10.0");

		decay = createVTextField("Decay:", GRAPH_WIDTH + 10, 10 + 160, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), true);
		decay.setText("0.8504");
	}

	public static Form getInstance(Screen parentScreen) {
		if (theLeakForm == null) {
			theLeakForm = new LeakForm(parentScreen);
		}
		
		return theLeakForm;
	}

}
