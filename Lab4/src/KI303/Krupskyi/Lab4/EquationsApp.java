package KI303.Krupskyi.Lab4;

import java.util.Scanner;
import java.io.*;

import static java.lang.System.out;

/**
 * Class <code>EquationsApp</code> implements driver for Equations class.
 * @author Krupskyi Ilya
 * @version 1.0
 */
public class EquationsApp {

    /**
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            out.print("Enter file name: ");
            Scanner in = new Scanner(System.in);
            String fName = in.nextLine();
            PrintWriter fout = new PrintWriter(new File(fName));

            try {
                try {
                    Equations eq = new Equations();
                    out.print("Enter X (in degrees): ");
                    double result = eq.calculate(in.nextInt());
                    fout.println("Result: " + result);
                    out.println("Result written to file successfully.");
                } 
                finally {
                    fout.flush();
                    fout.close();
                }
            } 
            catch (CalcException ex) {
                out.println(ex.getMessage());
            }
        } 
        catch (FileNotFoundException ex) {
            out.println("Exception reason: Perhaps wrong file path");
        }
    }
}
