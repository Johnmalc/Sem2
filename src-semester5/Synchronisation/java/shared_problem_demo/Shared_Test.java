/* Datei Shared_Test.java                         E. Ammann   */
/* Demo-Anwendung zum Synchronisationsproblem                 */
/* Paramenter:  - Zahl von Threads                            */

class Shared_Test {
    private final static int ANFANGSWERT = 1000;

    public static void main(String args[]) {
        int i;
        Shared shared;

        if (args.length != 1) {
            System.out.println("Syntaktisch falscher Aufruf des Programms");
            System.exit(1);
        }

        shared = new Shared(ANFANGSWERT);

        for (i = 0; i < (Integer.parseInt(args[0]) / 2); i++) {
            (new Actor(shared, 1)).start();
            (new Actor(shared, -1)).start();
        }

        try {
            Thread.sleep((int) 20000);
        } catch (InterruptedException e) {
        }
        System.out.println("Endwert: " + shared.get_counter());
    }
}
