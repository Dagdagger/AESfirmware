package stream18.aescp.view.form.system;

import java.sql.Connection;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.model.DBConnection;
import stream18.aescp.view.button.OkButton;
import stream18.aescp.view.button.UpdateButton;
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

	private String[] roleValues = {"Operator", "Supervisor", "Manager"};
	
	public EditUserForm(Screen parentScreen) {
		super(parentScreen);

		usernameField = createTextField("Username:", LABELS_LEFT, LABELS_TOP, LABELS_WIDTH, 16, true);
		passwordField = createPasswordField("Password:", LABELS_LEFT, LABELS_TOP + 40, LABELS_WIDTH, 16, true);
		roleField = createComboField("Role:", LABELS_LEFT, LABELS_TOP + 80, LABELS_WIDTH, 16, roleValues);
		
		add(UpdateButton.getInstance(LABELS_LEFT + LABELS_WIDTH, LABELS_TOP + 175));
	}
	public void add() {
        String uname = usernameField.getText();
        String passwd = passwordField.getText();
		DBConnection.insertUser(uname, passwd, String.valueOf(roleField.getSelectedItem()));
		
	}
	
	public void setValues(String userName, String role) {
		int index = 0;
		usernameField.setText(userName);
		if(role == "Operator") {
			index = 0;
		}
		if(role == "Supervisor") {
			index = 1;
		}
		if(role == "Admin") {
			index = 2;
		}
		roleField.setSelectedIndex(index);
	}
	
	public void change() {
		Connection con = DBConnection.getConnection();
		String uname = usernameField.getText();
		String role = String.valueOf(roleField.getSelectedItem());
		try {
			java.sql.Statement stmt=con.createStatement();  
			stmt.executeUpdate("delete from Users where uname = '" + uname + "' && role ='" + role + "'" );
			}
			catch (Exception e)
	        {
	          System.err.println("Got an exception!");
	          System.err.println(e.getMessage());
	        }
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
		DBConnection.insertAudiTrail("Edited User ", "Edited: " + uname);
		 Object[] options = {"OK"};
		    JOptionPane.showOptionDialog(null,
		                   "User Updated!","Success!",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.PLAIN_MESSAGE,
		                   null,
		                   options,
		                   options[0]);
			}
	}
	

	public static Form getInstance(Screen parentScreen) {
		if (theEditUserForm == null) {
			theEditUserForm = new EditUserForm(parentScreen);
		}
		return theEditUserForm;
	}
}
