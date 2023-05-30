package src.ai.model.data;

//  imports
import java.util.List;
import java.util.ArrayList;

/**
 * A class to represent a dataset to train an artificial intelligence model.
 *
 * @author devinlinux
 */
public class Dataset {

    /**
     * The features of the dataset.
     */
    private List<List<Double>> features;

    /**
     * The labels of the data.
     */
    private List<Double> labels;

    /**
     * Constructor to make a new dataset.
     */
    public Dataset() {
        this.features = new ArrayList<>();
        this.labels = new ArrayList<>();
    }

    /**
     * Method to add a data point to the dataset.
     *
     * @param featureVals the values of the features.
     * @param label       the label of the features.
     */
    public void addDataPoint(List<Double> featureVals, Double label) {
        this.features.add(featureVals);
        this.labels.add(label);
    }

    /**
     * Method to get the two-dimensional array of features.
     *
     * @return the two-dimensional array of features in the dataset.
     */
    public double[][] getFeaturesArray() {
        int numDataPoints = this.features.size();
        int numFeatures = this.features.get(0).size();
        double[][] featuresArray = new double[numDataPoints][numFeatures];

        for (int i = 0; i < numDataPoints; i++) {
            List<Double> featureVals = this.features.get(i);
            for (int j = 0; j < numFeatures; j++) {
                featuresArray[i][j] = featureVals.get(j);
            }
        }

        return featuresArray;
    }

    /**
     * Method to get the array of labels.
     *
     * @return the array of labels in this dataset.
     */
    public double[] getLabelsArray() {
        int numDataPoints = labels.size();
        double[] labelsArray = new double[numDataPoints];

        for (int i = 0; i < numDataPoints; i++) {
            labelsArray[i] = this.labels.get(i);
        }

        return labelsArray;
    }

    /**
     * Method to get the size of this dataset.
     *
     * @return the size of this dataset.
     */
    public int size() {
        return this.features.size();
    }
}
