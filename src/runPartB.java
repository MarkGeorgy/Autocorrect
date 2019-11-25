import java.io.*;
import java.util.ArrayList;


public class runPartB {

    public static void main(String[] args) throws IOException {
        RedBlackBST dictionaryTree = new RedBlackBST();
        MergeSort mergeSort = new MergeSort();
        File file1 = new File(("dictionary.txt"));
        File filesRemovedSpaces;
        String text = "";
        WordBreak wb = new WordBreak();
        String correctedOutput = "";

        BufferedReader br = new BufferedReader(new FileReader(file1));
        String line = br.readLine().toLowerCase();
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            dictionaryTree.put(words[i], 0);
        }
        for (int i = 0; i <20; i++) {
            String temp = i + ".txt";
            filesRemovedSpaces = new File("Part B - Removed Spaces/" + temp);
            br = new BufferedReader(new FileReader(filesRemovedSpaces));
            while ((line = br.readLine()) != null) {
                text = text + " " + line.toLowerCase();
            }
        }

        String[] removedSpacesText = text.split("[;.,\"() _-]");
        for (int i = 0; i < removedSpacesText.length; i++) {
            if (removedSpacesText[i].equals("")) {
                continue;
            }
            ArrayList<String> temp = wb.wordBreak(removedSpacesText[i], dictionaryTree);
            if (!(temp.isEmpty())) {
                for (int j = 0; j < temp.size(); j++) {
                    if(!(removedSpacesText[i].equals(temp.get(j))))
                        correctedOutput = correctedOutput + removedSpacesText[i] + "," + temp.get(j) + " " + "\n";
                }
            }
        }

        String[] correctedText = correctedOutput.split("\n");
        CorrectedWords[] correctedWords = new CorrectedWords[correctedText.length];
        for (int i = 0; i < correctedText.length; i++) {
            String[] temp = correctedText[i].split(",");
            correctedWords[i] = new CorrectedWords(temp[0], temp[1]);
        }

        String[] output = dictionaryTree.printBST(dictionaryTree.getRoot()).split(" ");
        FrequencyPerWord[] frequencyPerWords = new FrequencyPerWord[output.length];
        for (int i = 0; i < output.length; i++) {
            String[] temp = output[i].split(",");
            frequencyPerWords[i] = new FrequencyPerWord(temp[0], Integer.valueOf(temp[1]));
        }

        mergeSort.sort(frequencyPerWords);
        mergeSort.sort(correctedWords);
        //Arrays.sort(frequencyPerWords);

        PrintWriter outFrequencies = new PrintWriter("PartB-Frequencies.csv");
        PrintWriter outRepeated = new PrintWriter("PartB-Repeated.txt");
        PrintWriter outCorrected = new PrintWriter("PartB-Corrected.csv");
        for (int i = 0; i < frequencyPerWords.length; i++) {
            String printFrequencyPerWord = frequencyPerWords[i].getWord() + "," + frequencyPerWords[i].getFrequency();
            outFrequencies.println(printFrequencyPerWord);
        }

        for (int i = 0; i < frequencyPerWords.length; i++) {
            for (int j = 0; j < frequencyPerWords[i].getFrequency(); j++) {
                String repeatedOutput = frequencyPerWords[i].getWord() + " ";
                outRepeated.print(repeatedOutput);
            }
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



