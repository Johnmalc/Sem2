/* Datei Shared_sync.java                 E. Ammann    */
/* Klasse fuer eine Counter-Variable (, auf die        */
/*  mehrere Threads zugreifen)                         */
 
class Shared_sync {
    private int counter;

    // Konstruktormethode dieser Klasse
    Shared_sync(int anfangswert) {
       counter = anfangswert;
    }

    // Counter - Wert veraendern
    synchronized public void change_counter(int delta) {
        counter = counter + delta;
    }

    // Counter - Wert abfragen
    synchronized public int get_counter() {
        return counter;
    }
}
