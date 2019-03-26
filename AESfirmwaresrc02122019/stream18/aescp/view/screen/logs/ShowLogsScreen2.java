package stream18.aescp.view.screen.logs;

import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import stream18.aescp.Browser;
import stream18.aescp.controller.TestVars;
import stream18.aescp.view.button.DownLogsButton;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.LogsNavButton;
import stream18.aescp.view.button.UpLogsButton;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.screen.Screen;

/**
 * @author Simo
 *
 */
public class ShowLogsScreen2 extends Screen {

	static ShowLogsScreen2 theShowLogsScreen = null; 
  	static DefaultTableModel model = new DefaultTableModel(); 
    JTable table = new JTable(model); 
    	 
	public ShowLogsScreen2() {
    	super(true, true);

    	  model.addColumn("Col1"); 
    	  model.addColumn("Col2"); 
    	  model.addColumn("Col3"); 
     
     	leftSide.add(new UpLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-300));
    	leftSide.add(new DownLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-200)); 	
    	leftSide.add(new LogsNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));

        table.setBounds(new Rectangle(0,  0, Browser.SCREEN_WIDTH - LEFT_WIDTH, BOTTOM_HEIGHT));
       	bottom.add(table);   
      
       	addDataLine();
     //  	removeDataLines();
    }


	public static ShowLogsScreen2 getInstance() {
        if (theShowLogsScreen == null) {
        	theShowLogsScreen = new ShowLogsScreen2();
        }
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theShowLogsScreen.addStatus();
        theShowLogsScreen.addTop();
    	TopForm.getInstance(null).showLogsTopForm();   	
    	
        return theShowLogsScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theShowLogsScreen = ShowLogsScreen2.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theShowLogsScreen, previousScreen);
	}
	 public static void addDataLine() {
			
		model.addRow(new Object[]{			 
				TestVars.getTestUservar(),
				TestVars.getTestModevar(),
				TestVars.getTestTimeStampvar(),		
		});
		
	 }
	 public static void removeDataLines() {
			model.setRowCount(0);
		
	 }
	
	 
}
