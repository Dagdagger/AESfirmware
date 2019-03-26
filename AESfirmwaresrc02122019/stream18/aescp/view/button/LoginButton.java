package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.String;
import javax.swing.JOptionPane;

import stream18.aescp.controller.TestVars;
import stream18.aescp.view.form.system.UserForm;
import stream18.aescp.view.screen.HomeScreen;
 

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
				isAccessValid(UserForm.passwordField.getText(), UserForm.userField.getSelectedIndex());
	 
				System.out.println("user" + " " + UserForm.userField.getSelectedItem()+" "+ "Logged in");	 
		        TestVars.setTestUservar(String.valueOf(UserForm.userField.getSelectedItem()));
			}
		});
	}	

	public static LoginButton getInstance(int x, int y) {
		if (theOkButton == null) {
			theOkButton = new LoginButton(x, y);

		}
		return theOkButton;	
	}
	
	
	public static LoginButton isAccessValid(String password, int role) {
		if ((password.equals("1"))&&(role==0)) 
		{
			HomeScreen.getInstance().setActive(null);
			
			//ReadyScreen_A.getInstance().setActive(null);
			
		}
		else if((password.equals("2"))&&(role==1))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("3"))&&(role==2))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("4"))&&(role==3))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("5"))&&(role==4))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("6"))&&(role==5))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("7"))&&(role==6))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("8"))&&(role==7))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("9"))&&(role==8))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("10"))&&(role==9))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("11"))&&(role==10))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("12"))&&(role==11))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("13"))&&(role==12))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("14"))&&(role==13))
		{
			HomeScreen.getInstance().setActive(null);
		}
		else if((password.equals("15"))&&(role==14))
		{
			HomeScreen.getInstance().setActive(null);
		}
		
		
		else
		{
			JOptionPane.showMessageDialog(null, "Access Not Allowed");

		}
		return theOkButton;
		
		
	}
}
