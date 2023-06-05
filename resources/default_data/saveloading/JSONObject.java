package resources.default_data.saveloading;

import java.util.ArrayList;

interface JSON {

}

class JSONObject implements JSON {
    ArrayList<JSON> contents;
    
    private JSONObject() {
        // constructor suppressor
    }

    public static JSONObject createBaseObject() {
        return new JSONObject();
    }

    public static JSONObject addSubObject(JSONObject head, String title) {
        head.contents.add(0, new JSONObject());
        return (JSONObject) head.contents.get(0);
    }
    
    public static JSONData addData(JSONObject head, String title, String data) {
        head.contents.add(0, JSONData.newData(title, data));
        return (JSONData) head.contents.get(0);
    }

    public static ArrayList<JSON> listContents(JSONObject head) {
        return head.contents;
    }
}

class JSONData implements JSON {
    private String title, data;

    private JSONData(String title, String data) {
        this.title = title;
        this.data = data;
    }

    public static JSONData newData(String title, String data) {
        return new JSONData(title, data);
    }

    public static String getTitle(JSONData head) {
        return head.title;
    }

    public static String getData(JSONData head) {
        return head.data;
    }
}
