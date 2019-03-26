package stream18.aescp.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.model.Persistent;
import stream18.aescp.model.Program;
import stream18.aescp.view.screen.Screen;

public class OptionsForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int LABELS_WIDTH = 160;
	private static final int LABELS_TOP = 20;
	private static final int CB_HEIGHT = 48;
	private static final int CB_VGAP = 10;
	private static final int CB_WIDTH = (int) (((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 2) * 0.8);

	
	private static OptionsForm theOptionsForm;
	private static Program theProgram;

	private JTextField clampTimerField;
	private JCheckBox invertedClampCB;
	private JTextField sealTimerField;
	private JCheckBox invertedSealCB;
	private JTextField markTimerField;
	private JCheckBox invertedMarkCB;
	private JTextField bleedTimeField;
	
	public String getClampTimer() {
		return clampTimerField.getText();
	}

	public void setClampTimer(String clampTimerField) {
		this.clampTimerField.setText(clampTimerField);
	}

	public Boolean getInvertedClamp() {
		return invertedClampCB.isSelected();
	}

	public void setInvertedClamp(Boolean invertedClamp) {
		this.invertedClampCB.setSelected(invertedClamp);
	}

	public String getSealTimer() {
		return sealTimerField.getText();
	}

	public void setSealTimer(String sealTimerField) {
		this.sealTimerField.setText(sealTimerField);
	}

	public Boolean getInvertedSeal() {
		return invertedSealCB.isSelected();
	}

	public void setInvertedSeal(Boolean invertedSeal) {
		this.invertedSealCB.setSelected(invertedSeal);
	}

	public String getMarkTimer() {
		return markTimerField.getText();
	}

	public void setMarkTimer(String markTimer) {
		this.markTimerField.setText(markTimer);
	}

	public Boolean getInvertedMark() {
		return invertedMarkCB.isSelected();
	}

	public void setInvertedMark(Boolean invertedMark) {
		this.invertedMarkCB.setSelected(invertedMark);
	}

	public String getBleedTime() {
		return bleedTimeField.getText();
	}

	public void setBleedTime(String bleedTime) {
		this.bleedTimeField.setText(bleedTime);
	}

	public OptionsForm(Screen parentScreen) {
		super(parentScreen);

		int x, y;
		
		x = 0;
		y = LABELS_TOP;
				
    	clampTimerField = createTextField("Clamp timer:", x, y, LABELS_WIDTH, 8, true);
    	invertedClampCB = createCheckBox("Inverted Clamp", x + 420, y - 10, CB_WIDTH, CB_HEIGHT);

    	y += CB_HEIGHT + CB_VGAP;
    	
    	sealTimerField = createTextField("Seal timer:", x, y, LABELS_WIDTH, 8, true);
    	invertedSealCB = createCheckBox("Inverted Seal", x + 420, y - 10, CB_WIDTH, CB_HEIGHT);

    	y += CB_HEIGHT + CB_VGAP;
    	
    	markTimerField = createTextField("Mark timer:", x, y, LABELS_WIDTH, 8, true);
    	invertedMarkCB = createCheckBox("Inverted Mark", x + 420, y - 10, CB_WIDTH, CB_HEIGHT);

    	y += CB_HEIGHT + CB_VGAP;
    	
    	bleedTimeField = createTextFieldWithUnits("Bleed time:", x, y, LABELS_WIDTH, 6, true, "sec");
    	bleedTimeField.setText("000.00");
	}

	public static Form getInstance(Screen parentScreen) {
		if (theOptionsForm == null) {
			theOptionsForm = new OptionsForm(parentScreen);
		}
		setTheProgram(Program.getInstance());
		updateViewFields(theProgram);
		
		return theOptionsForm;
	}
	
	public static Program getTheProgram() {
		return theProgram;
	}

	public static void setTheProgram(Program theProgram) {
		OptionsForm.theProgram = theProgram;
		theProgram.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				if (action.getActionCommand().equals(Persistent.WAS_LOADED)) {
					// Update the field values from Program
					updateViewFields((Persistent) action.getSource());
					
				} else if (action.getActionCommand().equals(Persistent.WILL_BE_SAVED)) {
					// Update Program with field values
					updateModelFields((Persistent) action.getSource());
				}
			}
		});
	}
	
	private static void updateViewFields(Persistent source) {
		Program program = (Program)source;
		theOptionsForm.setClampTimer(program.getClampTimer());
		theOptionsForm.setInvertedClamp(program.getInvertedClamp() != null ? theProgram.getInvertedClamp() : false);
		theOptionsForm.setSealTimer(program.getSealTimer());
		theOptionsForm.setInvertedSeal(program.getInvertedSeal() != null ? theProgram.getInvertedSeal() : false);
		theOptionsForm.setMarkTimer(program.getMarkTimer());
		theOptionsForm.setInvertedMark(program.getInvertedMark() != null ? theProgram.getInvertedMark() : false);
		theOptionsForm.setBleedTime(program.getBleedTime());
		
		
	}

	private static void updateModelFields(Persistent source) {
		Program program = (Program)source;
		program.setClampTimer(theOptionsForm.getClampTimer());
		program.setInvertedClamp(theOptionsForm.getInvertedClamp());
		program.setSealTimer(theOptionsForm.getSealTimer());
		program.setMarkTimer(theOptionsForm.getMarkTimer());
		program.setInvertedSeal(theOptionsForm.getInvertedSeal());
		program.setBleedTime(theOptionsForm.getBleedTime());
	}

}
