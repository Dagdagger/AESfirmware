package stream18.aescp.view.screen.logs;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import stream18.aescp.Browser;
import stream18.aescp.controller.TestVars;
import stream18.aescp.model.DBConnection;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.button.DownLogsButton;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.button.logs.TurntoPDFButton;
import stream18.aescp.view.button.LogsNavButton;
import stream18.aescp.view.button.UpLogsButton;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.screen.HomeScreen;
import stream18.aescp.view.screen.Screen;

/**
 * @author Simo
 *
 */
public class ShowAlarms extends Screen {

	static ShowAlarms theShowAlarms = null; 
    	 

	
    public JTable connect() {
    	ResultSet rs=null;
    	JTable Auditable = null;
    	try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost/aes?serverTimezone=UTC","root","aes123");  
		java.sql.Statement stmt=con.createStatement();  
		
		rs = stmt.executeQuery("select * from Alarms");
		Auditable = new JTable(buildTableModel(rs));
		JScrollPane pane = new JScrollPane(Auditable);
		Auditable.setEnabled(false);
		pane.setBounds(new Rectangle(0,  0, Browser.SCREEN_WIDTH - LEFT_WIDTH, BOTTOM_HEIGHT));
		System.out.println("I Was here!");
		Auditable.repaint();
       	bottom.add(pane);   
       	SwingUtilities.updateComponentTreeUI(pane);

		con.close(); 
	    }
    	
    	catch(Exception e)
		{ System.out.println(e);
		}
	return Auditable;
}
    
    

	public ShowAlarms() throws SQLException {
    	super(true, true);
		connect();	
         
     	leftSide.add(new UpLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-300));
    	leftSide.add(new DownLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-200)); 	
    	leftSide.add(new LogsNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    	leftSide.add(new TurntoPDFButton(TurntoPDFButton.DEFAULT_X, TurntoPDFButton.DEFAULT_Y-150));

      
     //  	removeDataLines();
    }


	public static ShowAlarms getInstance() throws SQLException {
        	theShowAlarms = new ShowAlarms();
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theShowAlarms.addStatus();
        theShowAlarms.addTop();
    	TopForm.getInstance(null).showLogsTopForm("Alarms");   	
    	
        return theShowAlarms;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		try {
			theShowAlarms = ShowAlarms.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theShowAlarms, previousScreen);
	}
	public static Paragraph addTitle(String title){
        Font fontbold = FontFactory.getFont("Times-Roman", 40, Font.BOLD);
        Paragraph p = new Paragraph(title, fontbold);
        p.setSpacingAfter(20);
        p.setSpacingBefore(20);
        p.setAlignment(1); // Center
        return p;
   }
	
	 public void print() {
		 JTable table = connect();
		 try {
		 int count=table.getRowCount();
		   Document document=new Document(PageSize.A4.rotate());
		   document.addTitle("Alarms");
		          PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("alarmsTable.pdf"));
		          document.open();
		          PdfPTable tab=new PdfPTable(6);
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
		          document.add(addTitle("Alarms"));
		          tab.addCell("Num");
		          tab.addCell("Action");
		          tab.addCell("Details");
			   	  tab.addCell("Time");
			   	  tab.addCell("Tag");
			   	  tab.addCell("Status");
		   for(int i=0;i<count;i++){
		   Object obj1 = GetData(table, i, 0);
		   Object obj2 = GetData(table, i, 1);
		   Object obj3 = GetData(table, i, 2);
		   Object obj4 = GetData(table, i, 3);
		
		   String value1=obj1.toString();
		   String value2=obj2.toString();
		   String value3=obj3.toString();
		   String value4=obj4.toString();

		   
		   tab.addCell(value1);
		   tab.addCell(value2);
		   tab.addCell(value3);
		   tab.addCell(value4);
		   tab.addCell("CiCode");
		   tab.addCell("ON");

		   
		   }
		   document.add(tab);
		   document.close();
		       }
		       catch(Exception e){}
		  
		   // processBuilder.command("bash", "-c", "cd ~/");
			try {
				String[] b = new String[] {"bash", "-c", "sudo cp ~/alarmsTable.pdf /media/pi/*"};  
		        Process p = Runtime.getRuntime().exec(b);
				p.waitFor();
			} catch (IOException |InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		  
	 
	/* public void print() {
		 JTable table = connect();
		 try {
		 int count=table.getRowCount();
		   Document document=new Document();
 document.addTitle("Alarms");
		          PdfWriter.getInstance(document,new FileOutputStream("alarmsTable.pdf"));
		          document.open();
		          PdfPTable tab=new PdfPTable(4);
		          Image img = Image.getInstance("resources/logo.png");
		          document.add(img);
		          document.add(addTitle("Alarms"));
		          tab.addCell("Num");
		          tab.addCell("Action");
		          tab.addCell("Details");
		          tab.addCell("Time");
for(int i=0;i<count;i++){
		   Object obj1 = GetData(table, i, 0);
		   Object obj2 = GetData(table, i, 1);
		   Object obj3 = GetData(table, i, 2);
		   Object obj4 = GetData(table, i, 3);
		   String value1=obj1.toString();
		   String value2=obj2.toString();
		   String value3=obj3.toString();
		   String value4=obj4.toString();
		   
		   tab.addCell(value1);
		   tab.addCell(value2);
		   tab.addCell(value3);
		   tab.addCell(value4);
		   }
		   document.add(tab);
		   document.close();
		       }
		       catch(Exception e){}
		  
		   // processBuilder.command("bash", "-c", "cd ~/");
			try {
				String[] b = new String[] {"bash", "-c", "sudo cp ~/alarmsTable.pdf /media/pi/*"};  
		        Process p = Runtime.getRuntime().exec(b);
				p.waitFor();
			} catch (IOException |InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}*/
		  
		  
		 
		  
		  public Object GetData(JTable table, int row_index, int col_index){
			  return table.getModel().getValueAt(row_index, col_index);
			 }
	 public static DefaultTableModel buildTableModel(ResultSet rs)
		        throws SQLException {

		    ResultSetMetaData metaData = rs.getMetaData();

		    // names of columns
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }

		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }

		    return new DefaultTableModel(data, columnNames);

		}
	
	 
}
