public class HelloWorldToRun implements Runnable {
    public static void main(String[] args) {
        System.out.println("Anwendungs-Thread vor Threaderzeugung");

        HelloWorldToRun runner = new HelloWorldToRun();
        Thread t = new Thread(runner);
        t.start();

        System.out.println("Anwendungs-Thread nach Threaderzeugung");
    }

    public void run() {
        System.out.println("Neuer Thread: Hello World");
    }
}