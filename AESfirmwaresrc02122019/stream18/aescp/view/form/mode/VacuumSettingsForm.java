package stream18.aescp.view.form.mode;

import javax.swing.JTextField;

import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class VacuumSettingsForm extends SettingsForm {
	private static final long serialVersionUID = 1L;
	protected static SettingsForm theVacuumSettingsForm;
	private JTextField vacuumPressureTF;
	private JTextField toleranceTF;
	private JTextField flowMinTF;
	private JTextField settleTimeTF;
	private JTextField testTimeTF;
	private JTextField fillTimeTF;
	
	protected VacuumSettingsForm(Screen parentScreen) {
		super(parentScreen);
		
		// TODO: populate specific fields
		// Also, take into account data holders
		
		int x = X_LEFT, y = Y_TOP;
		
    	vacuumPressureTF = createTextFieldWithUnits("Vacuum pressure: ", x, y, 230, 6, true, "mbar");
    	vacuumPressureTF.setText("0");
    	x += 300; 
    	toleranceTF = createTextFieldWithUnits("Vacuum Tol: ", x, y, x, 3, true, "%");
    	toleranceTF.setText("10");

    	x = X_LEFT;
    	y += 40;
    	fillTimeTF = createTextFieldWithUnits("Fill time: ", x, y, 230, 6, true, "sec");
    	fillTimeTF.setText("000.00");

    	x = X_LEFT;
    	y += 40;
    	settleTimeTF = createTextFieldWithUnits("Settle time: ", x, y, 230, 6, true, "sec");
    	settleTimeTF.setText("000.00");
    	
    	x = X_LEFT;
    	y += 40;
    	testTimeTF = createTextFieldWithUnits("Test Time: ", x, y, 230, 6, true, "sec");
    	testTimeTF.setText("000.00");
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theVacuumSettingsForm == null) {
			theVacuumSettingsForm = new VacuumSettingsForm(parentScreen);
		}
		
		return theVacuumSettingsForm;
	}
}
