package GUI.Util;

import BE.Project;
import com.itextpdf.io.exceptions.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PDFGenerator {

    public static void generatePDF(String folderPath, String fileName, Project project) {
        String filePath = folderPath + File.separator + fileName;

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4); // Set page size to A4
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                float margin = 50; // Left margin
                float startY = page.getMediaBox().getHeight() - margin;
                float leading = 20; // Line spacing

                File imageFile = new File("src/GUI/Images/WUAVlogo.png");
                PDImageXObject image = PDImageXObject.createFromFileByExtension(imageFile, document);

                contentStream.drawImage(image, 50, 50, 170, 100);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, startY);
                contentStream.showText("Project ID: " + project.getProjectid());
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText("Name: " + project.getName());
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText("Date: " + project.getDate().toString());
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText("Description: ");

                // Split the description text into multiple lines
                String description = project.getDescription();
                List<String> descriptionLines = splitTextToLines(description, 80); // Adjust the line length as needed

                for (String line : descriptionLines) {
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -leading);
                }

                contentStream.showText("Technicians ID: " + project.getTechniciansIds());
                contentStream.newLineAtOffset(0, -leading);
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

    private static List<String> splitTextToLines(String text, int lineLength) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : text.split("\\s+")) {
            if (currentLine.length() + word.length() > lineLength) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder();
            }

            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }

            currentLine.append(word);
        }

        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines;
    }
}
