package main.controllers;

import main.createPDF.CreatePDF;
import main.pdfData.PdfBoxnovel;
import main.webScraper.ScraperBoxnovel;

import java.io.IOException;

public class MainController {

    private PdfBoxnovel pdfBoxnovel;
    private ScraperBoxnovel scraperBoxnovel;
    private CreatePDF createPDF;

    public MainController() {
        this.pdfBoxnovel = new PdfBoxnovel();
        createPDF = new CreatePDF(pdfBoxnovel);
        scraperBoxnovel = new ScraperBoxnovel(pdfBoxnovel, createPDF);
    }

    public void run() {
        scraperBoxnovel.getNovel();

    }
}
