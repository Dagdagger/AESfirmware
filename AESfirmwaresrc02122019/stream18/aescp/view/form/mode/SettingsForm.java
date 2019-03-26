package stream18.aescp.view.form.mode;
 
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public abstract class SettingsForm extends Form {
	private static final long serialVersionUID = 1L;
	
	protected static final int X_LEFT = 10;
	protected static final int Y_TOP = 20;

	protected SettingsForm(Screen parentScreen) {
		super(parentScreen);		 
	}
		
	// Must be overridden
	public static Form getInstance(Screen parentScreen) {
		return null;
	}
	
 
}
