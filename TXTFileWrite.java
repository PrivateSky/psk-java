package JarPrivateSky;

import java.io.*;

public class TXTFileWrite {

    private static String text;
    private static String patch;
    private static String name;

    public static void main(String text, String patch, String name) throws IOException {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(patch + name + ".txt"), "utf-8"))) {
            writer.write(text);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        JarPrivateSky.PrivateSkyTXT mainPrivateSkyTXT = new JarPrivateSky.PrivateSkyTXT();
        mainPrivateSkyTXT.setName(name);
        mainPrivateSkyTXT.setPatch(patch);
        String[] args = null;
        JarPrivateSky.PrivateSkyTXT.main(args);
    }
}