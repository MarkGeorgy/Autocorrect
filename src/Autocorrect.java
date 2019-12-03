import java.util.*;


public class Autocorrect  {

    public String correct(String word, RedBlackBST<String> dictionary) {

        if (dictionary.get(word)!=-1) {
            return word;
        }

        List<String> closeEdits = wordEdit(word);
        for (String closeEdit: closeEdits) {
            if (dictionary.contains(closeEdit)) {
                if(closeEdit.length()==word.length())
                    return closeEdit;
            }
        }

        List<String> extraEdits = new ArrayList<>();
        for(String closeEdit: closeEdits) {
            extraEdits.addAll(wordEdit(closeEdit));
        }

        for (String extraEdit: extraEdits) {
            if (dictionary.contains(extraEdit)) {
                if(extraEdit.length()==word.length())
                    return extraEdit;
            }
        }

        return word;
    }


    private List<String> wordEdit(String word) {
        List<String> closeWords = new ArrayList<String>();

        for (int i = 0; i < word.length() + 1; i++) {
            for (char character = 'a'; character <= 'z'; character++) {
                //Add Letters
                StringBuilder sb = new StringBuilder(word);
                sb.insert(i, character);
                closeWords.add(sb.toString());
            }
        }

        for (int i = 0; i < word.length(); i++) {
            for (char character = 'a'; character <= 'z'; character++) {
                //Replace Letters
                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(i, character);
                closeWords.add(sb.toString());

                //Remove Letters
                sb = new StringBuilder(word);
                sb.deleteCharAt(i);
                closeWords.add(sb.toString());
            }
        }

        return closeWords;
    }


}