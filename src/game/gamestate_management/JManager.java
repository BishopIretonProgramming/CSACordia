package src.game.gamestate_management;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class JManager {
    private static boolean exists = false;
    private static JManager self = null;
    private static String braces = "", tabs = "";

    static JManager get() {
        if (!exists)
            return self = new JManager();
        return self;
    }

    private JManager() {
        exists = true;
    }

    /**
     * the path array takes the individual directories in order and adds separators
     * between them. if the file separators were manually added, they can be sent in
     * as one string as the first index of the array and the user should leave the
     * rest blank
     */
    void generateJSONFile(JObject head, String... path) {
        File file = new File(Arrays.asList(path).stream().collect(Collectors.joining(File.separator)) + File.separator + JSON.getTitle(head) + ".game");

        try (PrintWriter pr = new PrintWriter(file)) {
            writeFolder(pr, head);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFolder(PrintWriter pr, JObject folder) {
        boolean wordFolder = JObject.listContents(folder).stream().filter(jobj -> jobj instanceof JWord).collect(Collectors.toList()).size() > 0;
        braces += wordFolder ? "[]" : "{}";
        pr.write(tabs + "\"" + JSON.getTitle(folder) + String.format("\": %c\n", braces.charAt(braces.length() - 2)));
        tabs += "\t";

        ArrayList<JSON> list = JObject.listContents(folder);
        for (int i = 0; i < list.size(); i++) {
            if (wordFolder) {
                pr.write(tabs + "\"" + JSON.getTitle(list.get(i)) + "\"");
            } else if (list.get(i) instanceof JData) {
                pr.write(tabs + "\"" + JSON.getTitle(list.get(i)) + ": " + JData.getData((JData) list.get(i)) + "\"");
            } else {
                writeFolder(pr, (JObject) list.get(i));
            }
            pr.write((i != list.size() - 1 ? "," : "") + "\n");
        }

        tabs = tabs.substring(0, tabs.length() - 1);
        pr.write(tabs + braces.charAt(braces.length() - 1));
        braces = braces.substring(0, braces.length() - 2);
    }

    JObject generateJSONTree(String... path) {
        return null;
    }
}
