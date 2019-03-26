package stream18.aescp.view.form.logs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

import stream18.aescp.Browser;
import stream18.aescp.view.button.system.LanConfigButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class LogsViewForm extends Form {
	private static final long serialVersionUID = 1L;
	protected static LogsViewForm theRecordDetailsForm;
 

	private static final int LABELS_LEFT = 180;
	protected static final int X_LEFT = 10;
	protected static final int Y_TOP = 30;
 
	private JTable table = new JTable();
	final JFrame frame = new JFrame();
	
	protected LogsViewForm(Screen parentScreen) {
		super(parentScreen);
		
  
        table.setFillsViewportHeight(true);
        frame.getContentPane().setLayout(new BorderLayout());
 

 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 200);
        frame.setVisible(true);

     
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theRecordDetailsForm == null) {
			theRecordDetailsForm = new LogsViewForm(parentScreen);
		}
		
		return theRecordDetailsForm;
	}
}
