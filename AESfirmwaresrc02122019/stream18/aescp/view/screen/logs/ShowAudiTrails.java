package stream18.aescp.view.screen.logs;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
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
public class ShowAudiTrails extends Screen {

	static ShowAudiTrails theShowAudiTrails = null; 
    	 

	
    public JTable connect() {
    	ResultSet rs=null;
    	JTable Auditable = null;
    	try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost/aes?serverTimezone=UTC","root","aes123");  
		java.sql.Statement stmt=con.createStatement();  
		
		rs = stmt.executeQuery("select * from Auditrails");
		DefaultTableModel yee = buildTableModel(rs);
		Auditable = new JTable(yee);

		JScrollPane pane = new JScrollPane(Auditable);
		Auditable.setEnabled(false);
		pane.setBounds(new Rectangle(0,  0, Browser.SCREEN_WIDTH - LEFT_WIDTH, BOTTOM_HEIGHT));
		System.out.println("I Was here!");
		Auditable.repaint();
		SwingUtilities.updateComponentTreeUI(pane);
		yee.fireTableDataChanged();
		Auditable.revalidate();
		SwingUtilities.updateComponentTreeUI(pane);
       	bottom.add(pane);   
       	SwingUtilities.updateComponentTreeUI(pane);
		con.close(); 
	    }
    	
    	catch(Exception e)
		{ System.out.println(e);
		}
	return Auditable;
}
    
    

	public ShowAudiTrails() throws SQLException {
    	super(true, true);
		connect();	
         
     	leftSide.add(new UpLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-300));
    	leftSide.add(new DownLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-200)); 	
    	leftSide.add(new LogsNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    	leftSide.add(new TurntoPDFButton(TurntoPDFButton.DEFAULT_X, TurntoPDFButton.DEFAULT_Y-150));

      
     //  	removeDataLines();
    }


	public static ShowAudiTrails getInstance() throws SQLException {
        	theShowAudiTrails = new ShowAudiTrails();
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theShowAudiTrails.addStatus();
        theShowAudiTrails.addTop();
    	TopForm.getInstance(null).showLogsTopForm("Trails");   	
    	
        return theShowAudiTrails;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		try {
			theShowAudiTrails = ShowAudiTrails.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theShowAudiTrails, previousScreen);
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
		 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		 try {
		 int count=table.getRowCount();
		   Document document=new Document(PageSize.A4.rotate());

		         PdfWriter writer =  PdfWriter.getInstance(document,  byteArrayOutputStream);
		          document.open();
		          PdfPTable tab=new PdfPTable(4);
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
		          document.add(addTitle("AudiTrails"));
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
		   byte[] pdfBytes = byteArrayOutputStream.toByteArray();
		   FileOutputStream out = new FileOutputStream("audiTrailsTable.pdf");
		   out.write(pdfBytes);
		   out.close();
		       }
		       catch(Exception e){e.printStackTrace();}
		  
		   // processBuilder.command("bash", "-c", "cd ~/");
			try {
				String[] b = new String[] {"bash", "-c", "sudo cp ~/audiTrailsTable.pdf /media/pi/*"};  
		        Process p = Runtime.getRuntime().exec(b);
				p.waitFor();
			} catch (IOException |InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
	 
	/* private void addHeader(PdfWriter writer){
	        PdfPTable header = new PdfPTable(2);
	        try {
	            // set defaults
	            header.setWidths(new int[]{2, 24});
	            header.setTotalWidth(527);
	            header.setLockedWidth(true);
	            header.getDefaultCell().setFixedHeight(40);
	            header.getDefaultCell().setBorder(Rectangle.OUT_BOTTOM);
	            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

	            // add image
	            Image logo = Image.getInstance(ShowAudiTrails.class.getResource("resources/logoevolution.png"));
	            header.addCell(logo);

	            // add text
	            PdfPCell text = new PdfPCell();
	            text.setPaddingBottom(15);
	            text.setPaddingLeft(10);
	            text.setBorder(Rectangle.OUT_BOTTOM);
	            text.setBorderColor(BaseColor.LIGHT_GRAY);
	            text.addElement(new Phrase("iText PDF Header Footer Example"));
	            text.addElement(new Phrase("https://memorynotfound.com"));
	            header.addCell(text);

	            // write content
	            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
	        } catch(DocumentException de) {
	            throw new ExceptionConverter(de);
	        } catch (MalformedURLException e) {
	            throw new ExceptionConverter(e);
	        } catch (IOException e) {
	            throw new ExceptionConverter(e);
	        }
	    }
		  */
		  
		 
		  
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
