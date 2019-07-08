package stream18.aescp.view.form.system;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.controller.TestVars;
import stream18.aescp.model.DBConnection;
import stream18.aescp.view.button.OkButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.LogsScreen;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.screen.SystemScreen;
import stream18.aescp.view.screen.system.EditUserScreen;

public class AddUserForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int LABELS_WIDTH = 140;
	private static final int LABELS_TOP = 50;
	private static final int LABELS_LEFT = 100;
	
	private static AddUserForm theAddUserForm;

	private JTextField usernameField;
	private JTextField passwordField;
	private JComboBox roleField;

	private String[] roleValues = {"Operator", "Supervisor", "Manager"};
	
	public AddUserForm(Screen parentScreen) {
		super(parentScreen);

		usernameField = createTextField("Username:", LABELS_LEFT, LABELS_TOP, LABELS_WIDTH, 16, true);
		passwordField = createPasswordField("Password:", LABELS_LEFT, LABELS_TOP + 40, LABELS_WIDTH, 16, true);
		roleField = createComboField("Role:", LABELS_LEFT, LABELS_TOP + 80, LABELS_WIDTH, 16, roleValues);
		
		add(OkButton.getInstance(LABELS_LEFT + LABELS_WIDTH, LABELS_TOP + 175));
	}
	public void add() {
		String uname = usernameField.getText();
		String passwd = passwordField.getText();
		if(passwd.length() < 8) {	
			  Object[] options = {"OK"};
			    JOptionPane.showOptionDialog(null,
			                   "Password needs 8 characters!","Error!",
			                   JOptionPane.PLAIN_MESSAGE,
			                   JOptionPane.PLAIN_MESSAGE,
			                   null,
			                   options,
			                   options[0]);
		} else {
		
		DBConnection.insertUser(uname, passwd, String.valueOf(roleField.getSelectedItem()));
		DBConnection.insertAudiTrail("Added User", "Added: "+ usernameField.getText());
		 Object[] options = {"OK"};
		    JOptionPane.showOptionDialog(null,
		                   "User Added!","Success!",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.PLAIN_MESSAGE,
		                   null,
		                   options,
		                   options[0]);
		    UserAdminForm jk = (UserAdminForm) UserAdminForm.getInstance(null);
		    jk.addToTable(uname,String.valueOf(roleField.getSelectedItem()) );
			}
		}
		
	

	public static Form getInstance(Screen parentScreen) {
		if (theAddUserForm == null) {
			theAddUserForm = new AddUserForm(parentScreen);
		}
		return theAddUserForm;
	}
}
