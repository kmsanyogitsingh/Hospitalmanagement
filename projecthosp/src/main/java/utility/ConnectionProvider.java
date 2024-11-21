package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	static public Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/hospital";
		return DriverManager.getConnection(url, "root", "sani@npk23");
	}
}