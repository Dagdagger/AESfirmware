package stream18.aescp.view.screen;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import stream18.aescp.Browser;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.form.OptionsForm;
import stream18.aescp.view.form.ProgramForm;
import stream18.aescp.view.form.TopForm;

/**
 * @author egarcia
 *
 * This Screen handles two forms: ProgramForm and OptionsForm
 */
public class SetupScreen extends Screen {

	// Singleton
	static SetupScreen theSetupScreen = null;
	static int ICON_SIZE = 48;
	
	public SetupScreen() {
    	super(true, true);
    	
    	// Here we will display the program details
    	// This is one has two tabs
    	
    	// https://stackoverflow.com/questions/6994352/how-to-set-jtabbedpane-tab-height-width-background-foreground-color-both-select
    	UIManager.put("TabbedPane.selected", new Color(0x9698B9));
    	UIDefaults def = UIManager.getLookAndFeelDefaults();
    	def.put("TabbedPane.textIconGap", 16);
    	
    	JTabbedPane tabbedPane = new JTabbedPane();
    	tabbedPane.setFont(Browser.FONT);
    	
    	ImageIcon icon = Screen.createImageIcon("resources/Open.png");
    	JComponent panel1 = Screen.makeFormPanel(ProgramForm.getInstance(this));
    	tabbedPane.setForeground(new Color(0xffffff));
        tabbedPane.addTab("Program", icon, panel1,
                "Program parameters");
        tabbedPane.setBackgroundAt(0, new Color(0x444444));
        ImageIcon icon2 = Screen.createImageIcon("resources/Setup.png");
    	JComponent panel2 = Screen.makeFormPanel(OptionsForm.getInstance(this));
        tabbedPane.addTab("Options", icon2, panel2,
                "Program options");
        tabbedPane.setBackgroundAt(1, new Color(0x444444));
        
        tabbedPane.setBounds(new Rectangle(0,  0, Browser.SCREEN_WIDTH - LEFT_WIDTH, BOTTOM_HEIGHT));
    	bottom.add(tabbedPane);
    	
    	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }
	
	public static SetupScreen getInstance() {
        if (theSetupScreen == null) {
        	theSetupScreen = new SetupScreen();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theSetupScreen.addStatus();

        theSetupScreen.addTop();

        return theSetupScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theSetupScreen = SetupScreen.getInstance();
		  TopForm.getInstance(null).setupTopForm();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theSetupScreen, previousScreen);
	}
}
