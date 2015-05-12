import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class DBConnect {
	Connection conn=null;
	public static Connection dbConn()
	{
	try
	{
	Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
	Connection conn=DriverManager.getConnection("jdbc:db2://localhost:50000/ISP","siddiqui", "hbo");
	return conn;
	}
	catch(Exception e)
	{
	JOptionPane.showMessageDialog(null, e);
	return null;
	}
	}
}
