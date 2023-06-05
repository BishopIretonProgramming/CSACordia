package resources.default_data.saveloading;

class JSONData extends JSON {
    private final String data;

    private JSONData(String title, String data) {
        super(title);
        this.data = data;
    }

    public static JSONData newData(String title, String data) {
        return new JSONData(title, data);
    }

    public static String getData(JSONData head) {
        return head.data;
    }
}