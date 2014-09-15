/* Datei Actor.java                  E.Ammann    */
/* Klasse fuer die Actor-Threads                 */

class Actor extends Thread {
    private Shared shared;
    private int delta;

    public Actor(Shared shared, int delta) {
        this.shared = shared;
        this.delta  = delta;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
           shared.change_counter(delta);
           yield();
        }
    }
}
