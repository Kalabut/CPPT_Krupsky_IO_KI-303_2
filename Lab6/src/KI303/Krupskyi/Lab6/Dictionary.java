package KI303.Krupskyi.Lab6;

import java.util.ArrayList;
import java.util.List;

/**
 * Параметризований контейнер -- Dictionary<T extends DictionaryItem>.
 * Мінімум методів: add, remove, findMax, findByKey. Додатково: getAll, size.
 * @param <T> тип елементів (мають реалізовувати DictionaryItem)
 */
public class Dictionary<T extends DictionaryItem> {
    private final ArrayList<T> items;

    public Dictionary() {
        items = new ArrayList<>();
    }

    /**
     * Додає елемент до словника.
     * @param item елемент
     */
    public void add(T item) {
        items.add(item);
        System.out.print("Added: ");
        item.print();
    }

    /**
     * Видаляє елемент за індексом.
     * @param index індекс
     * @throws IndexOutOfBoundsException якщо індекс некоректний
     */
    public void remove(int index) {
        T removed = items.remove(index);
        System.out.print("Removed: ");
        removed.print();
    }

    /**
     * Повертає максимальний елемент за правилом compareTo (null якщо пусто).
     * @return максимальний елемент або null
     */
    public T findMax() {
        if (items.isEmpty()) return null;
        T max = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            T cur = items.get(i);
            if (cur.compareTo(max) > 0) max = cur;
        }
        return max;
    }

    /**
     * Знаходить перший елемент за ключем.
     * @param key ключ
     * @return знайдений елемент або null
     */
    public T findByKey(String key) {
        for (T it : items) {
            if (it.getKey().equals(key)) return it;
        }
        return null;
    }

    /**
     * Повертає копію списку всіх елементів.
     * @return список елементів
     */
    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    /**
     * Розмір контейнера.
     * @return кількість елементів
     */
    public int size() { return items.size(); }
}
