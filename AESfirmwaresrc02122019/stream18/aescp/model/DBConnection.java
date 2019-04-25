package stream18.aescp.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import stream18.aescp.controller.TestVars;
import stream18.aescp.view.screen.HomeScreen;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;


/*TODO: Make a method to accept the names of the Database plus fill out all
 * the necessary columns. Make one method for inserting and another
 * for exporting the information and filling out the information in a graph
 * mainly the suggested Logs report within the application. 
 * 
 * 
 * Make sure to Log the data from the TestPhaser.java class after the thread
 * has ended. 
 * 
 * 
 */




public class DBConnection {

		public static Connection getConnection(){
			
Connection con = null;	
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");  
				con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/aes?serverTimezone=UTC","root","aes123");  
				
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return con;
		}

		
		
		public static void insertAudiTrail(String action, String details) {
			
			 java.util.Date date = null;
		      java.sql.Timestamp timeStamp = null;
			
		    try
		    {
		      Connection conn =  getConnection();
		    
		      // create a sql date object so we can use it in our INSERT statement
		      Calendar calendar=Calendar.getInstance();
		      calendar.setTime(new Date());
		      java.sql.Date dt = new java.sql.Date(calendar.getTimeInMillis());
		      java.sql.Time sqlTime=new java.sql.Time(calendar.getTime().getTime());
		      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		      date = simpleDateFormat.parse(dt.toString()+" "+sqlTime.toString());
		      timeStamp = new java.sql.Timestamp(date.getTime());
		      // the mysql insert statement
		      String query = " insert into Auditrails(Action, Details, Start_Date)"
		        + " values (?, ?, ?)";

		      // create the mysql insert prepared statement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, action);
		      preparedStmt.setString (2, details);
		      preparedStmt.setTimestamp(3, timeStamp);  

		      preparedStmt.execute();
		      
		      conn.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		    }
	}
		
		
		public static void insertAlarm(String action, String details) {
			
			 java.util.Date date = null;
		      java.sql.Timestamp timeStamp = null;
			
		    try
		    {
		      Connection conn =  getConnection();
		    
		      // create a sql date object so we can use it in our INSERT statement
		      Calendar calendar=Calendar.getInstance();
		      calendar.setTime(new Date());
		      java.sql.Date dt = new java.sql.Date(calendar.getTimeInMillis());
		      java.sql.Time sqlTime=new java.sql.Time(calendar.getTime().getTime());
		      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		      date = simpleDateFormat.parse(dt.toString()+" "+sqlTime.toString());
		      timeStamp = new java.sql.Timestamp(date.getTime());

		      // the mysql insert statement
		      String query = " insert into alarms(Action, Details, Start_Date)"
		        + " values (?, ?, ?)";

		      // create the mysql insert prepared statement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, action);
		      preparedStmt.setString (2, details);
		      preparedStmt.setTimestamp   (3, timeStamp);

		      preparedStmt.execute();
		      
		      conn.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		    }
	}
		
		public static void insertLogs(String testMode, String programName, String result,  String averageSettle, String maxDrop, String fillTime,String decay, String User) {
			
		    try
		    {
		      Connection conn =  getConnection();
		    
		      // create a sql date object so we can use it in our INSERT statement
		      Calendar calendar = Calendar.getInstance();
		      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

		      // the mysql insert statement
		      String query = " insert into test_log(testmode, program, result, average_settle, max_drop, fill_time, decay, user, startDate)"
		        + " values (?, ?, ?, ?, ?, ?,?,?,?)";

		      // create the mysql insert prepared statement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString(1,testMode );
		      preparedStmt.setString (2, programName);
		      preparedStmt.setString  (3, result);
		      preparedStmt.setString (4, averageSettle);
		      preparedStmt.setString(5, maxDrop);
		      preparedStmt.setString(6, fillTime);
		      preparedStmt.setString(7, decay);
		      preparedStmt.setString(8, User);
		      
		      
		      
		      preparedStmt.setDate(9, startDate);
		      

		      preparedStmt.execute();
		      
		      conn.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		    }
		}
public static void insertUser(String Username, String Password, String role) {
			
		    try
		    {
		      Connection conn =  getConnection();
		    
		      // create a sql date object so we can use it in our INSERT statement
		      Calendar calendar = Calendar.getInstance();
		      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

		      // the mysql insert statement
		      String query = " insert into users(uname, psswd, role)"
		        + " values (?, ?, ?)";

		      // create the mysql insert prepared statement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, Username);
		      preparedStmt.setString (2, Password);
		      preparedStmt.setString  (3, role);

		      preparedStmt.execute();
		      
		      conn.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		    }
	}


public static void insertTest(String Username, String Password, String role) {
	
    try
    {
      Connection conn =  getConnection();
    
      // create a sql date object so we can use it in our INSERT statement
      Calendar calendar = Calendar.getInstance();
      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

      // the mysql insert statement
      String query = " insert into users(uname, psswd, role)"
        + " values (?, ?, ?)";

      // create the mysql insert prepared statement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setString (1, Username);
      preparedStmt.setString (2, Password);
      preparedStmt.setString (3, role);

      preparedStmt.execute();
      
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
}
public static void insertCycles(int numPassed, int numFailed, String minDrop, String fillTime, String User) {
	 java.util.Date date = null;
     java.sql.Timestamp timeStamp = null;
	
   try
   {
     Connection conn =  getConnection();
   
     // create a sql date object so we can use it in our INSERT statement
     Calendar calendar=Calendar.getInstance();
     calendar.setTime(new Date());
     java.sql.Date dt = new java.sql.Date(calendar.getTimeInMillis());
     java.sql.Time sqlTime=new java.sql.Time(calendar.getTime().getTime());
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     date = simpleDateFormat.parse(dt.toString()+" "+sqlTime.toString());
     timeStamp = new java.sql.Timestamp(date.getTime());

      // the mysql insert statement
      String query = " insert into cycles(numPassed, numFailed, minDrop, fillTime, User, startDate)"
        + " values (?, ?, ?, ?, ?, ?)";

      // create the mysql insert prepared statement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt(1, numPassed);
      preparedStmt.setInt (2, numFailed);
      preparedStmt.setString  (3, minDrop);
      preparedStmt.setString (4, fillTime);
      preparedStmt.setString(5, User);
      preparedStmt.setTimestamp(6, timeStamp);

      preparedStmt.execute();
      
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
}
    
    
    public static void deleteAll() {
    	
        try
        {
          Connection conn =  getConnection();
        
          // create a sql date object so we can use it in our INSERT statement

          // the mysql insert statement
          java.sql.Statement stmt=conn.createStatement();  
			stmt.executeUpdate("delete from Auditrails");

			 stmt=conn.createStatement();  
		     stmt.executeUpdate("delete from cycles");

			
			stmt=conn.createStatement();  
			stmt.executeUpdate("delete from alarms");

          // create the mysql insert prepared statement

          
          conn.close();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
    }
/*public static String[] getTests() {
	
    try
    {
      Connection conn =  getConnection();
    
      // create a sql date object so we can use it in our INSERT statement
      Calendar calendar = Calendar.getInstance();
      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

      java.sql.Statement stmt=conn.createStatement();  
      // the mysql insert statement
      ResultSet rs=stmt.executeQuery("select * from Production where psswd = '" + password + "' && uname ='" + Username+ "' && role = 'Operator 1'");
      while (rs.next()) {
    		// retrieve and print the values for the current row
    		int i = rs.getInt("a");
    		String s = rs.getString("b");
    		float f = rs.getFloat("c");
    		System.out.println("ROW = " + i + " " + s + " " + f);
    	}

    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }*/
}

