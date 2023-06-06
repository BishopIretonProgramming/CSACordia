package src.game.gamestate_management;

class JWord extends JSON {

    private JWord(String title) {
        super(title);
    }

    static JWord newData(String title) {
        return new JWord(title);
    }
}