package stream18.aescp.view.form.mode;
 
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

import stream18.aescp.controller.BatchVars;
import stream18.aescp.controller.TestVars;
import stream18.aescp.view.button.StartBatchesButton;
import stream18.aescp.view.button.StopBatchesButton;
import stream18.aescp.view.button.logs.BatchesToCyclesButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public abstract class BatchesForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int RF_WIDTH = 300;
	protected static final int X_LEFT = 10;
	protected static final int Y_TOP = 20;
	private JTextField testField;
	private JTextField meanPressureField;
	private JTextField TotaltestsField;
	private JTextField passesField;
	private JTextField failuresField;
	private JTextField rangePressureField;
	private JTextField stdPressureField;
	public  JTextField batchNameField;
	DecimalFormat df = new DecimalFormat("0.00##");
	protected StartBatchesButton hi;
	
	

	protected BatchesForm(Screen parentScreen) {
		super(parentScreen);		 
		
		
		Font font = new Font("Courier", Font.BOLD,16);
		Border border = BorderFactory.createLineBorder(new Color(0xeeeeee));
		Border Guageborder = BorderFactory.createLineBorder(new Color(0x5D5C57));
		int x, y;
		
		x = 0;
		y = 10;
		
		 add(StartBatchesButton.getInstance(450, y));
      	 add(StopBatchesButton.getInstance(450, y+140));
      	 add(BatchesToCyclesButton.getInstance(280, 140));
		
		
	    testField = createTextField("Batches Tested:", x, y, 160, 8, false);		 
	    if(BatchVars.getPressures() != null) {
	    	testField.setText(Integer.toString(BatchVars.getNumofTests()));
	    	} else {
	    		testField.setText("No tests");
	    	}
	    	testField.setEditable(false);
    	
    	
    	y+= 30;
    	
    	
    	passesField = createTextField("Total Passes", x, y, 160, 8, false);
    	passesField.setText(Integer.toString(BatchVars.getPasses()));
    	passesField.setEditable(false);
    	
    	y+=30;
    	
    	failuresField = createTextField("Total Fails", x, y, 160, 8, false);
    	failuresField.setText(Integer.toString(BatchVars.getFailures()));
    	failuresField.setEditable(false);
    	

    	y += 30;
    	
    	meanPressureField =  createTextField("Mean Pressure", x, y, 160,8 , false);
    	meanPressureField.setText((Double.toString(BatchVars.getAveragePressures())));
    	meanPressureField.setEditable(false);
    	
    	
    	y+= 30;
    	
    	rangePressureField = createTextField("Pressure Range", x, y, 160, 8, false);
    	rangePressureField.setText(Double.toString(BatchVars.getRange()));
    	rangePressureField.setEditable(false);
    	
    	
    	y+=30;
    	
    	stdPressureField = createTextField ("Standard Dev.",x, y, 160, 8, false);
    	stdPressureField.setText(Double.toString(BatchVars.getStandardDeviation()));
    	stdPressureField.setEditable(false);
    	
    	y+=30;
    	
    	batchNameField = createTextField ("Batch Name:", x , y , 160, 8, true);
    	batchNameField.setText(BatchVars.getBatchName());
    	batchNameField.setEditable(true);
    		
    	

       
      	//StartBatchesButton.getInstance().setDisabled(true);
	}
	
	public void updateFields() {
		testField.setText(Integer.toString(BatchVars.getNumofTests()));
		meanPressureField.setText(df.format(BatchVars.getAveragePressures()));
		passesField.setText(Integer.toString(BatchVars.getPasses()));
		failuresField.setText(Integer.toString(BatchVars.getFailures()));
		stdPressureField.setText(df.format(BatchVars.getStandardDeviation()));
		rangePressureField.setText(df.format(BatchVars.getRange()));
		
	}
		
	
}
