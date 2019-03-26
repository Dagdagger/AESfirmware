package stream18.aescp.view.form.mode;

import stream18.aescp.view.button.StartButton;
import stream18.aescp.view.button.StopButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class VacuumResultsForm extends ResultsForm {
	private static final long serialVersionUID = 1L;
	protected static ResultsForm theVacuumResultsForm;
	
	private static String resultValueText = "Test Result:";
	private static String resultValueUnits = "mBar";
	
	protected VacuumResultsForm(Screen parentScreen) {
		super(parentScreen);
	 
	}

	@Override
	protected String getResultValueText() {
		return resultValueText;
	}

	@Override
	protected String getResultValueUnits() {
		return resultValueUnits;
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theVacuumResultsForm == null) {
			theVacuumResultsForm = new VacuumResultsForm(parentScreen);
		}
		
		return theVacuumResultsForm;
	}

}
