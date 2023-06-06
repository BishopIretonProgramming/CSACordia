package src.game.gamestate_management;

class JSONWord extends JSON {

    private JSONWord(String title) {
        super(title);
    }

    static JSONWord newData(String title) {
        return new JSONWord(title);
    }
}