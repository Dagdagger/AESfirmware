package stream18.aescp.view.screen.logs;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import stream18.aescp.Browser;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.LogsNavButton;
import stream18.aescp.view.form.logs.RecordDetailsForm;
import stream18.aescp.view.form.logs.ShowLogsForm;
import stream18.aescp.view.form.logs.ShowRecordsForm;
import stream18.aescp.view.screen.Screen;

/**
 * @author egarcia
 *
 * This Screen handles two forms: ProgramForm and OptionsForm
 */
public class ShowLogsScreen extends Screen {

	// Singleton
	static ShowLogsScreen theShowLogsScreen = null;
	
	public ShowLogsScreen() {
    	super(true, true);
    	
    	// This Screen contains thrre panes, each one with one Form:
    	// 1. Log list
    	// 2. Log records
    	// 3. Record details
    	
    	UIManager.put("TabbedPane.selected", new Color(0x9698B9));
    	UIDefaults def = UIManager.getLookAndFeelDefaults();
    	def.put("TabbedPane.textIconGap", 16);
    	
    	JTabbedPane tabbedPane = new JTabbedPane();
    	tabbedPane.setFont(Browser.FONT);
    	
    	ImageIcon icon = createImageIcon("resources/logs/ShowLogs.png");
    	JComponent panel1 = makeFormPanel(ShowLogsForm.getInstance(this));
    	tabbedPane.setForeground(new Color(0xffffff));
        tabbedPane.addTab("Log files", icon, panel1,
                "List of available log files");
        tabbedPane.setBackgroundAt(0, new Color(0x444444));
        
        ImageIcon icon2 = createImageIcon("resources/logs/ShowLogs.png");
    	JComponent panel2 = makeFormPanel(ShowRecordsForm.getInstance(this));
        tabbedPane.addTab("Records", icon2, panel2,
                "Records in log file");
        tabbedPane.setBackgroundAt(1, new Color(0x444444));

        ImageIcon icon3 = createImageIcon("resources/Mode.png");
    	JComponent panel3 = makeFormPanel(RecordDetailsForm.getInstance(this));
        tabbedPane.addTab("Record details", icon3, panel3,
                "Record detail");
        tabbedPane.setBackgroundAt(2, new Color(0x444444));

        tabbedPane.setBounds(new Rectangle(0,  0, Browser.SCREEN_WIDTH - LEFT_WIDTH, BOTTOM_HEIGHT));
    	bottom.add(tabbedPane);
    	
    	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new LogsNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }

	public static ShowLogsScreen getInstance() {
        if (theShowLogsScreen == null) {
        	theShowLogsScreen = new ShowLogsScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theShowLogsScreen.addStatus();
        
        return theShowLogsScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theShowLogsScreen = ShowLogsScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theShowLogsScreen, previousScreen);
	}
}
