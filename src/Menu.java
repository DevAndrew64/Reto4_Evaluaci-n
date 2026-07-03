import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Producto> productos;

    public Menu() {
        productos = new ArrayList<>();
        productos.add(new Producto("H1", "Hamburguesa Kairo's Clasica", 12000));
        productos.add(new Producto("H2", "Hamburguesa Kairo's Doble Carne", 16000));
        productos.add(new Producto("P1", "Perro Caliente Especial", 9000));
        productos.add(new Producto("F1", "Papas Fritas Grandes", 6000));
        productos.add(new Producto("F2", "Aros de Cebolla", 7000));
        productos.add(new Producto("B1", "Gaseosa 400ml", 4000));
        productos.add(new Producto("B2", "Limonada Natural", 5000));
        productos.add(new Producto("C1", "Combo Kairo's (Hamburguesa+Papas+Gaseosa)", 20000));
    }

    public void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("       MENU COMIDAS RAPIDAS KAIRO'S");
        System.out.println("========================================");
        for (Producto p : productos) {
            System.out.printf("%-4s %-42s $%,.0f%n", p.getCodigo(), p.getNombre(), p.getPrecio());
        }
        System.out.println("========================================");
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }
}
