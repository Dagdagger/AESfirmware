package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.controller.BatchVars;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.model.TestModeCfg;
import stream18.aescp.view.form.mode.BatchesForm;
import stream18.aescp.view.form.mode.VacuumChamberBatchesForm;



public class StartBatchesButton extends Button {
	private static final long serialVersionUID = 1L;
	protected static StartBatchesButton theStartBatchesButton = null;
	
	public StartBatchesButton() {
		super(BOTTOM_TYPE);
	 
	 
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
    			if (isDisabled) return;
				super.mouseReleased(e);			
				
				BatchVars.resetValues();
				BatchVars.setStartTime();
				BatchesForm thebatchesform = (BatchesForm) VacuumChamberBatchesForm.getInstance(null);	
				thebatchesform.updateFields();
			    BatchVars.setBatchName(thebatchesform.batchNameField.getText()); 
			}
		});
 
	}
	
	public void setDisabled(boolean disabled) {
		this.isDisabled = disabled;
		if (disabled) {
			config("resources/StartD.png", "Start Batches");

		} else {
			config("resources/StartG.png", "Restart Batches", new Color(0x00aa00));

		}
	}

	public static StartBatchesButton getInstance(int x, int y) {
		if (theStartBatchesButton == null) {
			theStartBatchesButton = new StartBatchesButton();
		}
		theStartBatchesButton.moveTo(x, y, 2);
		return theStartBatchesButton;
	}
	
	public static StartBatchesButton getInstance() {
		if (theStartBatchesButton == null) {
			theStartBatchesButton = new StartBatchesButton();
		}
		return theStartBatchesButton;
	}
	
	public void  setwithheight() {
		if (theStartBatchesButton == null) {
			theStartBatchesButton = new StartBatchesButton();	 
		}	 
	}

}
