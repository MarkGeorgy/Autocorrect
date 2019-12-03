import java.io.*;
import java.util.Arrays;
import java.util.List;



public class RunPartB {

    public static void main(String[] args) throws IOException {

        //Initializing
        RedBlackBST<String> dictionaryTree = new RedBlackBST<>();
        File dictionaryFile = new File(("dictionary.txt")); // Use this to read the given dictionary
        //File dictionaryFile = new File(("files for demo/dictionary.txt")); //Use this to read Demo dictionary
        File spacedRemovedFiles;
        String text = "";
        WordSplit removeSpace = new WordSplit();
        String correctedOutput = "";

        //Reading Files and Splitting Texts
        BufferedReader br = new BufferedReader(new FileReader(dictionaryFile));
        String line = br.readLine().toLowerCase();
        String[] words = line.split(" ");
        for (String word : words) {
            dictionaryTree.put(word, 0);
        }

        //Use this to read demo files
//        spacedRemovedFiles = new File("files for demo/500 Words/spaces_removed_file.txt");
//        br = new BufferedReader(new FileReader(spacedRemovedFiles));
//        while ((line = br.readLine()) != null) {
//            text = text + " " + line.toLowerCase();
//        }


            //Reading spaces removed text files or comment this part to read demo files
            for (int i = 0; i < 20; i++) {//Loop to choose number of files to run
                String temp = i + ".txt";
                spacedRemovedFiles = new File("Part B - Removed Spaces/" + temp);
                br = new BufferedReader(new FileReader(spacedRemovedFiles));
                while ((line = br.readLine()) != null) {
                    text = text + " " + line.toLowerCase();
                }
            }

        String[] spaceRemovedText = text.split("[:?;.,\"() _-]");
        //String[] spaceRemovedText = text.split("[;.,\"() _':]"); //Use this Split to read Demo files

        //Checking the words and correcting them
        for (String s : spaceRemovedText) {
            if (s.equals("")) {
                continue;
            }
            List<String> removedSpace = removeSpace.wordSplit(s, dictionaryTree);
            if (!(removedSpace.isEmpty())) {
                String[] increment = removedSpace.get(0).split(" ");
                for (String value : increment) {
                    dictionaryTree.get(value);
                }
                for (int j = 0; j < removedSpace.size(); j++) {
                    if (!(s.equals(removedSpace.get(j)))) {
                        correctedOutput = correctedOutput + s + "//" + removedSpace + "\n";
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

        //Sorting
        Arrays.sort(repeat);
        Arrays.sort(frequencyPerWords);
        Arrays.sort(correctedWords);

        PrintWriter outFrequencies = new PrintWriter("PartB-Frequencies.csv");
        PrintWriter outRepeated = new PrintWriter("PartB-Repeated.txt");
        PrintWriter outCorrected = new PrintWriter("PartB-Corrected.csv");

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



