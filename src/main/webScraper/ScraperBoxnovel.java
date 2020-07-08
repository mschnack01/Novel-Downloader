package main.webScraper;


import main.createPDF.CreatePDF;
import main.pdfData.PdfBoxnovel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ScraperBoxnovel {

    PdfBoxnovel pdfBoxnovel;
    CreatePDF createPDF;

    public ScraperBoxnovel(PdfBoxnovel pdfBoxnovel, CreatePDF createPDF) {
        this.pdfBoxnovel = pdfBoxnovel;
        this.createPDF = createPDF;
    }

    public void getNovel() {

        int pageCheck = 2;
        for (int page = 1; page <= pageCheck; page++) {

            int i = 0;
            String check = "BoxNovel";

            pdfBoxnovel.getNovel().clear();

            String novelName = "shuras-wrath";

            Document doc = null;
            try {
                doc = Jsoup.connect("https://boxnovel.com/novel/" + novelName + "/chapter-" + page).userAgent("mozilla/17.0").get();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Elements temp = doc.select("p");

            Elements test = doc.getElementsByClass("nav-next");

            if (test.size() == 2){
                pageCheck++;


                for ( Element novelText : temp) {

                    pdfBoxnovel.novel.add(novelText.getElementsByTag("p").first().text().replace("&lt;", "<").replace("&gt;", ">"));
                        pdfBoxnovel.allChapters.add(pdfBoxnovel.novel.get(i));
                        i++;
                    if (novelText.toString().contains(check)){
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
    }
}
