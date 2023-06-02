package src.ai.model.lossfunctions;

/**
 * An interface to represent a loss function to be used
 * in a neural network
 *
 * @author devinlinux
 */
public interface LossFunction {
    
    /**
     * Method to apply the loss function by calculating the loss
     * between the predicted outputs and the target outputs.
     * 
     * @param outputs the predicted outputs as a two-dimensional float array.
     * @param targets the actual outputs as a two-dimensional float array.
     * @return an array of {@code Object}s with two elements, the first element 
     *         is the result of the loss function, and the second element is
     *         the derivative of the loss function with respect to the outputs.
     */
    Object[] apply(float[][] outputs, float[][] targets);
}
