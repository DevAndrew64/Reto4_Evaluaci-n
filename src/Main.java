import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Menu menu = new Menu();
    static GestorFacturas gestor = new GestorFacturas();
    static int numeroFactura;

    public static void main(String[] args) {
        mostrarBienvenida();
        numeroFactura = gestor.obtenerSiguienteNumero();
        int opcion;
        do {
            System.out.println("\n1. Generar factura");
            System.out.println("2. Ver facturas guardadas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    generarFactura();
                    break;
                case 2:
                    verFacturas();
                    break;
                case 3:
                    System.out.println("Gracias por usar el sistema de Kairo's");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 3);
    }

    static void mostrarBienvenida() {
        System.out.println("****************************************");
        System.out.println("*   SISTEMA DE FACTURACION KAIRO'S     *");
        System.out.println("*   Desarrollado por: Jhon Ponton      *");
        System.out.println("*   SENA - ADSO23                      *");
        System.out.println("****************************************");
    }

    static void generarFactura() {
        menu.mostrarMenu();
        List<ItemFactura> items = new ArrayList<>();

        System.out.print("Nombre del cliente: ");
        String cliente = sc.nextLine();

        String continuar;
        do {
            System.out.print("Codigo del producto: ");
            String codigo = sc.nextLine();
            Producto p = menu.buscarPorCodigo(codigo);

            if (p == null) {
                System.out.println("Producto no encontrado");
            } else {
                System.out.print("Cantidad: ");
                int cantidad = leerEntero();
                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser mayor a 0");
                } else {
                    items.add(new ItemFactura(p, cantidad));
                }
            }

            System.out.print("Agregar otro producto? (s/n): ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        if (items.isEmpty()) {
            System.out.println("No se agregaron productos, factura cancelada");
            return;
        }

        String archivo = gestor.generarFactura(items, cliente, numeroFactura);
        if (archivo != null) {
            System.out.println("\nFactura generada con exito\n");
            gestor.leerFactura(archivo);
            numeroFactura++;
        }
    }

    static void verFacturas() {
        List<String> facturas = gestor.listarFacturas();
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas guardadas");
            return;
        }

        System.out.println("\nFacturas disponibles:");
        for (int i = 0; i < facturas.size(); i++) {
            System.out.println((i + 1) + ". " + facturas.get(i));
        }

        System.out.print("Seleccione el numero de factura a ver: ");
        int seleccion = leerEntero();

        if (seleccion >= 1 && seleccion <= facturas.size()) {
            System.out.println();
            gestor.leerFactura("facturas" + File.separator + facturas.get(seleccion - 1));
        } else {
            System.out.println("Seleccion invalida");
        }
    }

    static int leerEntero() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
