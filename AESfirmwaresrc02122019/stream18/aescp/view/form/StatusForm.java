package stream18.aescp.view.form;

import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.view.screen.Screen;

public class StatusForm extends Form {
	private static StatusForm theStatusForm;
	private JPanel statusIcon;
	private JTextField statusText;
	private JPanel statusLockedCB;
	private JTextField statusTime;

	public StatusForm(Screen parentScreen) {
		super(parentScreen);
		
		setBackground(new Color(0xeeeeee));
		setBounds(Screen.LEFT_WIDTH,  Browser.SCREEN_HEIGHT-Screen.STATUS_HEIGHT,  Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH, Screen.STATUS_HEIGHT);
		
		// This panel contains:
		// 1. Status icon: icon
		// 2. Status text: Input text, non editable
		// 3. Locked: checkbox
		// 4. Time: Input text, non editable
		
		
		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String formattedTime=time.format(formatter);
		
		
		statusIcon = createIcon("resources/logo.png", 5, 5, 40, 40);
		
    	statusText = createStatusText(" Select", 5 + 60, 5, 200);

    	statusLockedCB = createIcon("resources/logoevolution.png", 300, 1, 190, 50);

    	statusTime = createStatusText(formattedTime, 525, 5, 200);

	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theStatusForm == null) {
			theStatusForm = new StatusForm(parentScreen);
		}
		
		return theStatusForm;
	}
	
	public void setStatusText(String newStatusText) {
		statusText.setText(newStatusText);
	}

	public void updateTime() {
		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String formattedTime=time.format(formatter);
		statusTime.setText(formattedTime);
	}

	private static final long serialVersionUID = 1L;

}
