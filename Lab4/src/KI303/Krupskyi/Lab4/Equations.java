package KI303.Krupskyi.Lab4;

/**
 * Class <code>Equations</code> implements method for y = sin(x)/ctg(8x) expression calculation.
 * @author Krupskyi Ilya
 * @version 1.0
 */
public class Equations {

    /**
     * Method calculates y = sin(x)/ctg(8x) = sin(x)*tan(8x)
     * @param x Angle in degrees
     * @throws CalcException if x causes illegal math operation
     */
    public double calculate(int x) throws CalcException {
        double y, rad, rad8;
        rad = x * Math.PI / 180.0;
        rad8 = 8 * x * Math.PI / 180.0;

        try {
            double tan8x = Math.tan(rad8);
            y = Math.sin(rad) * tan8x;

            if (Double.isNaN(y) || Double.isInfinite(y) || tan8x == 0)
                throw new ArithmeticException();
        } 
        catch (ArithmeticException ex) {
            if (x % 45 == 0)
                throw new CalcException("Exception reason: Illegal value of X for tangent calculation");
            else if (x == 0)
                throw new CalcException("Exception reason: X = 0");
            else
                throw new CalcException("Unknown reason of the exception during expression calculation");
        }
        return y;
    }
}
