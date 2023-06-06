package src.game.gamestate_management;

class JSONData extends JSON {
    private final String data;

    private JSONData(String title, String data) {
        super(title);
        this.data = data;
    }

    static JSONData newData(String title, String data) {
        return new JSONData(title, data);
    }

    static String getData(JSONData head) {
        return head.data;
    }
}