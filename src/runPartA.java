import java.io.*;
import java.util.Arrays;


public class runPartA {


    public static void main(String[] args) throws IOException {
        RedBlackBST dictionaryTree = new RedBlackBST();
        File file1 = new File(("dictionary.txt"));
        File filesClean;
        String text = "";

        BufferedReader br = new BufferedReader(new FileReader(file1));
        String line = br.readLine().toLowerCase();
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            dictionaryTree.put(words[i], 0);
        }
        for (int i = 0; i < 20; i++) {
            String temp = i + ".txt";
            filesClean = new File("Part A - Clean/" + temp);
            br = new BufferedReader(new FileReader(filesClean));
            while ((line = br.readLine()) != null) {
                text = text + " " + line.toLowerCase();
            }
        }

        String[] cleanText = text.split("[;.,\"() _-]");
        for (int i = 0; i < cleanText.length; i++) {
            if (cleanText[i].equals("")) {
                continue;
            }
            dictionaryTree.get(cleanText[i]);
        }

        //Adding to frequencyPerWords data type
        String[] output = dictionaryTree.printBST(dictionaryTree.getRoot()).split(" ");
        FrequencyPerWord[] frequencyPerWords = new FrequencyPerWord[output.length];
        for (int i = 0; i < output.length; i++) {
            String[] temp = output[i].split(",");
            frequencyPerWords[i] = new FrequencyPerWord(temp[0], Integer.valueOf(temp[1]));
        }

        //Adding to repeated data type
        String repeated = "";
        for (int i = 0; i < frequencyPerWords.length; i++) {
            if (frequencyPerWords[i].getFrequency() != 0) {
                for (int j = 0; j < frequencyPerWords[i].getFrequency(); j++)
                    repeated = repeated + frequencyPerWords[i].getWord() + "///";
            }
        }
        String[] repeatedWords = repeated.split("///");
        Repeat[] repeat = new Repeat[repeatedWords.length];
        for (int i = 0; i < repeat.length; i++) {
            repeat[i] = new Repeat(repeatedWords[i]);
        }

        Arrays.sort(repeat);
        Arrays.sort(frequencyPerWords);

        PrintWriter outFrequencies = new PrintWriter("PartA-Frequencies.csv");
        PrintWriter outRepeated = new PrintWriter("PartA-Repeated.txt");
        for (int i = 0; i < frequencyPerWords.length; i++) {
            if (frequencyPerWords[i].getFrequency() != 0) {
                String printFrequencyPerWord = frequencyPerWords[i].getWord() + "," + frequencyPerWords[i].getFrequency();
                outFrequencies.println(printFrequencyPerWord);
            }
        }

        for (int i = 0; i < repeat.length; i++) {
            String printRepeated = repeat[i].getRepeatedWords() + " ";
            outRepeated.print(printRepeated);
        }
        outFrequencies.close();
        outRepeated.close();

    }
}

