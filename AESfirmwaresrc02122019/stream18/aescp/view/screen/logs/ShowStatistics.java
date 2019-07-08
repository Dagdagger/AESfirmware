package stream18.aescp.view.screen.logs;

import java.sql.SQLException;

import stream18.aescp.Browser;
import stream18.aescp.view.button.DownLogsButton;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.LogsNavButton;
import stream18.aescp.view.button.UpLogsButton;
import stream18.aescp.view.button.logs.TurntoPDFButton;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.screen.Screen;

public class ShowStatistics extends Screen {

	static ShowStatistics theShowStatistics = null;
	
	public ShowStatistics() throws SQLException {
    	super(true, true);
         
     	leftSide.add(new UpLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-300));
    	leftSide.add(new DownLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-200)); 	
    	leftSide.add(new LogsNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    	leftSide.add(new TurntoPDFButton(TurntoPDFButton.DEFAULT_X, TurntoPDFButton.DEFAULT_Y-150));

      
     //  	removeDataLines();
    }
	
	
	
	
	public static ShowStatistics getInstance() throws SQLException {
    	theShowStatistics = new ShowStatistics();
    
    // This has to be done every time, as the status bar is shared among
    // all the Screens that have a status
    theShowStatistics.addStatus();
    theShowStatistics.addTop();
	TopForm.getInstance(null).showLogsTopForm("Statistics");   	
	
    return theShowStatistics;
}
	
	public void setActive(Screen previousScreen) {
		try {
			theShowStatistics = ShowStatistics.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theShowStatistics, previousScreen);
	}
}
