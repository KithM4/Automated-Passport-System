package pas.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;

public class Application_Form {
	
	private boolean Normal_Service;
	private boolean OneDay_Service;
	private boolean AllCountriesDoc;
	private boolean MiddleEastDoc;
	private boolean EmergencyDoc;
	private boolean IdentityDoc;
	private int TravelDoc_No;
	private int NMRP_No;
	private String nic;
	private String surname;
	private String otherName;
	private String address;
	private String city;
	private String district;
	private String dob;
	private int birth_no;
	private boolean male;
	private boolean female;
	private String job;
	private boolean citizen_yes;
	private boolean citizen_no;
	private int citizen_Num;
	private int mobile;
	private String mail;
	
	Test_Connection ob;
	Connection conn ;
	

		public Application_Form(boolean normal_Service, boolean oneDay_Service, boolean allCountriesDoc,
			boolean middleEastDoc, boolean emergencyDoc, boolean identityDoc, int tavelDocNum, int nmrp_No,
			int brthNo, int ctznNum, int mobile, String nic, String surname, String otherName, String address,
			String city, String district, String dob, String job, String mail, boolean male, boolean female,
			boolean citizen_yes, boolean citizen_no) {
		// TODO Auto-generated constructor stub
			
			this.Normal_Service = normal_Service;
			this.OneDay_Service = oneDay_Service;
			this.AllCountriesDoc = allCountriesDoc;
			this.MiddleEastDoc = middleEastDoc;
			this.EmergencyDoc = emergencyDoc;
			this.IdentityDoc = identityDoc;
			this.TravelDoc_No = tavelDocNum;
			this.NMRP_No =  nmrp_No;
			this.birth_no = brthNo;
			this.citizen_Num = ctznNum;
			this.mobile = mobile;
			this.nic = nic;
			this.surname = surname;
			this.otherName = otherName;
			this.address = address;
			this.city = city;
			this.district =district;
			this.dob = dob;
			this.job = job;
			this.mail = mail;
			this.male = male;
			this.female = female;
			this.citizen_yes = citizen_yes;
			this.citizen_no = citizen_no;
			
			
			ob = new Test_Connection();
			conn = ob.getConnection();
		
	}


		public void Submit_Application(boolean normal_Service, boolean oneDay_Service, boolean allCountriesDoc, boolean middleEastDoc, boolean emergencyDoc, boolean identityDoc, int tavelDocNum, int nmrp_No, int brthNo, int ctznNum, int mobile, String nic, String surname, String otherName, String address, String city, String district, String dob, String job, String mail, boolean male, boolean female, boolean citizen_yes, boolean citizen_no) {

		    try {
		        // Validate if any of the essential fields are empty
		        if (validateFields(nic, surname, otherName, address, city, district, dob, job, mail)) {
		            // If validation is successful, proceed with the insertion
		            String sql = "INSERT INTO Application_Form"
		                    + "(Service_normal, Service_oneDay, DocType_allCountries, DocType_middlEast, DocType_emergency, DocType_identity, TravelDoc_No, NMRP_No, nic, surname, otherNames, address,"
		                    + "city, district, dob, birth_No, sex_male, sex_female, job, citizen_Yes, citizen_No, citizen_Number, mobile, mail) "
		                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		            PreparedStatement statement = conn.prepareStatement(sql);

		            statement.setBoolean(1, Normal_Service);
		            statement.setBoolean(2, OneDay_Service);
		            statement.setBoolean(3, AllCountriesDoc);
		            statement.setBoolean(4, MiddleEastDoc);
		            statement.setBoolean(5, EmergencyDoc);
		            statement.setBoolean(6, IdentityDoc);
		            statement.setInt(7, TravelDoc_No);
		            statement.setInt(8, NMRP_No);
		            statement.setString(9, nic);
		            statement.setString(10, surname);
		            statement.setString(11, otherName);
		            statement.setString(12, address);
		            statement.setString(13, city);
		            statement.setString(14, district);
		            statement.setString(15, dob);
		            statement.setInt(16, birth_no);
		            statement.setBoolean(17, male);
		            statement.setBoolean(18, female);
		            statement.setString(19, job);
		            statement.setBoolean(20, citizen_yes);
		            statement.setBoolean(21, citizen_no);
		            statement.setInt(22, citizen_Num);
		            statement.setInt(23, mobile);
		            statement.setString(24, mail);

		            statement.executeUpdate();
		            System.out.println("Data inserted successfully.");
		        } else {
		            System.out.println("Error: One or more required fields are empty.");
		            // You can also throw an exception or handle this case according to your application's logic
		        }

		    } catch (SQLException e) {
		        System.out.println("Error: " + e.getMessage());
		        e.printStackTrace();
		    }
		}

		// Validate if any of the essential fields are empty
		private boolean validateFields(String... fields) {
		    for (String field : fields) {
		        if (field == null || field.trim().isEmpty()) {
		            return false;
		        }
		    }
		    return true;
		}

		
		
		

}



