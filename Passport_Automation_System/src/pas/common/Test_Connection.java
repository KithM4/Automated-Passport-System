package pas.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test_Connection {

	public Test_Connection() {
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PAS", "root", "1234");
			System.out.println("Test Connection Success");
		} catch (SQLException e) {
			System.out.println("Test Connection Failed: " + e.getMessage());
		}
		return conn;
	}

}
