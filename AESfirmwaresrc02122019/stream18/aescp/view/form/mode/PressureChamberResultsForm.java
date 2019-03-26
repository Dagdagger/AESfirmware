package stream18.aescp.view.form.mode;

import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class PressureChamberResultsForm extends ResultsForm {
	private static final long serialVersionUID = 1L;
	protected static ResultsForm thePressureChamberForm;
	
	private final String resultValueText = "Decay:";
	private final String resultValueUnits = "sccm";
	
	protected PressureChamberResultsForm(Screen parentScreen) {
		super(parentScreen);
	}
	
	@Override
	protected String getResultValueUnits() {
		return resultValueUnits;
	}

	@Override
	protected String getResultValueText() {
	return resultValueText;
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (thePressureChamberForm == null) {
			thePressureChamberForm = new PressureChamberResultsForm(parentScreen);
		}
		
		return thePressureChamberForm;
	}

}
