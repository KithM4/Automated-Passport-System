package pas.common;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePDF {

	public static void main(String[] args) {

		try {
			String file_name = "C:\\Users\\hasal\\Desktop\\PAS_ADMIN\\Applications\\application.pdf";
			Document doc = new Document(PageSize.A4);
			PdfWriter.getInstance(doc, new FileOutputStream(file_name));
			doc.open();

			// title
			doc.add(new Phrase("Passport Application Data"));

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);

			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PAS", "root", "1234");
					PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Application_Form");
					ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					// Add header in the first column
					// Add data in the second column
					table.addCell(new PdfPCell(new Phrase("Application ID")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("application_id"))));

					table.addCell(new PdfPCell(new Phrase("Service Type - Normal")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("Service_normal"))));

					table.addCell(new PdfPCell(new Phrase("Service Type - One Day")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("Service_oneDay"))));

					table.addCell(new PdfPCell(new Phrase("DocType_AllCountry")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("DocType_allCountries"))));

					table.addCell(new PdfPCell(new Phrase("DocType_MiddleEast")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("DocType_middlEast"))));

					table.addCell(new PdfPCell(new Phrase("DocType_Emergency")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("DocType_emergency"))));

					table.addCell(new PdfPCell(new Phrase("DocType_Identity")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("DocType_identity"))));

					table.addCell(new PdfPCell(new Phrase("Travel Doc. No.")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("TravelDoc_No"))));

					table.addCell(new PdfPCell(new Phrase("NMRP No")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("NMRP_No"))));

					table.addCell(new PdfPCell(new Phrase("NIC")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("nic"))));

					table.addCell(new PdfPCell(new Phrase("Surname")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("surname"))));

					table.addCell(new PdfPCell(new Phrase("Other Names")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("otherNames"))));

					table.addCell(new PdfPCell(new Phrase("Address")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("address"))));

					table.addCell(new PdfPCell(new Phrase("City")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("city"))));

					table.addCell(new PdfPCell(new Phrase("District")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("district"))));

					table.addCell(new PdfPCell(new Phrase("Date of Birth")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("dob"))));

					table.addCell(new PdfPCell(new Phrase("Birth Certificate No.")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("birth_No"))));

					table.addCell(new PdfPCell(new Phrase("Male")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("sex_male"))));

					table.addCell(new PdfPCell(new Phrase("Female")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("sex_female"))));

					table.addCell(new PdfPCell(new Phrase("Occupation")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("job"))));

					table.addCell(new PdfPCell(new Phrase("Dual Citizen(YES)")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("citizen_Yes"))));

					table.addCell(new PdfPCell(new Phrase("Dual Citizen(NO)")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("citizen_No"))));

					table.addCell(new PdfPCell(new Phrase("Dual Citizen Number")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("citizen_Number"))));

					table.addCell(new PdfPCell(new Phrase("Telephone No.")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("mobile"))));

					table.addCell(new PdfPCell(new Phrase("Email")));
					table.addCell(new PdfPCell(new Phrase(resultSet.getString("mail"))));
				}
			}

			doc.add(table);

			doc.close();

			System.out.println("PDF generated successfully!");

		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
