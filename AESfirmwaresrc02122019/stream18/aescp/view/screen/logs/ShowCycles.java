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
 * @author Omar
 *
 */
public class ShowCycles extends Screen {

	static ShowCycles theShowCycles = null; 
    	 

	
    public JTable connect() {
    	ResultSet rs=null;
    	JTable Auditable = null;
    	try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost/aes?serverTimezone=UTC","root","aes123");  
		java.sql.Statement stmt=con.createStatement();  
		
		rs = stmt.executeQuery("select * from Cycles");
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
    
    

	public ShowCycles() throws SQLException {
    	super(true, true);
		connect();	
         
     	leftSide.add(new UpLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-300));
    	leftSide.add(new DownLogsButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y-200)); 	
    	leftSide.add(new LogsNavButton(LogsNavButton.DEFAULT_X, LogsNavButton.DEFAULT_Y));
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
    	leftSide.add(new TurntoPDFButton(TurntoPDFButton.DEFAULT_X, TurntoPDFButton.DEFAULT_Y-150));

      
     //  	removeDataLines();
    }


	public static ShowCycles getInstance() throws SQLException {
        	theShowCycles = new ShowCycles();
        
        // This has to be done every time, as the status bar is shared among
        // all the Screens that have a status
        theShowCycles.addStatus();
        theShowCycles.addTop();
    	TopForm.getInstance(null).showLogsTopForm("Cycles");   	
    	
        return theShowCycles;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		try {
			theShowCycles = ShowCycles.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theShowCycles, previousScreen);
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
		   document.addTitle("Cycles");
		          PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("cyclesTable.pdf"));
		          document.open();
		          PdfPTable tab=new PdfPTable(11);
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
		          document.add(addTitle("Cycles"));
		          tab.addCell("Num");
		          tab.addCell("Time");
		          tab.addCell("User");
			   	  tab.addCell("numPassed");
					   tab.addCell("numFailed");
					   tab.addCell("Fill Time");
					   tab.addCell("max Drop");
					   tab.addCell("Program");
					   tab.addCell("Mean Pres.");
					   tab.addCell("Pres. Range");
					   tab.addCell("Std. Dev.");
		   for(int i=0;i<count;i++){
		   Object obj1 = GetData(table, i, 0);
		   Object obj2 = GetData(table, i, 1);
		   Object obj3 = GetData(table, i, 2);
		   Object obj4 = GetData(table, i, 3);
		   Object obj5 = GetData(table, i, 4);
		   Object obj6 = GetData(table, i, 5);
		   Object obj7 = GetData(table, i, 6);
		   Object obj8 = GetData(table, i, 7);
		   Object obj9 = GetData(table, i, 8);
		   Object obj10 = GetData(table, i, 9);
		   Object obj11 = GetData(table, i, 10);
		   String value1=obj1.toString();
		   String value2=obj2.toString();
		   String value3=obj3.toString();
		   String value4=obj4.toString();
		   String value5=obj5.toString();
		   String value6=obj6.toString();
		   String value7=obj7.toString();
		   String value8=obj8.toString();
		   String value9 = obj9.toString();
		   String value10 = obj10.toString();
		   String value11 = obj11.toString();
		   
		   tab.addCell(value1);
		   tab.addCell(value2);
		   tab.addCell(value3);
		   tab.addCell(value4);
		   tab.addCell(value5);
		   tab.addCell(value6);
		   tab.addCell(value7);
		   tab.addCell(value8);
		   tab.addCell(value9);
		   tab.addCell(value10);
		   tab.addCell(value11);
		   
		   }
		   document.add(tab);
		   document.close();
		       }
		       catch(Exception e){e.printStackTrace();}
		  
		   // processBuilder.command("bash", "-c", "cd ~/");
			try {
				String[] b = new String[] {"bash", "-c", "./copypdfs.sh"};
		        Process p = Runtime.getRuntime().exec(b);
				p.waitFor();
			} catch (IOException |InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		  
		  
		 
		  
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
