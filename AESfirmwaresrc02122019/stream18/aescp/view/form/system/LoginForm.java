package stream18.aescp.view.form.system;

import javax.swing.JTextField;

import stream18.aescp.view.button.system.LoginButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class LoginForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int LABELS_WIDTH = 140;
	private static final int LABELS_TOP = 50;
	private static final int LABELS_LEFT = 100;
	
	private static LoginForm theLoginForm;

	private JTextField usernameField;
	private JTextField passwordField;

	
	public LoginForm(Screen parentScreen) {
		super(parentScreen);

		usernameField = createTextField("Username:", LABELS_LEFT, LABELS_TOP, LABELS_WIDTH, 16, true);
		passwordField = createPasswordField("Password:", LABELS_LEFT, LABELS_TOP + 40, LABELS_WIDTH, 16, true);
		
		add(LoginButton.getInstance(LABELS_LEFT + LABELS_WIDTH, LABELS_TOP + 100));
	}

	public static Form getInstance(Screen parentScreen) {
		if (theLoginForm == null) {
			theLoginForm = new LoginForm(parentScreen);
		}
		
		return theLoginForm;
	}
}