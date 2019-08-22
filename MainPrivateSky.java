package JarPrivateSky;

import java.io.IOException;

public class MainPrivateSky {

    public static void PrivateSkyCall(String type, String text, String patch, String name) {

        switch (type) {

            case "json":
                JarPrivateSky.JSONFileWrite mainJson = new JarPrivateSky.JSONFileWrite();
                try {
                    mainJson.main(text, patch, name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "xml":
                JarPrivateSky.XMLFileWrite mainXml = new JarPrivateSky.XMLFileWrite();
                mainXml.main(text, patch, name);
                break;
            case "txt":
                JarPrivateSky.TXTFileWrite mainTxt = new JarPrivateSky.TXTFileWrite();
                try {
                    mainTxt.main(text, patch, name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }
}