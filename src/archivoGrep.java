import java.io.*;

public class archivoGrep {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Uso: java GrepFile <texto> <archivo_entrada> <archivo_salida>");
            System.exit(1);
        }

        String texto = args[0];
        String archivoEntrada = args[1];
        String archivoSalida = args[2];

        try {
            // Construir el comando grep
            ProcessBuilder builder = new ProcessBuilder("grep", texto, archivoEntrada);

            // Redirigir la salida estándar al archivo de salida
            File outputFile = new File(archivoSalida);
            builder.redirectOutput(outputFile);

            // Iniciar el proceso
            Process proceso = builder.start();
            proceso.waitFor();

            if (proceso.exitValue() == 0) {
                System.out.println("Se ha completado la búsqueda y los resultados se han guardado en " + archivoSalida);
            } else {
                System.err.println("Error: El comando grep ha fallado.");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
