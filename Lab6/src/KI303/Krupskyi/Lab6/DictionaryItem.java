package KI303.Krupskyi.Lab6;

/**
 * Інтерфейс для елементів словника.
 * Елементи повинні бути порівнюваними, щоб контейнер міг знаходити максимальний елемент.
 */
public interface DictionaryItem extends Comparable<DictionaryItem> {
    /**
     * Повертає ключ елемента (наприклад, слово).
     * @return ключ
     */
    String getKey();

    /**
     * Повертає значення (наприклад, визначення).
     * @return значення
     */
    String getValue();

    /**
     * Друк інформації про елемент у stdout.
     */
    void print();
}
