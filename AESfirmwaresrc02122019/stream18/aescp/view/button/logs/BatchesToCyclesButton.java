package stream18.aescp.view.button.logs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import stream18.aescp.Browser;
import stream18.aescp.controller.BatchVars;
import stream18.aescp.controller.TestVars;
import stream18.aescp.model.DBConnection;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.screen.logs.ShowAlarms;
import stream18.aescp.view.screen.logs.ShowAudiTrails;
import stream18.aescp.view.screen.logs.ShowCycles;
import stream18.aescp.view.screen.logs.ShowLogsScreen;
import stream18.aescp.view.screen.logs.ShowLogsScreen2;
import stream18.aescp.view.screen.logs.ShowTotalLogs;
import stream18.aescp.view.screen.HomeScreen;


public class BatchesToCyclesButton extends Button{
	
	DecimalFormat df = new DecimalFormat("0.00##");
	
	protected static BatchesToCyclesButton theBatchesToCyclesButton = null;
	
	public BatchesToCyclesButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/mode/VacuumChamber.png", "print", new Color(0x008B8B));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				
				int shouldLogout = Browser.getInstance().displayDialog("Are you sure you want to Export to PDF?");
				if (shouldLogout == 0) {
					try {
						
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
						String currentTime = (dateFormat.format(date));
						
						try { 
							   Document document=new Document(PageSize.A4.rotate());
							   PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("BatchTable.pdf"));
							   document.open();
							   BufferedImage img = null;
						  		  InputStream file = Button.class.getClassLoader().getResourceAsStream("resources/logo.png");
						  		  img = ImageIO.read(file);
						  		PdfContentByte pdfCB = new PdfContentByte(writer);
						        Image image = Image.getInstance(pdfCB, img, 1);
						        
						          document.add(image);
						          
						          BufferedImage imgtwo = null;
						          InputStream filetwo = Button.class.getClassLoader().getResourceAsStream("resources/logoevolution.png");
						          imgtwo = ImageIO.read(filetwo);
						          Image imagetwo = Image.getInstance(pdfCB, imgtwo, 1);
						          
						          imagetwo.setAbsolutePosition(500, 480);
						          
						          document.add(imagetwo);
							   document.addTitle("Batch Summary Report");
							   PdfPTable tab=new PdfPTable(11);
							          
							   		Paragraph firstRow = new Paragraph(StringUtils.rightPad("Test User: " + TestVars.getTestUservar(), 100));
							   		firstRow.add("Batch Code: AE274");
							   		
							   		Paragraph secondRow = new Paragraph(StringUtils.rightPad("User Role: " + TestVars.gettestRoleVar(), 100));
							   		secondRow.add("Batch Description: " + TestVars.getprogramName());
							   		
							   		Paragraph thirdRow = new Paragraph(StringUtils.rightPad("Report ID: 3A52", 100));
							   		thirdRow.add("Batch Start Date:" + BatchVars.getStartTime());
							   		
							   		Paragraph fourthRow = new Paragraph(StringUtils.rightPad("Recipe Code: " + TopForm.progNumber.getText() , 100));
							   		fourthRow.add("Batch End Date:" + currentTime);
							   		
							   		Paragraph fifthRow = new Paragraph("Recipe Description: " + TestVars.getprogramName());
//							          document.add(new Paragraph(StringUtils.rightPad("Test User:", 20)));
							   		document.add(firstRow);
							   		document.add(secondRow);
							   		document.add(thirdRow);
							   		document.add(fourthRow);
							   		document.add(fifthRow);
//							        BufferedImage img = null;
//							  		PdfContentByte pdfCB = new PdfContentByte(writer);
							          document.add(addTitle("Batch Summary Report"));
							          tab.addCell("Num Tested");
							          tab.addCell("Time");
							          tab.addCell("User");
								   	  tab.addCell("Passes");
									  tab.addCell("Fails");
									  tab.addCell("Fill Time");
									  tab.addCell("max Drop");
									  tab.addCell("Program");
									  tab.addCell("Mean Pres.");
									  tab.addCell("Pres. Range");
									  tab.addCell("Std. Dev.");
									  
									  //Start Adding Data 
									  tab.addCell(Integer.toString(BatchVars.getNumofTests()));
									  tab.addCell(currentTime);
									  tab.addCell(TestVars.gettestRoleVar() + " " + TestVars.getTestUservar());
									  tab.addCell(Integer.toString(BatchVars.getPasses()));
									  tab.addCell(Integer.toString(BatchVars.getFailures()));
									  tab.addCell(Double.toString(TestVars.getChargevar()));
									  tab.addCell(Double.toString(TestVars.getmaxPressureDrop()));
									  tab.addCell(TestVars.getprogramName());
									  tab.addCell(df.format(BatchVars.getAveragePressures()));
									  tab.addCell(df.format(BatchVars.getRange()));
									  tab.addCell(df.format(BatchVars.getStandardDeviation())); 
								
							   
							   
							   
									  document.add(tab);
									  document.close();
							       }
					
						  catch(Exception f){f.printStackTrace();}
						try {
							String[] b = new String[] {"bash", "-c", "./copybatch.sh"};
					        Process p = Runtime.getRuntime().exec(b);
							p.waitFor();
						} catch (IOException |InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						DBConnection.insertAudiTrail("Exported logs to pdf", "User: "+ TestVars.getTestUservar());
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			public Paragraph addTitle(String title){ 
        Font fontbold = FontFactory.getFont("Times-Roman", 40, Font.BOLD);
        Paragraph p = new Paragraph(title, fontbold);
        p.setSpacingAfter(20);
        p.setSpacingBefore(20);
        p.setAlignment(1); // Center
        return p;
   }

		});
		
	

}
	
	
	public static BatchesToCyclesButton getInstance(int x, int y) {
		if (theBatchesToCyclesButton== null) {
			theBatchesToCyclesButton= new BatchesToCyclesButton(x, y);
		}
		return theBatchesToCyclesButton;
	}
}
