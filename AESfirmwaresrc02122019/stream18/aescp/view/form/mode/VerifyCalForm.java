package stream18.aescp.view.form.mode;


import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class VerifyCalForm extends Form {
	private static final long serialVersionUID = 1L;
	
	private static VerifyCalForm theVerifyCalForm;

	public VerifyCalForm(Screen parentScreen) {
		super(parentScreen);

		addChart();		
	}

	public static Form getInstance(Screen parentScreen) {
		if (theVerifyCalForm == null) {
			theVerifyCalForm = new VerifyCalForm(parentScreen);
		}
		
		return theVerifyCalForm;
	}

}
