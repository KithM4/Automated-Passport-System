package pas.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Cursor;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import pas.common.Application_Form;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField A7;
	private JTextField A8;
	private JTextField A9;
	private JTextField A10;
	private JTextField A11;
	private JTextField A12;
	private JTextField A13;
	private JTextField A15;
	private JTextField A16;
	private JTextField A22;
	private JTextField A23;
	private JTextField A24;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel imageLabel;
	private JLabel presentTravelDocsLabel;
	private JLabel birthCertificateLabel;
	private JLabel dualCitizenshipDocLabel;
	private JButton NextBtn;

	public Application() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/pas/resources/PAS LOGO.png")));
		setTitle("ONLINE PASSPORT APPLICATION SYSTEM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 433);

		// array of district names
		String[] districtNames = { "--Select District--", "Colombo", "Gampaha", "Kalutara", "Kandy", "Matale",
				"Nuwara Eliya", "Galle", "Matara", "Hambantota", "Jaffna", "Mannar", "Vavuniya", "Mullaitivu",
				"Batticaloa", "Ampara", "Trincomalee", "Kurunegala", "Puttalam", "Anuradhapura", "Polonnaruwa",
				"Badulla", "Monaragala", "Ratnapura", "Kegalle" };
		// array of jobs
		String[] occupationNames = { "--Select Occupation/Job--", "Engineer", "Teacher", "Doctor", "Artist",
				"Accountant", "Student", "Business Owner", "IT Professional", "Government Employee", "Freelancer", };

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		setContentPane(scrollPane);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setPreferredSize(new Dimension(600, 1050));

		scrollPane.setViewportView(contentPane);
		contentPane.setLayout(null);

		Label label = new Label("PASSPORT APPLICATION");
		label.setBounds(121, 40, 406, 44);
		label.setFont(new Font("Dialog", Font.PLAIN, 30));
		label.setAlignment(Label.CENTER);
		contentPane.add(label);

		JLabel lblNewLabel = new JLabel("01. Type of Service");
		lblNewLabel.setBounds(37, 126, 112, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_6 = new JLabel("02. Type of Travel Document");
		lblNewLabel_6.setBounds(37, 167, 149, 14);
		contentPane.add(lblNewLabel_6);

		JCheckBox A1 = new JCheckBox("Normal");
		A1.setBounds(235, 126, 97, 23);
		A1.setBackground(Color.WHITE);
		A1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		A1.setHorizontalTextPosition(SwingConstants.LEFT);
		contentPane.add(A1);

		JCheckBox A2 = new JCheckBox("One Day");
		A2.setBounds(334, 126, 97, 23);
		A2.setBackground(Color.WHITE);
		A2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		A2.setHorizontalTextPosition(SwingConstants.LEFT);
		contentPane.add(A2);

		ItemListener checkBoxListener1 = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {

					for (JCheckBox checkbox : new JCheckBox[] { A1, A2, }) {
						if (checkbox != e.getSource()) {
							checkbox.setEnabled(false);
						}
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {

					for (JCheckBox checkbox : new JCheckBox[] { A1, A2 }) {
						checkbox.setEnabled(true);
					}
				}
			}
		};
		A1.addItemListener(checkBoxListener1);
		A2.addItemListener(checkBoxListener1);

		JCheckBox A3 = new JCheckBox("All Countries");
		A3.setBounds(208, 163, 97, 23);
		A3.setBackground(Color.WHITE);
		A3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		A3.setHorizontalTextPosition(SwingConstants.LEFT);
		contentPane.add(A3);

		JCheckBox A4 = new JCheckBox("Middle East");
		A4.setBounds(307, 163, 97, 23);
		A4.setBackground(Color.WHITE);
		A4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		A4.setHorizontalTextPosition(SwingConstants.LEFT);
		contentPane.add(A4);

		JCheckBox A5 = new JCheckBox("Emergency");
		A5.setBounds(406, 163, 97, 23);
		A5.setBackground(Color.WHITE);
		A5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		A5.setHorizontalTextPosition(SwingConstants.LEFT);
		contentPane.add(A5);

		JCheckBox A6 = new JCheckBox("Identity");
		A6.setBounds(505, 163, 79, 23);
		A6.setBackground(Color.WHITE);
		A6.setHorizontalTextPosition(SwingConstants.LEFT);
		A6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(A6);

		ItemListener checkBoxListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {

					for (JCheckBox checkbox : new JCheckBox[] { A3, A4, A5, A6 }) {
						if (checkbox != e.getSource()) {
							checkbox.setEnabled(false);
						}
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {

					for (JCheckBox checkbox : new JCheckBox[] { A3, A4, A5, A6 }) {
						checkbox.setEnabled(true);
					}
				}
			}
		};
		A3.addItemListener(checkBoxListener);
		A4.addItemListener(checkBoxListener);
		A5.addItemListener(checkBoxListener);
		A6.addItemListener(checkBoxListener);

		JLabel lblNewLabel_1 = new JLabel("03. Present Travel Document No.(If any)");
		lblNewLabel_1.setBounds(37, 202, 209, 14);
		contentPane.add(lblNewLabel_1);

		A7 = new JTextField();
		A7.setBounds(57, 222, 235, 25);
		contentPane.add(A7);
		A7.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("04. NMRP No. (If any)");
		lblNewLabel_2.setBounds(334, 202, 132, 14);
		contentPane.add(lblNewLabel_2);

		A8 = new JTextField();
		A8.setBounds(359, 224, 235, 25);
		contentPane.add(A8);
		A8.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("05. National Identity Card No.");
		lblNewLabel_3.setBounds(37, 270, 209, 14);
		contentPane.add(lblNewLabel_3);

		A9 = new JTextField();
		A9.setBounds(230, 265, 266, 25);
		contentPane.add(A9);
		A9.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("06. Surname");
		lblNewLabel_4.setBounds(37, 318, 209, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("07. Other Names");
		lblNewLabel_5.setBounds(37, 358, 209, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_7 = new JLabel("08. Permenent Address");
		lblNewLabel_7.setBounds(37, 399, 209, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("09. Permenent Address City");
		lblNewLabel_8.setBounds(37, 441, 209, 14);
		contentPane.add(lblNewLabel_8);

		A10 = new JTextField();
		A10.setBounds(230, 313, 364, 25);
		contentPane.add(A10);
		A10.setColumns(10);

		A11 = new JTextField();
		A11.setBounds(230, 353, 364, 25);
		contentPane.add(A11);
		A11.setColumns(10);

		A12 = new JTextField();
		A12.setBounds(230, 394, 364, 25);
		contentPane.add(A12);
		A12.setColumns(10);

		A13 = new JTextField();
		A13.setBounds(230, 436, 364, 25);
		contentPane.add(A13);
		A13.setColumns(10);

		JComboBox<String> A14 = new JComboBox<>(districtNames);
		A14.setBounds(230, 480, 300, 25);
		contentPane.add(A14);

		JLabel lblNewLabel_9 = new JLabel("10. District");
		lblNewLabel_9.setBounds(37, 485, 100, 14);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("11. Date of Birth");
		lblNewLabel_10.setBounds(37, 528, 100, 14);
		contentPane.add(lblNewLabel_10);

		A15 = new JTextField();
		A15.setBounds(230, 523, 300, 25);
		contentPane.add(A15);
		A15.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("12. Birth Certificate \tNo");
		lblNewLabel_11.setBounds(37, 570, 150, 14);
		contentPane.add(lblNewLabel_11);

		A16 = new JTextField();
		A16.setBounds(230, 565, 100, 25);
		contentPane.add(A16);
		A16.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("13. Sex");
		lblNewLabel_12.setBounds(370, 568, 46, 14);
		contentPane.add(lblNewLabel_12);

		JCheckBox A17 = new JCheckBox("Male");
		A17.setBounds(433, 564, 60, 23);
		A17.setHorizontalTextPosition(SwingConstants.LEFT);
		A17.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		A17.setBackground(new Color(255, 255, 255));
		contentPane.add(A17);

		JCheckBox A18 = new JCheckBox("Female");
		A18.setBounds(500, 564, 97, 23);
		A18.setHorizontalTextPosition(SwingConstants.LEFT);
		A18.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		A18.setBackground(new Color(255, 255, 255));
		contentPane.add(A18);

		ItemListener checkBoxListener11 = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {

					for (JCheckBox checkbox : new JCheckBox[] { A17, A18 }) {
						if (checkbox != e.getSource()) {
							checkbox.setEnabled(false);
						}
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {

					for (JCheckBox checkbox : new JCheckBox[] { A17, A18 }) {
						checkbox.setEnabled(true);
					}
				}
			}
		};

		A17.addItemListener(checkBoxListener11);
		A18.addItemListener(checkBoxListener11);

		JLabel lblNewLabel_13 = new JLabel("14. Select Profession/ Occupation");
		lblNewLabel_13.setBounds(37, 614, 200, 14);
		contentPane.add(lblNewLabel_13);
		JComboBox<String> A19 = new JComboBox<>(occupationNames);

		A19.setBounds(230, 609, 300, 25);
		contentPane.add(A19);

		JLabel lblNewLabel_14 = new JLabel("15. Have you obtained Dual Citizenship in Sri Lanka?");
		lblNewLabel_14.setBounds(37, 657, 300, 14);
		contentPane.add(lblNewLabel_14);

		JCheckBox A20 = new JCheckBox("Yes");
		A20.setBackground(new Color(255, 255, 255));
		A20.setHorizontalTextPosition(SwingConstants.LEFT);
		A20.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		A20.setBounds(360, 653, 50, 23);
		contentPane.add(A20);

		JCheckBox A21 = new JCheckBox("No\r\n");
		A21.setBackground(new Color(255, 255, 255));
		A21.setHorizontalTextPosition(SwingConstants.LEFT);
		A21.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		A21.setBounds(444, 653, 50, 23);
		contentPane.add(A21);

		ItemListener checkBoxListener111 = new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {

					for (JCheckBox checkbox : new JCheckBox[] { A20, A21 }) {
						if (checkbox != e.getSource()) {
							checkbox.setEnabled(false);
						}
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {

					for (JCheckBox checkbox : new JCheckBox[] { A20, A21 }) {
						checkbox.setEnabled(true);
					}
				}
			}
		};

		A20.addItemListener(checkBoxListener111);
		A21.addItemListener(checkBoxListener111);

		JLabel lblNewLabel_15 = new JLabel("If Yes, Dual Citizenship No.");
		lblNewLabel_15.setBounds(75, 695, 150, 14);
		contentPane.add(lblNewLabel_15);

		A21.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean isSelected = A21.isSelected();

				A22.setEnabled(!isSelected);

				if (isSelected) {
					A22.setText(null);
				}
			}
		});

		A22 = new JTextField();
		A22.setBounds(230, 688, 300, 25);
		contentPane.add(A22);
		A22.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("16. Mobile/ Phone No. (to be notified via SMS when passport is ready )");
		lblNewLabel_16.setBounds(37, 735, 350, 14);
		contentPane.add(lblNewLabel_16);

		A23 = new JTextField();
		A23.setBounds(395, 730, 195, 25);
		contentPane.add(A23);
		A23.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("17. E-mail Address");
		lblNewLabel_17.setBounds(37, 785, 150, 14);
		contentPane.add(lblNewLabel_17);

		A24 = new JTextField();
		A24.setBounds(230, 780, 350, 25);
		contentPane.add(A24);
		A24.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(24, 836, 600, 2);
		contentPane.add(separator);

		imageLabel = new JLabel();
		imageLabel.setBounds(10, 40, 150, 150);
		contentPane.add(imageLabel);

		presentTravelDocsLabel = new JLabel();
		presentTravelDocsLabel.setBounds(10, 230, 150, 150);
		contentPane.add(presentTravelDocsLabel);

		birthCertificateLabel = new JLabel();
		birthCertificateLabel.setBounds(10, 420, 150, 150);
		contentPane.add(birthCertificateLabel);

		dualCitizenshipDocLabel = new JLabel();
		dualCitizenshipDocLabel.setBounds(10, 610, 150, 150);
		contentPane.add(dualCitizenshipDocLabel);

		JButton sbbtn = new JButton("Submit");
		sbbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean Normal_Service = A1.isSelected();
				boolean OneDay_Service = A2.isSelected();
				boolean AllCountriesDoc = A3.isSelected();
				boolean MiddleEastDoc = A4.isSelected();
				boolean EmergencyDoc = A5.isSelected();
				boolean IdentityDoc = A6.isSelected();

				String TravelDoc_No = A7.getText();
				int tavelDocNum = 0;
				if (!TravelDoc_No.isEmpty()) {
					try {
						tavelDocNum = Integer.parseInt(TravelDoc_No);
					} catch (NumberFormatException e1) {

						e1.printStackTrace();
					}
				}

				String NMRP_No = A8.getText();
				int nmrp_No = 0;
				if (!NMRP_No.isEmpty()) {
					try {
						nmrp_No = Integer.parseInt(NMRP_No);
					} catch (NumberFormatException e1) {

						e1.printStackTrace();
					}
				}

				String nic = A9.getText();
				String surname = A10.getText();
				String otherName = A11.getText();
				String address = A12.getText();
				String city = A13.getText();
				String district = (String) A14.getSelectedItem();

				String dob = A15.getText();

				String birth_no = A16.getText();
				int BrthNo = 0;
				if (!birth_no.isEmpty()) {
					BrthNo = Integer.parseInt(birth_no);
				}

				boolean male = A17.isSelected();
				boolean female = A18.isSelected();
				String job = (String) A19.getSelectedItem();
				boolean citizen_yes = A20.isSelected();
				boolean citizen_no = A21.isSelected();

				String citizen_Num = A22.getText();
				int CtznNum = 0;
				if (!citizen_Num.isEmpty()) {
					try {
						CtznNum = Integer.parseInt(citizen_Num);
					} catch (NumberFormatException e1) {

						e1.printStackTrace();
					}
				}

				String mobile = A23.getText();
				int Mobile = 0;
				if (!mobile.isEmpty()) {
					try {
						Mobile = Integer.parseInt(mobile);
					} catch (NumberFormatException e1) {

						e1.printStackTrace();
					}
				}

				String mail = A24.getText();

				Application_Form ar = new Application_Form(Normal_Service, OneDay_Service, AllCountriesDoc,
						MiddleEastDoc, EmergencyDoc, IdentityDoc, tavelDocNum, nmrp_No, BrthNo, CtznNum, Mobile, nic,
						surname, otherName, address, city, district, dob, job, mail, male, female, citizen_yes,
						citizen_no);
				ar.Submit_Application(Normal_Service, OneDay_Service, AllCountriesDoc, MiddleEastDoc, EmergencyDoc,
						IdentityDoc, tavelDocNum, nmrp_No, BrthNo, CtznNum, Mobile, nic, surname, otherName, address,
						city, district, dob, job, mail, male, female, citizen_yes, citizen_no);

			}
		});

		sbbtn.setBounds(95, 1010, 89, 25);
		sbbtn.addActionListener(e -> handleSubmit());
		contentPane.add(sbbtn);

		JButton ClearButton = new JButton("Clear");
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A1.setSelected(false);
				A2.setSelected(false);
				A3.setSelected(false);
				A4.setSelected(false);
				A5.setSelected(false);
				A6.setSelected(false);
				A7.setText(null);
				A8.setText(null);
				A9.setText(null);
				A10.setText(null);
				A11.setText(null);
				A12.setText(null);
				A13.setText(null);
				A14.setSelectedIndex(-1);
				A15.setText(null);
				A16.setText(null);
				A17.setSelected(false);
				A18.setSelected(false);
				A19.setSelectedIndex(-1);
				A20.setSelected(false);
				A21.setSelected(false);
				A22.setText(null);
				A23.setText(null);
				A24.setText(null);

			}
		});
		ClearButton.setBounds(279, 1010, 89, 25);
		contentPane.add(ClearButton);

		NextBtn = new JButton("Close");
		NextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicantsUI ob = new ApplicantsUI();
				ob.setVisible(true);
				dispose();

			}
		});
		NextBtn.setBounds(463, 1010, 89, 25);
		contentPane.add(NextBtn);

		JButton uploadBTN = new JButton("");
		uploadBTN.setIcon(new ImageIcon(Application.class.getResource("/pas/resources/10.png")));
		uploadBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		uploadBTN.setBounds(490, 870, 80, 100);
		contentPane.add(uploadBTN);

		uploadBTN.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				String filePath = openFileChooserForDocument();
				if (filePath != null) {

					System.out.println("Selected File: " + filePath);

					saveFileToDatabase(filePath);
				}
			}
		});

		// buttons for uploading files
		JButton uploadDocButton = new JButton("");
		uploadDocButton.setIcon(new ImageIcon(Application.class.getResource("/pas/resources/8.png")));
		uploadDocButton.setBounds(220, 870, 80, 100);
		contentPane.add(uploadDocButton);

		JButton uploadImageBtn = new JButton("");
		uploadImageBtn.setIcon(new ImageIcon(Application.class.getResource("/pas/resources/7.png")));
		uploadImageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		uploadImageBtn.setBounds(85, 870, 80, 100);
		contentPane.add(uploadImageBtn);

		JButton uploadCertBtn = new JButton("");
		uploadCertBtn.setIcon(new ImageIcon(Application.class.getResource("/pas/resources/9.png")));
		uploadCertBtn.setBounds(355, 870, 80, 100);
		contentPane.add(uploadCertBtn);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(24, 987, 600, 2);
		contentPane.add(separator_1);

		JLabel lblNewLabel_18 = new JLabel("Applicant Image");
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_18.setBounds(75, 845, 100, 14);
		contentPane.add(lblNewLabel_18);

		JLabel lblNewLabel_18_1 = new JLabel("Documents");
		lblNewLabel_18_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_18_1.setBounds(210, 845, 100, 14);
		contentPane.add(lblNewLabel_18_1);

		JLabel lblNewLabel_18_2 = new JLabel("Payment Slip");
		lblNewLabel_18_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_18_2.setBounds(345, 845, 100, 14);
		contentPane.add(lblNewLabel_18_2);

		JLabel lblNewLabel_18_3 = new JLabel("Other Documents");
		lblNewLabel_18_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_18_3.setBounds(480, 845, 100, 14);
		contentPane.add(lblNewLabel_18_3);

		// Add action listeners to the new buttons
		uploadDocButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String filePath = openFileChooserForDocument();
				if (filePath != null) {

					System.out.println("Selected Document File: " + filePath);

					saveFileToDatabase(filePath);
				}
			}
		});

		uploadImageBtn.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				String filePath = openFileChooserForImage();
				if (filePath != null) {

					System.out.println("Selected Image File: " + filePath);

					saveFileToDatabase(filePath);
				}
			}
		});

		uploadCertBtn.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				String filePath = openFileChooserForCertificate();
				if (filePath != null) {

					System.out.println("Selected Certificate File: " + filePath);

					saveFileToDatabase(filePath);
				}
			}
		});

		uploadBTN.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				String filePath = openFileChooserForDocument();
				if (filePath != null) {

					System.out.println("Selected File: " + filePath);
				}
			}
		});

	}

	private String openFileChooserForDocument() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Document files", "pdf", "doc", "docx");
		fileChooser.setFileFilter(filter);

		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile.getAbsolutePath();
		}
		return null;
	}

	private void saveFileToDatabase(String filePath) {
		try {

			FileInputStream fis = new FileInputStream(filePath);

			String url = "jdbc:mysql://localhost:3306/PAS";
			String username = "root";
			String password = "1234";
			Connection connection = DriverManager.getConnection(url, username, password);

			String sql = "INSERT INTO uploaded_files (file_name, file_type, file_content) VALUES (?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				preparedStatement.setString(1, new File(filePath).getName());
				preparedStatement.setString(2, getFileType(filePath));

				preparedStatement.setBinaryStream(3, fis, fis.available());

				preparedStatement.executeUpdate();

				fis.close();
				connection.close();
			}

			JOptionPane.showMessageDialog(this, "File uploaded successfully!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error uploading file.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private String getFileType(String filePath) {
		String[] parts = filePath.split("\\.");
		return parts.length > 1 ? parts[parts.length - 1] : "";

	}

	private String openFileChooserForImage() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(filter);

		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
			imageLabel.setIcon(icon);
			return selectedFile.getAbsolutePath();
		}
		return null;
	}

	private String openFileChooserForCertificate() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Certificate files", "pdf", "doc", "docx");
		fileChooser.setFileFilter(filter);

		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
			birthCertificateLabel.setIcon(icon);
			return selectedFile.getAbsolutePath();
		}
		return null;
	}

	private void handleSubmit() {

		if (validateForm()) {

			JOptionPane.showMessageDialog(this, "Application submitted successfully!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} else {

			JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean validateForm() {

		if (A7.getText().isEmpty() || A8.getText().isEmpty() || A9.getText().isEmpty() || A10.getText().isEmpty()
				|| A11.getText().isEmpty() || A12.getText().isEmpty() || A13.getText().isEmpty()
				|| A15.getText().isEmpty() || A16.getText().isEmpty() || A23.getText().isEmpty()
				|| A24.getText().isEmpty()) {
			return false;
		}
		return true;
	}

}
