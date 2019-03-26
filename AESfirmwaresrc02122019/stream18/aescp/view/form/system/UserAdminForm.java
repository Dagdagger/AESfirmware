package stream18.aescp.view.form.system;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import stream18.aescp.Browser;
import stream18.aescp.view.button.system.AddUserButton;
import stream18.aescp.view.button.system.EditUserButton;
import stream18.aescp.view.button.system.RemoveUserButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class UserAdminForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int LABELS_TOP = 50;
	private static final int LABELS_LEFT = 50;
	
	private static UserAdminForm theUserAdminForm;
	
	protected DefaultListModel<UserRoleBean> dm;
	
	public UserAdminForm(Screen parentScreen) {
		super(parentScreen);

		JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		
		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(40, 40));
		
		JList<UserRoleBean> jList = new JList<UserRoleBean>();

		dm = new DefaultListModel<UserRoleBean>();
		populateDM();
		jList.setModel(dm);
		
		jList.setCellRenderer(new UserAdminRenderer());
		
		jScrollPane1.setViewportView(jList);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
	    JLabel l = new JLabel("Username", JLabel.CENTER);
	    l.setBounds(new Rectangle(LABELS_LEFT, LABELS_TOP/2 - 4, 250, 30));
	    l.setFont(Browser.FONT);
	    l.setBackground(Color.WHITE);
	    l.setOpaque(true);
	    l.setBorder(border);
	    add(l);
	    JLabel lrole = new JLabel("Role", JLabel.CENTER);
	    lrole.setBounds(new Rectangle(LABELS_LEFT + 250, LABELS_TOP/2 - 4, 200, 30));
	    lrole.setFont(Browser.FONT);
	    lrole.setBackground(Color.WHITE);
	    lrole.setOpaque(true);
	    lrole.setBorder(border);
	    add(lrole);
		
		add(jScrollPane1);
		jScrollPane1.setBounds(LABELS_LEFT, LABELS_TOP, 450, 300);
		
		add(AddUserButton.getInstance(550, LABELS_TOP));
		add(RemoveUserButton.getInstance(550, LABELS_TOP + 100));
		add(EditUserButton.getInstance(550, LABELS_TOP + 200));
	}

	public static Form getInstance(Screen parentScreen) {
		if (theUserAdminForm == null) {
			theUserAdminForm = new UserAdminForm(parentScreen);
		}
		
		return theUserAdminForm;
	}
	
	protected void populateDM() {
		dm.clear();
		dm.addElement(new UserRoleBean("User_1", "Admin"));
		dm.addElement(new UserRoleBean("User_2", "Operator"));
		dm.addElement(new UserRoleBean("User_3", "Operator"));
		dm.addElement(new UserRoleBean("User_4", "Admin"));
		dm.addElement(new UserRoleBean("User_5", "Operator"));
		dm.addElement(new UserRoleBean("User_6", "Operator"));
		dm.addElement(new UserRoleBean("User_7", "Operator"));
		dm.addElement(new UserRoleBean("User_8", "Operator"));
		dm.addElement(new UserRoleBean("User_9", "Admin"));
		dm.addElement(new UserRoleBean("User_A", "Operator"));
	}
}