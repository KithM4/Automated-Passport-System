package pas.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.TextArea;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Cursor;

public class StartUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton instuctBtn;
	private JPanel mainPanel;
	private JPanel msgPanel;

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
					StartUI frame = new StartUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		try {
			DriverManager.getConnection("jdbc:mysql://localhost:3306/Connection", "root", "1234");
			System.out.println("Connection established successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error connecting to the database.");
		}
	}

	/**
	 * Create the frame.
	 */
	public StartUI() {

		setForeground(Color.DARK_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartUI.class.getResource("/pas/resources/PAS LOGO.png")));
		setTitle("Online Passport Application System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 433);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(7, 11, 630, 371);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		layeredPane.add(mainPanel, "name_77979834903600");
		mainPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel(
				"Before you may enter your personal information to get a passport, you must read carefuly the instructions\r\n");
		lblNewLabel_1.setBounds(22, 135, 586, 14);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(
				"Once you have read the instructions click on the box below to indicate you have read them.");
		lblNewLabel_2.setBounds(22, 185, 586, 14);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblNewLabel_2);

		instuctBtn = new JButton("--- Instructions to apply Passport ---");
		instuctBtn.setBounds(108, 155, 413, 23);
		instuctBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(msgPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		instuctBtn.setBorder(null);
		instuctBtn.setBackground(Color.WHITE);
		instuctBtn.setForeground(Color.BLUE);
		mainPanel.add(instuctBtn);

		JButton SubmitBTN = new JButton("SUBMIT\r\n");
		SubmitBTN.setBounds(219, 236, 89, 23);
		SubmitBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicantsUI ob = new ApplicantsUI();
				ob.setVisible(true);
				dispose();

			}
		});
		SubmitBTN.setEnabled(false);

		SubmitBTN.setFont(new Font("Tahoma", Font.PLAIN, 11));
		SubmitBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SubmitBTN.setBackground(UIManager.getColor("Button.light"));
		SubmitBTN.setForeground(Color.DARK_GRAY);
		mainPanel.add(SubmitBTN);

		JButton btnNewButton_3 = new JButton("CANCEL");
		btnNewButton_3.setBounds(322, 236, 89, 23);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBackground(UIManager.getColor("Button.light"));
		mainPanel.add(btnNewButton_3);

		JLabel lblNewLabel_3 = new JLabel(
				"___________________________________________________________________________________________________\r\n");
		lblNewLabel_3.setBounds(5, 260, 620, 14);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Need Help ?");
		lblNewLabel_4.setBounds(15, 283, 81, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		mainPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Ask Questions About Your Application: Call +94 76 343 6936");
		lblNewLabel_5.setBounds(15, 326, 291, 14);
		mainPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Report Technical Problems with this Form Filler: Email PassSys@state.gov");
		lblNewLabel_6.setBounds(15, 346, 374, 14);
		mainPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("You can check application status 14 business days after you apply.");
		lblNewLabel_7.setBounds(15, 308, 341, 14);
		mainPanel.add(lblNewLabel_7);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(377, 285, 1, 75);
		mainPanel.add(desktopPane);

		JLabel lblNewLabel_8 = new JLabel("Authority Used Only.");
		lblNewLabel_8.setBounds(388, 284, 138, 14);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		mainPanel.add(lblNewLabel_8);

		JButton btnNewButton_4 = new JButton("Administrator");
		btnNewButton_4.setBounds(409, 304, 97, 23);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdminUI ob = new AdminUI();
				ob.setVisible(true);
				dispose();

			}

		});
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_4.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Police");
		btnNewButton_5.setBounds(409, 330, 97, 23);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PoliceUI ob = new PoliceUI();
				ob.setVisible(true);
				dispose();

			}
		});
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_5.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(btnNewButton_5);

		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(532, 285, 1, 75);
		mainPanel.add(desktopPane_1);

		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setBounds(548, 280, 60, 80);
		Image img = new ImageIcon(this.getClass().getResource("/img1.png")).getImage();
		lblNewLabel_9.setIcon(new ImageIcon(img));
		mainPanel.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("ONLINE PASSPORT APPLICATION SYSTEM");
		lblNewLabel_10.setBounds(0, 0, 630, 84);
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setOpaque(true);
		lblNewLabel_10.setAutoscrolls(true);
		lblNewLabel_10.setAlignmentY(0.0f);
		lblNewLabel_10.setBackground(new Color(105, 105, 105));
		lblNewLabel_10.setFont(new Font("Agency FB", Font.BOLD, 39));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel(
				"We facilitate our citizens to apply passports through online comfortably at their fingertips.");
		lblNewLabel_11.setBounds(22, 110, 586, 14);
		lblNewLabel_11.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblNewLabel_11);

		JCheckBox agreeCHKBX = new JCheckBox("I agree to the instructions and conditions");
		agreeCHKBX.setBackground(Color.WHITE);
		agreeCHKBX.setBounds(203, 206, 223, 23);
		mainPanel.add(agreeCHKBX);

		agreeCHKBX.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("Checkbox state changed!");
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("Checkbox selected");

					SubmitBTN.setEnabled(true);
				} else {
					System.out.println("Checkbox deselected");

					SubmitBTN.setEnabled(false);
				}
			}
		});

		msgPanel = new JPanel();
		msgPanel.setBackground(UIManager.getColor("Button.light"));
		msgPanel.setLayout(null);
		layeredPane.add(msgPanel, "name_77979872966600");

		JLabel lblNewLabel = new JLabel("Instruction to Apply Passport");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 260, 24);
		msgPanel.add(lblNewLabel);

		TextArea textArea = new TextArea();
		textArea.setText(
				"Department of Immigration and Emigration\r\n\r\nOnline Travel Document Submission – Local Applicants\r\n\r\nInstructions to Apply Passport (Please read the instructions carefully)\r\n\r\n\r\n1. How to apply\r\n\r\n1.1. You can apply for either urgent service or normal service.\r\n1.2. If you select the urgent service, your travel document will be issued after three days of capturing\r\nyour fingerprints.\r\n1.3. If you select the normal service, your travel document will be issued after thirty days of capturing\r\nyour fingerprints.\r\n\r\n\r\n2. Eligibility\r\n\r\n2.1. The eligibility criteria in order to avail this service are as follows:\r\n2.1.1. Your age should be above 15 years as at the date of submitting your application.\r\n2.1.2. You should possess your valid passport.\r\n2.1.3. Your NIC /Passport/Full Name should not be existed as blacklist.\r\n2.1.4. You should not have a travel ban imposed by the courts of law.\r\n2.1.5. You should not have a travel ban imposed by the tri-forces or any other government\r\nenforcement authorities.\r\n2.1.6. Application for Official and Diplomatic passport cannot be made by utilizing this online\r\ntravel document application system.\r\n2.2. If you do not meet above mentioned criteria as stipulated in 1, you will not be permitted to apply\r\nonline. Hence, please visit Head office or Regional office of the Department.\r\n\r\n\r\n3. Required Documents\r\n\r\n3.1. The document requirements to scan and upload for a passport are given as follows.\r\n3.1.1. National Identity Card (NIC)\r\n3.1.2. Birth Certificate\r\n3.1.3. Section 5(2) of the Citizenship Act. No. 18 of 1948 (if applicable)\r\n3.1.4. Section 11 of the Citizenship Act. No. 18 of 1948 (if applicable)\r\n3.1.5. Dual Citizenship Certificate (if applicable)\r\n3.1.6. Letter from the employer (if applicable)\r\n3.1.7. Marriage certificate (if applicable)\r\n\r\n\r\n4. General Instructions\r\n\r\n4.1. Please log on to the following URL\r\nhttps://www.immigration.gov.lk/\r\n4.2. The application must be completed in English.\r\n4.3. The urgent service passport applicants will be facilitated to receive passports by courier service\r\nand the normal service applicants will be facilitated to receive passports by registered post.\r\n4.4. The courier charges/postal charges will be borne by the department.\r\n4.5. If you have already obtained a photo acknowledgement receipt, Please enter the photo\r\nacknowledgement number.\r\n4.6. All required documents must be in JPEG format and file size has to be under 5 MB in order to\r\nupload.\r\n\r\n\r\n5. Instruction to report the Service Location\r\n\r\n5.1. You are requested to select one of the service locations,\r\n5.1.1. Head office of the Department\r\n5.1.2. Regional offices of the Department\r\n5.1.3. Department for Registration of Persons unit established in Divisional Secretariat (DS – DRP)\r\n5.2. You are requested to submit followings at the selected service location,\r\n5.2.1. Finger print of the Applicant (Mandatory requirement)\r\n5.2.2. Signature of the Applicant (Mandatory requirement)\r\n5.2.3. Biometric enabled photograph of the applicant (Optional)\r\n5.3. At the time of submission of application is done, an ‘online passport appointment notice’ will be\r\nsent to your device and simultaneously a SMS will be served to your registered mobile phone\r\nnumber with us, notifying the application reference number.\r\n5.4. Upon your payment is successfully made, you will receive a SMS.\r\n5.5. Immediately after your application is accepted by the department, you will receive a SMS. You are\r\nstrongly advised not to report the service location until you receive the SMS.\r\n5.6. When you are reporting to the DS-DRP location you must keep ready to produce followings;\r\n5.6.1. Online passport appointment notice’ (Mandatory)\r\n5.6.2. Birth Certificate (Mandatory)\r\n5.6.3. National Identity Card (Mandatory)\r\n5.6.4. Current Passport (If Applicable)\r\n\r\n\r\n6. Conditions to Apply\r\n\r\n6.1. You must be a citizen of Sri Lanka and currently reside within the country in order to apply for a Sri\r\nLankan passport.\r\n6.2. You need to have a valid mobile number to register for the passport application process and a valid\r\nemail address is required to apply for a passport.\r\n6.3. If you are applying for a passport for the first time you should produce originals of following\r\ndocuments\r\n6.3.1. Birth Certificate\r\n6.3.2. National Identity Card\r\n6.4. If you already possess a valid Sri Lankan passport then you should have your current passport with\r\nyou when you are reporting to the Head office, Regional office or DS-DRP units for finger print\r\ncapturing.\r\n6.5. In case, if you have lost your active passport. It is a mandatory requirement to report to the Head\r\noffice / Regional office of the Department of Immigration and Emigration to process your new\r\npassport application.\r\n6.6. If you are above 60 years old and have already obtained a photo acknowledgement receipt, you\r\nmay download the signature template and upload the signature specimen directly without visiting\r\nthe Head office, Regional office or DS-DRP.\r\n6.7. If you are in between the age of 15 to 60, you need to visit the Head office, selected Regional office\r\nor DS-DRP in order to capture the photograph and provide your signature and finger prints.\r\n\r\n\r\n7. Payments\r\n\r\n7.1. Fee for the Urgent service is 15,000 LKR and the Normal service is 10,000 LKR.\r\n7.2. If you intend to obtain the passport photograph at DS-DRP units, the applicable fee for the same is\r\n350 LKR.\r\n7.3. If you want to make online payments, you should possess a valid credit/debit card or else you have\r\nthe option to choose the ‘Pay at bank’.\r\n7.4. If you select the ‘Pay at Bank’ option, first you have to download the receipt and then print it prior\r\nto visit the nearest Bank of Ceylon (BOC) branch to make the payment.\r\n7.5. All fees incurred during the application process are non-refundable.");
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textArea.setEditable(false);
		textArea.setBounds(10, 41, 586, 290);
		msgPanel.add(textArea);

		JButton instrucBackBtn = new JButton("Back");
		instrucBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(mainPanel);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		instrucBackBtn.setBounds(507, 337, 89, 23);
		msgPanel.add(instrucBackBtn);

	}
}
