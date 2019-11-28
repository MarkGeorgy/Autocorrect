import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class WordBreak {

    public List<String> wordBreak(String s, RedBlackBST wordDict) {
        Map<String, List<String>> backTrack = new HashMap<>();
        return wordBreakUtility(s, wordDict, backTrack);
    }

    private List<String> wordBreakUtility(String s, RedBlackBST wordDict, Map<String, List<String>> backTrack) {

        if (backTrack.containsKey(s)) {
            return backTrack.get(s);
        }

        List<String> result = new ArrayList<>();
        if (wordDict.contains(s)) {
            if (!(result.contains(s)))
                result.add(s);
            return result;
        }
        else {
            for (int i = 1; i <= s.length(); i++) {
                String prefix = s.substring(0, i);
                if (wordDict.contains(prefix)) {
                    List<String> returnStringsList = wordBreakUtility(s.substring(i), wordDict, backTrack);
                    if (!(result.contains(prefix)))
                        result.add(prefix);

                    result=removeExtra(result);

                    for (String returnString : returnStringsList) {
                        if (!(result.contains(returnString)))
                            result.add(prefix + " " + returnString);
                        result=removeExtra(result);
                    }

                }
                else {
                    String pre = s.substring(i);
                    if (wordDict.contains(pre)) {
                        if (!(result.contains(pre)))
                            result.add(pre);
                        result =removeExtra(result);
                    }
                }
            }
            backTrack.put(s, result);
            return result;
        }
    }

    private List<String> removeExtra (List<String> result) {
        for(int j =0; j<result.size(); j++){
            for(int k=j+1; k<result.size(); k++){
                if(result.get(j).length()>result.get(k).length())
                    result.remove(k);
                else
                    result.remove(j);
            }
        }
        return result;
    }
}

