package stream18.aescp.view.form.system;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.view.button.OkButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.screen.SystemScreen;
import stream18.aescp.view.screen.system.EditUserScreen;

public class EditUserForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int LABELS_WIDTH = 140;
	private static final int LABELS_TOP = 50;
	private static final int LABELS_LEFT = 100;
	
	private static EditUserForm theEditUserForm;

	private JTextField usernameField;
	private JTextField passwordField;
	private JComboBox roleField;

	private String[] roleValues = {"Operator", "Supervisor", "Admin"};
	
	public EditUserForm(Screen parentScreen) {
		super(parentScreen);

		usernameField = createTextField("Username:", LABELS_LEFT, LABELS_TOP, LABELS_WIDTH, 16, true);
		passwordField = createPasswordField("Password:", LABELS_LEFT, LABELS_TOP + 40, LABELS_WIDTH, 16, true);
		roleField = createComboField("Role:", LABELS_LEFT, LABELS_TOP + 80, LABELS_WIDTH, 16, roleValues);
		
		add(OkButton.getInstance(LABELS_LEFT + LABELS_WIDTH, LABELS_TOP + 175));
	}

	public static Form getInstance(Screen parentScreen) {
		if (theEditUserForm == null) {
			theEditUserForm = new EditUserForm(parentScreen);
		}
		return theEditUserForm;
	}
}
