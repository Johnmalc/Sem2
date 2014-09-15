
public class HelloWorldThread extends Thread
{
    public void run()
    {
        System.out.println("Neuer Thread: Hello World");
    }

    public static void main(String[] args)
    {
        System.out.println("\nAnwendungs-Thread vor Threaderzeugung");

        HelloWorldThread t = new HelloWorldThread();
        t.start();

        System.out.println("Anwendungs-Thread nach Threaderzeugung");
    }
}