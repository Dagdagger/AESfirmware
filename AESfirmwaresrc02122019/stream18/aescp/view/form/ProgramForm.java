package stream18.aescp.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.model.Persistent;
import stream18.aescp.model.Program;
import stream18.aescp.view.screen.Screen;

public class ProgramForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int LABELS_WIDTH = 140;
	private static final int LABELS_TOP = 20;
	
	private static final int CHECKS_TOP = 150;
	private static final int CB_HEIGHT = 48;
	private static final int CB_WIDTH = (int) (((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 2) * 0.8);

	private static ProgramForm theProgramForm;
	private static Program theProgram;

	private JTextField programField;
	private JTextField testNumField;
	private JTextField lotNumField;
	private JTextField operatorField;
	private JCheckBox saveAllPlotCB;
	private JCheckBox autoSwResultsCB;
	private JCheckBox saveAllModesCB;
	private JCheckBox autoResetCB;
	
	public String getProgram() {
		return programField.getText();
	}

	public void setProgram(String programField) {
		this.programField.setText(programField);
	}

	public String getTestNum() {
		return testNumField.getText();
	}

	public void setTestNum(String testNumField) {
		this.testNumField.setText(testNumField);
	}

	public String getLotNum() {
		return lotNumField.getText();
	}

	public void setLotNum(String lotNumField) {
		this.lotNumField.setText(lotNumField);
	}

	public String getOperator() {
		return operatorField.getText();
	}

	public void setOperator(String operatorField) {
		this.operatorField.setText(operatorField);
	}

	public Boolean getSaveAllPlot() {
		return saveAllPlotCB.isSelected();
	}

	public void setSaveAllPlot(Boolean saveAllPlotCB) {
		this.saveAllPlotCB.setSelected(saveAllPlotCB);
	}

	public Boolean getAutoSwResults() {
		return autoSwResultsCB.isSelected();
	}

	public void setAutoSwResults(Boolean autoSwResultsCB) {
		this.autoSwResultsCB.setSelected(autoSwResultsCB);
	}

	public Boolean getSaveAllModes() {
		return saveAllModesCB.isSelected();
	}

	public void setSaveAllModes(Boolean saveAllModesCB) {
		this.saveAllModesCB.setSelected(saveAllModesCB);
	}

	public Boolean getAutoReset() {
		return autoResetCB.isSelected();
	}

	public void setAutoReset(Boolean autoResetCB) {
		this.autoResetCB.setSelected(autoResetCB);
	}

	public ProgramForm(Screen parentScreen) {
		super(parentScreen);

    	programField = createTextField("Program:", 0, LABELS_TOP, LABELS_WIDTH, 16, true);
    	testNumField = createTextField("Test #:", 450, LABELS_TOP, LABELS_WIDTH, 4, true);

    	lotNumField = createTextField("Lot #:", 0, LABELS_TOP + 60, LABELS_WIDTH, 8, true);
    	operatorField = createTextField("Operator:", 275, LABELS_TOP + 60, LABELS_WIDTH, 16, true);
    	
    	saveAllPlotCB = createCheckBox("Save all plot data", 20, CHECKS_TOP, CB_WIDTH, CB_HEIGHT);
    	autoSwResultsCB = createCheckBox("Autoswitch results", 20 + CB_WIDTH + 50, CHECKS_TOP, CB_WIDTH, CB_HEIGHT);

    	saveAllModesCB = createCheckBox("Save all modes", 20 , CHECKS_TOP + 80, CB_WIDTH, CB_HEIGHT);
    	autoResetCB = createCheckBox("Auto reset test", 20 + CB_WIDTH + 50, CHECKS_TOP + 80, CB_WIDTH, CB_HEIGHT);
	}

	public static Form getInstance(Screen parentScreen) {
		if (theProgramForm == null) {
			theProgramForm = new ProgramForm(parentScreen);
			setTheProgram(Program.getInstance());
			updateViewFields(theProgram);
		}
		
		return theProgramForm;
	}

	public static Program getTheProgram() {
		return theProgram;
	}

	public static void setTheProgram(Program theProgram) {
		ProgramForm.theProgram = theProgram;
		theProgram.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				if (action.getActionCommand().equals(Persistent.WAS_LOADED)) {
					// Update the field values from Program
					updateViewFields(theProgram);
					
				} else if (action.getActionCommand().equals(Persistent.WILL_BE_SAVED)) {
					// Update Program with field values
					updateModelFields(theProgram);
				}
			}
		});
	}

	private static void updateViewFields(Program program) {
		theProgramForm.setProgram(program.getProgram());
		theProgramForm.setTestNum(program.getTestNum());
		theProgramForm.setLotNum(program.getTestLot());
		theProgramForm.setOperator(program.getOperator());
		theProgramForm.setSaveAllPlot(program.getSaveAllPlot() != null ? program.getSaveAllPlot() : false);
		theProgramForm.setAutoSwResults(program.getAutoSwResults() != null ? program.getAutoSwResults() : false);
		theProgramForm.setSaveAllModes(program.getSaveAllModes() != null ? program.getSaveAllModes() : false);
		theProgramForm.setAutoReset(program.getAutoReset() != null ? program.getAutoReset() : false);
	}

	private static void updateModelFields(Program program) {
		program.setProgram(theProgramForm.getProgram());
		program.setTestNum(theProgramForm.getTestNum());
		program.setTestLot(theProgramForm.getLotNum());
		program.setOperator(theProgramForm.getOperator());
		program.setSaveAllPlot(theProgramForm.getSaveAllPlot());
		program.setAutoSwResults(theProgramForm.getAutoSwResults());
		program.setSaveAllModes(theProgramForm.getSaveAllModes());
		program.setAutoReset(theProgramForm.getAutoReset());
	}	
}
