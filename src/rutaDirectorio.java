import java.io.*;

public class rutaDirectorio {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java ListDirectoryContents <ruta_del_directorio>");
            System.exit(1);
        }

        String directorioPath = args[0];
        File directorio = new File(directorioPath);

        if (!directorio.exists()) {
            System.err.println("El directorio no existe.");
            System.exit(1);
        }

        if (!directorio.isDirectory()) {
            System.err.println("La ruta no corresponde a un directorio.");
            System.exit(1);
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ls", "-lF", directorioPath);
            Process proceso = processBuilder.start();

            // Obtener el stream de salida del proceso
            InputStream inputStream = proceso.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String linea;
            int numeroLinea = 1;

            while ((linea = reader.readLine()) != null) {
                System.out.println(numeroLinea + ": " + linea);
                numeroLinea++;
            }

            // Esperar a que el proceso termine
            int exitCode = proceso.waitFor();
            if (exitCode == 0) {
                System.out.println("Listado de directorio completado.");
            } else {
                System.err.println("Error al listar el directorio.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
