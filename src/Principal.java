import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultarMoneda consulta = new ConsultarMoneda();
        GeneradorDeArchivo generador = new GeneradorDeArchivo(); // Instanciar GeneradorDeArchivo

        int opcion = 0;
        while (opcion != 8) {
            System.out.println("♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦\n" +
                    "Bienvenidos Al conversor de monedas\n\n" +
                    "Ingrese la conversión que deseas realizar\n\n" +
                    "1. Dollar a Peso Argentino\n" +
                    "2. Peso Argentino A Dollar\n" +
                    "3. Dollar a Real Brasilero\n" +
                    "4. Real Brasilero a Dollar\n" +
                    "5. Dollar a Peso Colombiano\n" +
                    "6. Peso Colombiano a Dollar\n" +
                    "7. Convertir otra moneda\n" +
                    "8. Salir");

            opcion = lectura.nextInt();
            lectura.nextLine();
            String monedaBase = "";
            String monedaTarget = "";

            try {
                switch (opcion) {
                    case 1:
                        monedaBase = "USD";
                        monedaTarget = "ARS";
                        break;
                    case 2:
                        monedaBase = "ARS";
                        monedaTarget = "USD";
                        break;
                    case 3:
                        monedaBase = "USD";
                        monedaTarget = "BRL";
                        break;
                    case 4:
                        monedaBase = "BRL";
                        monedaTarget = "USD";
                        break;
                    case 5:
                        monedaBase = "USD";
                        monedaTarget = "COP";
                        break;
                    case 6:
                        monedaBase = "COP";
                        monedaTarget = "USD";
                        break;
                    case 7:
                        System.out.println("Ingrese el código de la moneda base: ");
                        monedaBase = lectura.nextLine().toUpperCase();
                        System.out.println("Ingrese la moneda a convertir: ");
                        monedaTarget = lectura.nextLine().toUpperCase();
                        break;
                    case 8:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción no válida");
                        continue;
                }

                ConvertirMoneda.convertir(monedaBase, monedaTarget, consulta, lectura);

                // Generar y guardar el archivo JSON
                Monedas monedas = consulta.buscarMoneda(monedaBase, monedaTarget);
                generador.guardarJson(monedas);
                System.out.println("Archivo JSON generado: " + monedas.base_code() + "_to_" + monedas.target_code() + ".json");

            } catch (RuntimeException | IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

