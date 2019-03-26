package stream18.aescp.view.form.mode;

import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class VacuumChamberResultsForm extends ResultsForm {
	private static final long serialVersionUID = 1L;
	protected static ResultsForm theVacuumChamberForm;

	
	private final String resultValueText = "Decay:";
	private final String resultValueUnits = "mBar";
	private int testNum =1;
 
	
	protected VacuumChamberResultsForm(Screen parentScreen) {
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
		if (theVacuumChamberForm == null) {
			theVacuumChamberForm = new VacuumChamberResultsForm(parentScreen);
		}
		
		return theVacuumChamberForm;
	}

 



 
 

}
