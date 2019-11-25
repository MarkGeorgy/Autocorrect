public class FrequencyPerWord implements Comparable<FrequencyPerWord>{

    private String word;
    private int frequency;

   public FrequencyPerWord(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(FrequencyPerWord that) {
        if (this.frequency < that.frequency) {
            return -1;
        } else if (this.frequency > that.frequency) {
            return 1;
        }
      int comp = this.word.compareTo(that.word);
        return comp;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }
}