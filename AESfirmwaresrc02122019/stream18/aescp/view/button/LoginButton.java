package stream18.aescp.view.button;

import java.awt.Color;
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
				
				isAccessValid(UserForm.passwordField.getText(), UserForm.userField.getText());
				
				//System.out.println("user" + " " + UserForm.userField.getSelectedItem()+" "+ "Logged in");	 
				System.out.println("user" + " " + UserForm.userField.getText()+" "+ "Logged in");
		        //TestVars.setTestUservar(String.valueOf(UserForm.userField.getSelectedItem()));
				TestVars.setTestUservar(String.valueOf(UserForm.userField.getText()));
			}
		});
	}	

	public static LoginButton getInstance(int x, int y) {
		if (theOkButton == null) {
			theOkButton = new LoginButton(x, y);

		}
		return theOkButton;	
	}
	
	
	public static LoginButton isAccessValid(String password, String role) {
		System.out.println("Hello 0");
		//String user = UserForm.userField.getText();
		String user = role;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			System.out.println("Hello 1");
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/aes?serverTimezone=UTC","root","");  
			System.out.println("Hello 2");
			//database name, root is username and password  
			java.sql.Statement stmt=con.createStatement();  
			System.out.println("Hello 3");
			//ResultSet rs=stmt.executeQuery("select * from Users");
			
			ResultSet rs=stmt.executeQuery("select * from Users where uname = '" + user + "' && psswd ='" + password+ "'");
			//"SELECT * FROM users WHERE users_name='" + name + "' && users_password='" + password+ "'"
			System.out.println("Hello 4");
			//while(rs.next())  
			//	System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+"  "+rs.getString(4));
			//HomeScreen.getInstance().setActive(null);
			if(rs.next())
            {
            	HomeScreen.getInstance().setActive(null);
            }
           else{
                   //JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
                    JOptionPane.showMessageDialog(null, "Access Not Allowed");
               }
			con.close();  
			
			}catch(Exception e)
				{ System.out.println(e);
				}  
		
		return theOkButton;
			}  
		
		
	
	private static Connection getDBConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/AES:3306","root","");
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return conn;
		
		
	}

}
	



