package JarPrivateSky;

public class PrivateSkyToken {
    private static String token;

    public PrivateSkyToken(String token) {
        this.token = token;
    }

    public static String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}