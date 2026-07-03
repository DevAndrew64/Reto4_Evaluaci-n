import java.util.Locale;

public class Moneda {

    private static final Locale COLOMBIA = new Locale("es", "CO");

    public static String formatear(double valor) {
        return String.format(COLOMBIA, "$%,.0f", valor);
    }
}
