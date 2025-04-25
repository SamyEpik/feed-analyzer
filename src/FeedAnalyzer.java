import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class FeedAnalyzer {
    public static void main(String[] args) {
        String account = "Potus";
        String path = Paths.get("resources/").toString() + "/";
        double totalSentiment = 0.0;
        Map<AnalyzedText, String> analyzedMessages = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path + "potus_tweets_2017_webarchive_publicaccess.csv"))) {
            String line = reader.readLine();
            while (line != null) {
                AnalyzedText analyzed = new AnalyzedText(line);
                analyzedMessages.put(analyzed,account);
                totalSentiment += analyzed.getSentiment();
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Total Sentiment: " + totalSentiment);
    }
}
