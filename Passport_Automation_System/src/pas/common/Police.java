package pas.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

public class Police {

	private String Name;
	private String USName;
	private String Mail;
	private String Password;
	private String CPassword;
	private int PID;

	Test_Connection ob;
	Connection conn;

	// register
	public Police(String name, String uname, String eMail, String passcode, String cpasscode) {

		this.Name = name;
		this.USName = uname;
		this.Mail = eMail;
		this.Password = passcode;
		this.CPassword = cpasscode;

		ob = new Test_Connection();
		conn = ob.getConnection();
	}

	// login
	public Police(int policeID, String userName, String password) {

		this.PID = policeID;
		this.USName = userName;
		this.Password = password;

		ob = new Test_Connection();
		conn = ob.getConnection();

	}

	// Verification
	public Police(int apli_ID, int case_ID, String verification_Date, String background_Check, String comment) {

		new JDateChooser();

		ob = new Test_Connection();
		conn = ob.getConnection();

	}

	// Method to police registration
	public void Police_Registration(String name, String uName, String eMail, String password, String cPassword) {

		try {
			String sql = "INSERT INTO  police(full_name,username,email,password,cpassword) values (?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, Name);
			statement.setString(2, USName);
			statement.setString(3, Mail);
			statement.setString(4, Password);
			statement.setString(5, CPassword);

			statement.executeUpdate();
			System.out.println();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e);
		}

	}

	// Method to police login
	public void loginPolice(int policeID, String userName, String password) {
		try {
			String sql = "SELECT * FROM police WHERE police_id=? AND username=? AND password=?";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, PID);
			statement.setString(2, USName);
			statement.setString(3, Password);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				resultSet.getInt("police_id");
				resultSet.getString("username");
				resultSet.getString("password");

			}

		} catch (SQLException e) {
			System.out.println("Error during login: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Method to send a verification report
	public void Send(int apli_ID, int cas_ID, String verification_Date, String background_Check, String aditional_con) {
		try {
			java.sql.Date sqlDate = null;
			if (verification_Date != null && !verification_Date.isEmpty()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = LocalDate.parse(verification_Date, formatter);
				sqlDate = java.sql.Date.valueOf(localDate);
			}

			String sql = "INSERT INTO verification (application_id, case_id, VerificationDate, backgroundCheck, AdditionalComments) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, apli_ID);
			statement.setInt(2, cas_ID);
			statement.setDate(3, sqlDate);
			statement.setString(4, background_Check);
			statement.setString(5, aditional_con);

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(null, "Verification report sent successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			System.out.println("Error inserting data into database: " + e.getMessage());
			e.printStackTrace();
		} catch (DateTimeParseException e) {
			System.out.println("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Method to update a verification report
	public void updateVerification(int apli_ID, int cas_ID, String verification_Date, String background_Check,
			String aditional_con) {
		try {

			String selectSql = "SELECT * FROM verification WHERE application_id = ? AND case_id = ?";
			PreparedStatement selectStatement = conn.prepareStatement(selectSql);
			selectStatement.setInt(1, apli_ID);
			selectStatement.setInt(2, cas_ID);
			ResultSet resultSet = selectStatement.executeQuery();

			if (resultSet.next()) {
				String updateSql = "UPDATE verification SET VerificationDate = ?, backgroundCheck = ?, AdditionalComments = ? WHERE application_id = ? AND case_id = ?";
				PreparedStatement updateStatement = conn.prepareStatement(updateSql);
				updateStatement.setString(1, verification_Date);
				updateStatement.setString(2, background_Check);
				updateStatement.setString(3, aditional_con);
				updateStatement.setInt(4, apli_ID);
				updateStatement.setInt(5, cas_ID);

				int rowsUpdated = updateStatement.executeUpdate();

				if (rowsUpdated > 0) {
					JOptionPane.showMessageDialog(null, "Verification report updated successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "No matching records found", "Update Failed",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error updating data in database: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
