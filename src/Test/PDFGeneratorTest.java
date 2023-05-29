package Test;

import static org.junit.jupiter.api.Assertions.*;

import BE.Project;
import GUI.Util.PDFGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PDFGeneratorTest {

    @Test
    @DisplayName("testGeneratePDF")
    public void testGeneratePDF() {
        // Define test data
        String filePath = "junitTest.pdf";
        Project project = new Project();
        project.setProjectid(1);
        project.setName("Test Project");
        project.setDate(LocalDate.now());
        project.setDescription("Test description");
        project.setTechniciansIds(Arrays.asList(1, 2, 3));
        project.setCustomerid(456);

        // Generate the PDF
        PDFGenerator.generatePDF(filePath, project);

        // Verify if the PDF file is created
        File file = new File(filePath);
        assertTrue(file.exists());

        // Clean up - delete the generated PDF file
        file.delete();
    }
}
