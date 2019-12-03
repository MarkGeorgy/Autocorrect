import java.io.*;
import java.util.Arrays;

public class RunPartC {
    public static void main(String[] args) throws IOException {

        //Initializing
        RedBlackBST<String> dictionaryTree = new RedBlackBST<>();
        Autocorrect autocorrect = new Autocorrect();
        //File dictionaryFile = new File(("files for demo/dictionary.txt")); //Use this to read Demo dictionary
        File dictionaryFile = new File(("dictionary.txt")); // Use this to read given dictionary
        File mutatedFiles;
        String text = "";

        BufferedReader br = new BufferedReader(new FileReader(dictionaryFile));
        String line = br.readLine().toLowerCase();
        String[] words = line.split(" ");
        for (String word : words) {
            dictionaryTree.put(word, 0);
        }

        //Use this to read demo input files
//        mutatedFiles = new File("files for demo/500 Words/mutated_file_500.txt");
//        br = new BufferedReader(new FileReader(mutatedFiles));
//        while ((line = br.readLine()) != null) {
//            text = text + " " + line.toLowerCase();
//        }

        //Reading mutated text files or comment this part to read demo files
        for (int i = 0; i < 20; i++) {//Loop to choose number of files to run
            String temp = i + ".txt";
            mutatedFiles = new File("Part C - Mutated [BONUS]/" + temp);
            br = new BufferedReader(new FileReader(mutatedFiles));
            while ((line = br.readLine()) != null) {
                text = text + " " + line.toLowerCase();
            }
        }

        String output = "";
        String[] mutatedText = text.split("[.,\"() _-]");
        //String[] mutatedText = text.split("[.,\"() _':]");
        for (String s : mutatedText) {
            if (s.equals("")) {
                continue;
            }
            String temp = autocorrect.correct(s, dictionaryTree);
            dictionaryTree.get(temp);
            if (!(temp.equals(s)))
                output = output + s + "\t " + temp + "\n";
        }
        output.trim();
        //Printing to File
        String[] correctedText = output.split("\n");
        CorrectedWords[] correctedWords = new CorrectedWords[correctedText.length];
        for (int i = 0; i < correctedText.length; i++) {
            String[] temp = correctedText[i].split("\t");
            correctedWords[i] = new CorrectedWords(temp[0], temp[1]);
        }

        String[] output1 = dictionaryTree.printBST(dictionaryTree.getRoot()).split(" ");
        FrequencyPerWord[] frequencyPerWords = new FrequencyPerWord[output1.length];
        for (int i = 0; i < output1.length; i++) {
            String[] temp = output1[i].split(",");
            frequencyPerWords[i] = new FrequencyPerWord(temp[0], Integer.parseInt(temp[1]));
        }

        //Adding to repeated data type
        String repeated = "";
        for (FrequencyPerWord perWord : frequencyPerWords) {
            if (perWord.getFrequency() != 0) {
                for (int j = 0; j < perWord.getFrequency(); j++)
                    repeated = repeated + perWord.getWord() + "///";
            }
        }
        String[] repeatedWords = repeated.split("///");
        RepeatedWords[] repeat = new RepeatedWords[repeatedWords.length];
        for (int i = 0; i < repeat.length; i++) {
            repeat[i] = new RepeatedWords(repeatedWords[i]);
        }

        Arrays.sort(repeat);
        Arrays.sort(frequencyPerWords);
        Arrays.sort(correctedWords);

        PrintWriter outFrequencies = new PrintWriter("PartC-Frequencies.csv");
        PrintWriter outRepeated = new PrintWriter("PartC-Repeated.txt");
        PrintWriter outCorrected = new PrintWriter("PartC-Corrected.csv");

        for (FrequencyPerWord frequencyPerWord : frequencyPerWords) {
            if (frequencyPerWord.getFrequency() != 0) {
                String printFrequencyPerWord = frequencyPerWord.getWord() + "," + frequencyPerWord.getFrequency();
                outFrequencies.println(printFrequencyPerWord);
            }
        }

        for (RepeatedWords value : repeat) {
            String printRepeated = value.getRepeatedWords() + " ";
            outRepeated.print(printRepeated);
        }

        for (CorrectedWords correctedWord : correctedWords) {
            String printFrequencyPerWord = correctedWord.getWord() + "," + correctedWord.getCorrectedWord();
            outCorrected.println(printFrequencyPerWord);
        }
        outFrequencies.close();
        outRepeated.close();
        outCorrected.close();
    }
}
