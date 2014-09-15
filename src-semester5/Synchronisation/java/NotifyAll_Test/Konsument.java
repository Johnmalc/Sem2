/* Datei Konsument.java                    E.Ammann    */
/* Klasse fuer die Konsumenten-Threads                 */

class Konsument extends Thread {
    private FIFOPuffer fifo;
    private int zaehler;

    public Konsument(int zaehler, FIFOPuffer fifo) {
        this.zaehler = zaehler;
        this.fifo = fifo;
    }

    public void run() {
        int temp;

        temp = fifo.auslesen();
        System.out.println("Konsument " + zaehler + " hat ausgelesen: " + temp);
    }

}
