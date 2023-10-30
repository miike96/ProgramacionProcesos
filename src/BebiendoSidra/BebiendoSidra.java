package BebiendoSidra;

class BebiendoSidra {
    public static void main(String[] args) throws InterruptedException {
        MonitorSidra objetoMonitor = new MonitorSidra();
        Camarero hilocam = new Camarero( );
        Cliente hilocli = new Cliente( );
        hilocli.start();
        hilocam.start();
    }
}
class Camarero extends Thread{
    public void run() {
        System.out.println("Sidra escanciada...");
    }
}
class Cliente extends Thread{
    public void run() {
        System.out.println("Que rica !");
    }
}
