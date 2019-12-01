import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RunPartC {
    public static void main(String[] args) throws IOException {
        RedBlackBST<String> dictionaryTree = new RedBlackBST<>();
        SpellingCorrector spellingCorrector = new SpellingCorrector();

        File dictionaryFile = new File(("dictionary.txt"));
        File mutatedFiles;
        String text = "";

        BufferedReader br = new BufferedReader(new FileReader(dictionaryFile));
        String line = br.readLine().toLowerCase();
        String[] words = line.split(" ");
        for (String word : words) {
            dictionaryTree.put(word, 0);
        }
        for (int i = 0; i < 1; i++) {
            String temp = i + ".txt";
            mutatedFiles = new File("Part C - Mutated [BONUS]/" + temp);
            br = new BufferedReader(new FileReader(mutatedFiles));
            while ((line = br.readLine()) != null) {
                text = text + " " + line.toLowerCase();
            }
        }

        String output = "";
        String[] mutatedText = text.split("[.,\"() _-]");
        for (String s : mutatedText) {
            if (s.equals("")) {
                continue;
            }
            String temp = spellingCorrector.correct(s, dictionaryTree);
            if (!(temp.equals(s)))
                output = output + s + "\t " + temp + "\n";
        }
        System.out.println(output);
    }
}
