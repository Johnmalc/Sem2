/* Datei Shared_Test_sync.java                    E. Ammann   */
/* Demo-Anwendung zum Synchronisationsproblem                 */
/* Paramenter:  - Zahl von Threads                            */


class Shared_Test_sync {
    private final static int ANFANGSWERT = 1000;

    public static void main(String args[]) {
        int i;
        Shared_sync shared;

        if (args.length != 1) {
            System.out.println("Syntaktisch falscher Aufruf des Programms");
            System.exit(1);
        }

        shared = new Shared_sync(ANFANGSWERT);

        for (i = 0; i < (Integer.parseInt(args[0]) / 2); i++) {
            (new Actor_sync(shared, 1)).start();
            (new Actor_sync(shared, -1)).start();
        }

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
        }
        System.out.println("Endwert: " + shared.get_counter());
    }
}
