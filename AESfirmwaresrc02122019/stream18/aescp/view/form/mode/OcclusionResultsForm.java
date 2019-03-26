package stream18.aescp.view.form.mode;

import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class OcclusionResultsForm extends ResultsForm {
	private static final long serialVersionUID = 1L;
	protected static ResultsForm theOcclusionResultsForm;
	
	private static String resultValueText = "Flow Rate:";
	
	protected OcclusionResultsForm(Screen parentScreen) {
		super(parentScreen);
	}

	protected String getResultValueText() {
		return resultValueText;
	}
	
	protected String getResultValueUnits() {
		return "";
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theOcclusionResultsForm == null) {
			theOcclusionResultsForm = new OcclusionResultsForm(parentScreen);
		}
		
		return theOcclusionResultsForm;
	}

}
