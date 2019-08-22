package JarPrivateSky;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONFileWrite {

    private static String text;
    private static String patch;
    private static String name;

    public static void main(String text, String patch, String name) throws IOException {

        JSONObject obj = new JSONObject();

        obj.put("Text", text);

        try (FileWriter file = new FileWriter(patch + name + ".json")) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        }

        JarPrivateSky.PrivateSkyJson mainPrivateSkyJson = new JarPrivateSky.PrivateSkyJson();
        mainPrivateSkyJson.setName(name);
        mainPrivateSkyJson.setPatch(patch);
        String[] args = null;
        JarPrivateSky.PrivateSkyJson.main(args);
    }
}