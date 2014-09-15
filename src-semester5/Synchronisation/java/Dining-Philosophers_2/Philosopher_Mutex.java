

public class Philosopher_Mutex implements Runnable
{
    private Semaphore mutex;
    private int number;

    public Philosopher_Mutex(Semaphore mutex, int platz)
    {
        this.mutex = mutex;
        this.number = platz;
    }

    public void run()
    {
        while(true)
        {
            think(number);
            mutex.p();
            eat(number);
            mutex.v();
        }
    }

    private void think(int number)
    {
        System.out.println("Philosopher " + number + " is thinking");
        try
        {
            Thread.sleep((int) (Math.random() * 5000));
        }
        catch(InterruptedException e) {}
    }

    private void eat(int number)
    {
        System.out.println("Philosopher " + number + " starts eating");
        try
        {
            Thread.sleep((int) (Math.random() * 5000));
        }
        catch(InterruptedException e)
        {
        }
        System.out.println("Philosopher " + number + " stops eating");
    }
    private static final int NUMBER = 5;

    public static void main(String[] args)
    {
        Semaphore mutex = new Semaphore(1);
        for(int i = 0; i < NUMBER; i++)
            new Thread(new Philosopher_Mutex(mutex, i)).start();
    }
}