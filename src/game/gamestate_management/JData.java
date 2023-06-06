package src.game.gamestate_management;

class JData extends JSON {
    private final String data;

    private JData(String title, String data) {
        super(title);
        this.data = data;
    }

    static JData newData(String title, String data) {
        return new JData(title, data);
    }

    static String getData(JData head) {
        return head.data;
    }
}