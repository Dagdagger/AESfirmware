package stream18.aescp.model;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

		public static Connection getConnection(){
			
			Connection conn = null;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/AESDB","root","");
				
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return conn;
		}
}