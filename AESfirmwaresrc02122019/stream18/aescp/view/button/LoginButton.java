package stream18.aescp.view.button;

import java.awt.Color;
import stream18.aescp.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

//import com.mysql.cj.xdevapi.Statement;

import stream18.aescp.controller.TestVars;
import stream18.aescp.view.form.system.UserForm;
import stream18.aescp.view.screen.HomeScreen;
 
//import stream18.aescp.model.DBConnection;

public class LoginButton extends Button {
	
	private static final long serialVersionUID = 1L;
	private static LoginButton theOkButton;
	
	public LoginButton(int x, int y) {
		super(BOTTOM_TYPE);
		
		moveTo(x, y, 0.75f, 0.75f);
		config("resources/system/login_normal.png", "Login", new Color(0x3C90CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
				//isAccessValid(UserForm.usernameField.getText(), 
						//	UserForm.passwordField.getText(), UserForm.roleField.getSelectedIndex());				
				//isAccessValid(UserForm.passwordField.getText(), UserForm.userField.getSelectedIndex());
				
				isAccessValid(UserForm.passwordField.getText(), String.valueOf(UserForm.userField.getSelectedItem()), UserForm.usernameField.getText());
				
				System.out.println("user" + " " + UserForm.userField.getSelectedItem()+" "+ "Logged in");	 
				//System.out.println("user" + " " + UserForm.userField.getToolkit()+" "+ "Logged in");
		        TestVars.setTestUservar(String.valueOf(UserForm.userField.getSelectedItem()));
				//TestVars.setTestUservar(String.valueOf(UserForm.userField.getToolkit()));
			}
		});
	}	

	public static LoginButton getInstance(int x, int y) {
		if (theOkButton == null) {
			theOkButton = new LoginButton(x, y);

		}
		return theOkButton;	
	}
	
	
	public static LoginButton isAccessValid(String password, String role, String Username) {
		//String user = UserForm.userField.getText();
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost/aes?serverTimezone=UTC","root","aes123");  
			java.sql.Statement stmt=con.createStatement();  
			System.out.println("role = " + role + " Username = " + Username + " Pword = " + password);
			ResultSet rs=stmt.executeQuery("select * from Users where psswd = '" + password + "' && uname ='" + Username+ "' && role ='" + role+ "'" );
			if(rs.next())
            {
				DBConnection.insertAudiTrail("Login", Username);
            	HomeScreen.getInstance().setActive(null);
            }
           else{
                    JOptionPane.showMessageDialog(null, "Access Not Allowed");
               }
			con.close();  
			
			}catch(Exception e)
				{ System.out.println(e);
				}  
		
		return theOkButton;
			}  
		
		
		

}
	



