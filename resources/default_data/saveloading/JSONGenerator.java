package resources.default_data.saveloading;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

class JSONGenerator {
    private JSONGenerator() {
        // suppress constructor
    }

    /**
     * the path array takes the individual directories in order and adds separators
     * between them. if the file separators were manually added, they can be sent in
     * as one string as the first index of the array and the user should leave the
     * rest blank
     */
    public static void generateJSONFILE(JSONObject head, String... path) {
        File file = new File(Arrays.asList(path).stream().collect(Collectors.joining(File.separator)));

        try (PrintWriter pr = new PrintWriter(file)) {
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
