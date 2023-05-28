package GUI.Util;

import BE.Project;
import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFGenerator {

    public static void generatePDF(String filePath, Project project) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("Project ID: " + project.getProjectid());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Name: " + project.getName());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Date: " + project.getDate().toString());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Description: " + project.getDescription());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Technician ID: " + project.getTechniciansid());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Customer ID: " + project.getCustomerid());
                contentStream.endText();
            }

            document.save(filePath);
            System.out.println("PDF generated successfully!");
        } catch (IOException e) {
            System.out.println("Error generating PDF: " + e.getMessage());
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
