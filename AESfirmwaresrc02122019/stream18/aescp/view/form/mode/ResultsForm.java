package stream18.aescp.view.form.mode;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.Border;
import stream18.aescp.Browser;
import stream18.aescp.controller.TestStatus;
import stream18.aescp.controller.TestVars;
import stream18.aescp.model.DBConnection;
import stream18.aescp.model.Test;
import stream18.aescp.view.button.StartButton;
import stream18.aescp.view.button.StopButton;
import stream18.aescp.view.form.BigLabel;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.screen.Screen;
 
public abstract class ResultsForm extends Form {
	private static final long serialVersionUID = 1L;

	private static final int RF_WIDTH = 300;
	private JTextField testField;
	public  JTextField dateField;
	private JTextField passField;
	private JTextField resultNameField;
	protected JTextField resultValueField;
	private static TestStatus theTestStatus;
	public BigLabel resultFieldPass;
	public BigLabel resultFieldFail;
	public BigLabel resultFieldWait;
	public BigLabel resultFieldTesting;
	public BigLabel resultFieldStopping;
	public static int cycles;
	public static int passes;
	protected JCheckBox resulbtn;
	protected StartButton bt;
	private JTextField resultlbl;
	private static ResultsForm theresultsform;
	
	private StartButton theStartButton;
	private StopButton theStopButton;
	
	protected ResultsForm(Screen parentScreen) {
		super(parentScreen);
 
		
		Font font = new Font("Courier", Font.BOLD,16);
		Border border = BorderFactory.createLineBorder(new Color(0xeeeeee));
		Border Guageborder = BorderFactory.createLineBorder(new Color(0x5D5C57));
		int x, y;
		
		x = 0;
		y = 10;
	 

    	testField = createTextField("Cycles Tested:", x-15, y, 160, 8, false);		 
    	testField.setText("0/" + Integer.toString(TestVars.getCycles()));
    	testField.setEditable(false);
    	setCyclesto0();
    	
        passField = createTextField("Passes:", x+173, y, 160, 8, false);		 
    	passField.setText("0/" + Integer.toString(TestVars.getCycles()));
    	passField.setEditable(false);
    	
    	
    	resultValueField = createTextFieldWithUnits(getResultValueText(), x, y + 50, 160, 10, false, getResultValueUnits());
    	resultValueField.setText(" Waiting...");
    	resultValueField.setEditable(false);
    	
    	resultNameField = createTextField("Prog Name:", 350, y + 280, 180, 10, false);
    	resultNameField.setText("NA");
    	resultNameField.setEditable(false);
    	
    	dateField = createTextField("Date:", x , y+100, 100, 20, false);
    	dateField.setText(java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    	dateField.setEditable(false);
     	   	
    	resultlbl = createTextField("Status:", x+40, y+150, 80, 18, false); 
    	resultlbl.setEditable(false);
    	resultlbl.setBackground(new Color(0xeeeeee));
    	resultlbl.setBorder(border);
    	resultlbl.setFont(font);
    	
    	int centered_x = (Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH - RF_WIDTH ) / 2;
   
    	 
    	resultFieldFail = new BigLabel("Fail", Color.RED, x+70, 200, RF_WIDTH, RF_WIDTH / 3);
    	resultFieldFail.setVisible(false);
    	add(resultFieldFail);
    	
    	resultFieldPass = new BigLabel("Pass", Color.GREEN, x+70, 200, RF_WIDTH, RF_WIDTH / 3);
    	resultFieldPass.setVisible(false);
    	add(resultFieldPass);
    	
    	resultFieldWait = new BigLabel("Ready to Test", Color.YELLOW, x+70, 200, RF_WIDTH, RF_WIDTH / 3);
    	resultFieldWait.setVisible(true);
    	add(resultFieldWait);
    	
    	resultFieldTesting = new BigLabel("Testing....", Color.CYAN, x+70, 200, RF_WIDTH, RF_WIDTH / 3);
    	resultFieldTesting.setVisible(false);
    	add(resultFieldTesting);
    	
    	resultFieldStopping = new BigLabel("Stopping....", Color.ORANGE, x+70, 200, RF_WIDTH, RF_WIDTH / 3);
    	resultFieldTesting.setVisible(false);
    	add(resultFieldStopping);
    	
    	
    	
    	 
    	 
   	 add(StartButton.getInstance(450, y));
  	 add(StopButton.getInstance(450, y+140));
   
  	StartButton.getInstance().setDisabled(true);
	}
	
	public void updateResultFieldCycles() {

		cycles++;
		if(cycles == TestVars.getCycles()) {
			DBConnection.insertCycles(passes, cycles-passes, Double.toString(TestVars.getmaxPressureDrop()), Double.toString(TestVars.getChargevar()), TestVars.getTestUservar());
			cycles = 0;
		}
		testField.setText(Integer.toString(cycles) + "/" + Integer.toString(TestVars.getCycles()));
	}
	public void setInitialFieldCycles(int thecycles) {
		cycles = 0;
		testField.setText("0/" + Integer.toString(thecycles));
	}
	public void setInitialFieldPasses(int thecycles) {
		passes = 0;
		passField.setText("0/" + Integer.toString(thecycles));
	}
	
	public void setCyclesto0() {
		cycles = 0;
		testField.setText("0");
	}
	
	public void setName(String setName) {
		resultNameField.setText(setName);
	}
	
	
	public void updateResultFieldPasses() {
		passes++;
		passField.setText(Integer.toString(passes) + "/" + Integer.toString(TestVars.getCycles()));
	}
	
	
	public void setResultFieldTesting(boolean visibility){
		resultFieldWait.setVisible(false);
		resultFieldPass.setVisible(false);
		resultFieldFail.setVisible(false);
		resultFieldStopping.setVisible(false);
		resultFieldTesting.setVisible(visibility);

	}
	public void setResultFieldPass(boolean visibility) throws InterruptedException {
		resultFieldTesting.setVisible(false);
		resultFieldStopping.setVisible(false);
		resultFieldPass.setVisible(visibility);
		resultFieldWait.setVisible(false);
		TimeUnit.SECONDS.sleep(3);
		resultFieldPass.setVisible(false);
		resultFieldWait.setVisible(true);

     }
	public void setResultFieldDecay(String message) {
		resultValueField.setText(message);
     }
	
	public void setResultFieldStopping(boolean visibility) {
		resultFieldTesting.setVisible(false);
		resultFieldPass.setVisible(false);
		resultFieldFail.setVisible(false);
		resultFieldWait.setVisible(false);
		resultFieldStopping.setVisible(visibility);
		
	}
	
	public void setResultFieldWait(boolean visibility) {
		resultFieldTesting.setVisible(false);
		resultFieldStopping.setVisible(false);
		resultFieldPass.setVisible(false);
		resultFieldFail.setVisible(false);
		resultFieldWait.setVisible(visibility);
		
	}
	public void setResult(String result) {
		resultlbl.setText(result);
	}
	
	public void setResultFieldFailing(boolean visibility, String message) throws InterruptedException {
		resultFieldTesting.setVisible(false);
		resultFieldStopping.setVisible(false);
		resultFieldWait.setVisible(false);
		resultFieldPass.setVisible(false);
		resultFieldFail.setVisible(visibility);
		resultFieldFail.setText(message);
		
	}
	public void setResultFieldPassing(boolean visibility, String message) throws InterruptedException {
		resultFieldTesting.setVisible(false);
		resultFieldStopping.setVisible(false);
		resultFieldWait.setVisible(false);
		resultFieldFail.setVisible(false);
		resultFieldPass.setVisible(visibility);
		resultFieldPass.setText(message);
		
	}
	
	public void setResultFieldFail(boolean visibility, String message) throws InterruptedException {
		resultFieldTesting.setVisible(false);
		resultFieldStopping.setVisible(false);
		resultFieldFail.setVisible(visibility);
		resultFieldFail.setText(message);
		resultFieldWait.setVisible(false);
		TimeUnit.SECONDS.sleep(3);
		resultFieldFail.setVisible(false);
		resultFieldWait.setVisible(true);
		
	}
	
	protected abstract String getResultValueText();
    protected abstract String getResultValueUnits(); 
	
}

 
 