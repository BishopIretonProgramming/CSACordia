package src.game.gamestate_management;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import src.game.Game;
import src.game.player.Player;

public class SaveLoader {

    private static final String GAME_STATE_SAVES_DIR = String.format("resources%1$ssaves", File.separatorChar);

    private SaveLoader() {
        // suppress default constructor
    }

    public static void saveGame(Game game) {
        ArrayList<Player> players = new ArrayList<Player>(game.getPlayers());
        game.getMap().network();
    }

    public static Game loadGame(String name) {
        // do stuff to recreate a game object from the save
        return null;
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
}
