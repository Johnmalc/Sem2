public class Philosopher_SemaphoreGroup extends Thread {
    private SemaphoreGroup sems;
    private int leftFork;
    private int rightFork;

    public Philosopher_SemaphoreGroup(SemaphoreGroup sems, int number) {
        this.sems = sems;
        leftFork = number;
        if (number + 1 < sems.getNumberOfMembers())
            rightFork = number + 1;
        else
            rightFork = 0;
        start();
    }

    public void run() {
        int[] deltas = new int[sems.getNumberOfMembers()];
        for (int i = 0; i < deltas.length; i++)
            deltas[i] = 0;
        int number = leftFork;
        while (true) {
            think(number);
            deltas[leftFork] = -1;
            deltas[rightFork] = -1;
            sems.changeValues(deltas);
            eat(number);
            deltas[leftFork] = 1;
            deltas[rightFork] = 1;
            sems.changeValues(deltas);
        }
    }

    private void think(int number) {
        System.out.println("Philosopher " + number + " is thinking");
        try {
            sleep((int) (Math.random() * 5000));
        } catch (InterruptedException e) {
        }
    }

    private void eat(int number) {
        System.out.println("Philosopher " + number + " starts eating");
        try {
            sleep((int) (Math.random() * 5000));
        } catch (InterruptedException e) {
        }
        System.out.println("Philosopher " + number + " stops eating");
    }

    private static final int NUMBER = 5;

    public static void main(String[] args) {
        SemaphoreGroup forks = new SemaphoreGroup(NUMBER);
        int[] init = new int[NUMBER];
        for (int i = 0; i < NUMBER; i++) {
            init[i] = 1;
        }
        forks.changeValues(init);
        for (int i = 0; i < NUMBER; i++) {
            new Philosopher_SemaphoreGroup(forks, i);
        }
    }
}