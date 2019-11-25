
public class CorrectedWords implements Comparable<CorrectedWords>{

    private String word;
    private String correctedWord;

    public CorrectedWords(String word, String correctedWord) {
        this.word = word;
        this.correctedWord = correctedWord;
    }


    public String getWord() {
        return word;
    }

    public String getCorrectedWord() {
        return correctedWord;
    }


    @Override
    public int compareTo(CorrectedWords that) {
        return this.getWord().compareTo(that.getWord());
    }
}