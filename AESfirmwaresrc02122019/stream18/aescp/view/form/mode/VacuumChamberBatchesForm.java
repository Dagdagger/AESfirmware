package stream18.aescp.view.form.mode;

import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class VacuumChamberBatchesForm extends BatchesForm {
	private static final long serialVersionUID = 1L;
	protected static BatchesForm theVacuumChamberForm;

	
	private final String resultValueText = "Decay:";
	private final String resultValueUnits = "mBar";
	private int testNum =1;
 
	
	protected VacuumChamberBatchesForm(Screen parentScreen) {
		super(parentScreen);
		
	}
	

	
	public static Form getInstance(Screen parentScreen) {
		if (theVacuumChamberForm == null) {
			theVacuumChamberForm = new VacuumChamberBatchesForm(parentScreen);
		}
		
		return theVacuumChamberForm;
	}

 



 
 

}