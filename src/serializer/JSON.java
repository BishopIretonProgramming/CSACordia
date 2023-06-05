package src.serializer;

//  imports
import java.lang.reflect.Field;

/**
 * A class to serialize and deserialize objects to JSON format.
 *
 * @author deivnlinux
 */
public class JSON {
    
    /**
     * A method to serialize an object to JSON form.
     *
     * @param object the object to serialize.
     * @return       the {@code String} with the serialized form of the 
     *               class in JSON format.
     */
    public static <T extends Saveable> String serialize(T object) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            jsonBuilder.append("\"").append(fields[i].getName()).append("\":");
            if (fields[i].getType() == String.class) {
                try { jsonBuilder.append("\"").append(fields[i].get(object)).append("\""); } catch (IllegalAccessException e) { e.printStackTrace(); }
            } else {
                try { jsonBuilder.append(fields[i].get(object)); } catch (IllegalAccessException e) { e.printStackTrace(); }
            }
            if (i < fields.length - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}
