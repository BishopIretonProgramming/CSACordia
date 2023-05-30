package src.ai.model.util;

/**
 * A class to scale data for datasets.
 *
 * @author devinlinux
 */
public class StandardScaler {

    /**
     * The mean of the features.
     */
    private double[] mean;

    /**
     * The standard deviation of the features.
     */
    private double[] std;

    /**
     * Method to fit the std and mean on features.
     *
     * @param features the features to fit.
     */
    public void fit(double[][] features) {
        int numFeatures = features[0].length;
        int numDataPoints = features.length;
        this.mean = new double[numFeatures];
        this.std = new double[numFeatures];

        for (int i = 0; i < numFeatures; i++) {
            double sum = 0.0;
            for (int j = 0; j < numDataPoints; j++) {
                sum += features[j][i];
            }
            this.mean[i] = sum / numDataPoints;

            double squaredSum = 0.0;
            for (int j = 0; j < numDataPoints; j++) {
                double diff = features[j][i] - mean[i];
                squaredSum += diff * diff;
            }
            this.std[i] = Math.sqrt(squaredSum / numDataPoints);
        }
    }

    /**
     * Method to transform a two-dimensional array of features.
     *
     * @param features the features to fit to.
     * @return the transformed features made from the features.
     */
    public double[][] transform(double[][] features) {
        int numDataPoints = features.length;
        int numFeatures = features[0].length;
        double[][] transformedFeatures = new double[numDataPoints][numFeatures];

        for (int i = 0; i < numDataPoints; i++) {
            for (int j = 0; j < numFeatures; j++) {
                transformedFeatures[i][j] = (features[i][j] - mean[j]) / std[j];
            }
        }

        return transformedFeatures;
    }
}
