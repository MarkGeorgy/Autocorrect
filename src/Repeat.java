public class Repeat implements Comparable<Repeat>{
    String repeatedWords;

    public Repeat(String repeatedWords) {
        this.repeatedWords = repeatedWords;
    }

    public String getRepeatedWords() {
        return repeatedWords;
    }

    @Override
    public int compareTo(Repeat that) {
        return this.getRepeatedWords().compareTo(that.getRepeatedWords());
    }
}
