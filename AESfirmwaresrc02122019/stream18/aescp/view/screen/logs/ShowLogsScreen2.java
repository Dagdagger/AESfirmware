package stream18.aescp.view.screen.logs;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import stream18.aescp.Browser;
import stream18.aescp.controller.TestVars;
import stream18.aescp.view.button.DownLogsButton;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.logs.TurntoPDFButton;
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
    static JTable table = new JTable(model); 
    	 
	public ShowLogsScreen2() {
    	super(true, true);

    	  model.addColumn("User"); 
    	  model.addColumn("Test Mode"); 
    	  model.addColumn("Time"); 
    	  model.addColumn("Results");
    	  model.addColumn("Fill Time");
    	  model.addRow(new Object[]{			 
  				"User",
  				"Test Mode",
  				"Start Time",		
  				"Results",
  				"Fill Time"
  		});
     
     	leftSide.add(new UpLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-300));
    	leftSide.add(new DownLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-200)); 	
    	leftSide.add(new LogsNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    	leftSide.add(new TurntoPDFButton(TurntoPDFButton.DEFAULT_X, TurntoPDFButton.DEFAULT_Y-150));

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
    	TopForm.getInstance(null).showLogsTopForm("Test Logs");   	
    	
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
				TestVars.getdidPass(),
				TestVars.getChargevar()
		});
		
	 }
	 
	 public static void print() {
		    Document document = new Document(PageSize.A4.rotate());
		    try {
		      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("testsjTable.pdf"));

		      document.open();
		      PdfContentByte cb = writer.getDirectContent();

		      cb.saveState();

			  Graphics2D g2 = cb.createGraphics(1000, 500);
		      Shape oldClip = g2.getClip();
		      g2.clipRect(0, 0, 5000, 5000);

		      table.print(g2);
		      g2.setClip(oldClip);

		      g2.dispose();
		      cb.restoreState();
		    } catch (Exception e) {
		      System.err.println(e.getMessage());
		    }
		    document.close();
		   // processBuilder.command("bash", "-c", "cd ~/");
			try {
				String[] b = new String[] {"bash", "-c", "sudo cp ~/testsjTable.pdf /media/pi/*"};  
		        Process p = Runtime.getRuntime().exec(b);
				p.waitFor();
			} catch (IOException |InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }
	 public static void removeDataLines() {
			model.setRowCount(0);
		
	 }
	
	 
}
