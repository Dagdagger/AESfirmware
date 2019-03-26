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
import stream18.aescp.view.button.logs.ViewRecordsButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.form.system.ShowLogsRenderer;
import stream18.aescp.view.screen.Screen;

public class ShowLogsForm extends Form {
	private static final long serialVersionUID = 1L;
	private static final int LABELS_TOP = 50;
	private static final int LABELS_LEFT = 50;
	
	private static ShowLogsForm theShowLogsForm;
	
	protected DefaultListModel<String> dm;
	
	public ShowLogsForm(Screen parentScreen) {
		super(parentScreen);

		JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		
		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(40, 40));
		
		JList<String> jList = new JList<String>();

		dm = new DefaultListModel<String>();
		populateDM();
		jList.setModel(dm);
		
		jList.setCellRenderer(new ShowLogsRenderer());
		
		jScrollPane1.setViewportView(jList);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
	    JLabel l = new JLabel("Logfile", JLabel.CENTER);
	    l.setBounds(new Rectangle(LABELS_LEFT, LABELS_TOP/2 - 4, 250, 30));
	    l.setFont(Browser.FONT);
	    l.setBackground(Color.WHITE);
	    l.setOpaque(true);
	    l.setBorder(border);
	    add(l);
		
		add(jScrollPane1);
		jScrollPane1.setBounds(LABELS_LEFT, LABELS_TOP, 450, 240);
		
		add(ViewRecordsButton.getInstance(550, LABELS_TOP));
	}

	public static Form getInstance(Screen parentScreen) {
		if (theShowLogsForm == null) {
			theShowLogsForm = new ShowLogsForm(parentScreen);
		}
		
		return theShowLogsForm;
	}
	
	protected void populateDM() {
		dm.clear();
		dm.addElement("2018_08_01.log");
		dm.addElement("2018_08_02.log");
		dm.addElement("2018_08_03.log");
		dm.addElement("2018_08_06.log");
		dm.addElement("2018_08_07.log");
		dm.addElement("2018_08_08.log");
		dm.addElement("2018_08_09.log");
		dm.addElement("2018_08_10.log");
		dm.addElement("2018_08_13.log");
		dm.addElement("2018_08_14.log");
		dm.addElement("2018_08_15.log");
		dm.addElement("2018_08_16.log");
		dm.addElement("2018_08_17.log");
	}
}