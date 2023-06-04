package resources.default_data;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SaveLoader {

    private static final String GAME_STATE_SAVES_DIR = String.format("resources%1$ssaves", File.separatorChar);

    private SaveLoader() {
        // suppress default constructor
    }

    public static boolean loadSave() {
        return false;
    }

    public static ArrayList<String> existingNames() {
        try (Stream<Path> names = Files.list(Paths.get(GAME_STATE_SAVES_DIR))
                .filter(file -> !Files.isDirectory(file))
                .map(Path::getFileName)) {
            return (ArrayList<String>) names.map(Path::toString).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }
}
