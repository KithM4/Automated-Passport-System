package pas.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;

public class User {

	private String Name;
	private String eMail;
	private String Password;
	private String CPassword;
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/PAS";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "1234";
	private static Connection connection;
	
	Test_Connection ob;
	Connection conn;

	public boolean User_Login() {
		boolean isValid = false;

		try {

			connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			String query = "SELECT * FROM users WHERE email = ? AND passcode = ?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, eMail);
				statement.setString(2, Password);

				try (ResultSet resultSet = statement.executeQuery()) {

					isValid = resultSet.next();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}

		return isValid;
	}

	public User(String name, String eMail, String passcode, String cpasscode) {

		this.Name = name;
		this.eMail = eMail;
		this.Password = passcode;
		this.CPassword = cpasscode;

		ob = new Test_Connection();
		conn = ob.getConnection();
	}

	public User(String eMail, String passcode) {

		this.eMail = eMail;
		this.Password = passcode;

		ob = new Test_Connection();
		conn = ob.getConnection();
	}

	public User(int applicationID, JLabel statusLabel) {

		ob = new Test_Connection();
		conn = ob.getConnection();

	}

	public User(int appointmentID, JLabel lblNewLabel_20, JLabel lblEndDate, JLabel lblNewLabel_1_1) {
		// Initialize the database connection
		ob = new Test_Connection();
		conn = ob.getConnection();
	}

	public void User_Registration(String name, String eMail, String passcode, String cpasscode) {

		try {
			String sql = "INSERT INTO  User_Registration(name,email,passcode,cpasscode) values (?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, Name);
			statement.setString(2, eMail);
			statement.setString(3, Password);
			statement.setString(4, CPassword);

			statement.executeUpdate();
			System.out.println();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e);
		}

	}

	public void displayStatus(int applicationID, JLabel statusLabel) {
		try {
			String sql = "SELECT status FROM status_update WHERE application_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, applicationID);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String status = resultSet.getString("status");
				statusLabel.setText("Status: " + status);
			} else {
				statusLabel.setText("No status found for Application ID " + applicationID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getAppointmentDetails(int appointmentID) throws SQLException {
		String sql = "SELECT date, time, massage FROM schedule_appoinment WHERE application_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, appointmentID);
		return statement.executeQuery();
	}

	public void displayAppointmentDetails(int appointmentID, JLabel dateLabel, JLabel timeLabel, JLabel messageLabel) {
		try {
			ResultSet resultSet = getAppointmentDetails(appointmentID);
			if (resultSet.next()) {
				String date = resultSet.getString("date");
				String time = resultSet.getString("time");
				String message = resultSet.getString("massage");

				dateLabel.setText("Given Date: " + date);
				timeLabel.setText("Given Time: " + time);
				messageLabel.setText("Message: " + message);
			} else {

				dateLabel.setText("No appointment found for ID " + appointmentID);
				timeLabel.setText("");
				messageLabel.setText("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
