package BenendoSidraCompartida;

class MonitorSidra {
    public void beboSidra() {
        System.out.println("Que rica !");
    }
    public void camareroEscancia(){
        System.out.println("Sidra escanciada...");
    }
}
class BebiendoSidra {
    public static void main(String[] args) throws InterruptedException {
        MonitorSidra objetoMonitor = new MonitorSidra();
        Camarero hilocam = new Camarero( objetoMonitor);
        Cliente hilocli = new Cliente( objetoMonitor);
        hilocli.start();
        hilocam.start();
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