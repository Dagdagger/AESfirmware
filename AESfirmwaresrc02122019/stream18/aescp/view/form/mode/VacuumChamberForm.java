package stream18.aescp.view.form.mode;


import javax.swing.JTextField;

import stream18.aescp.Browser;

import stream18.aescp.controller.TestVars;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class VacuumChamberForm extends Form {
	private static final long serialVersionUID = 1L;

	private JTextField chargeTimeTF;
	private JTextField settleTimeTF;
	private JTextField testTimeTF;

	
	private static VacuumChamberForm theVacuumChamberForm;
	private static TestVars thetestvars;

	public VacuumChamberForm(Screen parentScreen) {
		super(parentScreen);

		addPlot();
		/*
		chargeTimeTF = createVTextField("Plot Time", GRAPH_WIDTH + 10, 10, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), false);
		chargeTimeTF.setText(String.valueOf(TestVars.getChargevar()));
		
		settleTimeTF = createVTextField("Settle time:", GRAPH_WIDTH + 10, 10 + 80, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), false);
		settleTimeTF.setText(String.valueOf(TestVars.getSettlevar()));
		
		testTimeTF = createVTextField("Test time:", GRAPH_WIDTH +  10, 10 + 160, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), false);
		testTimeTF.setText(String.valueOf(TestVars.getTestvar()));*/
	 
	}	

	public static Form getInstance(Screen parentScreen) {
		if (theVacuumChamberForm == null) {
			theVacuumChamberForm = new VacuumChamberForm(parentScreen);
		}
		
		return theVacuumChamberForm;
	}
}
