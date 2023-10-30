package sincronizacion;
import java.util.Random;
class NumeroOculto {
    private int numeroOculto;
    boolean juegoTerminado = false;

    public NumeroOculto() {
        Random rand = new Random();
        numeroOculto = rand.nextInt(101); // Generar número al azar entre 0 y 100
    }

    public synchronized int propuestaNumero(int num) {
        if (juegoTerminado) {
            return -1; // El juego ha terminado
        }

        if (num == numeroOculto) {
            juegoTerminado = true;
            return 1; // Número adivinado
        }

        return 0; // Número incorrecto
    }
}

class AdivinadorDeNumero extends Thread {
    private NumeroOculto numeroOculto;

    public AdivinadorDeNumero(NumeroOculto numeroOculto) {
        this.numeroOculto = numeroOculto;
    }

    @Override
    public void run() {
        while (true) {
            int num = new Random().nextInt(101); // Generar número aleatorio para adivinar
            int resultado = numeroOculto.propuestaNumero(num);

            if (resultado == 1) {
                System.out.println("¡Hilo " + Thread.currentThread().getId() + " adivinó el número!");
                break; // El hilo adivinó el número, termina su ejecución
            } else if (resultado == -1) {
                System.out.println("Juego terminado. Hilo " + Thread.currentThread().getId() + " se detiene.");
                break; // El juego ha terminado, termina su ejecución
            }
        }
    }
}

public class JuegoAdivinaNumero {
    public static void main(String[] args) {
        NumeroOculto numeroOculto = new NumeroOculto();
        Thread generador = new Thread(() -> {
            while (!numeroOculto.juegoTerminado) {
                // El hilo generador puede realizar acciones adicionales si es necesario
            }
        });

        generador.start(); // Iniciar el hilo generador

        Thread[] adivinadores = new Thread[10];
        for (int i = 0; i < 10; i++) {
            adivinadores[i] = new AdivinadorDeNumero(numeroOculto);
            adivinadores[i].start(); // Iniciar los hilos adivinadores
        }
    }
}

