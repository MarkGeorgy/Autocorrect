import java.util.ArrayList;
import java.util.List;


public class WordSplit {

    public List<String> wordSplit(String s, RedBlackBST<String> wordDict) {
        return wordSplitUtility(s, wordDict);
    }

    private List<String> wordSplitUtility(String s, RedBlackBST<String> wordDict) {

        List<String> result = new ArrayList<>();
        if (wordDict.contains(s)) {
            result.add(s);
            return result;
        }

        else {
            for (int i = 1; i <= s.length(); i++) {
                String firstPart = s.substring(0, i);
                if (wordDict.contains(firstPart)) {
                    List<String> splitWordList = wordSplitUtility(s.substring(i), wordDict);
                    if (!(result.contains(firstPart)))
                        result.add(firstPart);
                    removeExtra(result);
                    for (String splitWords : splitWordList) {
                        if (!(result.contains(splitWords)))
                            result.add(firstPart + " " + splitWords);
                        removeExtra(result);
                    }

                }

                else {
                    String secondPart = s.substring(i);
                    if (wordDict.contains(secondPart)) {
                        if (!(result.contains(secondPart)))
                            result.add(secondPart);
                        removeExtra(result);
                    }
                }
            }
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

