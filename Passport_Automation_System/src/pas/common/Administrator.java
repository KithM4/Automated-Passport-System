package pas.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator {
	private int applicationID;
	private String scheduleDate;
	private String scheduleTime;
	private String scheduleMassage;

	Test_Connection ob;
	Connection conn;

	public Administrator(String fullName, String userName, String email, String password) {

		ob = new Test_Connection();
		conn = ob.getConnection();
	}

	public Administrator(int adminID, String userName, String password) {

		ob = new Test_Connection();
		conn = ob.getConnection();
	}

	public Administrator(int appID, String date, String time, String massage) {

		this.applicationID = appID;
		this.scheduleDate = date;
		this.scheduleTime = time;
		this.scheduleMassage = massage;

		ob = new Test_Connection();
		conn = ob.getConnection();
	}

	public Administrator(int applicationID, String status) {

		this.applicationID = applicationID;
		ob = new Test_Connection();
		conn = ob.getConnection();
	}

	public Administrator(int verifyID, int caseID, File file1, File file2) {
		ob = new Test_Connection();
		conn = ob.getConnection();
	}

	public Administrator() {
		// TODO Auto-generated constructor stub
	}

	public void registerAdmin(String fullName, String userName, String email, String password) {
		try {
			String sql = "INSERT INTO administrator (full_name, username, email, password) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, fullName);
			statement.setString(2, userName);
			statement.setString(3, email);
			statement.setString(4, password);

			statement.executeUpdate();
			System.out.println("Registration successful");
		} catch (SQLException e) {
			System.out.println("Error during registration: " + e.getMessage());
		}
	}

	public void loginAdmin(int adminID, String userName, String password) {
		try {
			String sql = "SELECT * FROM administrator WHERE admin_id=? AND username=? AND password=?";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, adminID);
			statement.setString(2, userName);
			statement.setString(3, password);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				resultSet.getInt("admin_id");
				resultSet.getString("username");
				resultSet.getString("password");

			}

		} catch (SQLException e) {
			System.out.println("Error during login: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void schedule(int appID, String date, String time, String massage) {

		try {
			String sql = "INSERT INTO schedule_appoinment (application_id, date, time, massage) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, applicationID);
			statement.setString(2, scheduleDate);
			statement.setString(3, scheduleTime);
			statement.setString(4, scheduleMassage);

			statement.executeUpdate();
			System.out.println("Schedule successful");
		} catch (SQLException e) {
			System.out.println("Error during schedule: " + e.getMessage());
		}
	}

	public void send_massage(int applicationID, String method, String massage) {

		try {
			String sql = "INSERT INTO massage (application_id, how, massage) VALUES (?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, applicationID);
			statement.setString(2, method);
			statement.setString(3, massage);

			statement.executeUpdate();
			System.out.println("Massage update successful");
		} catch (SQLException e) {
			System.out.println("Error during send Massage: " + e.getMessage());
		}
	}

	public void set_status(int applicationID, String status) {

		try {
			String sql = "INSERT INTO status_update (application_id,status) VALUES (?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, applicationID);
			statement.setString(2, status);

			statement.executeUpdate();
			System.out.println("Status Set successful");
		} catch (SQLException e) {
			System.out.println("Error during set status: " + e.getMessage());
		}
	}

	public void update_status(int applicationID, String status) {

		try {

			String sql = "UPDATE status_update SET status = ? WHERE application_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, status);

			statement.setInt(2, applicationID);

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Data updated successfully!");
			} else {
				System.out.println("Data update Failed!");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e);
		}

	}

	public boolean send_Verify(int verifyID, int caseID, File file1) {
		try {
			

			if (file1 != null) {
				uploadFileToDatabase(verifyID, caseID, file1);
				System.out.println("File 1 uploaded successfully");
			}

			System.out.println("Data and files successfully sent for verification");
			return true;
		} catch (SQLException | IOException e) {
			System.out.println("Error during send data: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	private void uploadFileToDatabase(int verifyID, int caseID, File file1) throws SQLException, IOException {
		if (file1 == null || !file1.exists()) {
			System.out.println("File is null or does not exist");
			return;
		}

		String sql = "INSERT INTO for_police (application_id, case_id, file_name, file_type, file_content) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, verifyID);
			statement.setInt(2, caseID);
			statement.setString(3, file1.getName());
			statement.setString(4, "application/octet-stream"); 
			statement.setBinaryStream(5, new FileInputStream(file1), (int) file1.length());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("File uploaded successfully");
			} else {
				System.out.println("File upload failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void send_Verify(int verifyID, int caseID, String applicationFileName, String applicationFileType,
			byte[] applicationFileBytes, String filesFileName, String filesFileType, byte[] filesFileBytes) {

	}

}
