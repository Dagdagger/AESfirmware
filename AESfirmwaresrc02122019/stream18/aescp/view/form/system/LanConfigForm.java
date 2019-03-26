package stream18.aescp.view.form.system;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import stream18.aescp.view.button.OkButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class LanConfigForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int LABELS_WIDTH = 140;
	private static final int LABELS_TOP = 50;
	private static final int LABELS_LEFT = 100;
	
	private static LanConfigForm theLoginForm;

	private JTextField ssidField;
	private JComboBox securityField;
	private JTextField passwordField;
	private JTextField portField;

	private String[] securityValues = {"None", "WPA", "WPA2"};
	
	public LanConfigForm(Screen parentScreen) {
		super(parentScreen);

		ssidField = createTextField("SSID:", LABELS_LEFT, LABELS_TOP, LABELS_WIDTH, 16, true);
		securityField = createComboField("Security:", LABELS_LEFT, LABELS_TOP + 40, LABELS_WIDTH, 16, securityValues);
		passwordField = createPasswordField("Password:", LABELS_LEFT, LABELS_TOP + 80, LABELS_WIDTH, 16, true);
		portField = createTextField("Port:", LABELS_LEFT, LABELS_TOP + 120, LABELS_WIDTH, 5, true);
		
		add(OkButton.getInstance(LABELS_LEFT + LABELS_WIDTH, LABELS_TOP + 175));
	}

	public static Form getInstance(Screen parentScreen) {
		if (theLoginForm == null) {
			theLoginForm = new LanConfigForm(parentScreen);
		}
		
		return theLoginForm;
	}
}
