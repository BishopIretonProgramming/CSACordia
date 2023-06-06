package src.game.gamestate_management;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import src.game.Game;

public class SaveLoader {

    private static final String GAME_STATE_SAVES_DIR = String.format("resources%1$ssaves", File.separatorChar);

    private SaveLoader() {
        // suppress default constructor
    }

    public static Game loadSave() {
        // do stuff to recreate a game object from the save
        return Game.DO_NOT_USE___SAVE_LOADER_GAME_INITIALIZER(null, null, null, null, null);
    }

    public static ArrayList<String> existingNames() {
        try (Stream<Path> names = Files.list(Paths.get(GAME_STATE_SAVES_DIR))) {
            return (ArrayList<String>) names
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    // this is for testing ignore it
    public static void main(String[] args) {
        JSONObject file = JSONObject.createBaseObject();
        JSONObject folder = JSONObject.addSubObject(file, "folder");
        JSONObject subfolder = JSONObject.addSubObject(folder, "subfolder");
        System.out.println(JSONObject.addData(folder, "num1", "50"));
        System.out.println(JSONObject.addData(folder, "num2", "25"));
        System.out.println(JSONObject.addWord(subfolder, "boing"));
        System.out.println(JSONObject.addData(subfolder, "num2", "25"));
        JSONGenerator.get().generateJSONFile(file, "resources", "saves");
    }
}
