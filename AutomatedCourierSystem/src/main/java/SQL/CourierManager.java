package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CourierManager
{
	public static Connection getConnection() throws SQLException,Exception
	{
		Connection conn = null;
		try
		{
			String dbDriver = "com.mysql.jdbc.Driver";
	        String dbURL = "jdbc:mysql://localhost:3306/";
	        // Database name to access
	        String dbName = "AutomatedCourier";
	        String dbUsername = "root";
	        String dbPassword = "Daniel25#";
	  
	        Class.forName(dbDriver);
	        conn = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
	        return conn;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
