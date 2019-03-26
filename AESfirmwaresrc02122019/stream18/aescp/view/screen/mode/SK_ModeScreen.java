package stream18.aescp.view.screen.mode;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import stream18.aescp.Browser;
import stream18.aescp.controller.TestMode;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.controller.TestVars;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.LogsNavButton;
import stream18.aescp.view.button.ModeNavButton;
import stream18.aescp.view.button.SetupButton;
import stream18.aescp.view.button.StartButton;
import stream18.aescp.view.button.StopButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

/**
 * @author egarcia
 *
 * This Screen handles three forms: TestMode, Results and Settings
 * It is an skeleton for five different types of TestModes
 */
public abstract class SK_ModeScreen extends Screen {

	// Singleton
	static SK_ModeScreen theConcreteModeScreen = null;
	private StartButton theStartButton;
	private StopButton theStopButton;
	private static ActionListener commonActionListener;
	private TestMode theTestMode;
	
	public SK_ModeScreen(String firstTabTitle, Form theResultsForm, Form theGraphForm, Form theSettingsForm) {
    	super(true, true);
    	theResultsForm.setParentScreen(this);
    	theGraphForm.setParentScreen(this);
    	theSettingsForm.setParentScreen(this);
    //	
    	theTestMode = new TestMode();
  
    	// https://stackoverflow.com/questions/6994352/how-to-set-jtabbedpane-tab-height-width-background-foreground-color-both-select
    	UIManager.put("TabbedPane.selected", new Color(0x9698B9));
    	UIDefaults def = UIManager.getLookAndFeelDefaults();
    	def.put("TabbedPane.textIconGap", 16);
    	
    	JTabbedPane tabbedPane = new JTabbedPane();
    	tabbedPane.setFont(Browser.FONT);
    	
        ImageIcon icon2 = createImageIcon("resources/Setup.png");
     	JComponent panel2 = makeFormPanel(theResultsForm);
         tabbedPane.addTab("Results", icon2, panel2,
                 "Test results");
         tabbedPane.setBackgroundAt(0, new Color(0x444444));
    	
    	ImageIcon icon = createImageIcon("resources/Open.png");
    	JComponent panel1 = makeFormPanel(theGraphForm);
    	tabbedPane.setForeground(new Color(0xffffff));
        tabbedPane.addTab(firstTabTitle, icon, panel1,
                "TBD");
        tabbedPane.setBackgroundAt(1, new Color(0x444444));
        

        ImageIcon icon3 = createImageIcon("resources/Setup.png");
    	JComponent panel3 = makeFormPanel(theSettingsForm);
        tabbedPane.addTab("Settings", icon3, panel3,
                "Test settings");
        tabbedPane.setBackgroundAt(2, new Color(0x444444));
        
        tabbedPane.setBounds(new Rectangle(0,  0, Browser.SCREEN_WIDTH - LEFT_WIDTH, BOTTOM_HEIGHT));
    	bottom.add(tabbedPane);
    	
    	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    	
    	    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new ModeNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    }
	
	// This must be overwritten
	static SK_ModeScreen getInstance() {
		return null;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		
		try {
			Method method = this.getClass().getMethod("getInstance");
			theConcreteModeScreen = (SK_ModeScreen) method.invoke(this);
			theConcreteModeScreen.addActionListener(commonActionListener);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("theConcreteModeScreen: " + theConcreteModeScreen.toString());
				
		// Now ask the browser to make this screen active
		// instead of doing it here, notify the Controller, which will be the one to
		// call setScreen, and also will do other things like changing the top area's
		// programName
		this.previousScreen = previousScreen;
		fireActionEvent(0);	// The Event ID does not really matter
	}

	public static void addCommonActionListener(ActionListener actionListener) {
		commonActionListener = actionListener;
	}
	
	public Mode getTestMode() {
		return theTestMode.getTestMode();
	}
	
	public void setTestMode(Mode mode) {
		theTestMode.setTestMode(mode);
	}
	

}
