import java.rmi.Naming;

public class Server {
    public static void main(String[] args) {
        try {
            SleepImpl server;
            server = new SleepImpl();
            Naming.rebind("SleepServer", server);
        } catch (Exception e) {
        }
    }
}