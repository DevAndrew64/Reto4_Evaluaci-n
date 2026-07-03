import java.io.File;
import java.io.IOException;

public class PruebaNumeracion {

    public static void main(String[] args) throws IOException {
        GestorFacturas gestor = new GestorFacturas();

        File carpeta = new File("facturas");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        File archivoPrueba = new File(carpeta, "factura_9999.txt");
        archivoPrueba.createNewFile();

        int resultado = gestor.obtenerSiguienteNumero();

        archivoPrueba.delete();

        if (resultado == 10000) {
            System.out.println("OK: con factura_9999.txt presente, el siguiente numero es 10000");
        } else {
            System.out.println("FALLO: se esperaba 10000 y se obtuvo " + resultado);
        }
    }
}
