package stream18.aescp.view.form.mode;


import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class FlowResultsForm extends ResultsForm {
	private static final long serialVersionUID = 1L;
	protected static ResultsForm theFlowResultsForm;
	private final String resultValueText = "Flow Rate:";
	private final String resultValueUnits = "sccsm";
	
	protected FlowResultsForm(Screen parentScreen) {
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
		if (theFlowResultsForm == null) {
			theFlowResultsForm = new FlowResultsForm(parentScreen);

		}
		
		return theFlowResultsForm;
	}

}
