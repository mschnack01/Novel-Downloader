package main.createPDF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import main.pdfData.PdfBoxnovel;

public class CreatePDF {

    PdfBoxnovel pdfBoxnovel;

    public CreatePDF( PdfBoxnovel pdfBoxnovel) {
        this.pdfBoxnovel = pdfBoxnovel;
    }

    public void Test1(String novelName) {

            Document document = new Document();

            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(novelName + ".pdf"));
                document.open();
                for (int i = 0; i < pdfBoxnovel.getAllChapters().size(); i++) {
                    document.add(new Paragraph(pdfBoxnovel.allChapters.get(i) + "\n\n"));
                }
                document.close();
                writer.close();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

    }

    public void Test(String novelName){

        Document document = new Document();


        try
        {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(novelName + ".pdf"));
            document.open();

            //Create chapter and sections
            for (int i = 0; i < pdfBoxnovel.getAllChapters().size(); i++) {

                if (pdfBoxnovel.allChapters.get(i).contains("Chapter - ")){
                    int a = i++;
                    int chapter = 1;
                    int depth = 0;
                    Paragraph chapterTitle = new Paragraph(pdfBoxnovel.allChapters.get(a) + "\n\n\n\n");
                    Chapter chapter1 = new Chapter(chapterTitle, chapter);
                    chapter1.setNumberDepth(depth);
                    chapter++;
                    depth++;
                    document.add(chapter1);

                } else {
                    document.add(new Paragraph(pdfBoxnovel.allChapters.get(i) + "\n\n"));
                }
            }
            document.close();
            writer.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

