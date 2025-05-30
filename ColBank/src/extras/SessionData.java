package extras;

public class SessionData {
    private static String cedula;

    public static String getCedula() {
        return cedula;
    }

    public static void setCedula(String cedula) {
        SessionData.cedula = cedula;
    }
}
