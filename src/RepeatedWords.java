
public class RepeatedWords implements Comparable<RepeatedWords>{
    String repeatedWords;

    public RepeatedWords(String repeatedWords) {
        this.repeatedWords = repeatedWords;
    }

    public String getRepeatedWords() {
        return repeatedWords;
    }

    @Override
    public int compareTo(RepeatedWords that) {
        return this.getRepeatedWords().compareTo(that.getRepeatedWords());
    }
}
