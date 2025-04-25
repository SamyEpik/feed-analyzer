import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Utility {
    public static double analyzeText(String text) {
        String path = Paths.get("resources").toString() + "/";
        Set<String> stopWords = new HashSet<>();
        Map<String, Double> vaderLexicon = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path + "SmartStoplist.txt"))) {
            String line = reader.readLine();
            reader.readLine(); // skip to second
            while (line != null) {
                stopWords.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(path + "vader_lexicon.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.trim().split("\t");
                vaderLexicon.put(parts[0], Double.parseDouble(parts[1]));
                line = reader.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        for (String stopWord : stopWords) {
            vaderLexicon.remove(stopWord);
        }
        String[] words = text.split(" ");
        double totalValue = 0.0;
        for (String word : words) {
            totalValue += vaderLexicon.getOrDefault(word, 0.0);
        }
        return totalValue;
    }
}
