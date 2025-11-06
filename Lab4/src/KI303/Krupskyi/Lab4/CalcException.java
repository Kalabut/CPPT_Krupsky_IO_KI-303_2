package KI303.Krupskyi.Lab4;

/**
 * Class <code>CalcException</code> specifies custom calculation exception.
 * @author Krupskyi Ilya
 * @version 1.0
 */
public class CalcException extends ArithmeticException {

    public CalcException() {}

    public CalcException(String cause) {
        super(cause);
    }
}
