package stream18.aescp.view.form.mode;

import javax.swing.JTextField;

import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class LeakSettingsForm extends SettingsForm {
	private static final long serialVersionUID = 1L;
	protected static SettingsForm theFlowSettingsForm;
	private JTextField pressureTF;
	private JTextField toleranceTF;
	private JTextField changeTimeTF;
	private JTextField testTimeTF;
	private JTextField settleTimeTF;
	private JTextField exhaustTF;
	private JTextField decayMaxTF;
	
	protected LeakSettingsForm(Screen parentScreen) {
		super(parentScreen);
		
		// TODO: populate specific fields
		// Also, take into account data holders
		
		int x = X_LEFT, y = Y_TOP;
		
    	pressureTF = createTextFieldWithUnits("Test Pressure: ", x, y, 200, 6, true, "mBar");
    	pressureTF.setText("0.0000");
    	x += 260; 
    	toleranceTF = createTextField("% Tol: ", x, y, x, 3, true);
    	toleranceTF.setText("10");

    	x = X_LEFT;
    	y += 40;
    	changeTimeTF = createTextFieldWithUnits("Charge Time: ", x, y, 200, 6, true, "mBar");
    	changeTimeTF.setText("000.00");
    	x += 260; 
    	decayMaxTF = createTextFieldWithUnits("Decay Max: ", x, y, x, 6, true, "mBar");
    	decayMaxTF.setText("0.0000");

    	x = X_LEFT;
    	y += 40;
    	settleTimeTF = createTextFieldWithUnits("Settle Time: ", x, y, 200, 6, true, "sec");
    	settleTimeTF.setText("000.00");
    	x += 260;
    	exhaustTF = createTextFieldWithUnits("Exhaust: ", x, y, x, 6, true, "mBar");
    	exhaustTF.setText("0.0000");

    	x = X_LEFT;
    	y += 40;
    	testTimeTF = createTextFieldWithUnits("Test Time: ", x, y, 200, 6, true, "sec");
    	testTimeTF.setText("000.00");
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theFlowSettingsForm == null) {
			theFlowSettingsForm = new LeakSettingsForm(parentScreen);
		}
		
		return theFlowSettingsForm;
	}
}
