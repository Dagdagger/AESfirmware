package stream18.aescp.view.form.system;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import stream18.aescp.view.button.Button;
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
	
 
	public static JComboBox userField;
	public static String[] userValues = {
								"Operator",
								"Supervisor",
								"Manager",
								};
	
	
	public UserForm(Screen parentScreen) {
		super(parentScreen);  
		
		Border border = BorderFactory.createLineBorder(new Color(0xeeeeee));
		Font font = new Font("Courier", Font.BOLD,12);

		
		userField = createComboField("Role:", LABELS_LEFT, LABELS_TOP + 80, LABELS_WIDTH, 16, userValues);
		passwordField = createPasswordField("Password:", LABELS_LEFT, LABELS_TOP + 160, LABELS_WIDTH, 16, true);
		usernameField= createTextField("Username:", LABELS_LEFT, LABELS_TOP + 120, LABELS_WIDTH, 16, true);
		add(LoginButton.getInstance(LABELS_LEFT + LABELS_WIDTH+10, LABELS_TOP + 200));	
			
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
		
		
	
	BufferedImage myPicture = null;
	try {
		InputStream file = Button.class.getClassLoader().getResourceAsStream("resources/logoevolution.png");
		myPicture = ImageIO.read(file);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	picLabel.setBounds(400, 90, 300, 100);
	// JPanel p = new JPanel(); 
	  
     // add label to panel 
    // p.add(picLabel); 

     // add panel to frame 
     add(picLabel); 

     // set the size of frame 
	//add(picLabel);


		
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
