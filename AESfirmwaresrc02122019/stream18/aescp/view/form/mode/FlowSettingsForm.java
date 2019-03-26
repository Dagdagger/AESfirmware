package stream18.aescp.view.form.mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import stream18.aescp.controller.TestStatus;
import stream18.aescp.controller.phaser.TestPhaser;
import stream18.aescp.model.Program;
import stream18.aescp.view.button.ReadyScreenButton;
import stream18.aescp.view.button.mode.FlowButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.ModeScreen;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.screen.VKScreen;
import stream18.aescp.view.screen.mode.FlowScreen;


public class FlowSettingsForm extends SettingsForm {
	private static final long serialVersionUID = 1L;
	protected static SettingsForm theFlowSettingsForm;
	private JTextField pressureTF;
	private JTextField toleranceTF;
	private JTextField flowMinTF;
	private JTextField flowMaxTF;
	private JTextField testTimeTF;
	private JTextField fillTimeTF;
	//private JTextField testUnitTF;
	public static JComboBox unitField;
	public static String[] userUnits = {"mBar"};

	protected FlowSettingsForm(Screen parentScreen) {
		super(parentScreen);	
		
		 

		
		// TODO: populate specific fields
		// Also, take into account data holders
		
		int x = X_LEFT, y = Y_TOP;
		
    	pressureTF = createTextField("Pressure: ", x, y, 150, 10, true);
    	pressureTF.setText("10.000");
    
  
    	
    	x += 260; 	
    	
  
   
    	unitField = createComboField("", x, y, 30, 8, userUnits);
    	
    	toleranceTF = createTextField("% Tol: ", x, y, x, 3, true);
    	toleranceTF.setText("10");

    	x = X_LEFT;
    	y += 40;
    	flowMinTF = createTextFieldWithUnits("Flow Min: ", x, y, 150, 10, true, "sccm");
    	flowMinTF.setText("0");

    	x = X_LEFT;
    	y += 40;
    	flowMaxTF = createTextFieldWithUnits("Flow Max: ", x, y, 150, 10, true, "sccm");
    	flowMaxTF.setText("0");
    	
    	x = X_LEFT;
    	y += 40;
    	testTimeTF = createTextFieldWithUnits("Test Time: ", x, y, 150, 6, true, "sec");
    	testTimeTF.setText("000.00");
    	x += 260; 
    	
    	
    	
    	//testGivenNumberTF = createTextFieldWithUnits("Test Num: ", x, y, 150, 4, true, "");
    //	testGivenNumberTF.setText("23");
    //	x = X_LEFT;
    //	y += 40;
    	
    //	testGivenNameTF = createTextFieldWithUnits("Description: ", x, y, 150, 18, true, "");
    //	testGivenNameTF.setText("High Pressure Test");
    //	 
    	
    	
    	
    	fillTimeTF = createTextFieldWithUnits("Fill Time: ", x, y, x, 6, true, "sec");
    	fillTimeTF.setText("000.00");
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theFlowSettingsForm == null) {
			theFlowSettingsForm = new FlowSettingsForm(parentScreen);
			 
		}
		
		return theFlowSettingsForm;
	}
	
	
	public void itemStateChanged()  
    {
        int selection = unitField.getSelectedIndex();
        switch (selection)
        {
            case 0: pressureTF.setText("mBar");
                break;
            case 1: pressureTF.setText("H2O");
                break;
            default: break;
        }
    }
}
