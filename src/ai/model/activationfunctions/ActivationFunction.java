package src.ai.model.activationfunctions;

/**
 * Very simple class to do the activation functions of relu and relu derivative.
 *
 * @author devinlinux
 */
public class ActivationFunction {

    /**
     * Method to calculate the relu activation function.
     *
     * @param x the value to calculate for.
     */
    public static double relu(double x) {
        return Math.max(0, x);
    }

    /**
     * Method to calculate the relu derivative acitvation function.
     *
     * @param x the value to calculate for.
     */
    public static double reluDerivative(double x) {
        return x > 0 ? 1 : 0;
    }
}
