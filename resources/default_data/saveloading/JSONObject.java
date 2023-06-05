package resources.default_data.saveloading;

import java.util.ArrayList;

class JSONObject extends JSON {
    private ArrayList<JSON> contents;
    
    private JSONObject(String title) {
        super(title);
    }

    public static JSONObject createBaseObject() {
        return new JSONObject("head");
    }

    public static JSONObject addSubObject(JSONObject head, String title) {
        head.contents.add(0, new JSONObject(title));
        return (JSONObject) head.contents.get(0);
    }
    
    public static JSONData addData(JSONObject head, String title, String data) {
        for (JSON jobj : head.contents) {
            if (JSONData.getTitle(jobj).equals(title)) {
                return null;
            }
        }
        head.contents.add(0, JSONData.newData(title, data));
        return (JSONData) head.contents.get(0);
    }

    public static ArrayList<JSON> listContents(JSONObject head) {
        return head.contents;
    }
}