package resources.default_data.saveloading;

class JSONWord extends JSON {

    private JSONWord(String title) {
        super(title);
    }

    public static JSONWord newData(String title) {
        return new JSONWord(title);
    }
}