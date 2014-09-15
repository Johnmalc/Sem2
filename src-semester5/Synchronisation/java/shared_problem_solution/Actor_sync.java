/* Datei Actor_sync.java             E.Ammann    */
/* Klasse fuer die Actor-Threads                 */

class Actor_sync extends Thread {
    private Shared_sync shared;
    private int delta;

    public Actor_sync(Shared_sync shared, int delta) {
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
