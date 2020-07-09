package main.webScraper;


import main.createPDF.CreatePDF;
import main.pdfData.PdfBoxnovel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ScraperBoxnovel {

    PdfBoxnovel pdfBoxnovel;
    CreatePDF createPDF;

    public ScraperBoxnovel(PdfBoxnovel pdfBoxnovel, CreatePDF createPDF) {
        this.pdfBoxnovel = pdfBoxnovel;
        this.createPDF = createPDF;
    }

    public static final String[] websites = { "boxnovel.com", "boxnovel.net" };

    public void getNovel() {



String checkWebsite = "boxnovel.com";

        JFrame frame = new JFrame("Choose a website");
        String novelWebsite = (String) JOptionPane.showInputDialog(frame,
                "From what website do you wish to download the from?",
                "Choose a website",
                JOptionPane.QUESTION_MESSAGE,
                null,
                websites,
                websites[0]);

        String novelName = JOptionPane.showInputDialog("Novel Name - Make sure to put '-' between each word: 'shuras-wrath'");


        if (novelWebsite.equals(checkWebsite)) {

            int pageCheck = 2;
            for (int page = 1; page <= pageCheck; page++) {

                int i = 0;
                String check = "BoxNovel";

                pdfBoxnovel.getNovel().clear();

                Document doc = null;
                try {
                    doc = Jsoup.connect("https://boxnovel.com/novel/" + novelName + "/chapter-" + page).userAgent("mozilla/17.0").get();
                } catch (IOException e) {
                    e.printStackTrace();
                    String errorMessage1 = "Could not find novel, try again";
                    JOptionPane.showMessageDialog(null, errorMessage1);
                    getNovel();
                }


                Elements temp = doc.select("p");

                Elements test = doc.getElementsByClass("nav-next");


                if (test.size() == 2) {
                    pageCheck++;


                    for (Element novelText : temp) {

                        pdfBoxnovel.novel.add(novelText.getElementsByTag("p").first().text().replace("&lt;", "<").replace("&gt;", ">"));
                        pdfBoxnovel.allChapters.add(pdfBoxnovel.novel.get(i));
                        i++;
                        if (novelText.toString().contains(check)) {
                            pdfBoxnovel.allChapters.add("\n\n\n\n\n");
                            break;
                        }
                    }

                    System.out.println("Chapter " + page);

                } else {
                    pageCheck = 0;
                }

                createPDF.Test(novelName);
            }
        } else {
            getNovel();
            String errorMessage2 = "The chosen website is not supported yet";
            JOptionPane.showMessageDialog(null, errorMessage2);
        }
    }
}
