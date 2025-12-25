package es.iessoterohernandez.daw.endes.HelloWorldPdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class HelloWorldPdf {
    public static void main(String[] args) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
            document.open();
            document.add(new Paragraph("Hola Mundo"));
            document.close();
            System.out.println("PDF generado correctamente.");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
