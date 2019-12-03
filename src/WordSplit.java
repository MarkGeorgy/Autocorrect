import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WordSplit {

    static private Map<String,List<String>> wordsChecked = new HashMap<>();

    public List<String> wordSplit(String s, RedBlackBST<String> dictionary) {
        return wordSplitUtility(s, dictionary);
    }

    private List<String> wordSplitUtility(String word, RedBlackBST<String> dictionary) {
        List<String> result = new ArrayList<>();
        if(wordsChecked.containsKey(word)){
            return wordsChecked.get(word);
        }
        else if (dictionary.contains(word)) {
            result.add(word);
            return result;
        }
        else {
            for (int i = 1; i <= word.length(); i++) {
                String firstPart = word.substring(0, i);
                if (dictionary.contains(firstPart)) {
                    List<String> splitWordList = wordSplitUtility(word.substring(i), dictionary);
                    result.add(firstPart);
                    removeExtra(result);
                    for (String splitWords : splitWordList) {
                        result.add(firstPart + " " + splitWords);
                        removeExtra(result);
                    }
                }

                else {
                    String secondPart = word.substring(i);
                    if (dictionary.contains(secondPart)) {
                        result.add(secondPart);
                        removeExtra(result);
                    }
                }
            }
            wordsChecked.put(word, result);
            return result;
        }
    }

    private void removeExtra (List<String> result) {
        for(int j =0; j<result.size(); j++){
            for(int k=j+1; k<result.size(); k++){
                if(result.get(j).length()>result.get(k).length())
                    result.remove(k);
                else
                    result.remove(j);
            }
        }
    }
}

