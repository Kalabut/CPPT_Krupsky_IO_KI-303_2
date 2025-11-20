package KI303.Krupskyi.Lab6;

import java.util.Scanner;

/**
 * @author Krupskyi Ilya
 * @version 1.0
 * Драйвер для демонстрації Dictionary:
 * - додає дві різні класи елементів (WordEntry, PhraseEntry),
 * - демонструє add, findByKey, findMax, remove.
 */
public class DictionaryApp {
    public static void main(String[] args) {
        Dictionary<DictionaryItem> dict = new Dictionary<>();
        
        // Додаємо різні типи елементів
        dict.add(new WordEntry("apple", "A fruit"));
        dict.add(new PhraseEntry("to break the ice", "initiate conversation"));
        dict.add(new WordEntry("hippopotamus", "Large semiaquatic mammal"));
        dict.add(new PhraseEntry("once in a blue moon", "very rarely"));

        System.out.println("Dictionary size: " + dict.size());

        // Знайти за ключем
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter key to search: ");
        String userKey = sc.nextLine();

        DictionaryItem found = dict.findByKey(userKey);

        if (found != null) {
            System.out.print("Found by key '" + userKey + "': ");
            found.print();
        } else {
            System.out.println("No item found for key '" + userKey + "'");
        }

        // Знайти максимальний елемент (за довжиною ключа)
        DictionaryItem max = dict.findMax();
        System.out.print("Max element (by key length): ");
        if (max != null) max.print();

       // Видалення елемента
        dict.remove(1);
        System.out.println("After removal, size: " + dict.size());
    }
}
