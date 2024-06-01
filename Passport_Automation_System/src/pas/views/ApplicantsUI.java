package pas.views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import pas.common.User;

public class ApplicantsUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField loginTxt;
	private JPasswordField passcodeLBL;
	private JPanel RegisterPanel;
	private JTextField nameTF;
	private JPasswordField passTF;
	private JTextField mailTF;
	private JPasswordField cpassTF;
	private JPanel Profile_View;
	private JLabel lblNewLabel_8;
	private JPanel Status;
	private JLabel dateTimeLabel;
	private JPanel Help;
	private JLabel lblNewLabel_17;
	private JTextField textField;
	private JPanel Appoinment;
	private JTextField textField_1;

	Connection conn = null;

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
					ApplicantsUI frame = new ApplicantsUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}

	public Object[] checkLogin(String email, String password) {
		String query = "SELECT name FROM User_Registration WHERE email = ? AND passcode = ?";

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PAS", "root", "1234");
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setString(1, email);
			statement.setString(2, password);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					return new Object[] { true, resultSet.getString("name") };
				} else {

					return new Object[] { false, null };
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return new Object[] { false, null };
		}
	}

	/**
	 * Create the frame.
	 */
	public ApplicantsUI() {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PAS", "root", "1234");
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ApplicantsUI.class.getResource("/pas/resources/PAS LOGO.png")));
		setTitle("Online Passport Application System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 433);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(5, 5, 640, 384);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel MainPanel = new JPanel();
		MainPanel.setBackground(Color.WHITE);
		layeredPane.add(MainPanel, "name_438777179400400");
		MainPanel.setLayout(null);

		JLabel lblNewLabel_13_1 = new JLabel("Password");
		lblNewLabel_13_1.setForeground(new Color(128, 0, 128));
		lblNewLabel_13_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_13_1.setBounds(60, 205, 130, 14);
		MainPanel.add(lblNewLabel_13_1);

		JLabel lblNewLabel_13 = new JLabel("Enter your E-mail");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_13.setForeground(new Color(128, 0, 128));
		lblNewLabel_13.setBounds(60, 153, 130, 14);
		MainPanel.add(lblNewLabel_13);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(112, 41, 78, 64);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(ApplicantsUI.class.getResource("/pas/resources/profile.png")));
		MainPanel.add(lblNewLabel_4);

		JLabel passwordLabel = new JLabel("Password", SwingConstants.LEFT);
		passwordLabel.setBounds(75, 219, 90, 25);
		passwordLabel.setForeground(Color.GRAY);
		MainPanel.add(passwordLabel);

		Label label_1_1 = new Label("\r\n");
		label_1_1.setBounds(60, 169, 5, 25);
		label_1_1.setForeground(Color.RED);
		label_1_1.setBackground(new Color(128, 0, 128));
		label_1_1.setAlignment(Label.CENTER);
		MainPanel.add(label_1_1);

		// Create label for email field
		JLabel emailLBL = new JLabel("Enter your email address", SwingConstants.LEFT);
		emailLBL.setBounds(75, 170, 172, 23);
		emailLBL.setForeground(Color.GRAY);
		MainPanel.add(emailLBL);

		JLabel lblNewLabel_2 = new JLabel("\r\n");
		lblNewLabel_2.setBounds(309, 31, 321, 201);
		lblNewLabel_2.setIcon(new ImageIcon(ApplicantsUI.class.getResource("/pas/resources/3.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.YELLOW);
		MainPanel.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("First-time users must register with the System.");
		lblNewLabel.setBounds(323, 243, 292, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		MainPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(" Please Register");
		lblNewLabel_1.setBounds(323, 256, 292, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.BLACK);
		MainPanel.add(lblNewLabel_1);

		Status = new JPanel();
		Status.setBackground(new Color(255, 255, 255));
		layeredPane.add(Status, "name_438777242823000");
		Status.setLayout(null);

		JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Profile_View);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnNewButton_2_1.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		btnNewButton_2_1.setBounds(600, 343, 30, 30);
		Status.add(btnNewButton_2_1);

		JLabel statusLabel = new JLabel("Status : ");
		statusLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		statusLabel.setForeground(new Color(0, 0, 0));
		statusLabel.setBounds(40, 147, 300, 30);
		Status.add(statusLabel);

		JButton refreshButton = new JButton("Refresh Status");
		refreshButton.setForeground(new Color(128, 0, 128));
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String applicationIDString = textField.getText();

				if (applicationIDString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter an Application ID.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				int applicationID = Integer.parseInt(applicationIDString);

				User ob = new User(applicationID, statusLabel);
				ob.displayStatus(applicationID, statusLabel);
			}

		});
		refreshButton.setBounds(53, 328, 150, 30);
		Status.add(refreshButton);

		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setBackground(new Color(250, 235, 215));
		lblNewLabel_14.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\10181763.png"));
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setBounds(264, 0, 376, 384);
		Status.add(lblNewLabel_14);

		lblNewLabel_17 = new JLabel("Application ID :");
		lblNewLabel_17.setBounds(40, 93, 100, 14);
		Status.add(lblNewLabel_17);

		JLabel statslbl = new JLabel("Status Information");
		statslbl.setForeground(new Color(128, 0, 128));
		statslbl.setFont(new Font("Arial", Font.BOLD, 20));
		statslbl.setBounds(25, 40, 229, 14);
		Status.add(statslbl);

		textField = new JTextField();
		textField.setBounds(40, 111, 180, 25);
		Status.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("REGISTER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(RegisterPanel);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnNewButton.setBounds(427, 289, 89, 23);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton.setForeground(new Color(128, 0, 128));
		btnNewButton.setSelectedIcon(new ImageIcon(ApplicantsUI.class.getResource("/pas/resources/PasImg.png")));
		MainPanel.add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("Login Account");
		lblNewLabel_3.setBounds(60, 108, 188, 23);
		lblNewLabel_3.setForeground(new Color(128, 0, 128));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		MainPanel.add(lblNewLabel_3);

		JButton BackBTN = new JButton("BACK");
		BackBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StartUI ob = new StartUI();
				ob.setVisible(true);
				dispose();

			}
		});
		BackBTN.setBounds(156, 312, 89, 23);
		BackBTN.setFont(new Font("Arial", Font.BOLD, 11));
		BackBTN.setForeground(new Color(128, 0, 128));
		BackBTN.setContentAreaFilled(false);
		BackBTN.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		BackBTN.setBackground(new Color(128, 0, 128));
		MainPanel.add(BackBTN);

		JButton logingBTN = new JButton("LOGIN");
		logingBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = loginTxt.getText();
				char[] passwordChars = passcodeLBL.getPassword();
				String password = new String(passwordChars);

				if (email.isEmpty() || password.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Email and password are required", "Login Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Object[] loginResult = checkLogin(email, password);

					if ((boolean) loginResult[0]) {

						String userName = (String) loginResult[1];

						lblNewLabel_8.setText("Welcome, " + userName + "!");

						updateDateTime();

						layeredPane.removeAll();
						layeredPane.add(Profile_View);
						layeredPane.repaint();
						layeredPane.revalidate();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid email or password");
					}
				}

				Arrays.fill(passwordChars, ' ');
				passcodeLBL.setText("");
			}
		});

		logingBTN.setBounds(57, 312, 89, 23);
		logingBTN.setFont(new Font("Arial", Font.BOLD, 11));
		logingBTN.setContentAreaFilled(false);
		logingBTN.setForeground(new Color(128, 0, 128));
		logingBTN.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		logingBTN.setBackground(new Color(128, 0, 128));
		MainPanel.add(logingBTN);

		Label label_1 = new Label("\r\n");
		label_1.setBounds(62, 219, 5, 25);
		label_1.setForeground(Color.RED);
		label_1.setBackground(new Color(128, 0, 128));
		label_1.setAlignment(Label.CENTER);
		MainPanel.add(label_1);

		passcodeLBL = new JPasswordField();
		passcodeLBL.setBounds(68, 219, 179, 25);
		passcodeLBL.setToolTipText("\r\n");
		passcodeLBL.setForeground(Color.BLACK);
		passcodeLBL.setActionCommand("");
		passcodeLBL.setEchoChar('●');
		passcodeLBL.setBackground(new Color(255, 255, 255));
		passcodeLBL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		MainPanel.add(passcodeLBL);

		passcodeLBL.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				passwordLabel.setVisible(false);
			}

			public void focusLost(FocusEvent e) {
				passwordLabel.setVisible(passcodeLBL.getPassword().length == 0);
			}
		});

		loginTxt = new JTextField();
		loginTxt.setBounds(66, 169, 179, 25);
		loginTxt.setToolTipText("");
		loginTxt.setForeground(Color.BLACK);
		loginTxt.setName("");
		loginTxt.setBackground(new Color(255, 255, 255));
		loginTxt.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		MainPanel.add(loginTxt);
		loginTxt.setColumns(10);

		loginTxt.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				emailLBL.setVisible(false);
			}

			public void focusLost(FocusEvent e) {
				if (loginTxt.getText().isEmpty()) {
					emailLBL.setVisible(true);
				}
			}
		});

		JCheckBox passChckBx = new JCheckBox("Show");
		passChckBx.setBounds(187, 253, 58, 23);
		passChckBx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (passChckBx.isSelected()) {
					passcodeLBL.setEchoChar((char) 0);

				} else {
					passcodeLBL.setEchoChar(('●'));

				}

			}
		});

		passChckBx.setHorizontalTextPosition(SwingConstants.LEFT);
		passChckBx.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		passChckBx.setHorizontalAlignment(SwingConstants.CENTER);
		passChckBx.setBackground(new Color(255, 240, 245));
		MainPanel.add(passChckBx);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(0, 0, 308, 375);
		lblNewLabel_10.setOpaque(true);
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setFont(new Font("Agency FB", Font.BOLD, 39));
		lblNewLabel_10.setBackground(new Color(255, 240, 245));
		lblNewLabel_10.setAutoscrolls(true);
		lblNewLabel_10.setAlignmentY(0.0f);
		MainPanel.add(lblNewLabel_10);

		RegisterPanel = new JPanel();
		RegisterPanel.setBackground(Color.WHITE);
		RegisterPanel.setLayout(null);
		layeredPane.add(RegisterPanel, "name_438777289863600");

		JLabel lblNewLabel_5 = new JLabel("Please provide the following\r\n");
		lblNewLabel_5.setForeground(new Color(128, 0, 128));
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_5.setBounds(32, 27, 320, 32);
		RegisterPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Information to register");
		lblNewLabel_6.setForeground(new Color(128, 0, 128));
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_6.setBounds(32, 51, 320, 32);
		RegisterPanel.add(lblNewLabel_6);

		JLabel NameLbl = new JLabel("Name");
		NameLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		NameLbl.setBounds(42, 96, 277, 15);
		RegisterPanel.add(NameLbl);

		nameTF = new JTextField();
		nameTF.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(128, 0, 128), new Color(199, 21, 133), null, null));
		nameTF.setBounds(42, 113, 248, 25);
		RegisterPanel.add(nameTF);
		nameTF.setColumns(10);

		JLabel PasswordLbl = new JLabel("Password");
		PasswordLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		PasswordLbl.setBounds(42, 208, 255, 15);
		RegisterPanel.add(PasswordLbl);

		passTF = new JPasswordField();
		passTF.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(128, 0, 128), new Color(199, 21, 133), null, null));
		passTF.setBounds(42, 225, 248, 25);
		RegisterPanel.add(passTF);
		passTF.setColumns(10);

		JLabel mailLbl = new JLabel("E-mail");
		mailLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mailLbl.setBounds(42, 152, 135, 15);
		RegisterPanel.add(mailLbl);

		mailTF = new JTextField();
		mailTF.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(128, 0, 128), new Color(199, 21, 133), null, null));
		mailTF.setBounds(42, 168, 248, 25);
		RegisterPanel.add(mailTF);
		mailTF.setColumns(10);

		JLabel ConfirmPasswordLbl = new JLabel("Confirm Password");
		ConfirmPasswordLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ConfirmPasswordLbl.setBounds(42, 270, 135, 15);
		RegisterPanel.add(ConfirmPasswordLbl);

		cpassTF = new JPasswordField();
		cpassTF.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(128, 0, 128), new Color(199, 21, 133), null, null));
		cpassTF.setBounds(42, 285, 248, 25);
		RegisterPanel.add(cpassTF);
		cpassTF.setColumns(10);

		JButton ClearBtn = new JButton("Clear");
		ClearBtn.setForeground(new Color(128, 0, 128));
		ClearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				nameTF.setText(null);
				mailTF.setText(null);
				passTF.setText(null);
				cpassTF.setText(null);

			}
		});
		ClearBtn.setBounds(69, 331, 89, 23);
		RegisterPanel.add(ClearBtn);

		JButton SubmitBtn = new JButton("Submit");
		SubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTF.getText();
				String eMail = mailTF.getText();
				@SuppressWarnings("deprecation")
				String passcode = passTF.getText();
				@SuppressWarnings("deprecation")
				String cpasscode = cpassTF.getText();

				if (name.isEmpty() || eMail.isEmpty() || passcode.isEmpty() || cpasscode.isEmpty()) {

					JOptionPane.showMessageDialog(null, "All fields are required", "Registration Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (!passcode.equals(cpasscode)) {

					JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match",
							"Registration Error", JOptionPane.ERROR_MESSAGE);
				} else {

					User ar = new User(name, eMail, passcode, cpasscode);
					ar.User_Registration(name, eMail, passcode, cpasscode);

					JOptionPane.showMessageDialog(null, "Successfully registered!", "Registration Success",
							JOptionPane.INFORMATION_MESSAGE);

					layeredPane.removeAll();
					layeredPane.add(MainPanel);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
			}
		});

		SubmitBtn.setForeground(new Color(128, 0, 128));
		SubmitBtn.setBounds(168, 331, 89, 23);
		RegisterPanel.add(SubmitBtn);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				layeredPane.removeAll();
				layeredPane.add(MainPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		btnNewButton_2.setBounds(600, 343, 30, 30);
		RegisterPanel.add(btnNewButton_2);

		JLabel lblNewLabel_7 = new JLabel("\r\n");
		lblNewLabel_7.setIcon(new ImageIcon(ApplicantsUI.class.getResource("/pas/resources/4.png")));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(331, 0, 301, 384);
		RegisterPanel.add(lblNewLabel_7);

		Label label = new Label("\r\n");
		label.setBounds(62, 147, 5, 25);
		label.setBackground(new Color(128, 0, 128));
		label.setForeground(new Color(255, 0, 0));

		Profile_View = new JPanel();
		Profile_View.setBackground(Color.WHITE);
		Profile_View.setLayout(null);
		layeredPane.add(Profile_View, "name_438777341893700");

		dateTimeLabel = new JLabel();
		dateTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateTimeLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 10));
		dateTimeLabel.setBounds(31, 248, 250, 30);
		Profile_View.add(dateTimeLabel);

		JButton btnNewButton_3 = new JButton("Application Status");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Status);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnNewButton_3.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_3.setForeground(new Color(128, 0, 128));
		btnNewButton_3.setBounds(10, 328, 151, 38);
		Profile_View.add(btnNewButton_3);

		JButton helpBTN = new JButton("Help");
		helpBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Help);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		helpBTN.setFont(new Font("Arial", Font.PLAIN, 12));
		helpBTN.setForeground(new Color(128, 0, 128));
		helpBTN.setBounds(163, 328, 151, 38);
		Profile_View.add(helpBTN);

		JButton btnNewButton_7 = new JButton("Logout ");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
						"Logout Confirmation", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {

					layeredPane.removeAll();
					layeredPane.add(MainPanel);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
			}
		});
		btnNewButton_7.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_7.setForeground(new Color(128, 0, 128));
		btnNewButton_7.setBounds(541, 343, 89, 23);
		Profile_View.add(btnNewButton_7);

		lblNewLabel_8 = new JLabel("Profile details");
		lblNewLabel_8.setOpaque(true);
		lblNewLabel_8.setBackground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Trebuchet MS", Font.BOLD, 21));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(0, 23, 350, 32);
		Profile_View.add(lblNewLabel_8);

		JButton btnNewButton_3_1 = new JButton("Apply Passport");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application ob = new Application();
				ob.setVisible(true);
				dispose();

			}
		});
		btnNewButton_3_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_3_1.setForeground(new Color(128, 0, 128));
		btnNewButton_3_1.setBounds(10, 289, 151, 38);
		Profile_View.add(btnNewButton_3_1);

		JButton btnNewButton_4_1 = new JButton("Change Appoinment");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Appoinment);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_4_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_4_1.setForeground(new Color(128, 0, 128));
		btnNewButton_4_1.setBounds(163, 289, 151, 38);
		Profile_View.add(btnNewButton_4_1);

		JLabel lblNewLabel_11 = new JLabel("\r\n");
		lblNewLabel_11.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\21.png"));
		lblNewLabel_11.setBackground(new Color(255, 255, 255));
		lblNewLabel_11.setBounds(298, 18, 342, 348);
		Profile_View.add(lblNewLabel_11);

		JLabel lblNewLabel_9 = new JLabel("\r\n");
		lblNewLabel_9.setBounds(433, 76, 97, 129);
		Profile_View.add(lblNewLabel_9);

		JLabel lblNewLabel_12 = new JLabel("\r\n");
		lblNewLabel_12.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\12.png"));
		lblNewLabel_12.setBounds(88, 76, 151, 186);
		Profile_View.add(lblNewLabel_12);

		Help = new JPanel();
		Help.setBackground(new Color(255, 255, 255));
		layeredPane.add(Help, "name_438777402708800");
		Help.setLayout(null);

		JLabel lblNewLabel_15 = new JLabel("Need Help, Contact Us");
		lblNewLabel_15.setForeground(new Color(139, 0, 139));
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_15.setBounds(215, 0, 210, 52);
		Help.add(lblNewLabel_15);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Profile_View);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnNewButton_4.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		btnNewButton_4.setBounds(600, 343, 30, 30);
		Help.add(btnNewButton_4);

		JLabel imgLBL = new JLabel("\r\n");
		imgLBL.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\4220713.png"));
		imgLBL.setBackground(new Color(255, 255, 255));
		imgLBL.setHorizontalAlignment(SwingConstants.CENTER);
		imgLBL.setBounds(280, 11, 350, 362);
		Help.add(imgLBL);

		JLabel lblNewLabel_16 = new JLabel("Telephone : 076 343 6936");
		lblNewLabel_16.setFont(new Font("Tw Cen MT", Font.BOLD, 24));
		lblNewLabel_16.setBounds(32, 300, 371, 30);
		Help.add(lblNewLabel_16);

		JLabel lblNewLabel_16_1 = new JLabel("E-Mail : pas@gmail.com");
		lblNewLabel_16_1.setFont(new Font("Tw Cen MT", Font.BOLD, 24));
		lblNewLabel_16_1.setBounds(32, 329, 371, 30);
		Help.add(lblNewLabel_16_1);

		JLabel lblNewLabel_22 = new JLabel("");
		lblNewLabel_22.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\9233778_4111242.png"));
		lblNewLabel_22.setBounds(10, 47, 333, 283);
		Help.add(lblNewLabel_22);

		Appoinment = new JPanel();
		Appoinment.setBackground(new Color(255, 255, 255));
		layeredPane.add(Appoinment, "name_442496574043900");
		Appoinment.setLayout(null);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Profile_View);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		JLabel lblNewLabel_3_1_1 = new JLabel("Check Apoinment Details");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_1_1.setBounds(25, 113, 162, 14);
		Appoinment.add(lblNewLabel_3_1_1);
		btnNewButton_1.setBounds(600, 342, 30, 30);
		Appoinment.add(btnNewButton_1);

		JLabel lblNewLabel_2_1 = new JLabel("Appoinment Details");
		lblNewLabel_2_1.setForeground(new Color(128, 0, 128));
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1.setBackground(Color.WHITE);
		lblNewLabel_2_1.setBounds(25, 11, 248, 38);
		Appoinment.add(lblNewLabel_2_1);

		JLabel dateLbl = new JLabel("Given Date : ");
		dateLbl.setBounds(35, 138, 262, 14);
		Appoinment.add(dateLbl);

		JLabel lblEndDate = new JLabel("Given Time :");
		lblEndDate.setBounds(35, 163, 262, 14);
		Appoinment.add(lblEndDate);

		JLabel lblNewLabel_1_1 = new JLabel("Massage :");
		lblNewLabel_1_1.setBounds(35, 188, 274, 20);
		Appoinment.add(lblNewLabel_1_1);

		JLabel lblNewLabel_3_1 = new JLabel("Request for Rescheduling");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_1.setBounds(25, 254, 162, 14);
		Appoinment.add(lblNewLabel_3_1);

		JLabel lblSelectDate = new JLabel("Select Date:");
		lblSelectDate.setBounds(35, 282, 80, 14);
		Appoinment.add(lblSelectDate);

		JLabel lblTime = new JLabel("Select Time:");
		lblTime.setBounds(35, 307, 80, 14);
		Appoinment.add(lblTime);

		// array for time
		String[] selecttime = { "--Select Time Slot--", "09.00 AM - 10.00 AM", "10.00 AM - 11.00 AM",
				"11.00 AM - 12.00 PM", "01.00 PM - 02.00 PM", "02.00 PM - 03.00 PM", "03.00 PM - 04.00 PM", };

		JComboBox<?> comboBox = new JComboBox<Object>(selecttime);
		comboBox.setBounds(96, 303, 150, 22);
		Appoinment.add(comboBox);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(96, 279, 150, 20);
		Appoinment.add(dateChooser);

		JButton scheduleButton = new JButton("Request New Appointment");
		scheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date selectedDate = dateChooser.getDate();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate = sdf.format(selectedDate);

				String selectedTime = (String) comboBox.getSelectedItem();

				String id = textField_1.getText();

				try {
					String sql = "INSERT INTO reschedule (application_id, date, time) VALUES (?, ?, ?)";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, id);
					statement.setString(2, formattedDate);
					statement.setString(3, selectedTime);
					statement.executeUpdate();

					JOptionPane.showMessageDialog(null, "Request sent successfully!");
				} catch (SQLException ex) {
					ex.printStackTrace();

					JOptionPane.showMessageDialog(null, "Error: Failed to reschedule appointment.");
				}
			}
		});
		scheduleButton.setForeground(new Color(128, 0, 128));
		scheduleButton.setBounds(39, 340, 189, 23);
		Appoinment.add(scheduleButton);

		JLabel lblNewLabel_21 = new JLabel("");
		lblNewLabel_21.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\kanm.png"));
		lblNewLabel_21.setBounds(309, 4, 331, 379);
		Appoinment.add(lblNewLabel_21);

		JButton btnCheckAppointmentDetails = new JButton("Check Appointment Details");
		btnCheckAppointmentDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String appointmentIDString = textField_1.getText();

				if (appointmentIDString.isEmpty()) {

					System.out.println("Please enter an Appointment ID.");
					return;
				}

				int appointmentID = Integer.parseInt(appointmentIDString);

				User ob = new User(appointmentID, dateLbl, lblEndDate, lblNewLabel_1_1);
				ob.displayAppointmentDetails(appointmentID, dateLbl, lblEndDate, lblNewLabel_1_1);
			}
		});
		btnCheckAppointmentDetails.setForeground(new Color(128, 0, 128));
		btnCheckAppointmentDetails.setBounds(37, 219, 189, 23);
		Appoinment.add(btnCheckAppointmentDetails);

		textField_1 = new JTextField();
		textField_1.setBounds(34, 76, 150, 20);
		Appoinment.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_18 = new JLabel("Enter Application ID");
		lblNewLabel_18.setBounds(35, 60, 150, 14);
		Appoinment.add(lblNewLabel_18);

		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setOpaque(true);
		lblNewLabel_5_1.setBackground(Color.WHITE);
		lblNewLabel_5_1.setBounds(10, 11, 301, 372);
		Appoinment.add(lblNewLabel_5_1);

	}

	private void updateDateTime() {
		Thread dateTimeUpdater = new Thread(() -> {
			while (true) {
				LocalDateTime currentDateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String formattedDateTime = currentDateTime.format(formatter);

				dateTimeLabel.setText(" " + formattedDateTime);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		dateTimeUpdater.setDaemon(true);
		dateTimeUpdater.start();
	}
}
