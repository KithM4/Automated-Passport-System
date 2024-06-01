package pas.common;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PasDB_Connect {

	public static void main(String[] args) {
		try {
			DriverManager.getConnection("jdbc:mysql://localhost:3306/PAS", "root", "1234");
			System.out.println("PasDB Connection Success");
		} catch (SQLException e) {
			System.out.println("PasDB Connection Failed");
		}

	}

}
