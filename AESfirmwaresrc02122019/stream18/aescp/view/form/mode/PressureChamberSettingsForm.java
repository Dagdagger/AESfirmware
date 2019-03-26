package stream18.aescp.view.form.mode;

import javax.swing.JTextField;

import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class PressureChamberSettingsForm extends SettingsForm {
	private static final long serialVersionUID = 1L;
	protected static SettingsForm thePressureChamberSettingsForm;
	private JTextField pressureTF;
	private JTextField toleranceTF;
	private JTextField pressureDropMin;
	private JTextField pressureDropMinPercentage;
	private JTextField pressureDropMax;
	private JTextField pressureDropMaxPercentage;
	private JTextField timerClamp;
	private JTextField timerBleed;
	private JTextField timerFill;

 
	
	protected PressureChamberSettingsForm(Screen parentScreen) {
		super(parentScreen);
		
		// TODO: populate specific fields
		// Also, take into account data holders
		
		int x = X_LEFT, y = Y_TOP;
		
    	pressureTF = createTextFieldWithUnits("Pressure: ", x, y, 150, 10, true, "mBar");
    	pressureTF.setText("0");
    	x += 260; 
    	toleranceTF = createTextField("% Tol: ", x, y, x, 3, true);
    	toleranceTF.setText("10");

    	x = X_LEFT;
    	y += 40;
    	pressureDropMin = createTextFieldWithUnits("Min Drop: ", x, y, 150, 10, true, "mBar");
    	pressureDropMin.setText("0");

    	x +=260;
    	
    	pressureDropMinPercentage = createTextFieldWithUnits("Min Drop ", x, y, x, 6, true, "%");
    	pressureDropMinPercentage.setText("0");
    	
    
    	x = X_LEFT;
    	y += 40;
    	
    	pressureDropMax = createTextFieldWithUnits("Max Drop: ", x, y, 150, 10, true, "mBar");
    	pressureDropMax.setText("0");
    	
     	x +=260;
    	pressureDropMaxPercentage = createTextFieldWithUnits("Max Drop: ", x, y, x, 6, true, "%");
    	pressureDropMaxPercentage.setText("0");
    	x = X_LEFT;
     	x +=260;
    	y += 60;
    	
   
    	timerClamp = createTextFieldWithUnits("Clamp Time: ", x, y, x, 6, true, "sec");
    	timerClamp.setText("000.00");
    	y += 40;
    	 
    	timerBleed = createTextFieldWithUnits("Bleed Time: ", x, y, x, 6, true, "sec");
    	timerBleed.setText("000.00");
    	y += 40;
   	 
    	timerFill = createTextFieldWithUnits("Fill Time: ", x, y, x, 6, true, "sec");
    	timerFill.setText("000.00");
     
     
    
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (thePressureChamberSettingsForm == null) {
			thePressureChamberSettingsForm = new PressureChamberSettingsForm(parentScreen);
		}
		
		return thePressureChamberSettingsForm;
	}
}
