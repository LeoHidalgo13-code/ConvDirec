package conversiondirecciones;
import java.util.HashMap;
import java.util.Scanner;
public class ConversionDirecciones {
  private static HashMap<Integer, Integer> tablaPaginas;
    public ConversionDirecciones() {
        tablaPaginas = new HashMap<>();
        generarMapeos(); // Llamamos al método para generar los mapeos automáticamente
    }
    // Método para generar mapeos automáticamente
    private void generarMapeos() {
        int paginaFisicaInicial = 10;
        for (int i = 0; i < 1000; i++) {
            tablaPaginas.put(i, paginaFisicaInicial + i * 10);
        } 
    }  
    public static int traducirDireccion(int direccionVirtual, boolean incluirOffset) {
        int pagina = direccionVirtual / 100; // Tamaño de la página
        int offset = direccionVirtual % 100;
        if (tablaPaginas.containsKey(pagina)) {
            int paginaFisica = tablaPaginas.get(pagina);
            if (incluirOffset) {
                return paginaFisica * 100 + offset;
            } else {
                return paginaFisica;
            }
        } else {
            // Manejo de error si la página virtual no está mapeada
            return -1;
        }
    }
    public static void main(String[] args) {
        ConversionDirecciones conversion = new ConversionDirecciones();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("Selecciona una opcion:");
            System.out.println("1. Calcular direccion fisica con offset");
            System.out.println("2. Calcular pagina física solamente");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    calcularDireccionConOffset(scanner);
                    break;
                case 2:
                    calcularPaginaFisica(scanner);
                    break;
                case 3:
                    
                    break;
                default:
                    System.out.println("Opcion invalida. Inténtalo de nuevo.");
            }
        } while (opcion != 3);
        
        scanner.close();
    }
    public static void calcularDireccionConOffset(Scanner scanner) {
        System.out.println("Ingresa las coordenadas (x, y): ");
        System.out.print("x: ");
        int x = scanner.nextInt();
        System.out.print("y: ");
        int y = scanner.nextInt();
        int direccionVirtual = x * 100 + y;
        int direccionFisica = traducirDireccion(direccionVirtual, true);
        if (direccionFisica != -1) {
            System.out.println("La dirección fisica correspondiente a la direccion virtual (" + x + ", " + y + ") es " + direccionFisica);
        } else {
            System.out.println("Error: La página virtual correspondiente no está mapeada");
        }
    }
    public static void calcularPaginaFisica(Scanner scanner) {
        System.out.println("Ingresa el valor de x: ");
        int x = scanner.nextInt();
        int paginaVirtual = x;
        int paginaFisica = traducirDireccion(paginaVirtual * 100, false);
        if (paginaFisica != -1) {
            System.out.println("La pagina física correspondiente a la pagina virtual " + x + " es " + paginaFisica);
        } else {
            System.out.println("Error: La pagina virtual correspondiente no está mapeada");
        }
    }
}
    
