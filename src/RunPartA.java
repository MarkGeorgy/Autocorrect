

import java.io.*;
import java.util.Arrays;


public class RunPartA {

    public static void main(String[] args) throws IOException {

        //Initializing
        RedBlackBST<String> dictionaryTree = new RedBlackBST<String>();
        File dictionaryFile = new File(("dictionary.txt")); //Use this line to read given dictionary
        //File dictionaryFile = new File("files for demo/dictionary.txt");  //Use this to read Demo dictionary
        File cleanFiles;
        String text = "";

        //Reading dictionary file
        BufferedReader br = new BufferedReader(new FileReader(dictionaryFile));
        String line = br.readLine().toLowerCase();
        String[] words = line.split(" ");
        for (String word : words) {
            dictionaryTree.put(word, 0);
        }

        //Use this code to read Demo files
        //cleanFiles = new File("files for demo/500 Words/clean_file.txt");
        //br = new BufferedReader(new FileReader(cleanFiles));
        //while ((line = br.readLine()) != null) {
        //    text = text + " " + line.toLowerCase();
        //}

        //Reading clean text files or comment this part to read demo files
        for (int i = 0; i < 20; i++) { //Loop to choose number of files to run
            String temp = i + ".txt";
            cleanFiles = new File("Part A - Clean/" + temp);
            br = new BufferedReader(new FileReader(cleanFiles));
            while ((line = br.readLine()) != null) {
                text = text + " " + line.toLowerCase();
            }
        }

        String[] cleanText = text.split("[;.,\"() _-]"); // Use this split to read given files
        //String[] cleanText = text.split("[;.,\"() _':]"); // Use this split to read demo files

        for (String s : cleanText) {
            if (s.equals("")) {
                continue;
            }
            dictionaryTree.get(s);
        }

        //Adding to frequencyPerWords data type
        String[] output = dictionaryTree.printBST(dictionaryTree.getRoot()).split(" ");
        FrequencyPerWord[] frequencyPerWords = new FrequencyPerWord[output.length];
        for (int i = 0; i < output.length; i++) {
            String[] temp = output[i].split(",");
            frequencyPerWords[i] = new FrequencyPerWord(temp[0], Integer.parseInt(temp[1]));
        }

        //Adding to repeated data type
        String repeated = "";
        for (FrequencyPerWord frequencyPerWord : frequencyPerWords) {
            if (frequencyPerWord.getFrequency() != 0) {
                for (int j = 0; j < frequencyPerWord.getFrequency(); j++)
                    repeated = repeated + frequencyPerWord.getWord() + "///";
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


        //Printing to files
        PrintWriter outFrequencies = new PrintWriter("PartA-Frequencies.csv");
        PrintWriter outRepeated = new PrintWriter("PartA-Repeated.txt");
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

        outFrequencies.close();
        outRepeated.close();

    }
}

