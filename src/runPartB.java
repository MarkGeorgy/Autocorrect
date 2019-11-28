import java.io.*;
import java.util.Arrays;
import java.util.List;


public class runPartB {
    public static void main(String[] args) throws IOException {
        RedBlackBST dictionaryTree = new RedBlackBST();
        File file1 = new File(("dictionary.txt"));
        File filesRemovedSpaces;
        String text = "";
        WordBreak wb = new WordBreak();
        String correctedOutput = "";

        //Reading Files and Splitting Texts
        BufferedReader br = new BufferedReader(new FileReader(file1));
        String line = br.readLine().toLowerCase();
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            dictionaryTree.put(words[i], 0);
        }
        for (int i = 0; i < 1; i++) {
            String temp = i + ".txt";
            filesRemovedSpaces = new File("Part B - Removed Spaces/" + temp);
            br = new BufferedReader(new FileReader(filesRemovedSpaces));
            while ((line = br.readLine()) != null) {
                text = text + " " + line.toLowerCase();
            }
        }

        String[] removedSpacesText = text.split("[:?;.,\"() _-]");

        //Checking the words and correcting them
        for (int i = 0; i < removedSpacesText.length; i++) {
            if (removedSpacesText[i].equals("")) {
                continue;
            }
            List<String> temp = wb.wordBreak(removedSpacesText[i], dictionaryTree);
            if (!(temp.isEmpty())) {
                String[] increment = temp.get(0).split(" ");
                for (int k = 0; k < increment.length; k++) {
                    dictionaryTree.get(increment[k]);
                }
                for (int j = 0; j < temp.size(); j++) {
                    if (!(removedSpacesText[i].equals(temp.get(j)))) {
                        correctedOutput = correctedOutput + removedSpacesText[i] + "//" + temp + "\n";
                    }
                }
            }
        }


        //Printing to File
        String[] correctedText = correctedOutput.split("\n");
        CorrectedWords[] correctedWords = new CorrectedWords[correctedText.length];
        for (int i = 0; i < correctedText.length; i++) {
            String[] temp = correctedText[i].split("//");
            correctedWords[i] = new CorrectedWords(temp[0], temp[1]);
        }

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
        //Arrays.sort(correctedWords);

        PrintWriter outFrequencies = new PrintWriter("PartB-Frequencies.csv");
        PrintWriter outRepeated = new PrintWriter("PartB-Repeated.txt");
        PrintWriter outCorrected = new PrintWriter("PartB-Corrected.csv");
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

        for (int i = 0; i < correctedWords.length; i++) {
            String printFrequencyPerWord = correctedWords[i].getWord() + "," + correctedWords[i].getCorrectedWord();
            outCorrected.println(printFrequencyPerWord);
        }
        outFrequencies.close();
        outRepeated.close();
        outCorrected.close();
    }
}



