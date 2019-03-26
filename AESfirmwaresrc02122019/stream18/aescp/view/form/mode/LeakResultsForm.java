package stream18.aescp.view.form.mode;

import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class LeakResultsForm extends ResultsForm {
	private static final long serialVersionUID = 1L;
	protected static ResultsForm theLeakResultsForm;
	
	private final String resultValueText = "Test result:";
	private final String resultValueUnits = "mBar";
	
	protected LeakResultsForm(Screen parentScreen) {
		super(parentScreen);
    	resultValueField.setText("0.0000");
	}

	@Override
	protected String getResultValueText() {
		return resultValueText;
	}
	
	@Override
	protected String getResultValueUnits() {
		return resultValueUnits ;
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theLeakResultsForm == null) {
			theLeakResultsForm = new LeakResultsForm(parentScreen);
		}
		
		return theLeakResultsForm;
	}

}
