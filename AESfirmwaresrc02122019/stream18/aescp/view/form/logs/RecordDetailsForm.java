package stream18.aescp.view.form.logs;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import stream18.aescp.Browser;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class RecordDetailsForm extends Form {
	private static final long serialVersionUID = 1L;
	protected static RecordDetailsForm theRecordDetailsForm;
	private JTextField timestampF;
	private JTextField typeF;
	private JTextField userIDF;
	private JTextField actionF;
	private JTextField descriptionF;

	private static final int LABELS_LEFT = 180;
	protected static final int X_LEFT = 10;
	protected static final int Y_TOP = 30;

	private String logfile;
	
	protected RecordDetailsForm(Screen parentScreen) {
		super(parentScreen);
		
		// TODO: populate specific fields
		// Also, take into account data holders
		
		int x = X_LEFT, y = Y_TOP;
		
		// TODO: get from previous tab's selected item
		logfile = "2008-08-01.log";
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	    JLabel l = new JLabel("Logfile: " + logfile, JLabel.LEFT);
	    l.setBounds(new Rectangle(50, Y_TOP/2 - 4, 350, 30));
	    l.setFont(Browser.FONT);
	    l.setBackground(Color.WHITE);
	    l.setOpaque(true);
	    l.setBorder(border);
	    add(l);
	    
    	x = X_LEFT;
    	y += 40;
    	timestampF = createTextField("Timestamp: ", x, y, LABELS_LEFT, 8, false);
    	timestampF.setText("08.00.10");

    	x = X_LEFT;
    	y += 40;
    	typeF = createTextField("Type: ", x, y, LABELS_LEFT, 6, false);
    	typeF.setText("USER");

    	x = X_LEFT;
    	y += 40;
    	userIDF = createTextField("User ID: ", x, y, LABELS_LEFT, 16, false);
    	userIDF.setText("Username_1");

    	x = X_LEFT;
    	y += 40;
    	actionF = createTextField("Action: ", x, y, LABELS_LEFT, 16, false);
    	actionF.setText("SYS_LOGIN_FAIL");

    	x = X_LEFT;
    	y += 40;
    	descriptionF = createTextField("Description: ", x, y, LABELS_LEFT, 32, false);
    	descriptionF.setText("The user entered an invalid password");
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theRecordDetailsForm == null) {
			theRecordDetailsForm = new RecordDetailsForm(parentScreen);
		}
		
		return theRecordDetailsForm;
	}
}
