package KI303.Krupskyi.Lab5;

import java.io.*;
import java.util.*;

/**
 * Class <code>FioApp</code> implements main driver for CalcWFio class.
 * @author Krupskyi Ilya
 * @version 1.0
 */
public class FioApp {

    /**
     * Main entry point.
     * @param args command-line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        CalcWFio obj = new CalcWFio();
        Scanner s = new Scanner(System.in);

        System.out.print("Enter X (in degrees): ");
        double data = s.nextDouble();

        obj.calculate(data);
        System.out.println("Result is: " + obj.getResult());

        obj.writeResTxt("ResultText.txt");
        obj.writeResBin("ResultBin.bin");

        obj.readResBin("ResultBin.bin");
        System.out.println("Binary read result: " + obj.getResult());

        obj.readResTxt("ResultText.txt");
        System.out.println("Text read result: " + obj.getResult());
    }
}
