package KI303.Krupskyi.Lab6;

/**
 * Запис словника: слово з визначенням.
 * Порівняння: за довжиною ключа, при рівності — лексикографічно.
 */
public class WordEntry implements DictionaryItem {
    private final String key;
    private final String definition;

    /**
     * Конструктор.
     * @param key слово
     * @param definition визначення
     */
    public WordEntry(String key, String definition) {
        this.key = key;
        this.definition = definition;
    }

    @Override
    public String getKey() { return key; }

    @Override
    public String getValue() { return definition; }

    @Override
    public void print() {
        System.out.printf("WordEntry{key='%s', def='%s'}%n", key, definition);
    }

    @Override
    public int compareTo(DictionaryItem o) {
        if (o == null) return 1;

        // 1) За довжиною ключа
        int cmp = Integer.compare(this.key.length(), o.getKey().length());
        if (cmp != 0) return cmp;

        // 2) Якщо однакова довжина — лексикографічно
        return this.key.compareTo(o.getKey());
    }
}

