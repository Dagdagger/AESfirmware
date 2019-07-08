package stream18.aescp.view.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import stream18.aescp.Browser;
 

//import javax.swing.JCheckBox;
//import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import stream18.aescp.Browser;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.button.HomeButton;


public class TopForm extends Form {
	private static TopForm theTopForm;
	public static JTextField programlbl;
	private JTextField screenlbl;
	private JTextField testMode;
	private JTextField testPhase;
	private JTextField gaugePressure;
	private static JTextField gaugeUnit;
	private JCheckBox 	startTest;
	private JCheckBox 	stopTest;
	
	public static JTextField progNumber;
	private JTextField testModelbl;
	private JTextField testPhaselbl;
	private JTextField gaugelbl;
	private JTextField unitlbl;
	
	private JTextField testNumlblLogs;
	private JTextField testPassFailLogs;
	private JTextField testDecayLogs;
	private JTextField testOperatorLogs;
	private JTextField testTimeStampLogs;
 
	private static final int LABELS_LEFT = 180;
//	private UnitButton_S unit;
 

	public TopForm(Screen parentScreen) {
		super(parentScreen);
		Font font = new Font("Courier", Font.BOLD,14);
 
		Border border = BorderFactory.createLineBorder(new Color(0xeeeeee));
		Border Guageborder = BorderFactory.createLineBorder(new Color(0x5D5C57));
	//	setBackground(new Color(0xeeeeee));
		setBounds(new Rectangle(Screen.LEFT_WIDTH,  0,  Browser.SCREEN_WIDTH, Screen.TOP_HEIGHT));
		
		// This panel contains:
		// 1. Program name
		// 2. Test mode
		// 3. Test phase
	//	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
		screenlbl = createStatusText("Prog:", 0 , 0, 62);
		screenlbl.setBackground(new Color(0xeeeeee));
		screenlbl.setBorder(border);	
		screenlbl.setFont(font);
		screenlbl.setVisible(false);
		
		
		
		setProgramlbl(createStatusText("Prog:", 2 , 1, 72));
		getProgramlbl().setBackground(new Color(0xeeeeee));
		getProgramlbl().setBorder(border);	
		getProgramlbl().setFont(font);
		progNumber = createStatusText("1", 64 , 12, 40);
		//Screen.setComponentZOrder(progNumber, 12);
		 
		testModelbl = createStatusText("Mode:", 116 , 1, 56);
		testModelbl.setBackground(new Color(0xeeeeee));
		testModelbl.setBorder(border);
		testModelbl.setFont(font);
        testMode = createStatusText("Not Set", 176, 12, 120);
        
        testPhaselbl= createStatusText("Phase:", 300, 1, 66);
        testPhaselbl.setBackground(new Color(0xeeeeee));
        testPhaselbl.setBorder(border);
        testPhaselbl.setFont(font);
    	
    	testPhase = createStatusText("Idle", 366, 10, 100);
    	//testPhase.setBackground(new Color(0xeeeeee));
    	//testPhase.setBorder(border);
    	
    	unitlbl= createStatusText("Gauge:", 470, 1, 64);
    	unitlbl.setBackground(new Color(0xeeeeee));
    	unitlbl.setBorder(border);
    	unitlbl.setFont(font);
    	
    	 gaugePressure = createStatusText("0.000", 538, 10, 80);
      	 
    	
    	gaugelbl= createStatusText("Unit:", 622, 1, 50);
    	gaugelbl.setBackground(new Color(0xeeeeee));
    	gaugelbl.setBorder(border);
    	gaugelbl.setFont(font);
    	
		gaugeUnit = createStatusText("mBAR", 670, 10, 70);
		
		
		
//--- Logs View Top Form-------
		
		testNumlblLogs=createStatusText("Test #:", 0 , 20, 64);
		testNumlblLogs.setBackground(new Color(0xeeeeee));
		testNumlblLogs.setBorder(border);	
		testNumlblLogs.setFont(font);
		
		testPassFailLogs=createStatusText("Result:", 80 , 20, 64);
		testPassFailLogs.setBackground(new Color(0xeeeeee));
		testPassFailLogs.setBorder(border);	
		testPassFailLogs.setFont(font);
		
		testDecayLogs=createStatusText("Decay:", 160 , 20, 64);
		testDecayLogs.setBackground(new Color(0xeeeeee));
		testDecayLogs.setBorder(border);	
		testDecayLogs.setFont(font);
		
		testOperatorLogs=createStatusText("User:", 240 , 20, 64);
		testOperatorLogs.setBackground(new Color(0xeeeeee));
		testOperatorLogs.setBorder(border);	
		testOperatorLogs.setFont(font);
				
		testTimeStampLogs=createStatusText("Date:", 320 , 20, 64);
		testTimeStampLogs.setBackground(new Color(0xeeeeee));
		testTimeStampLogs.setBorder(border);	
		testTimeStampLogs.setFont(font);
		     	
	}
	
	public static TopForm getInstance(Screen parentScreen) {
		if (theTopForm == null) {
			theTopForm = new TopForm(parentScreen);
		}
		
		return theTopForm;
	}
	
	public void setProgName(String progNameText) {
	
		getProgramlbl().setText(progNameText);
	
	}
	
//	public in getProgName()
	
	public void setTestMode(String testModeText) {
		testMode.setText(testModeText);
	}

	public void setTestPhase(String testPhaseText) {
		testPhase.setText(testPhaseText);
	}
	public void setPressure(String pressure) {
		gaugePressure.setText(pressure);
	}
	public static String getUnits() {
		return gaugeUnit.getText();
	}

	private static final long serialVersionUID = 1L;
	
	public void clearTopForm() {
		screenlbl.setVisible(false);
		getProgramlbl().setVisible(false);
		progNumber.setVisible(false);
		testModelbl.setVisible(false);
        testMode.setVisible(false);
        testPhaselbl.setVisible(false);
    	testPhase.setVisible(false); 	
    	unitlbl.setVisible(false);
    	gaugePressure.setVisible(false);
    	gaugelbl.setVisible(false);
		gaugeUnit.setVisible(false);
		testNumlblLogs.setVisible(false);
		testPassFailLogs.setVisible(false);
		testDecayLogs.setVisible(false);
		testOperatorLogs.setVisible(false);
		testTimeStampLogs.setVisible(false);
	}
	
	public void readyModeTopForm() {	
		clearTopForm();
 
		screenlbl.setVisible(true);
		progNumber.setVisible(true);
		testModelbl.setVisible(true);
        testMode.setVisible(true);
        testPhaselbl.setVisible(true);
    	testPhase.setVisible(true); 	
    	unitlbl.setVisible(true);
    	gaugePressure.setVisible(true);
    	gaugelbl.setVisible(true);
		gaugeUnit.setVisible(true);
		 
	}
	public void setupTopForm() {
		clearTopForm();
		getProgramlbl().setText("Setup:");
		getProgramlbl().setVisible(true);
		
	}
	public void ModesTopForm() {
		clearTopForm();
		getProgramlbl().setText("Modes:");
		getProgramlbl().setVisible(true);
		
	}
	public void logsTopForm() {
		clearTopForm();
		getProgramlbl().setText("Logs:");
		getProgramlbl().setVisible(true);
		
	}
	public void menuTopForm() {
		clearTopForm();
		getProgramlbl().setText("Menu:");
		getProgramlbl().setVisible(true);
		
	}
	public void systemTopForm() {
		clearTopForm();
		getProgramlbl().setText("System:");
		getProgramlbl().setVisible(true);
		
	}
	

	public void showLogsTopForm(String logs) {
		clearTopForm();
		getProgramlbl().setText(logs);
		getProgramlbl().setVisible(true);
		
		//testNumlblLogs.setVisible(true);
		////testPassFailLogs.setVisible(true);
		//testDecayLogs.setVisible(true);
		//testOperatorLogs.setVisible(true);
		//testTimeStampLogs.setVisible(true);
	}
	
	
	

	public JTextField getProgramlbl() {
		return programlbl;
	}

	public void setProgramlbl(JTextField programlbl) {
		this.programlbl = programlbl;
	}

}
