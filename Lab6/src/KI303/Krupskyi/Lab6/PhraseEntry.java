package KI303.Krupskyi.Lab6;

/**
 * Запис для фраз (багатослівні ключі). Порівняння: за довжиною ключа, потім лексикографічно.
 */
public class PhraseEntry implements DictionaryItem {
    private final String key;
    private final String meaning;

    /**
     * Конструктор.
     * @param key фраза
     * @param meaning значення / переклад
     */
    public PhraseEntry(String key, String meaning) {
        this.key = key;
        this.meaning = meaning;
    }

    @Override
    public String getKey() { return key; }

    @Override
    public String getValue() { return meaning; }

    @Override
    public void print() {
        System.out.printf("PhraseEntry{key='%s', meaning='%s'}%n", key, meaning);
    }

    @Override
    public int compareTo(DictionaryItem o) {
        if (o == null) return 1;
        int cmp = Integer.compare(this.key.length(), o.getKey().length());
        if (cmp != 0) return cmp;
        return this.key.compareTo(o.getKey());
    }
}
