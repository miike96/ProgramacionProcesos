package BebiendoSidraOrdenada;

class MonitorSidra {
    private boolean sidraEscanciada = false;
    private int edad;
    public synchronized void beboSidra() {

        while (!sidraEscanciada) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sidra bebida...");
    }
    public synchronized void camareroEscancia() {
        this.sidraEscanciada = true;
        notifyAll();
        System.out.println("Sidra escanciada...");
    }
}
class BebiendoSidra {
    public static void main(String[] args) throws InterruptedException {
        MonitorSidra objetoMonitor = new MonitorSidra();
        Camarero hilocam = new Camarero( objetoMonitor);
        Alumno hiloalum = new Alumno( objetoMonitor, 19);
        Alumno hiloalum2 = new Alumno( objetoMonitor, 17);
        Alumno hiloalum3 = new Alumno( objetoMonitor, 20);
        Alumno hiloalum4 = new Alumno( objetoMonitor, 16);



        if (hiloalum.edad >= 18){
            hiloalum.start();
            hiloalum2.start();
            hiloalum3.start();
            hiloalum4.start();
            hilocam.start();
        } else {
            System.out.println("No puede beber sidra");
        }


    }
}
class Camarero extends Thread{
    MonitorSidra objetoMonitor;
    public Camarero(MonitorSidra objetoMonitor) {
        this.objetoMonitor = objetoMonitor;
    }
    public void run() {
        objetoMonitor.camareroEscancia();
    }
}
class Cliente extends Thread{
    MonitorSidra objetoMonitor;
    public Cliente(MonitorSidra objetoMonitor) {
        this.objetoMonitor = objetoMonitor;
    }
    public void run() {
        objetoMonitor.beboSidra();
    }
}

class Alumno extends Thread{
    MonitorSidra objetoMonitor;
    int edad;
    public Alumno(MonitorSidra objetoMonitor, int edad) {
        this.objetoMonitor = objetoMonitor;
        this.edad = edad;
    }
    public void run(){
        if (edad < 18){
            System.out.println("No puede beber sidra");
        } else {
            System.out.println("Bebiendo sidra...");
        }
    }
}


