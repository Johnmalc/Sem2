/* Datei Produzent.java                    E.Ammann  */
/* Klasse fuer die Produzenten-Threads               */

class Produzent extends Thread {
    private FIFOPuffer fifo;
    private int zaehler;
    private int num_kons; 

    public Produzent(int zaehler, FIFOPuffer fifo) {
        this.zaehler = zaehler;
        this.fifo = fifo;
    }

    public void run() {

            fifo.einfuegen(100 * zaehler);
            System.out.println("Produzent " + zaehler + " hat eingefuegt: "
                                                            + (100*zaehler));
    }
}
