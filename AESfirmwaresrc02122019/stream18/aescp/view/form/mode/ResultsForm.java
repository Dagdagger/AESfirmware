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
	private JTextField dateField;
	protected JTextField resultValueField;
	private static TestStatus theTestStatus;
	public BigLabel resultFieldPass;
	public BigLabel resultFieldFail;
	public BigLabel resultFieldWait;
	public BigLabel resultFieldTesting;
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
	 

    	testField = createTextField("Test:", x, y, 160, 8, false);		 
    	testField.setText("");
    	testField.setEditable(false);
    	
    	resultValueField = createTextFieldWithUnits(getResultValueText(), x, y + 50, 160, 10, false, getResultValueUnits());
    	resultValueField.setText("0.0000");
    	resultValueField.setEditable(false);
    	
    	
    	dateField = createTextField("Date:", x , y+100, 100, 20, false);
    	dateField.setText(java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    	dateField.setEditable(false);
     	   	
    	resultlbl = createTextField("Result:", x+40, y+150, 80, 18, false); 
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
    	
    	
    	
    	
    	 
    	 
   	 add(StartButton.getInstance(450, y));
  	 add(StopButton.getInstance(450, y+140));
   
  	StartButton.getInstance().setDisabled(true);
	}
	
	public void setResultFieldTesting(boolean visibility){
		resultFieldWait.setVisible(false);
		resultFieldPass.setVisible(false);
		resultFieldFail.setVisible(false);
		resultFieldTesting.setVisible(visibility);

	}
	public void setResultFieldPass(boolean visibility) throws InterruptedException {
		resultFieldTesting.setVisible(false);
		resultFieldPass.setVisible(visibility);
		resultFieldWait.setVisible(false);
		TimeUnit.SECONDS.sleep(10);
		resultFieldPass.setVisible(false);
		resultFieldWait.setVisible(true);

     }
	public void setResultFieldDecay(String message) {
		resultValueField.setText(message);
     }
	
	public void setResultFieldWait(boolean visibility) {
		resultFieldTesting.setVisible(false);
		resultFieldPass.setVisible(false);
		resultFieldFail.setVisible(false);
		resultFieldWait.setVisible(visibility);
		
	}
	public void setResult(String result) {
		resultlbl.setText(result);
	}
	
	public void setResultFieldFail(boolean visibility, String message) throws InterruptedException {
		resultFieldTesting.setVisible(false);
		resultFieldFail.setVisible(visibility);
		resultFieldFail.setText(message);
		resultFieldWait.setVisible(false);
		TimeUnit.SECONDS.sleep(10);
		resultFieldFail.setVisible(false);
		resultFieldWait.setVisible(true);
		
	}
	
	protected abstract String getResultValueText();
    protected abstract String getResultValueUnits(); 
	
}

 
 