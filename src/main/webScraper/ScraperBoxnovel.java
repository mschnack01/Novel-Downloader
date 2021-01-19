package main.webScraper;


import main.createPDF.CreatePDF;
import main.pdfData.PdfBoxnovel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;

public class ScraperBoxnovel {

    PdfBoxnovel pdfBoxnovel;
    CreatePDF createPDF;

    public ScraperBoxnovel(PdfBoxnovel pdfBoxnovel, CreatePDF createPDF) {
        this.pdfBoxnovel = pdfBoxnovel;
        this.createPDF = createPDF;
    }

    public static final String[] websites = {"boxnovel.com", "boxnovel.net", "wuxiaworld.com"};

    public void getNovel() {

        int page;
        int pageCheck = 2;

        String checkWebsite1 = "boxnovel.com";
        String checkWebsite2 = "wuxiaworld.com";

        JFrame frame1 = new JFrame("Choose a website");
        String novelWebsite = (String) JOptionPane.showInputDialog(frame1,
                "From what website do you wish to download the from?",
                "Choose a website",
                JOptionPane.QUESTION_MESSAGE,
                null,
                websites,
                websites[0]);

        String novelName = JOptionPane.showInputDialog("Novel Name - Make sure to put '-' between each word: 'shuras-wrath'");

        if (novelWebsite.equals(checkWebsite1)) {


            for (page = 1; page <= pageCheck; page++) {

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

                int k = 1;

                if (temp.size() < 30) {

                    try {
                        doc = Jsoup.connect("https://boxnovel.com/novel/" + novelName + "/chapter-" + page + "_" + k).userAgent("mozilla/17.0").get();
                        temp = doc.select("p");

                        if (temp.size() < 30) {
                            try {
                                doc = Jsoup.connect("https://boxnovel.com/novel/" + novelName + "/chapter-" + page + "-" + "end").userAgent("mozilla/17.0").get();
                                temp = doc.select("p");

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                            if (temp.size() < 30) {
                                try {
                                    doc = Jsoup.connect("https://boxnovel.com/novel/" + novelName + "/chapter-" + page).userAgent("mozilla/17.0").get();
                                    temp = doc.select("p");
                                    String errorMessage2 = "Download Complete";
                                    JOptionPane.showMessageDialog(null, errorMessage2);
                                    System.exit(1);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }


                        } catch (IOException e) {
                        e.printStackTrace();

                        k++;

                    }
                    }

                    if (test.size() <= 2) {
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

                    } else if (test.size() == 0) {
                        for (Element novelText : temp) {

                            pdfBoxnovel.novel.add(novelText.getElementsByTag("p").first().text().replace("&lt;", "<").replace("&gt;", ">"));
                            pdfBoxnovel.allChapters.add(pdfBoxnovel.novel.get(i));
                            i++;
                            if (novelText.toString().contains(check)) {
                                pdfBoxnovel.allChapters.add("\n\n\n\n\n");

                            }
                        }

                        break;

                    }

                    createPDF.writePDF(novelName);

                }
                String errorMessage2 = "Download Complete";
                JOptionPane.showMessageDialog(null, errorMessage2);
                System.exit(1);

            }

            if (novelWebsite.equals(checkWebsite1)) {


                for (page = 1; page <= pageCheck; page++) {

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

                    int k = 1;

                    if (temp.size() < 30 && test.size() != 0) {

                        try {
                            doc = Jsoup.connect("https://boxnovel.com/novel/" + novelName + "/chapter-" + page + "_" + k).userAgent("mozilla/17.0").get();
                            temp = doc.select("p");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        k++;

                    } else if (temp.size() < 30) {
                        try {
                            doc = Jsoup.connect("https://boxnovel.com/novel/" + novelName + "/chapter-" + page + "-" + "end").userAgent("mozilla/17.0").get();
                            temp = doc.select("p");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (test.size() <= 2) {
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

                    } else if (test.size() == 0) {
                        for (Element novelText : temp) {

                            pdfBoxnovel.novel.add(novelText.getElementsByTag("p").first().text().replace("&lt;", "<").replace("&gt;", ">"));
                            pdfBoxnovel.allChapters.add(pdfBoxnovel.novel.get(i));
                            i++;
                            if (novelText.toString().contains(check)) {
                                pdfBoxnovel.allChapters.add("\n\n\n\n\n");

                            }
                        }

                        break;

                    }

                    if (temp.size() > 30) {
                        createPDF.writePDF(novelName);
                    } else {
                        break;
                    }
                }
                String errorMessage2 = "Download Complete";
                JOptionPane.showMessageDialog(null, errorMessage2);
                System.exit(1);

            }
        }
    }

