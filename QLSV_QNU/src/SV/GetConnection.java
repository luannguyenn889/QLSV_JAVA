package SV;
import java.sql.*;
public class GetConnection {
	public static Connection getconnection() {
		   Connection con = null;
		   try {
				String url = "jdbc:sqlserver://localhost:1433;databaseName=student_management;trustServerCertificate=true";
				String username = "sa";
				String password = "123456";
				con = DriverManager.getConnection(url, username, password);
				
				System.out.println("ket noi duoc");
		   }catch(SQLException e) {
			   System.err.print(e.getMessage());
		   }
		   return con;
	   }
	   
	   public static void closeConnection(Connection con, PreparedStatement ps) {
			if (ps != null) {
				try {
					ps.close();
					System.out.println("PreparedStatement closed successfully.");
				} catch (SQLException e) {
					System.err.println("Error closing PreparedStatement: " + e.getMessage());
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					if (!con.isClosed()) {
						con.close();
						System.out.println("Connection closed successfully.");
					}
				} catch (SQLException e) {
					System.err.println("Error closing Connection: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
}
