import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorFacturas {

    private static final String CARPETA = "facturas";

    public String generarFactura(List<ItemFactura> items, String cliente, int numeroFactura) {
        File carpeta = new File(CARPETA);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        String nombreArchivo = CARPETA + File.separator + "factura_" + numeroFactura + ".txt";
        File archivo = new File(nombreArchivo);

        String contenido = construirContenido(items, cliente, numeroFactura);

        try {
            archivo.createNewFile();
            escribirEnArchivo(archivo, contenido);
            return nombreArchivo;
        } catch (IOException e) {
            System.out.println("Error al crear la factura: " + e.getMessage());
            return null;
        }
    }

    private String construirContenido(List<ItemFactura> items, String cliente, int numeroFactura) {
        StringBuilder contenido = new StringBuilder();
        contenido.append("========================================\n");
        contenido.append("        COMIDAS RAPIDAS KAIRO'S\n");
        contenido.append("========================================\n");
        contenido.append("Factura N: ").append(numeroFactura).append("\n");
        contenido.append("Cliente: ").append(cliente).append("\n");
        contenido.append("----------------------------------------\n");

        double total = 0;
        for (ItemFactura item : items) {
            contenido.append(String.format("%-25s x%-3d $%,.0f%n",
                    item.getProducto().getNombre(), item.getCantidad(), item.getSubtotal()));
            total += item.getSubtotal();
        }

        double iva = total * 0.08;
        double totalFinal = total + iva;

        contenido.append("----------------------------------------\n");
        contenido.append(String.format("Subtotal: $%,.0f%n", total));
        contenido.append(String.format("IVA (8%%): $%,.0f%n", iva));
        contenido.append(String.format("TOTAL A PAGAR: $%,.0f%n", totalFinal));
        contenido.append("========================================\n");
        contenido.append("Gracias por su compra en Kairo's\n");
        contenido.append("Sistema desarrollado por Jhon Ponton - SENA ADSO23\n");

        return contenido.toString();
    }

    private void escribirEnArchivo(File archivo, String contenido) {
        try {
            FileWriter escritura = new FileWriter(archivo);
            escritura.write(contenido);
            escritura.close();
        } catch (IOException e) {
            System.out.println("Error al escribir la factura: " + e.getMessage());
        }
    }

    public void leerFactura(String rutaArchivo) {
        try {
            FileReader lector = new FileReader(rutaArchivo);
            BufferedReader lectura = new BufferedReader(lector);
            String linea = lectura.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = lectura.readLine();
            }
            lectura.close();
        } catch (IOException e) {
            System.out.println("Error al leer la factura: " + e.getMessage());
        }
    }

    public List<String> listarFacturas() {
        List<String> nombres = new ArrayList<>();
        File carpeta = new File(CARPETA);
        if (carpeta.exists()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File f : archivos) {
                    nombres.add(f.getName());
                }
            }
        }
        return nombres;
    }

    public int obtenerSiguienteNumero() {
        List<String> archivos = listarFacturas();
        int mayor = 0;
        for (String nombre : archivos) {
            if (nombre.startsWith("factura_") && nombre.endsWith(".txt")) {
                String numero = nombre.substring(8, nombre.length() - 4);
                try {
                    int n = Integer.parseInt(numero);
                    if (n > mayor) {
                        mayor = n;
                    }
                } catch (NumberFormatException e) {
                    // nombre de archivo distinto al esperado, se ignora
                }
            }
        }
        return mayor + 1;
    }
}
