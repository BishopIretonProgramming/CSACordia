package src.ai.model.layers;

//  imports
import src.ai.model.activationfunctions.ActivationFunction;

/**
 * A class to represent a dense (fully-connected) layer of a neural network.
 *
 * @author devinlinux
 */
public class Dense {

    /**
     * The size of the input to the layer.
     */
    private int inputSize;

    /**
     * The size of the layer output.
     */
    private int outputSize;

    /**
     * The weights of this layer.
     */
    private double[][] weights;

    /**
     * The biases of this layer.
     */
    private double[] biases;

    /**
     * The weight gradients of this layer.
     */
    private double[][] weightGradients;

    /**
     * The bias gradients of this layer.
     */
    private double[] biasGradients;

    /**
     * The activation function of this layer
     */
    private ActivationFunction activation;
}
