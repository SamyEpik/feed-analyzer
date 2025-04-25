public class AnalyzedText implements Comparable<AnalyzedText> {
    private String text;
    private double sentiment;

    AnalyzedText(String text) {
        this.text = text;
        this.sentiment = Utility.analyzeText(text);
    }

    public String getText() {
        return text;
    }

    public double getSentiment() {
        return sentiment;
    }

    @Override
    public int compareTo(AnalyzedText o) {
        return Double.compare(this.sentiment, o.sentiment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof AnalyzedText)) return false;
        AnalyzedText a = (AnalyzedText) o;
        if (this.sentiment != a.getSentiment()) return false;
        return text != null ? this.getText().equals(a.getText()) : a.getText() == null;
    }

    @Override
    public String toString() {
        return text + " " + sentiment;
    }

    @Override
    public int hashCode() {
        return (getText().hashCode() + Double.hashCode(getSentiment())) * 31;
    }
}
