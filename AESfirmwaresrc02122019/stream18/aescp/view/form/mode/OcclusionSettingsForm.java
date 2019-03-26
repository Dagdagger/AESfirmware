package stream18.aescp.view.form.mode;

import javax.swing.JTextField;

import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class OcclusionSettingsForm extends SettingsForm {
	private static final long serialVersionUID = 1L;
	protected static SettingsForm theOcclusionSettingsForm;
	private JTextField pressureTF;
	private JTextField toleranceTF;
	private JTextField flowMinTF;
	private JTextField flowMaxTF;
	private JTextField testTimeTF;
	private JTextField fillTimeTF;
	
	protected OcclusionSettingsForm(Screen parentScreen) {
		super(parentScreen);
		
		// TODO: populate specific fields
		// Also, take into account data holders
		
		int x = X_LEFT, y = Y_TOP;
		
    	pressureTF = createTextField("Pressure: ", x, y, 150, 10, true);
    	pressureTF.setText("0");
    	x += 260; 
    	toleranceTF = createTextField("% Tol: ", x, y, x, 10, true);
    	toleranceTF.setText("10");

    	x = X_LEFT;
    	y += 40;
    	flowMinTF = createTextField("Flow Min: ", x, y, 150, 10, true);
    	flowMinTF.setText("0");

    	x = X_LEFT;
    	y += 40;
    	flowMaxTF = createTextField("Flow Max: ", x, y, 150, 10, true);
    	flowMaxTF.setText("0");
    	
    	x = X_LEFT;
    	y += 40;
    	testTimeTF = createTextField("Test Time: ", x, y, 150, 10, true);
    	testTimeTF.setText("0");
    	x += 260; 
    	fillTimeTF = createTextField("Fill Time: ", x, y, x, 10, true);
    	fillTimeTF.setText("0");
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theOcclusionSettingsForm == null) {
			theOcclusionSettingsForm = new OcclusionSettingsForm(parentScreen);
		}
		
		return theOcclusionSettingsForm;
	}
}
