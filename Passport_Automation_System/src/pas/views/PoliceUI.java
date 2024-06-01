package pas.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import pas.common.Police;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.Cursor;

public class PoliceUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField uNameTFL;
	private JTextField idTF;
	private JPasswordField passTF;
	private JTextField nameTF;
	private JTextField uNameTF;
	private JTextField mailTF;
	private JTextField passwordTF;
	private JTextField CpasswordTF;
	private JTextField P1;
	private JTextField P2;
	private JTextField P5;
	private JPanel pRegisterPanel;
	private JLayeredPane layeredPane;
	private JPanel pLoginPanel;
	private JPanel pMainPanel;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PoliceUI frame = new PoliceUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean checkLogin(int Police_id, String UserName, String password) {

		String query = "SELECT * FROM police WHERE police_id=? AND username=? AND password=?";

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PAS", "root", "1234");
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, Police_id);
			statement.setString(2, UserName);
			statement.setString(3, password);

			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Create the frame.
	 */
	public PoliceUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PoliceUI.class.getResource("/pas/resources/PAS LOGO.png")));
		setTitle("PAS - Police Department");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 434);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 326, 373);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		pLoginPanel = new JPanel();
		pLoginPanel.setBackground(new Color(255, 255, 255));
		layeredPane.add(pLoginPanel, "name_18723800510800");
		pLoginPanel.setLayout(null);

		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String policeID = idTF.getText();
				int PoliceID = Integer.parseInt(policeID);
				String UserName = uNameTFL.getText();
				char[] passwordChars = passTF.getPassword();
				String password = new String(passwordChars);

				if (policeID.isEmpty() || UserName.isEmpty() || password.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Both Username and Password are required", "Login Error",
							JOptionPane.ERROR_MESSAGE);
				} else {

					if (checkLogin(PoliceID, UserName, password)) {

						JOptionPane.showMessageDialog(null, "Login Successful!!");

						layeredPane.removeAll();
						layeredPane.add(pMainPanel);
						layeredPane.repaint();
						layeredPane.revalidate();

					} else {

						JOptionPane.showMessageDialog(null, "Invalid UserName or Password", "Login Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});
		loginBtn.setBounds(64, 324, 89, 23);
		pLoginPanel.add(loginBtn);

		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StartUI ob = new StartUI();
				ob.setVisible(true);
				dispose();

			}
		});
		exitBtn.setBounds(172, 324, 89, 23);
		pLoginPanel.add(exitBtn);

		uNameTFL = new JTextField();
		uNameTFL.setColumns(10);
		uNameTFL.setBounds(37, 222, 250, 25);
		pLoginPanel.add(uNameTFL);

		idTF = new JTextField();
		idTF.setColumns(10);
		idTF.setBounds(37, 177, 250, 25);
		pLoginPanel.add(idTF);

		JLabel lblNewLabel_3 = new JLabel("Police ID");
		lblNewLabel_3.setBounds(39, 162, 55, 14);
		pLoginPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("User Name");
		lblNewLabel_3_1.setBounds(39, 207, 55, 14);
		pLoginPanel.add(lblNewLabel_3_1);

		passTF = new JPasswordField();
		passTF.setBounds(37, 265, 250, 25);
		pLoginPanel.add(passTF);

		JLabel lblNewLabel_3_1_1 = new JLabel("Password");
		lblNewLabel_3_1_1.setBounds(39, 251, 55, 14);
		pLoginPanel.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\hiiiiiiiii.png"));
		lblNewLabel_4.setBounds(27, 61, 264, 98);
		pLoginPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_1 = new JLabel("PAS - POLICE");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(34, 20, 257, 45);
		pLoginPanel.add(lblNewLabel_1);

		JCheckBox showPasswrd = new JCheckBox("Show Password");
		showPasswrd.setFont(new Font("Tahoma", Font.PLAIN, 10));
		showPasswrd.setHorizontalTextPosition(SwingConstants.LEFT);
		showPasswrd.setHorizontalAlignment(SwingConstants.RIGHT);
		showPasswrd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showPasswrd.setBackground(new Color(255, 255, 255));
		showPasswrd.setBounds(172, 290, 115, 14);
		pLoginPanel.add(showPasswrd);
		showPasswrd.addActionListener(e -> {
			if (showPasswrd.isSelected()) {
				passTF.setEchoChar((char) 0);
			} else {
				passTF.setEchoChar('*');
			}
		});

		pRegisterPanel = new JPanel();
		pRegisterPanel.setBackground(new Color(255, 255, 255));
		layeredPane.add(pRegisterPanel, "name_18725967195900");
		pRegisterPanel.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("PAS - POLICE");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(0, 0, 128));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_5.setBounds(34, 20, 257, 45);
		pRegisterPanel.add(lblNewLabel_5);

		nameTF = new JTextField();
		nameTF.setBounds(38, 98, 250, 20);
		pRegisterPanel.add(nameTF);
		nameTF.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Name");
		lblNewLabel_6.setBounds(38, 83, 46, 14);
		pRegisterPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("User Name");
		lblNewLabel_6_1.setBounds(38, 129, 69, 14);
		pRegisterPanel.add(lblNewLabel_6_1);

		uNameTF = new JTextField();
		uNameTF.setColumns(10);
		uNameTF.setBounds(38, 144, 250, 20);
		pRegisterPanel.add(uNameTF);

		JLabel lblNewLabel_6_2 = new JLabel("E-Mail");
		lblNewLabel_6_2.setBounds(38, 175, 46, 14);
		pRegisterPanel.add(lblNewLabel_6_2);

		mailTF = new JTextField();
		mailTF.setColumns(10);
		mailTF.setBounds(38, 190, 250, 20);
		pRegisterPanel.add(mailTF);

		JLabel lblNewLabel_6_3 = new JLabel("Password");
		lblNewLabel_6_3.setBounds(38, 221, 46, 14);
		pRegisterPanel.add(lblNewLabel_6_3);

		passwordTF = new JTextField();
		passwordTF.setColumns(10);
		passwordTF.setBounds(38, 234, 250, 20);
		pRegisterPanel.add(passwordTF);

		JLabel lblNewLabel_6_4 = new JLabel("Confirm Password");
		lblNewLabel_6_4.setBounds(38, 265, 116, 14);
		pRegisterPanel.add(lblNewLabel_6_4);

		CpasswordTF = new JTextField();
		CpasswordTF.setColumns(10);
		CpasswordTF.setBounds(38, 278, 250, 20);
		pRegisterPanel.add(CpasswordTF);

		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTF.getText();
				String uname = uNameTF.getText();
				String eMail = mailTF.getText();
				String passcode = passwordTF.getText();
				String cpasscode = CpasswordTF.getText();

				if (name.isEmpty() || uname.isEmpty() || eMail.isEmpty() || passcode.isEmpty() || cpasscode.isEmpty()) {

					JOptionPane.showMessageDialog(null, "All fields are required", "Registration Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (!passcode.equals(cpasscode)) {

					JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match",
							"Registration Error", JOptionPane.ERROR_MESSAGE);
				} else {

					Police ar = new Police(name, uname, eMail, passcode, cpasscode);
					ar.Police_Registration(name, uname, eMail, passcode, cpasscode);

					JOptionPane.showMessageDialog(null, "Successfully registered!", "Registration Success",
							JOptionPane.INFORMATION_MESSAGE);

					layeredPane.removeAll();
					layeredPane.add(pMainPanel);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
			}
		});

		registerBtn.setBounds(43, 325, 73, 23);
		pRegisterPanel.add(registerBtn);

		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameTF.setText("");
				uNameTF.setText("");
				mailTF.setText("");
				passwordTF.setText("");
				CpasswordTF.setText("");
			}
		});
		clearBtn.setBounds(126, 325, 73, 23);
		pRegisterPanel.add(clearBtn);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(pMainPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton.setBounds(209, 325, 73, 23);
		pRegisterPanel.add(btnNewButton);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\watermrk.png"));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(10, 53, 306, 289);
		pRegisterPanel.add(lblNewLabel_7);

		JPanel pVerifyPanel = new JPanel();
		pVerifyPanel.setBackground(new Color(255, 255, 255));
		layeredPane.add(pVerifyPanel, "name_18728427305000");
		pVerifyPanel.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("PAS - POLICE");
		lblNewLabel_8.setForeground(new Color(0, 0, 128));
		lblNewLabel_8.setBackground(new Color(255, 255, 255));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setEnabled(true);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_8.setBounds(34, 20, 257, 45);
		pVerifyPanel.add(lblNewLabel_8);

		P1 = new JTextField();
		P1.setBounds(38, 93, 250, 22);
		pVerifyPanel.add(P1);
		P1.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Application ID");
		lblNewLabel_9.setBounds(38, 76, 79, 14);
		pVerifyPanel.add(lblNewLabel_9);

		P2 = new JTextField();
		P2.setColumns(10);
		P2.setBounds(38, 143, 250, 22);
		pVerifyPanel.add(P2);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(38, 190, 250, 22);
		pVerifyPanel.add(dateChooser);

		JLabel lblNewLabel_9_1 = new JLabel("Case ID");
		lblNewLabel_9_1.setBounds(38, 126, 79, 14);
		pVerifyPanel.add(lblNewLabel_9_1);

		JLabel lblNewLabel_9_1_1 = new JLabel("Verification Date");
		lblNewLabel_9_1_1.setBounds(38, 176, 79, 14);
		pVerifyPanel.add(lblNewLabel_9_1_1);

		JLabel lblNewLabel_9_1_1_1 = new JLabel("Background Check");
		lblNewLabel_9_1_1_1.setBounds(38, 226, 122, 14);
		pVerifyPanel.add(lblNewLabel_9_1_1_1);

		// array for verification status
		String[] verificationStatusOptions = { "--Select Status--", "Pending", "Approved", "Rejected", "Under Review",
				"Completed", "On Hold", "Cancelled" };

		JComboBox<String> P4 = new JComboBox<>(verificationStatusOptions);
		P4.setBounds(38, 243, 250, 22);
		pVerifyPanel.add(P4);

		JButton btnNewButton_3 = new JButton("Update");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Aplication_ID = P1.getText();
				String Case_ID = P2.getText();
				String Background_Check = (String) P4.getSelectedItem();
				String Aditional_con = P5.getText();

				if (Aplication_ID.isEmpty() || Case_ID.isEmpty() || Background_Check.equals("--Select Status--")) {
					JOptionPane.showMessageDialog(null, "Please fill all required fields", "Missing Fields",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Date selectedDate = dateChooser.getDate();
				if (selectedDate == null) {
					JOptionPane.showMessageDialog(null, "Please select a verification date", "Date not selected",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String Verification_Date = sdf.format(selectedDate);

				int Apli_ID = Integer.parseInt(Aplication_ID);
				int Cas_ID = Integer.parseInt(Case_ID);
				Police ob = new Police(Apli_ID, Cas_ID, Verification_Date, Background_Check, Aditional_con);
				ob.updateVerification(Apli_ID, Cas_ID, Verification_Date, Background_Check, Aditional_con);
			}
		});
		btnNewButton_3.setBounds(85, 339, 75, 23);
		pVerifyPanel.add(btnNewButton_3);

		JButton btnNewButton_3_1 = new JButton("Clear");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				P1.setText("");
				P2.setText("");
				P5.setText("");
				P4.setSelectedIndex(0);

				dateChooser.setDate(null);
			}
		});
		btnNewButton_3_1.setBounds(165, 339, 75, 23);
		pVerifyPanel.add(btnNewButton_3_1);

		JButton btnNewButton_3_1_1 = new JButton("Send");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Aplication_ID = P1.getText();
				String Case_ID = P2.getText();
				String Background_Check = (String) P4.getSelectedItem();
				String Aditional_con = P5.getText();

				if (Aplication_ID.isEmpty() || Case_ID.isEmpty() || Background_Check.equals("--Select Status--")) {
					JOptionPane.showMessageDialog(null, "Please fill all required fields", "Missing Fields",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Date selectedDate = dateChooser.getDate();
				if (selectedDate == null) {
					JOptionPane.showMessageDialog(null, "Please select a verification date", "Date not selected",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String Verification_Date = sdf.format(selectedDate);

				int Apli_ID = Integer.parseInt(Aplication_ID);
				int Cas_ID = Integer.parseInt(Case_ID);
				Police ob = new Police(Apli_ID, Cas_ID, Verification_Date, Background_Check, Aditional_con);
				ob.Send(Apli_ID, Cas_ID, Verification_Date, Background_Check, Aditional_con);
			}
		});
		btnNewButton_3_1_1.setBounds(5, 339, 75, 23);
		pVerifyPanel.add(btnNewButton_3_1_1);

		JButton btnNewButton_3_1_1_1 = new JButton("Back");
		btnNewButton_3_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(pMainPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_3_1_1_1.setBounds(245, 339, 75, 23);
		pVerifyPanel.add(btnNewButton_3_1_1_1);

		JLabel lblNewLabel_9_1_2 = new JLabel("Aditional Comments");
		lblNewLabel_9_1_2.setBounds(38, 275, 122, 14);
		pVerifyPanel.add(lblNewLabel_9_1_2);

		P5 = new JTextField();
		P5.setColumns(10);
		P5.setBounds(38, 292, 250, 22);
		pVerifyPanel.add(P5);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\watermrk.png"));
		lblNewLabel_10.setBounds(43, 55, 239, 279);
		pVerifyPanel.add(lblNewLabel_10);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(38, 190, 250, 22);
		pVerifyPanel.add(dateChooser);

		pMainPanel = new JPanel();
		pMainPanel.setLayout(null);
		pMainPanel.setBackground(Color.WHITE);
		layeredPane.add(pMainPanel, "name_77020622299500");

		JButton btnRegisterNewAccount = new JButton("Register Account");
		btnRegisterNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(pRegisterPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnRegisterNewAccount.setForeground(new Color(0, 0, 205));
		btnRegisterNewAccount.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		btnRegisterNewAccount.setBounds(20, 325, 140, 37);
		pMainPanel.add(btnRegisterNewAccount);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
						"Logout Confirmation", JOptionPane.YES_NO_OPTION);

				if (choice == JOptionPane.YES_OPTION) {

					passTF.setText("");

					layeredPane.removeAll();
					layeredPane.add(pLoginPanel);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
			}
		});
		btnLogout.setForeground(new Color(0, 0, 205));
		btnLogout.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		btnLogout.setBounds(170, 325, 140, 37);
		pMainPanel.add(btnLogout);

		JLabel lblNewLabel_2 = new JLabel("PAS - POLICE");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(0, 0, 139));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_2.setBackground(new Color(248, 248, 255));
		lblNewLabel_2.setBounds(34, 20, 257, 45);
		pMainPanel.add(lblNewLabel_2);

		JButton btnUpdateReport = new JButton("Update Report");
		btnUpdateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(pVerifyPanel);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnUpdateReport.setForeground(new Color(0, 0, 205));
		btnUpdateReport.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		btnUpdateReport.setBounds(170, 277, 140, 37);
		pMainPanel.add(btnUpdateReport);

		JButton btnSendVerificationReport = new JButton("Send Verification ");
		btnSendVerificationReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				layeredPane.removeAll();
				layeredPane.add(pVerifyPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnSendVerificationReport.setForeground(new Color(0, 0, 205));
		btnSendVerificationReport.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		btnSendVerificationReport.setBounds(20, 277, 140, 37);
		pMainPanel.add(btnSendVerificationReport);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\7966.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(23, 30, 279, 246);
		pMainPanel.add(lblNewLabel);
	}
}
