package pas.views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import pas.common.Administrator;

public class AdminUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField unameTF;
	private JTextField idTF;
	private JTextField FnameTF;
	private JTextField UnameTF;
	private JTextField mailTF;
	private JTextField passwordTF;
	private JTextField idTextF;
	private JTextField dateTF;
	private JTextField msgTF;
	private JTextField S1;
	private JTextField S2;
	private JTextField S3;
	private JTextField st1;
	private JTextField v1;
	private JTextField v2;
	private JTable table;
	private JTextField textField_6;
	private JPanel viewPanel;
	private JPanel view_report_pane;
	private JPanel send_police_panel;
	private JPanel schedule_panel;
	private JPanel sms_panel;
	private JPanel register_panel;
	private JPanel main_panel;
	private JPanel admin_login;
	private JPanel status_panel;
	private JPasswordField passTF;

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
					AdminUI frame = new AdminUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static final String DB_URL = "jdbc:mysql://localhost:3306/PAS";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	String[] time = { "--Select Time Slot--", "09.00 AM - 10.00 AM", "10.00 AM - 11.00 AM", "11.00 AM - 12.00 PM",
			"01.00 PM - 02.00 PM", "02.00 PM - 03.00 PM", "03.00 PM - 04.00 PM", };
	String[] status = { "--Select Status type--", "Pending Approval", "Under Review", "Approved", "Issued",
			"Dispatched", "Delivered", "Rejected", "Expired", "Lost or Stolen", "Under Investigation", "On Hold",
			"Cancelled", };

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
	public AdminUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminUI.class.getResource("/pas/resources/PAS LOGO.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(5, 5, 640, 384);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		admin_login = new JPanel();
		admin_login.setBackground(new Color(255, 255, 255));
		layeredPane.add(admin_login, "name_283508173792800");
		admin_login.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\admin1.png"));
		lblNewLabel.setBounds(300, 10, 340, 370);
		admin_login.add(lblNewLabel);

		unameTF = new JTextField();
		unameTF.setBounds(26, 212, 225, 25);
		admin_login.add(unameTF);
		unameTF.setColumns(10);

		idTF = new JTextField();
		idTF.setColumns(10);
		idTF.setBounds(26, 162, 225, 25);
		admin_login.add(idTF);

		JLabel lblNewLabel_1 = new JLabel("Admin ID");
		lblNewLabel_1.setBounds(26, 146, 100, 16);
		admin_login.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("User Name");
		lblNewLabel_1_1.setBounds(26, 197, 100, 16);
		admin_login.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Password");
		lblNewLabel_1_2.setBounds(26, 246, 100, 16);
		admin_login.add(lblNewLabel_1_2);

		JButton loginBTN = new JButton("Login ");
		loginBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String adminID = idTF.getText();
				String userName = unameTF.getText();
				char[] passwordChars = passTF.getPassword();
				String password = new String(passwordChars);

				if (adminID.isEmpty() || userName.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Incomplete Information",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				try {
					int AdminID = Integer.parseInt(adminID);

					Administrator ob = new Administrator(AdminID, userName, password);
					ob.loginAdmin(AdminID, userName, password);

					Arrays.fill(passwordChars, ' ');
					passTF.setText("");

					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid Admin ID.", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
				}
				layeredPane.removeAll();
				layeredPane.add(main_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
			
			
		});

		loginBTN.setForeground(Color.DARK_GRAY);
		loginBTN.setBounds(39, 323, 89, 23);
		admin_login.add(loginBTN);

		JButton exiBTN = new JButton("Exit");
		exiBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartUI ob = new StartUI();
				ob.setVisible(true);
				dispose();

			}
		});

		exiBTN.setForeground(Color.DARK_GRAY);
		exiBTN.setBounds(149, 323, 89, 23);
		admin_login.add(exiBTN);

		JLabel lblNewLabel_2 = new JLabel("Administrator Login");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Sitka Text", Font.PLAIN, 27));
		lblNewLabel_2.setBounds(26, 27, 344, 59);
		admin_login.add(lblNewLabel_2);

		passTF = new JPasswordField();
		passTF.setBounds(26, 261, 225, 25);
		admin_login.add(passTF);

		main_panel = new JPanel();
		layeredPane.add(main_panel, "name_283508186950600");
		main_panel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\admin1.png"));
		lblNewLabel_3.setBounds(300, 10, 340, 370);
		main_panel.add(lblNewLabel_3);

		JButton logoutBTN = new JButton("Logout");
		logoutBTN.setOpaque(false);
		logoutBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
						"Logout Confirmation", JOptionPane.YES_NO_OPTION);

				if (choice == JOptionPane.YES_OPTION) {
					layeredPane.removeAll();
					layeredPane.add(admin_login);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
			}
		});

		logoutBTN.setBackground(Color.YELLOW);
		logoutBTN.setForeground(Color.DARK_GRAY);
		logoutBTN.setBounds(10, 336, 294, 40);
		main_panel.add(logoutBTN);

		JButton ScheduleBTN = new JButton("Schedule Applicants for Manual Verification");
		ScheduleBTN.setOpaque(false);
		ScheduleBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(schedule_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		ScheduleBTN.setBackground(Color.YELLOW);
		ScheduleBTN.setForeground(Color.DARK_GRAY);
		ScheduleBTN.setBounds(10, 54, 294, 40);
		main_panel.add(ScheduleBTN);

		JButton StatusBtn = new JButton("Update Passport Status");
		StatusBtn.setOpaque(false);
		StatusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(status_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		StatusBtn.setBackground(Color.YELLOW);
		StatusBtn.setForeground(Color.DARK_GRAY);
		StatusBtn.setBounds(10, 148, 294, 40);
		main_panel.add(StatusBtn);

		JButton RegisterBTN = new JButton("Register ");
		RegisterBTN.setOpaque(false);
		RegisterBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(register_panel);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		RegisterBTN.setBackground(Color.YELLOW);
		RegisterBTN.setForeground(Color.DARK_GRAY);
		RegisterBTN.setBounds(10, 289, 294, 40);
		main_panel.add(RegisterBTN);

		JButton msgBTN = new JButton("Send SMS and Email Updates ");
		msgBTN.setOpaque(false);
		msgBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(sms_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		msgBTN.setBackground(Color.YELLOW);
		msgBTN.setForeground(Color.DARK_GRAY);
		msgBTN.setBounds(10, 101, 294, 40);
		main_panel.add(msgBTN);

		JButton verifyBTN = new JButton("Send Applications to Police Verfication");
		verifyBTN.setOpaque(false);
		verifyBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(send_police_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		verifyBTN.setBackground(Color.YELLOW);
		verifyBTN.setForeground(Color.DARK_GRAY);
		verifyBTN.setBounds(10, 195, 294, 40);
		main_panel.add(verifyBTN);

		JButton reportBtn = new JButton("View Police Report");
		reportBtn.setOpaque(false);
		reportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(view_report_pane);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		reportBtn.setBackground(Color.YELLOW);
		reportBtn.setForeground(Color.DARK_GRAY);
		reportBtn.setBounds(10, 242, 294, 40);
		main_panel.add(reportBtn);

		JButton applicationBTN = new JButton("View Applications");
		applicationBTN.setOpaque(false);
		applicationBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String applicationID = JOptionPane.showInputDialog("Enter Application ID:");
				if (applicationID == null) {

					return;
				}
				if (checkApplicationIDExistence(applicationID)) {
					generatePDF(applicationID);
				} else {
					JOptionPane.showMessageDialog(AdminUI.this, "Application ID does not exist in the database.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		contentPane.add(applicationBTN);

		setContentPane(contentPane);

		applicationBTN.setBackground(Color.YELLOW);
		applicationBTN.setForeground(Color.DARK_GRAY);
		applicationBTN.setBounds(10, 7, 294, 40);
		main_panel.add(applicationBTN);

		register_panel = new JPanel();
		layeredPane.add(register_panel, "name_283508202550300");
		register_panel.setLayout(null);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(main_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		btnNewButton_2.setBounds(600, 11, 30, 30);
		register_panel.add(btnNewButton_2);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\admin1.png"));
		lblNewLabel_4.setBounds(300, 10, 340, 370);
		register_panel.add(lblNewLabel_4);

		FnameTF = new JTextField();
		FnameTF.setBounds(31, 113, 250, 25);
		register_panel.add(FnameTF);
		FnameTF.setColumns(10);

		UnameTF = new JTextField();
		UnameTF.setColumns(10);
		UnameTF.setBounds(31, 169, 250, 25);
		register_panel.add(UnameTF);

		mailTF = new JTextField();
		mailTF.setColumns(10);
		mailTF.setBounds(31, 224, 250, 25);
		register_panel.add(mailTF);

		passwordTF = new JTextField();
		passwordTF.setColumns(10);
		passwordTF.setBounds(31, 277, 250, 25);
		register_panel.add(passwordTF);

		JButton registerBTN = new JButton("Register");
		registerBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (FnameTF.getText().equals("") || mailTF.getText().equals("") || passwordTF.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Please fill Complete Information");
				} else {

					String fullName = FnameTF.getText();
					String userName = UnameTF.getText();
					String email = mailTF.getText();
					String password = passwordTF.getText();

					JOptionPane.showMessageDialog(null, "Registration Successful!");

					Administrator ar = new Administrator(fullName, userName, email, password);
					ar.registerAdmin(fullName, userName, email, password);

				}
			}
		});

		registerBTN.setForeground(Color.DARK_GRAY);
		registerBTN.setBounds(61, 328, 89, 23);
		register_panel.add(registerBTN);

		JButton clearBTN = new JButton("Clear");
		clearBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FnameTF.setText(null);
				UnameTF.setText(null);
				mailTF.setText(null);
				passwordTF.setText(null);

			}
		});
		clearBTN.setForeground(Color.DARK_GRAY);
		clearBTN.setBounds(173, 328, 89, 23);
		register_panel.add(clearBTN);

		JLabel lblNewLabel_5 = new JLabel("Register your Account ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_5.setForeground(Color.DARK_GRAY);
		lblNewLabel_5.setBounds(31, 11, 250, 54);
		register_panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("User Name");
		lblNewLabel_6.setForeground(Color.DARK_GRAY);
		lblNewLabel_6.setBounds(31, 154, 161, 16);
		register_panel.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("Full Name");
		lblNewLabel_6_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_6_1.setBounds(31, 99, 161, 16);
		register_panel.add(lblNewLabel_6_1);

		JLabel lblNewLabel_7 = new JLabel("E-mail");
		lblNewLabel_7.setForeground(Color.DARK_GRAY);
		lblNewLabel_7.setBounds(31, 210, 144, 14);
		register_panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Password");
		lblNewLabel_8.setForeground(Color.DARK_GRAY);
		lblNewLabel_8.setBounds(31, 265, 46, 14);
		register_panel.add(lblNewLabel_8);

		schedule_panel = new JPanel();
		layeredPane.add(schedule_panel, "name_283508215788200");
		schedule_panel.setLayout(null);

		JLabel lblNewLabel_10 = new JLabel("Schedule dates for manual verification");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_10.setBounds(10, 11, 469, 44);
		schedule_panel.add(lblNewLabel_10);

		idTextF = new JTextField();
		idTextF.setBounds(30, 85, 230, 25);
		schedule_panel.add(idTextF);
		idTextF.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Application ID");
		lblNewLabel_11.setForeground(Color.DARK_GRAY);
		lblNewLabel_11.setBounds(30, 66, 82, 14);
		schedule_panel.add(lblNewLabel_11);

		JLabel lblNewLabel_11_1 = new JLabel("Selected Date ");
		lblNewLabel_11_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_11_1.setBounds(30, 121, 82, 14);
		schedule_panel.add(lblNewLabel_11_1);

		dateTF = new JTextField();
		dateTF.setColumns(10);
		dateTF.setBounds(30, 140, 230, 25);
		schedule_panel.add(dateTF);

		JLabel lblNewLabel_12 = new JLabel("Selected Time");
		lblNewLabel_12.setForeground(Color.DARK_GRAY);
		lblNewLabel_12.setBounds(30, 176, 82, 14);
		schedule_panel.add(lblNewLabel_12);

		JLabel lblNewLabel_11_2 = new JLabel("Massage For Applicant");
		lblNewLabel_11_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_11_2.setBounds(30, 228, 131, 14);
		schedule_panel.add(lblNewLabel_11_2);

		msgTF = new JTextField();
		msgTF.setColumns(10);
		msgTF.setBounds(30, 247, 230, 68);
		schedule_panel.add(msgTF);

		JComboBox<String> timeCombobox = new JComboBox<>(time);
		timeCombobox.setBounds(30, 192, 230, 25);
		schedule_panel.add(timeCombobox);

		JButton bckBTN = new JButton("");
		bckBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(main_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		bckBTN.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		bckBTN.setBounds(600, 11, 30, 30);
		schedule_panel.add(bckBTN);

		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\admin1.png"));
		lblNewLabel_9.setBounds(300, 10, 340, 370);
		schedule_panel.add(lblNewLabel_9);

		JButton scheduleBTN = new JButton("Schedule");
		scheduleBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ApplicationID = idTextF.getText();
				int appID = Integer.parseInt(ApplicationID);

				String date = dateTF.getText();
				String time = (String) timeCombobox.getSelectedItem();
				String massage = msgTF.getText();

				JOptionPane.showMessageDialog(null, "Schedule Application Date Successful!");

				Administrator ar = new Administrator(appID, date, time, massage);
				ar.schedule(appID, date, time, massage);

			}
		});

		scheduleBTN.setBounds(49, 337, 89, 23);
		schedule_panel.add(scheduleBTN);

		JButton clrBTN = new JButton("Clear");
		clrBTN.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				dateTF.setText("");
				idTextF.setText("");
				msgTF.setText("");
				timeCombobox.setSelectedIndex(0);

			}
		});

		clrBTN.setBounds(148, 337, 89, 23);
		schedule_panel.add(clrBTN);

		sms_panel = new JPanel();
		layeredPane.add(sms_panel, "name_286437069233700");
		sms_panel.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("Send SMS & Emails");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_14.setBounds(20, 11, 337, 63);
		sms_panel.add(lblNewLabel_14);

		S1 = new JTextField();
		S1.setBounds(34, 116, 230, 25);
		sms_panel.add(S1);
		S1.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Application ID");
		lblNewLabel_15.setForeground(Color.DARK_GRAY);
		lblNewLabel_15.setBounds(34, 100, 99, 14);
		sms_panel.add(lblNewLabel_15);

		JLabel lblNewLabel_15_1 = new JLabel("Email/Mobile No.");
		lblNewLabel_15_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_15_1.setBounds(34, 153, 99, 14);
		sms_panel.add(lblNewLabel_15_1);

		S2 = new JTextField();
		S2.setColumns(10);
		S2.setBounds(34, 169, 230, 25);
		sms_panel.add(S2);

		JLabel lblNewLabel_15_2 = new JLabel("Massage");
		lblNewLabel_15_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_15_2.setBounds(34, 205, 99, 14);
		sms_panel.add(lblNewLabel_15_2);

		S3 = new JTextField();
		S3.setColumns(10);
		S3.setBounds(34, 221, 230, 79);
		sms_panel.add(S3);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id = S1.getText();
				int applicationID = Integer.parseInt(id);
				String method = S2.getText();
				String massage = S3.getText();

				JOptionPane.showMessageDialog(null, "Massage Sent Successfully!");

				Administrator ar = new Administrator(applicationID, method, massage);
				ar.send_massage(applicationID, method, massage);

			}
		});
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(55, 328, 89, 23);
		sms_panel.add(btnNewButton);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				S1.setText("");
				S2.setText("");
				S3.setText("");

			}
		});

		btnClear.setForeground(Color.DARK_GRAY);
		btnClear.setBounds(163, 328, 89, 23);
		sms_panel.add(btnClear);

		JButton btnNewButton_12 = new JButton("");
		btnNewButton_12.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(main_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_12.setBounds(600, 11, 30, 30);
		sms_panel.add(btnNewButton_12);

		JLabel lblNewLabel_13 = new JLabel("\r\n");
		lblNewLabel_13.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\admin1.png"));
		lblNewLabel_13.setBounds(300, 10, 340, 370);
		sms_panel.add(lblNewLabel_13);

		status_panel = new JPanel();
		layeredPane.add(status_panel, "name_286446996658900");
		status_panel.setLayout(null);

		JLabel lblNewLabel_17 = new JLabel("Status Update");
		lblNewLabel_17.setEnabled(true);
		lblNewLabel_17.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_17.setBounds(28, 11, 193, 76);
		status_panel.add(lblNewLabel_17);

		st1 = new JTextField();
		st1.setBounds(38, 125, 222, 25);
		status_panel.add(st1);
		st1.setColumns(10);

		JLabel lblNewLabel_19 = new JLabel("Application ID");
		lblNewLabel_19.setBounds(38, 108, 121, 14);
		status_panel.add(lblNewLabel_19);

		JLabel lblNewLabel_19_1 = new JLabel("Status");
		lblNewLabel_19_1.setBounds(38, 161, 121, 14);
		status_panel.add(lblNewLabel_19_1);

		JComboBox<String> st2 = new JComboBox<>(status);
		st2.setBounds(38, 177, 222, 25);
		status_panel.add(st2);

		JButton setBTN = new JButton("Set Status");
		setBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id = st1.getText();
				int applicationID = Integer.parseInt(id);
				String status = (String) st2.getSelectedItem();

				Administrator ay = new Administrator(applicationID, status);
				ay.set_status(applicationID, status);

			}
		});
		setBTN.setBounds(38, 237, 107, 23);
		status_panel.add(setBTN);

		JButton UpdBTN = new JButton("Update Status");
		UpdBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id = st1.getText();
				int applicationID = Integer.parseInt(id);
				String status = (String) st2.getSelectedItem();

				Administrator ay = new Administrator(applicationID, status);
				ay.update_status(applicationID, status);

				JOptionPane.showMessageDialog(contentPane, "Update successful", "Success",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});

		UpdBTN.setBounds(155, 237, 105, 23);
		status_panel.add(UpdBTN);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(main_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		btnNewButton_4.setBounds(600, 11, 30, 30);
		status_panel.add(btnNewButton_4);

		JLabel lblNewLabel_18 = new JLabel("");
		lblNewLabel_18.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\admin1.png"));
		lblNewLabel_18.setBounds(290, 11, 340, 370);
		status_panel.add(lblNewLabel_18);

		send_police_panel = new JPanel();
		layeredPane.add(send_police_panel, "name_289629691713400");
		send_police_panel.setLayout(null);

		v1 = new JTextField();
		v1.setBounds(28, 109, 236, 25);
		send_police_panel.add(v1);
		v1.setColumns(10);

		JLabel lblNewLabel_21 = new JLabel("Send Details to Verification");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_21.setBounds(28, 29, 314, 38);
		send_police_panel.add(lblNewLabel_21);

		JLabel lblNewLabel_22 = new JLabel("Application ID");
		lblNewLabel_22.setBounds(28, 92, 89, 14);
		send_police_panel.add(lblNewLabel_22);

		JLabel lblNewLabel_22_1 = new JLabel("Case ID");
		lblNewLabel_22_1.setBounds(28, 145, 89, 14);
		send_police_panel.add(lblNewLabel_22_1);

		v2 = new JTextField();
		v2.setColumns(10);
		v2.setBounds(28, 162, 236, 25);
		send_police_panel.add(v2);

		JButton v3 = new JButton("Application");
		v3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectFile();

			}
		});
		v3.setBounds(28, 221, 112, 69);
		send_police_panel.add(v3);

		JButton v4 = new JButton("Files");
		v4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectFile();

			}
		});
		v4.setBounds(152, 221, 112, 69);
		send_police_panel.add(v4);

		JLabel lblNewLabel_23 = new JLabel("Upload Documents If needed");
		lblNewLabel_23.setBounds(28, 206, 236, 14);
		send_police_panel.add(lblNewLabel_23);

		JButton sendBTN = new JButton("Send");
		sendBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String veriID = v1.getText();
				int verifyID = Integer.parseInt(veriID);
				String csID = v2.getText();
				int caseID = Integer.parseInt(csID);

				Administrator administrator = new Administrator();
				administrator.send_Verify(verifyID, caseID, null);
			}
		});

		sendBTN.setBounds(51, 318, 89, 23);
		send_police_panel.add(sendBTN);

		JButton ClearBTNN = new JButton("Clear");
		ClearBTNN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				v1.setText("");
				v2.setText("");

			}
		});

		ClearBTNN.setBounds(152, 318, 89, 23);
		send_police_panel.add(ClearBTNN);

		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(main_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_7.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		btnNewButton_7.setBounds(600, 11, 30, 30);
		send_police_panel.add(btnNewButton_7);

		JLabel lblNewLabel_20 = new JLabel("\r\n");
		lblNewLabel_20.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\admin1.png"));
		lblNewLabel_20.setBounds(300, 10, 340, 370);
		send_police_panel.add(lblNewLabel_20);

		view_report_pane = new JPanel();
		layeredPane.add(view_report_pane, "name_289635781544800");
		view_report_pane.setLayout(null);

		JLabel lblNewLabel_25 = new JLabel("Police Report");
		lblNewLabel_25.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_25.setBounds(25, 11, 277, 48);
		view_report_pane.add(lblNewLabel_25);

		table = new JTable();
		table.setBounds(25, 126, 252, 235);
		view_report_pane.add(table);

		textField_6 = new JTextField();
		textField_6.setBounds(23, 95, 163, 20);
		view_report_pane.add(textField_6);
		textField_6.setColumns(10);

		JButton btnNewButton_8 = new JButton("Go");
		btnNewButton_8.setBounds(198, 94, 78, 23);
		view_report_pane.add(btnNewButton_8);

		JLabel lblNewLabel_26 = new JLabel("Application ID");
		lblNewLabel_26.setBounds(23, 80, 116, 14);
		view_report_pane.add(lblNewLabel_26);

		JButton btnNewButton_9 = new JButton("");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(main_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_9.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		btnNewButton_9.setBounds(600, 11, 30, 30);
		view_report_pane.add(btnNewButton_9);

		JLabel lblNewLabel_24 = new JLabel("");
		lblNewLabel_24.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\admin1.png"));
		lblNewLabel_24.setBounds(300, 10, 340, 370);
		view_report_pane.add(lblNewLabel_24);

		viewPanel = new JPanel();
		viewPanel.setBackground(new Color(255, 255, 255));
		layeredPane.add(viewPanel, "name_297228222798200");
		viewPanel.setLayout(null);

		JLabel lblNewLabel_27 = new JLabel("Aplication Details");
		lblNewLabel_27.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_27.setBounds(229, 0, 181, 43);
		viewPanel.add(lblNewLabel_27);

		JButton btnNewButton_11 = new JButton("");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(main_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_11.setIcon(new ImageIcon(
				"C:\\Users\\hasal\\eclipse-workspace\\Passport_Automation_System\\src\\pas\\resources\\arrows.png"));
		btnNewButton_11.setBounds(600, 348, 30, 30);
		viewPanel.add(btnNewButton_11);
	}

	private static File selectFile() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			return null;
		}
	}

	private boolean checkApplicationIDExistence(String applicationID) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			String query = "SELECT * FROM Application_Form WHERE application_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, applicationID);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					return resultSet.next();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error checking application ID existence: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			System.err.println(e);
			return false;
		}
	}

	private void generatePDF(String applicationID) {
		try {

			pas.common.GeneratePDF.main(new String[] { applicationID });

			JOptionPane.showMessageDialog(this,
					"PDF generated successfully! Check The Admin Folder to View the Application");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error generating PDF: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			System.err.println(e);
		}
	}

}
