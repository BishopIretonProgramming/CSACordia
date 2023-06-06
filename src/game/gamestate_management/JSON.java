package src.game.gamestate_management;

class JSON {
    private final String title;

    protected JSON(String title) {
        this.title = title;
    }    

    static String getTitle(JSON head) {
        return head.title;
    }
}