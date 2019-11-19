
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
        }

        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void delete(Key key) {
        if (isEmpty() || !contains(key)) {
            return;
        }

        int rank = rank(key);
        for (int i = rank; i < N - 1; i++) {
            keys[i] = keys[i + 1];
            vals[i] = vals[i + 1];
        }

        keys[N - 1] = null;
        vals[N - 1] = null;
        N--;

        if (N > 1 && N == keys.length / 4) {
            resize(keys.length / 2);
        }
    }

    private void resize(int newSize)
    {
        Key[] tempKeys = (Key[]) new Comparable[newSize];
        Value[] tempValues = (Value[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            tempKeys[i] = keys[i];
            tempValues[i]=vals[i];
        }
        vals=tempValues;
        keys = tempKeys;

    }
}