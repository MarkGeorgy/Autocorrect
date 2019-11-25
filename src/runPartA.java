import java.io.*;
import java.util.Arrays;


public class runPartA {

    public static void main(String[] args) throws IOException {
        RedBlackBST dictionaryTree = new RedBlackBST();
        MergeSort mergeSort = new MergeSort();
        File file1 = new File(("dictionary.txt"));
        File filesClean;
        String text = "";

        BufferedReader br = new BufferedReader(new FileReader(file1));
        String line = br.readLine().toLowerCase();
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            dictionaryTree.put(words[i], 0);
        }
        for (int i = 0; i < 1; i++) {
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

        String[] output = dictionaryTree.printBST(dictionaryTree.getRoot()).split(" ");
        FrequencyPerWord[] frequencyPerWords = new FrequencyPerWord[output.length];
        for (int i = 0; i < output.length; i++) {
            String[] temp = output[i].split(",");
            frequencyPerWords[i] = new FrequencyPerWord(temp[0], Integer.valueOf(temp[1]));
        }
        mergeSort.sort(frequencyPerWords);
        //Arrays.sort(frequencyPerWords);

        PrintWriter outFrequencies = new PrintWriter("PartA-Frequencies.csv");
        PrintWriter outRepeated = new PrintWriter("PartA-Repeated.txt");
        for(int i=0; i<frequencyPerWords.length;i++){
            String printFrequencyPerWord = frequencyPerWords[i].getWord()+ "," + frequencyPerWords[i].getFrequency();
            outFrequencies.println(printFrequencyPerWord);
        }

        for(int i=0; i<frequencyPerWords.length;i++){
            for (int j=0; j<frequencyPerWords[i].getFrequency(); j++) {
                String repeatedOutput = frequencyPerWords[i].getWord() + " ";
                outRepeated.print(repeatedOutput);
            }
        }
        outFrequencies.close();
        outRepeated.close();
    }
}

