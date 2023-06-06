package src.game.gamestate_management;

import java.util.ArrayList;

class JObject extends JSON {
    private ArrayList<JSON> contents;
    
    private JObject(String title) {
        super(title);
        contents = new ArrayList<JSON>();
    }

    static JObject createBaseObject() {
        return new JObject("head");
    }

    static JObject addSubObject(JObject head, Object title) {
        head.contents.add(0, new JObject(title.toString()));
        return (JObject) head.contents.get(0);
    }
    
    static JData addData(JObject head, Object title, Object data) {
        for (JSON jobj : head.contents) 
            if (JSON.getTitle(jobj).equals(title.toString()) || jobj instanceof JWord) 
                return null;

        head.contents.add(0, JData.newData(title.toString(), data.toString()));
        return (JData) head.contents.get(0);
    }
    
    static JWord addWord(JObject head, Object title) {
        for (JSON jobj : head.contents) 
            if (JSON.getTitle(jobj).equals(title.toString()) || jobj instanceof JData) 
                return null;

        head.contents.add(0, JWord.newData(title.toString()));
        return (JWord) head.contents.get(0);
    }

    static ArrayList<JSON> listContents(JObject head) {
        return head.contents;
    }
}