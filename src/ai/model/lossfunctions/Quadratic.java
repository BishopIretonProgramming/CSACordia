package src.ai.model.lossfunctions;

public class Quadratic implements LossFunction {

    /**
     * The steepness factor of this loss function
     */
    private float steepnessFactor;

    /**
     * Constructor to make a new quadratic loss function with a steepness factor.
     *
     * @param steepnessFactor the steepness factor
     */
    public Quadratic(float steepnessFactor) {
        this.steepnessFactor = steepnessFactor;
    }

    /**
     * Method to apply the loss function by calculating the loss
     * between the predicted outputs and the target outputs.
     *
     * @param outputs the predicted outputs as a two-dimensional float array.
     * @param targets the actual outputs as a two-dimensional float array.
     * @return an array of {@code Object}s with two elements, the first element
     * is the result of the loss function, and the second element is
     * the derivative of the loss function with respect to the outputs.
     */
    @Override
    public Object[] apply(float[][] outputs, float[][] targets) {
        float dHalf = steepnessFactor / 2;
        return null;
    }
}
