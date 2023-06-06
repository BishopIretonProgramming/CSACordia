package src.cli;

import static java.io.File.separatorChar;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

import src.game.Game;
import src.game.map.Map;
import src.game.player.Player;

public class GameCLI {

    private static final String SAVES_DIRECTORY = String.format("resources%csaves%cunfinished_games", separatorChar, separatorChar);

    public static Game run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================================");
        System.out.println("                        WELCOME TO CSACORDIA                      ");
        System.out.println("==================================================================");
        System.out.println("                                                                  ");
        System.out.println("                                                                  ");
        System.out.println("                                                                  ");
        System.out.println("-------- Would you like to load a game or make a new game --------");
        System.out.println("------ Enter 'load' to load a game or 'make' to make a game ------");
        String input = scanner.nextLine();

        Game game = null;
        if (input.equalsIgnoreCase("make")) {
            List<Player> players = getPlayers(scanner);
            String gameName = getGameName(scanner);
            game = new Game(gameName, new Map(Map.IMPERIUM), players, null, players.get(0));
        } else if (input.equalsIgnoreCase("load")) {
            String filePath = chooseFile(scanner);
            System.exit(0);  // there is no parser
        } else {
            System.out.println("Invalid input.");
            System.exit(0);
        }
        return game;
    }

    private static List<Player> getPlayers(Scanner scanner) {
        List<Player> players = new ArrayList<>();
        System.out.println("=================================================================");
        System.out.println("                          PLAYER CREATION                        ");
        System.out.println("=================================================================");
        System.out.println("                                                                 ");
        System.out.println("                                                                 ");
        System.out.println("                                                                 ");
        System.out.println("------------------- Enter the number of players -----------------");
        int numPlayers = scanner.nextInt();
        if (numPlayers < 2 || numPlayers > 5) {
            System.out.println("Invalid number of players");
            return players;
        }
        scanner.nextLine();

        System.out.println("--------------------- Enter the player names --------------------");
        for (int i = 0; i < numPlayers; i++) {
            String name = scanner.nextLine();
            Player player = new Player(name);
            players.add(player);
        }
        return players;
    }

    private static String getGameName(Scanner scanner) {
        System.out.println("================================================================");
        System.out.println("                          GAME NAME SETUP                       ");
        System.out.println("================================================================");
        System.out.println("                                                                 ");
        System.out.println("                                                                 ");
        System.out.println("                                                                 ");
        System.out.println("-------------------- Enter the name of the game -----------------");
        return scanner.nextLine();
    }

    private static String chooseFile(Scanner scanner) {
        File savesDirectory = new File(SAVES_DIRECTORY);
        File[] files = savesDirectory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No save files found.");
            return null;
        }

        System.out.println("Select a file to load:");

        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }

        int selection = scanner.nextInt();

        if (selection <= 0 || selection > files.length) {
            System.out.println("Invalid selection. Please try again.");
            return null;
        }

        return files[selection - 1].getAbsolutePath();
    }
}

