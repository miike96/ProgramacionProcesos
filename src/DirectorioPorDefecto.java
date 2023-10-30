import java.util.Map;

public class Ejercicio2 {

    public static void main(String[] args) {

    }

        /*
        Método que muestre cuál es el directorio por defecto de ejecución de un
        proceso, con el método directory () de ProcessBuilder, y que ejecute el mismo comando
        (por ejemplo, ls) en distintos directorios, asignados con el método directory (File
        directorio) de ProcessBuilder en debian.
         */

    public static void directorioPorDefecto() {
        ProcessBuilder pb = new ProcessBuilder("ls");
        try {
            Process p = pb.start();
            Map<String, String> env = pb.environment();
            System.out.println("El directorio por defecto es: " + env.get("HOME"));
        } catch (Exception e) {
            System.out.println("Error al ejecutar el proceso");
        }
    }

}
