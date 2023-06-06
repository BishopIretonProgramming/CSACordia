package src.cli;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import src.game.Game;
import src.game.map.Map;
import src.game.player.Player;

public class GameCLI {
    
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
        String name = scanner.nextLine();
        return name;
    }
}

