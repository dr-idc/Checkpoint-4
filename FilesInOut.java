import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FilesInOut {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java FilesInOut inputFile outputFile");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            boolean newSentence = true;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                StringBuilder sb = new StringBuilder();
                for (String word : words) {
                    if (word.isEmpty()) {
                        continue;
                    }
                    char firstChar = word.charAt(0);
                    if (newSentence && Character.isLowerCase(firstChar)) {
                        firstChar = Character.toUpperCase(firstChar);
                    }
                    sb.append(firstChar).append(word.substring(1)).append(" ");
                    if (word.matches(".*[.?!]$")) {
                        newSentence = true;
                    } else {
                        newSentence = false;
                    }
                }
                writer.println(sb.toString().trim());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}