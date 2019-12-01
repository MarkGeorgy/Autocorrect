import java.util.*;
import java.util.stream.Stream;

public class SpellingCorrector  {

    public String correct(String word, RedBlackBST<String> dictionary) {
        if (word == null || word.trim().isEmpty()) {
            return word;
        }

        // If the word exists in our dictionary then return
        if (dictionary.contains(word)) {
            return word;
        }

        Map<String, Integer> possibleMatches = new HashMap<>();

        List<String> closeEdits = wordEdits(word);
        for (String closeEdit: closeEdits) {
            if (dictionary.contains(closeEdit)) {
                possibleMatches.put(closeEdit, dictionary.get(closeEdit));
            }
        }

        if (!possibleMatches.isEmpty()) {
            // Sorted least likely first
            Object[] matches = sortByValue(possibleMatches).keySet().toArray();

            // Try to match anything of the same length first
            String bestMatch = "";
            for(Object o: matches) {
                if (o.toString().length() == word.length()) {
                    bestMatch = o.toString();
                }
            }

            if (!bestMatch.trim().isEmpty()) {
                return bestMatch;
            }

            // Just return whatever is the best match
            return matches[matches.length - 1].toString();
        }

        // Ok we did't find anything, so lets run the edits function on the previous results and use those
        // this gives us results which are 2 characters away from whatever was entered
        List<String> furtherEdits = new ArrayList<>();
        for(String closeEdit: closeEdits) {
            furtherEdits.addAll(this.wordEdits(closeEdit));
        }

        for (String futherEdit: furtherEdits) {
            if (dictionary.contains(futherEdit)) {
                possibleMatches.put(futherEdit, dictionary.get(futherEdit));
            }
        }

        if (!possibleMatches.isEmpty()) {
            // Sorted least likely first
            Object[] matches = sortByValue(possibleMatches).keySet().toArray();

            // Try to match anything of the same length first
            String bestMatch = "";
            for(Object o: matches) {
                if (o.toString().length() == word.length()) {
                    bestMatch = o.toString();
                }
            }

            if (!bestMatch.trim().isEmpty()) {
                return bestMatch;
            }

            // Just return whatever is the best match
            return matches[matches.length - 1].toString();
        }


        // If unable to find something better return the same string
        return word;
    }


    private List<String> wordEdits(String word) {
        List<String> closeWords = new ArrayList<String>();

        for (int i = 0; i < word.length() + 1; i++) {
            for (char character = 'a'; character <= 'z'; character++) {
                // Maybe they forgot to type a letter? Try adding one
                StringBuilder sb = new StringBuilder(word);
                sb.insert(i, character);
                closeWords.add(sb.toString());
            }
        }

        for (int i = 0; i < word.length(); i++) {
            for (char character = 'a'; character <= 'z'; character++) {
                // Maybe they mistyped a single letter? Try replacing them all
                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(i, character);
                closeWords.add(sb.toString());

                // Maybe they added an extra letter? Try deleting one
                sb = new StringBuilder(word);
                sb.deleteCharAt(i);
                closeWords.add(sb.toString());
            }
        }

        return closeWords;
    }


    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted( Map.Entry.comparingByValue() ).forEachOrdered( e -> result.put(e.getKey(), e.getValue()) );

        return result;
    }

}