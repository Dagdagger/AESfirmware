package stream18.aescp.view.form.system;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import stream18.aescp.view.button.LoginButton;
import stream18.aescp.view.button.OkButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.HomeScreen;
import stream18.aescp.view.screen.Screen;

public class UserForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int LABELS_WIDTH = 140;
	private static final int LABELS_TOP = 5;
	private static final int LABELS_LEFT = 10;
	
	private static UserForm theUserForm;
	public static JTextField usernameField;
	public static JTextField passwordField;
	public static JComboBox roleField;
	public static String[] roleValues = {"Manager", "Supervisor", "Operator"};
	
	public static JTextField companyName;
	public static JTextField companyAddress;
	public static JTextField companyAddress2;
	public static JTextField companyPhone;
	
 
	public static JTextField userField;
	public static String[] userValues = {
								"Operator 1", "Operator 2", "Operator 3", "Operator 4", "Operator 5",
								"Supervisor 1", "Supervisor 2", "Supervisor 3", "Supervisor 4", "Supervisor 5",
								"Manager 1", "Manager 2", "Manager 3", "Manager 4", "Manager 5"
								
								};
	
	
	public UserForm(Screen parentScreen) {
		super(parentScreen);  
		
		Border border = BorderFactory.createLineBorder(new Color(0xeeeeee));
		Font font = new Font("Courier", Font.BOLD,12);

		userField = createUserField("Role:", LABELS_LEFT, LABELS_TOP + 80, LABELS_WIDTH, 16, true);
		passwordField = createPasswordField("Password:", LABELS_LEFT, LABELS_TOP + 120, LABELS_WIDTH, 16, true);	
		//userField = createComboField("Role:", LABELS_LEFT, LABELS_TOP + 80, LABELS_WIDTH, 16, userValues);
		passwordField = createPasswordField("Password:", LABELS_LEFT, LABELS_TOP + 120, LABELS_WIDTH, 16, true);
		add(LoginButton.getInstance(LABELS_LEFT + LABELS_WIDTH+10, LABELS_TOP + 160));	
			
		companyName = createStatusText("AES Corporation USA ", LABELS_LEFT+300 ,LABELS_TOP + 220,LABELS_WIDTH+150);
		companyName.setBackground(new Color(0xeeeeee));
		companyName.setBorder(border);	
		companyName.setFont(font);
		
		companyAddress=createStatusText("4630 Border Village Rd. Suite N494 ",LABELS_LEFT+300 ,LABELS_TOP + 250,LABELS_WIDTH+250);
		companyAddress.setBackground(new Color(0xeeeeee));
		companyAddress.setBorder(border);
		companyAddress.setFont(font);
		
		companyAddress2=createStatusText("San Diego CA, 92173 ", LABELS_LEFT+300 ,LABELS_TOP + 270,LABELS_WIDTH+150);
		companyAddress2.setBackground(new Color(0xeeeeee));
		companyAddress2.setBorder(border);
		companyAddress2.setFont(font);
		 
		
		
		companyPhone=createStatusText("1-877 248-7099", LABELS_LEFT+300 ,LABELS_TOP + 290,LABELS_WIDTH+150);
		companyPhone.setBackground(new Color(0xeeeeee));
		companyPhone.setBorder(border);
		companyPhone.setFont(font);
		
		passwordField.setText("1");
		//add(LoginButton.getInstance(LABELS_LEFT + LABELS_WIDTH, LABELS_TOP + 270));	 
		
	//	UserForm.usernameField.setText("1");
	//	UserForm.passwordField.setText("1");
	
		
	}

	public static UserForm getInstance(Screen parentScreen) {
		if (theUserForm == null) {
			theUserForm = new UserForm(parentScreen);
		}
		 
		return theUserForm;
	}
	
 
 
}
