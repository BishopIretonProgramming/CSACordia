package src.game.gamestate_management;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class JSONGenerator {
    private static boolean exists = false;
    private static JSONGenerator self = null;
    private String braces = "", tabs = "";

    public static JSONGenerator get() {
        if (!exists)
            return self = new JSONGenerator();
        return self;
    }

    private JSONGenerator() {
        exists = true;
    }

    /**
     * the path array takes the individual directories in order and adds separators
     * between them. if the file separators were manually added, they can be sent in
     * as one string as the first index of the array and the user should leave the
     * rest blank
     */
    public void generateJSONFile(JSONObject head, String... path) {
        File file = new File(Arrays.asList(path).stream().collect(Collectors.joining(File.separator)) + File.separator + JSON.getTitle(head) + ".game");

        try (PrintWriter pr = new PrintWriter(file)) {
            writeFolder(pr, head);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFolder(PrintWriter pr, JSONObject folder) {
        boolean wordFolder = JSONObject.listContents(folder).stream().filter(jobj -> jobj instanceof JSONWord).collect(Collectors.toList()).size() > 0;
        braces += wordFolder ? "[]" : "{}";
        pr.write(tabs + "\"" + JSON.getTitle(folder) + String.format("\": %c\n", braces.charAt(braces.length() - 2)));
        tabs += "\t";

        ArrayList<JSON> list = JSONObject.listContents(folder);
        for (int i = 0; i < list.size(); i++) {
            if (wordFolder) {
                pr.write(tabs + "\"" + JSON.getTitle(list.get(i)) + "\"");
            } else if (list.get(i) instanceof JSONData) {
                pr.write(tabs + "\"" + JSON.getTitle(list.get(i)) + ": " + JSONData.getData((JSONData) list.get(i)) + "\"");
            } else {
                writeFolder(pr, (JSONObject) list.get(i));
            }
            pr.write((i != list.size() - 1 ? "," : "") + "\n");
        }

        tabs = tabs.substring(0, tabs.length() - 1);
        pr.write(tabs + braces.charAt(braces.length() - 1));
        braces = braces.substring(0, braces.length() - 2);
    }
}
