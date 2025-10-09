package src;
import java.io.*;
import java.util.*;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Головний клас програми.
 * Програма формує квадратну матрицю, заповнену символами за введеними параметрами,
 * та виводить її у вигляді візерунка з чергуванням прямокутників.
 * 
 * @author Крупський І. О.
 * @version 1.0
 * @since 2025-10-07
 */
public class main {

    /**
     * Головний метод програми.
     * Зчитує з клавіатури розмір квадратної матриці та символ-заповнювач.
     * Створює двовимірний масив і виводить його у вигляді чергування заповнених і порожніх стовпців.
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out, true, UTF_8);

        // Запит розміру квадратної матриці
        out.println("Введіть розмір квадратної матриці: ");
        Scanner in = new Scanner(System.in);
        int nRows = in.nextInt();

        // Запит символу-заповнювача
        out.println("Введіть символ-заповнювач: ");
        in.nextLine();
        String filler = in.nextLine();

        // Перевірка коректності введеного символу
        if (filler.length() != 1)
        {
            out.print("\nСимвол-заповнювач введено невірно.");
            System.exit(0);
        }

        // Оголошення квадратного масиву
        char[][] arr = new char[nRows][nRows];		

        // Формування і виведення фігури
        for (int I = 0; I < nRows; I++)
        {
            for (int J = 0; J < nRows; J++)
            {
                // Перший стовпець порожній, далі чергуються заповнений і порожній стовпці
                if (J == 0 || (J % 2 == 0))
                    arr[I][J] = ' ';
                else
                    arr[I][J] = filler.charAt(0);

                out.print(arr[I][J] + "\t");
            }
            out.println();		
        }	

        // Закриття потоків
        in.close();
        out.close();
    }
}
