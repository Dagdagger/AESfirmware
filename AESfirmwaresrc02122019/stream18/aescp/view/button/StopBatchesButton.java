package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import stream18.aescp.Browser;
import stream18.aescp.controller.BatchVars;
import stream18.aescp.controller.TestVars;
import stream18.aescp.model.DBConnection;
import stream18.aescp.view.screen.system.InitialScreen;

public class StopBatchesButton extends Button {
	private static final long serialVersionUID = 1L;
	DecimalFormat df = new DecimalFormat("0.00##");

	protected static StopBatchesButton theStopBatchesButton = null;
	
	public StopBatchesButton() {
		super(BOTTOM_TYPE);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
    			if (isDisabled) return;
				super.mouseReleased(e);
				
				// Stop test: just notify the Controller
				// The controller should disable Stop and enable Start buttons
				//DBConnection.insertCycles(BatchVars.getPasses(), BatchVars.getFailures(), Double.toString(BatchVars.getAveragePressures()), Double.toString(TestVars.getChargevar()), TestVars.getTestUservar() + " " + TestVars.gettestRoleVar(), TestVars.getprogramName());
				//Reset the BatchVars!!!
				int shouldStore= Browser.getInstance().displayDialog("Are you sure you want to Save Batches?");
				if (shouldStore == 0) {
					DBConnection.insertCycles(BatchVars.getPasses(), BatchVars.getFailures(), Double.toString(BatchVars.getAveragePressures()), Double.toString(TestVars.getChargevar()), TestVars.getTestUservar() + " " + TestVars.gettestRoleVar(), TestVars.getprogramName(), df.format(BatchVars.getAveragePressures()), df.format(BatchVars.getRange()), df.format(BatchVars.getStandardDeviation()));
				}
			}
		});
	}
	
	public void setDisabled(boolean disabled) {
		this.isDisabled = disabled;
		if (disabled) {
			config("resources/StopD.png", "Store Batches");
		} else {
			config("resources/StopR.png", "Store Batches", new Color(0x429ef4));
		}
	}

	public static StopBatchesButton getInstance(int x, int y) {
		if (theStopBatchesButton == null) {
			theStopBatchesButton = new StopBatchesButton();
		}
		theStopBatchesButton.moveTo(x, y, 2);
		return theStopBatchesButton;
	}
	
	public static StopBatchesButton getInstance() {
		if (theStopBatchesButton == null) {
			theStopBatchesButton = new StopBatchesButton();
		}
		return theStopBatchesButton;
	}
}
