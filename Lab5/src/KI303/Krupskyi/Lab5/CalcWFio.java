package KI303.Krupskyi.Lab5;

import java.io.*;
import java.util.*;

/**
 * Class <code>CalcWFio</code> implements methods for calculation and
 * file I/O (text and binary) of expression y = sin(x)/ctg(8x).
 * @author Krupskyi Ilya
 * @version 1.0
 */
public class CalcWFio {

    private double result;

    /**
     * Calculates y = sin(x)/ctg(8x)
     * @param x angle in degrees
     */
    public void calculate(double x) {
        double rad = Math.toRadians(x);
        double rad8 = Math.toRadians(8 * x);

        double sinX = Math.sin(rad);
        double sin8X = Math.sin(rad8);
        double cos8X = Math.cos(rad8);

        double ctg8X = cos8X / sin8X;
        result = sinX / ctg8X;
    }

    /**
     * Writes result to a text file
     * @param fName file name
     * @throws FileNotFoundException if file path invalid
     */
    public void writeResTxt(String fName) throws FileNotFoundException {
        PrintWriter f = new PrintWriter(fName);
        f.printf("%f", result);
        f.close();
    }

    /**
     * Reads result from a text file
     * @param fName file name
     */
    public void readResTxt(String fName) {
        try {
            File f = new File(fName);
            if (f.exists()) {
                Scanner s = new Scanner(f);
                result = s.nextDouble();
                s.close();
            } else
                throw new FileNotFoundException("File " + fName + " not found");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Writes result to a binary file
     * @param fName file name
     * @throws IOException if I/O error occurs
     */
    public void writeResBin(String fName) throws IOException {
        DataOutputStream f = new DataOutputStream(new FileOutputStream(fName));
        f.writeDouble(result);
        f.close();
    }

    /**
     * Reads result from a binary file
     * @param fName file name
     * @throws IOException if I/O error occurs
     */
    public void readResBin(String fName) throws IOException {
        DataInputStream f = new DataInputStream(new FileInputStream(fName));
        result = f.readDouble();
        f.close();
    }

    /**
     * Returns last calculated or read result
     * @return result value
     */
    public double getResult() {
        return result;
    }
}
