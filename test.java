package JarPrivateSky;

public class test {
    public static void main(String[] args){

        String text = "test1";
        String type = "json";
        String patch = "e:\\Intellij_profects\\SmartCI_intellij\\JsonFiles\\";
        String name = "test11";
        JarPrivateSky.MainPrivateSky main = new JarPrivateSky.MainPrivateSky();
        main.PrivateSkyCall(type,text,patch,name);

        System.out.println("Tokenul este : " + JarPrivateSky.PrivateSkyToken.getToken());
    }
}