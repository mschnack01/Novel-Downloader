package main.pdfData;

import java.util.ArrayList;

public class PdfBoxnovel {

    public ArrayList<String> novel = new ArrayList<>();
    public ArrayList<String> allChapters = new ArrayList<>();

    String Novel;

    public PdfBoxnovel(ArrayList<String> novel, ArrayList<String> allChapters ) {
        novel = new ArrayList<>();
        allChapters = new ArrayList<>();
    }

    public PdfBoxnovel(String novel) {
        Novel = novel;
    }

    public PdfBoxnovel() {
    }

    public void setNovel(String novel) {
        Novel = novel;
    }

    public ArrayList<String> getNovel() {
        return novel;
    }

    public void setNovel(ArrayList<String> novel) {
        this.novel = novel;
    }

    public ArrayList<String> getAllChapters() {
        return allChapters;
    }

    public void setAllChapters(ArrayList<String> allChapters) {
        this.allChapters = allChapters;
    }
}
