package stream18.aescp.view.form.logs;

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
import stream18.aescp.model.LogRecordBean;
import stream18.aescp.view.button.logs.ViewRecordButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class ShowRecordsForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int LABELS_TOP = 50;
	private static final int LABELS_LEFT = 50;
	
	private static ShowRecordsForm theShowRecordsForm;
	private String logfile;
	
	protected DefaultListModel<LogRecordBean> dm;
	
	public ShowRecordsForm(Screen parentScreen) {
		super(parentScreen);

		JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		
		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(40, 40));
		
		JList<LogRecordBean> jList = new JList<LogRecordBean>();

		dm = new DefaultListModel<LogRecordBean>();
		populateDM();
		jList.setModel(dm);
		
		jList.setCellRenderer(new RecordRenderer());
		
		jScrollPane1.setViewportView(jList);
		
		// TODO: get from previous tab's selected item
		logfile = "2008-08-01.log";
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);		
	    JLabel l = new JLabel("Logfile: " + logfile, JLabel.LEFT);
	    l.setBounds(new Rectangle(LABELS_LEFT, LABELS_TOP/2 - 4, 350, 30));
	    l.setFont(Browser.FONT);
	    l.setBackground(Color.WHITE);
	    l.setOpaque(true);
	    l.setBorder(border);
	    add(l);
		
		add(jScrollPane1);
		jScrollPane1.setBounds(LABELS_LEFT, LABELS_TOP, 450, 240);
		
		add(ViewRecordButton.getInstance(550, LABELS_TOP));
	}

	public static Form getInstance(Screen parentScreen) {
		if (theShowRecordsForm == null) {
			theShowRecordsForm = new ShowRecordsForm(parentScreen);
		}
		
		return theShowRecordsForm;
	}
	
	protected void populateDM() {
		dm.clear();
		dm.addElement(new LogRecordBean("08:00:10", "SYS_LOGIN_FAIL"));
		dm.addElement(new LogRecordBean("08:01:30", "SYS_LOGIN"));
		dm.addElement(new LogRecordBean("08:01:00", "OPEN_PRG"));
		dm.addElement(new LogRecordBean("08:01:05", "MODE_SELECT"));
		dm.addElement(new LogRecordBean("08:01:10", "TEST_START"));
		dm.addElement(new LogRecordBean("08:02:45", "TEST_STOP"));
		dm.addElement(new LogRecordBean("08:12:55", "SYS_LOGOUT"));
		dm.addElement(new LogRecordBean("08:41:30", "SYS_LOGIN"));
		dm.addElement(new LogRecordBean("08:41:50", "OPEN_PRG"));
	}
}