package src.game.gamestate_management;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import src.game.Colonist;
import src.game.Game;
import src.game.Good;
import src.game.House;
import src.game.Storeable;
import src.game.cards.PersonalityCard;
import src.game.map.CityNode;
import src.game.player.Player;

public class SaveLoader {

    private static final String GAME_STATE_SAVES_DIR = String.format("resources%1$ssaves", File.separatorChar);

    private SaveLoader() {
        // suppress default constructor
    }

    public static void saveGame(Game game) {
        ArrayList<Player> plObjs = new ArrayList<Player>(game.getPlayers());
        JObject head = JObject.createBaseObject();

        // players
        JObject players = JObject.addSubObject(head, "players");
        for (Integer i = 0; i < plObjs.size(); i++) {
            JObject player = JObject.addSubObject(players, i);
            Player p = plObjs.get(i);

            JObject.addData(player, "color", p.color());
            JObject.addData(player, "money", p.sestertii());

            JObject[] folders = new JObject[] { JObject.addSubObject(player, "deck"),
                    JObject.addSubObject(player, "discard"), JObject.addSubObject(player, "houses") };
            ArrayList<?>[] data = new ArrayList<?>[] { p.cards(), p.discard(), p.houses() };
            for (int j = 0; i < data.length; j++)
                for (Object object : data[j])
                    JObject.addWord(folders[j], object);

            JObject storehouse = JObject.addSubObject(player, "storehouse");
            for (Storeable store : p.storeHouse().getResources())
                JObject.addWord(storehouse, store instanceof Good ? ((Good) store).name() : ((Colonist) store).type());

            JObject colonists = JObject.addSubObject(player, "colonists");
            for (int j = 0; j < p.colonists().size(); j++) {
                JObject colonist = JObject.addSubObject(colonists, j);
                Colonist c = p.colonists().get(j);

                JObject.addData(colonist, "type", c.type());

                JObject location = JObject.addSubObject(colonist, "location");
                for (CityNode city : c.path().nodes())
                    JObject.addWord(location, city.name());
            }
        }

        // cities
        JObject cities = JObject.addSubObject(head, "cities");
        for (int i = 0; i < game.getMap().network().nodeCount(); i++) {
            
        }
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
