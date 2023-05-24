package GUI.Util;

import BE.Project;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PDFGenerator {

    Document document;

    Project project;

    String path = "pdfFiles/project.pdf";

    List<File> files;

    public PDFGenerator(Project project, List<File> files) {
        this.project = project;
        this.files = files;
    }

    public void createDocument() {
        try {

            PdfWriter pdfWriter = new PdfWriter(path);
            File file = new File(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            document = new Document(pdfDocument);

            if (!files.isEmpty()) {
                //createImages();
            }
            document.close();


        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception("Kunne ikke printe dokumentation", e));
        }
    }
}
