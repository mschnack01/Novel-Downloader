package main.pdfData;

public class NovelText {

    public String Heading;
    public String NText;

    public NovelText(String heading, String NText) {
        Heading = heading;
        this.NText = NText;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public String getNText() {
        return NText;
    }

    public void setNText(String NText) {
        this.NText = NText;
    }
}
